package io.reactivesw.payment.application.model;

import io.reactivesw.payment.domain.model.PayedOrder;

/**
 * The type Payed order mapper.
 */
public final class PayedOrderMapper {

  /**
   * Instantiates a new Payed order mapper.
   */
  private PayedOrderMapper() {
  }

  /**
   * Build payed order.
   *
   * @param orderId the order id
   * @param paymentView the payment view
   * @return the payed order
   */
  public static PayedOrder build(String orderId, PaymentView paymentView) {
    PayedOrder result = new PayedOrder();

    result.setOrderId(orderId);
    result.setPaymentId(paymentView.getId());

    return result;
  }
}
