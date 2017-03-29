package io.reactivesw.payment.application.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Calendar;

/**
 * Created by Davis on 17/2/3.
 */
@Getter
@Setter
@ToString
public class CreditCardView {
  /**
   * The Id.
   */
  private String id;

  /**
   * The Bin.
   */
  private String bin;

  /**
   * The Cardholder name.
   */
  private String cardholderName;

  /**
   * The Card type.
   */
  private String cardType;

  /**
   * The Customer id.
   */
  private String customerId;

  /**
   * The Expiration month.
   */
  private String expirationMonth;

  /**
   * The Expiration year.
   */
  private String expirationYear;

  /**
   * The Expired.
   */
  private boolean expired;

  /**
   * The Last 4.
   */
  private String last4;

  /**
   * The Commercial.
   */
  private String commercial;

  /**
   * The Token.
   */
  private String token;

  /**
   * The Updated at.
   */
  private Calendar updatedAt;

  /**
   * The Cvv.
   */
  private String cvv;
}
