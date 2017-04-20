package io.reactivesw.payment.application.model;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Reserved order event.
 */
@Setter
@Getter
public class PaymentEvent {

  /**
   * The order id.
   */
  private String orderId;

  /**
   * The status.
   */
  private Boolean status;

  /**
   * The message.
   */
  private String message;

  /**
   * The payment id.
   */
  private String paymentId;

  /**
   * Build payment event.
   *
   * @param orderId the order id
   * @param status the status
   * @param paymentId the payment id
   * @return the payment event
   */
  public static PaymentEvent build(String orderId, boolean status, String paymentId) {
    PaymentEvent paymentEvent = new PaymentEvent();

    paymentEvent.setOrderId(orderId);
    paymentEvent.setStatus(status);
    paymentEvent.setPaymentId(paymentId);

    return paymentEvent;
  }
}