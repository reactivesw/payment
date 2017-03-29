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

  /**
   * The Id.
   */
  private String id;

  /**
   * The Version.
   */
  private Integer version;

  /**
   * The Customer.
   */
  private Reference customer;

  /**
   * The External id.
   */
  private String externalId;

  /**
   * The Interface id.
   */
  private String interfaceId;

  /**
   * The Amount planned.
   */
  private Money amountPlanned;

  /**
   * The Amount authorized.
   */
  private Money amountAuthorized;

  /**
   * The Authorized until.
   */
  private String authorizedUntil;

  /**
   * The Amount paid.
   */
  private Money amountPaid;

  /**
   * The Amount refunded.
   */
  private Money amountRefunded;

  /**
   * The Payment method info.
   */
  private PaymentMethodInfo paymentMethodInfo;

  /**
   * The Payment status.
   */
  private PaymentStatus paymentStatus;

  /**
   * The Transactions.
   */
  private List<TransactionModel> transactions;

  /**
   * The Created at.
   */
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime createdAt;

  /**
   * The Last modified at.
   */
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime lastModifiedAt;
}
