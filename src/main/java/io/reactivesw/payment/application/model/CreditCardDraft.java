package io.reactivesw.payment.application.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Davis on 17/3/15.
 */
@Getter
@Setter
@ToString
public class CreditCardDraft {
  /**
   * The Number.
   */
  private String number;

  /**
   * The Cardholder name.
   */
  private String cardholderName;

  /**
   * The Card type.
   */
  private String cardType;

  /**
   * The Expiration month.
   */
  private String expirationMonth;

  /**
   * The Expiration year.
   */
  private String expirationYear;

  /**
   * The Commercial.
   */
  private String commercial;

  /**
   * The Cvv.
   */
  private String cvv;
}
