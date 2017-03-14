package io.reactivesw.payment.application.model;

import io.reactivesw.model.Money;
import io.reactivesw.model.Reference;

import java.util.List;

/**
 * Created by umasuo on 16/11/17.
 */
public class PaymentDraft {

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

  private List<TransactionDraft> transactions;
}
