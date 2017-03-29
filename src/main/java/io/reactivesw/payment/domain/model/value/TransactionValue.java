package io.reactivesw.payment.domain.model.value;

import io.reactivesw.payment.infrastructure.enums.TransactionState;
import io.reactivesw.payment.infrastructure.enums.TransactionType;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.GenericGenerator;

import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by Davis on 17/2/4.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "order_payment_transaction")
public class TransactionValue {

  /**
   * Id.
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  protected String id;

  /**
   * transaction time.
   */
  @Column
  private ZonedDateTime timestamp;

  /**
   * transaction type.
   */
  @Column
  private TransactionType type;

  /**
   * transaction amount.
   */
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private MoneyValue amount;

  /**
   * interaction id.
   */
  @Column(name = "interaction_id")
  private String interactionId;

  /**
   * transaction state.
   */
  @Column
  private TransactionState state;
}
