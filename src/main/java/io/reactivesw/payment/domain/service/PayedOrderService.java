package io.reactivesw.payment.domain.service;

import io.reactivesw.payment.application.model.PayedOrderMapper;
import io.reactivesw.payment.application.model.PaymentView;
import io.reactivesw.payment.domain.model.PayedOrder;
import io.reactivesw.payment.infrastructure.repository.PayedOrderRepository;
import io.reactivesw.payment.infrastructure.repository.PayedOrderSpecification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Payed order service.
 */
@Service
public class PayedOrderService {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(PayedOrderService.class);

  /**
   * The Repository.
   */
  private transient PayedOrderRepository repository;

  /**
   * Instantiates a new Payed order service.
   *
   * @param repository the repository
   */
  @Autowired
  public PayedOrderService(PayedOrderRepository repository) {
    this.repository = repository;
  }

  /**
   * Save payed order.
   *
   * @param orderId the order id
   * @param paymentView the payment view
   */
  public void savePayedOrder(String orderId, PaymentView paymentView) {
    LOG.debug("Enter. OrderId: {}, paymentId: {}.", orderId, paymentView.getId());

    PayedOrder payedOrder = PayedOrderMapper.build(orderId, paymentView);

    PayedOrder savedEntity = repository.save(payedOrder);

    LOG.debug("Exit. PayedOrderId: {}.", savedEntity.getId());
  }

  /**
   * Is reserved order.
   *
   * @param orderId the order id
   * @return the boolean
   */
  public boolean isPayedOrder(String orderId) {
    LOG.debug("Enter. OrderId: {}.", orderId);

    boolean result = true;

    PayedOrder order = repository.findOne(PayedOrderSpecification.exist(orderId));

    if (order != null) {
      result = false;
    }

    LOG.debug("Exit. Result: {}.", result);
    return result;
  }
}
