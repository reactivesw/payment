package io.reactivesw.payment.domain.model;

import io.reactivesw.payment.domain.model.value.MoneyValue;
import io.reactivesw.payment.domain.model.value.PaymentMethodInfoValue;
import io.reactivesw.payment.domain.model.value.PaymentStatusValue;
import io.reactivesw.payment.domain.model.value.TransactionValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Created by Davis on 17/2/4.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "payment")
public class Payment {

  /**
   * Id
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  protected String id;

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
   * The External id.
   */
  @Column(name = "external_id")
  private String externalId;

  /**
   * The Interface id.
   */
  @Column(name = "interface_Id")
  private String interfaceId;

  /**
   * The Amount planned.
   */
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private MoneyValue amountPlanned;

  /**
   * The Amount authorized.
   */
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private MoneyValue amountAuthorized;

  /**
   * The Authorized until.
   */
  @Column(name = "authorized_until")
  private String authorizedUntil;

  /**
   * The Amount paid.
   */
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private MoneyValue amountPaid;

  /**
   * The Amount refunded.
   */
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private MoneyValue amountRefunded;

  /**
   * payment method info.
   */
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private PaymentMethodInfoValue paymentMethodInfo;

  /**
   * The Payment status value.
   */
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private PaymentStatusValue paymentStatus;

  /**
   * the transaction list.
   */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<TransactionValue> transactions;

}
