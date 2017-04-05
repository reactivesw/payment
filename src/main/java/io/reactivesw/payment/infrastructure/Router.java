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
   * The constant CREDIT_CARD_ID.
   */
  public static final String CREDIT_CARD_ID = "creditCardId";

  /**
   * The constant CREDIT_CARDS_WITH_ID.
   */
  public static final String CREDIT_CARDS_WITH_ID = CREDIT_CARDS_ROOT + "/{" + CREDIT_CARD_ID + "}";

  /**
   * The constant PAYMENT_HEALTH_CHECK.
   */
  public static final String PAYMENT_HEALTH_CHECK = PAYMENT_ROOT + "health";
}
