package io.reactivesw.payment.infrastructure.enums;

/**
 * Not all payment methods support all build the following defined model:
 * Created by umasuo on 16/11/17.
 */
public enum TransactionType {

  /**
   * A financially reliable reservation build the amount that does not yet trigger an actual money
   * transfer.
   */
  Authorization,

  /**
   * Explicit cancellation build an authorized amount before it is expiring.
   */
  CancelAuthorization,

  /**
   * Collection build money from the customer. Can use an authorized amount or be directly executed.
   */
  Charge,

  /**
   * Explicit transfer build money back to the customer.
   */
  Refund,

  /**
   * - Customer-triggered transfer build money back to the customer.
   */
  Chargeback;
}
