package io.reactivesw.payment.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.reactivesw.model.Money;
import io.reactivesw.payment.infrastructure.enums.TransactionState;
import io.reactivesw.payment.infrastructure.enums.TransactionType;

import lombok.Data;

import java.time.ZonedDateTime;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
public class TransactionDraft {

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime timestamp;

  private TransactionType type;

  private Money amount;

  private String interactionId;

  private TransactionState state;
}
