package io.reactivesw.payment.application.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
  @NotNull
  @Size(min = 12)
  private String number;

  /**
   * The Cardholder name.
   */
  @NotNull
  @Size(min = 3)
  private String cardholderName;

  /**
   * The Card type.
   */
  private String cardType;

  /**
   * The Expiration month.
   */
  @NotNull
  @Size(min = 2)
  private String expirationMonth;

  /**
   * The Expiration year.
   */
  @NotNull
  @Size(min = 4)
  private String expirationYear;

  /**
   * The Commercial.
   */
  private String commercial;

  /**
   * The Cvv.
   */
  @NotNull
  @Size(min = 3)
  private String cvv;

  /**
   * the customer id in our system.
   */
  @NotNull
  @Size(min = 12)
  private String customerId;
}
