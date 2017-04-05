package io.reactivesw.payment.application.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
   * The version.
   */
  private Integer version;

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
   * The Expired.
   */
  private boolean expired;

  /**
   * The Last 4.
   */
  private String last4;

  /**
   * if the credit card is default credit card.
   */
  private Boolean selected;
}
