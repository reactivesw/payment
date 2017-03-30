package io.reactivesw.payment.application.model;

import io.reactivesw.model.Money;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Davis on 17/3/30.
 */
@Getter
@Setter
@ToString
public class PayRequest {
  /**
   * The Customer id.
   */
  @NotNull
  @Size(min = 12)
  private String customerId;

  /**
   * The Amount.
   */
  @NotNull
  private Money amount;

  /**
   * The Credit card id.
   */
  @NotNull
  @Size(min = 12)
  private String creditCardId;
}
