package io.reactivesw.payment.infrastructure;

/**
 * Created by Davis on 17/1/5.
 */
public final class Router {
  /**
   * The constant payment_root.
   */
  public static final String PAYMENT_ROOT = "/";

  /**
   * customer id.
   */
  public static final String CUSTOMER_ID = "customerId";

  /**
   * credit card.
   */
  public static final String CREDIT_CARDS = "credit-cards";

  /**
   * The constant PAYMENT_WITH_CREDIT_CARDS.
   */
  public static final String CREDIT_CARDS_ROOT = PAYMENT_ROOT + CREDIT_CARDS;

  /**
   * payment with credit cards and customer id, /payments/credit-cards/{subjectId}.
   */
  public static final String CREDIT_CART_WITH_CUSTOMER_ID =
      CREDIT_CARDS_ROOT + "/{" + CUSTOMER_ID + "}";

  /**
   * The constant PAYMENT_HEALTH_CHECK.
   */
  public static final String PAYMENT_HEALTH_CHECK = PAYMENT_ROOT + "health";
}
