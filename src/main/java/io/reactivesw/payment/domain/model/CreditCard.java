package io.reactivesw.payment.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Created by Davis on 17/3/15.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "credit_card")
@EntityListeners(AuditingEntityListener.class)
public class CreditCard {
  /**
   * id.
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  private String id;

  /**
   * The Created at.
   */
  @CreatedDate
  @Column(name = "created_at")
  protected ZonedDateTime createdAt;

  /**
   * The Last modified at.
   */
  @LastModifiedDate
  @Column(name = "last_modified_at")
  protected ZonedDateTime lastModifiedAt;

  /**
   * version.
   */
  @Version
  @Column(name = "version")
  private Integer version;

  /**
   * The Customer id.
   */
  @Column(name = "customer_id", nullable = false)
  private String customerId;

  /**
   * first 6 number.
   */
  @Column(name = "bin", nullable = false)
  private String bin;

  /**
   * card holder name.
   */
  @Column(name = "card_holder_name", nullable = false)
  private String cardholderName;

  /**
   * card type.
   */
  @Column(name = "card_type", nullable = false)
  private String cardType;

  /**
   * expiration month.
   */
  @Column(name = "expiration_month", nullable = false)
  private String expirationMonth;

  /**
   * expiration year.
   */
  @Column(name = "expiration_year", nullable = false)
  private String expirationYear;

  /**
   * is expired.
   */
  @Column(name = "expired")
  private boolean expired;

  /**
   * last 4 number.
   */
  @Column(name = "last4", nullable = false)
  private String last4;

  /**
   * commercial.
   */
  @Column(name = "commercial")
  private String commercial;

  /**
   * payment method token.
   */
  @Column(name = "token", nullable = false)
  private String token;

  /**
   * if credit card default.
   */
  @Column(name = "selected")
  private Boolean selected;
}
