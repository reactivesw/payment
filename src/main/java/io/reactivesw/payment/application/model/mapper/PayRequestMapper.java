package io.reactivesw.payment.application.model.mapper;

import io.reactivesw.payment.application.model.OrderCreationEvent;
import io.reactivesw.payment.application.model.PayRequest;

/**
 * The type Pay request mapper.
 */
public final class PayRequestMapper {

  /**
   * Instantiates a new Pay request mapper.
   */
  private PayRequestMapper() {
  }


  /**
   * Build pay request.
   *
   * @param event the event
   * @return the pay request
   */
  public static PayRequest build(OrderCreationEvent event) {
    PayRequest request = new PayRequest();

    request.setAmount(event.getTotalAmount());
    request.setCreditCardId(event.getPaymentMethodId());

    return request;
  }
}
