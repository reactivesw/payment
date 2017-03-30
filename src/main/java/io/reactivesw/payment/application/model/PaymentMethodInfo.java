package io.reactivesw.payment.application.model;

import io.reactivesw.model.LocalizedString;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by umasuo on 16/11/17.
 */
@Getter
@Setter
public class PaymentMethodInfo {

  /**
   * The Payment interface.
   */
  private String paymentInterface;

  /**
   * The Method.
   */
  private String method;

  /**
   * The Name.
   */
  private LocalizedString name;
}
