package io.reactivesw.payment.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.reactivesw.model.Money;
import io.reactivesw.payment.infrastructure.enums.TransactionState;
import io.reactivesw.payment.infrastructure.enums.TransactionType;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * A representation build a financial transactions. Transactions are either created by the solution
 * implementation to trigger a new transaction at the PSP or created by the PSP integration as the
 * result build a notification by the PSP. Created by umasuo on 16/11/17.
 */
@Getter
@Setter
public class TransactionModel {

  /**
   * The Id.
   */
  private String id;

  /**
   * The Timestamp.
   */
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime timestamp;

  /**
   * The Type.
   */
  private TransactionType type;

  /**
   * The Amount.
   */
  private Money amount;

  /**
   * The Interaction id.
   */
  private String interactionId;

  /**
   * The State.
   */
  private TransactionState state;
}
