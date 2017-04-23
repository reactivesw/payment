package io.reactivesw.payment.application.service;

import io.reactivesw.exception.NotExistException;
import io.reactivesw.payment.application.model.OrderCreationEvent;
import io.reactivesw.payment.application.model.PayRequest;
import io.reactivesw.payment.application.model.PaymentView;
import io.reactivesw.payment.application.model.mapper.PayRequestMapper;
import io.reactivesw.payment.domain.service.EventMessageService;
import io.reactivesw.payment.domain.service.PayedOrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Order creation event handler.
 */
@Service
public class OrderCreationEventHandler {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(OrderCreationEventHandler.class);


  /**
   * The Event message service.
   */
  private transient EventMessageService eventMessageService;

  /**
   * The Payed order service.
   */
  private transient PayedOrderService payedOrderService;

  /**
   * The Payment application.
   */
  private transient PaymentApplication paymentApplication;

  /**
   * Instantiates a new Order creation event handler.
   *
   * @param eventMessageService the event message service
   * @param payedOrderService the payed order service
   * @param paymentApplication the payment application
   */
  public OrderCreationEventHandler(EventMessageService eventMessageService,
      PayedOrderService payedOrderService, PaymentApplication paymentApplication) {
    this.eventMessageService = eventMessageService;
    this.payedOrderService = payedOrderService;
    this.paymentApplication = paymentApplication;
  }

  /**
   * Handle order creation.
   *
   * @param event the event
   */
  @Transactional
  public void handleOrderCreation(OrderCreationEvent event) {
    LOG.debug("Enter. OrderId: {}.", event.getOrderId());

    if (payedOrderService.isPayedOrder(event.getOrderId())) {
      // If orderId is exist, can not pay again.
      LOG.debug("Order: {} is exist.", event.getOrderId());
    } else {
      //1. get paymentMethodId and totalAmount
      PayRequest request = PayRequestMapper.build(event);

      Boolean reservedStatus = false;
      String paymentId = "";
      try {
        PaymentView paymentView = paymentApplication.checkout(request);
        //2. save orderId
        payedOrderService.savePayedOrder(event.getOrderId(), paymentView);
        reservedStatus = true;
        paymentId = paymentView.getId();
      } catch (NumberFormatException | NotExistException exception) {
        // Something wrong and can not pay again.
        LOG.debug("Pay for order: {} fail.", event.getOrderId(), exception);
      }
      //3. save payment event
      eventMessageService.savePaymentEvent(event.getOrderId(), reservedStatus, paymentId);
    }
    LOG.debug("Exit.");
  }
}
