package io.reactivesw.payment.application.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Calendar;

/**
 * Created by Davis on 17/3/15.
 */
@Getter
@Setter
@ToString
public class CreditCardDraft {
  private String number;
  private String cardholderName;
  private String cardType;
  private String expirationMonth;
  private String expirationYear;
  private String commercial;
  private String cvv;
}
