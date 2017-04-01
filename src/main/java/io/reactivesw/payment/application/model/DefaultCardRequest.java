package io.reactivesw.payment.application.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Davis on 17/4/1.
 */
@Setter
@Getter
@ToString
public class DefaultCardRequest {
  /**
   * The Customer id.
   */
  private String customerId;

  /**
   * The Credit card id.
   */
  private String creditCardId;
}
