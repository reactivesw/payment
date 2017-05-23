package io.reactivesw.payment.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Braintree config model.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BraintreeConfigModel {
  /**
   * The environment.
   */
  private String environment;

  /**
   * The merchant id.
   */
  private String merchantId;

  /**
   * The public key.
   */
  private String publicKey;

  /**
   * The private key.
   */
  private String privateKey;
}
