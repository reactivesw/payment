package io.reactivesw.payment.application.model;

import io.reactivesw.model.Reference;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by umasuo on 16/11/17.
 */
@Getter
@Setter
public class PaymentStatus {

  private String interfaceCode;

  private String interfaceText;

  private Reference state;
}
