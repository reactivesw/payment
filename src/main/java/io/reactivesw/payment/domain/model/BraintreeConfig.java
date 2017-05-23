package io.reactivesw.payment.domain.model;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Braintree config entity.
 */
@Setter
@Getter
@Entity
@Table(name = "braintree_config")
public class BraintreeConfig {

  /**
   * The id.
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  private String id;

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
