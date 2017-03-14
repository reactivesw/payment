package io.reactivesw.payment.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.reactivesw.model.Money;
import io.reactivesw.model.Reference;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PaymentView {

  private String id;

  private Integer version;

  private Reference customer;

  private String externalId;

  private String interfaceId;

  private Money amountPlanned;

  private Money amountAuthorized;

  private String authorizedUntil;

  private Money amountPaid;

  private Money amountRefunded;

  private PaymentMethodInfo paymentMethodInfo;

  private PaymentStatus paymentStatus;

  private List<TransactionModel> transactions;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime createdAt;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime lastModifiedAt;
}
