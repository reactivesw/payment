package io.reactivesw.payment.application.model.mapper;

import com.google.common.collect.Lists;

import com.braintreegateway.Transaction;

import io.reactivesw.model.Money;
import io.reactivesw.model.Reference;
import io.reactivesw.payment.application.model.PaymentView;
import io.reactivesw.payment.domain.model.Payment;
import io.reactivesw.payment.domain.model.value.MoneyValue;
import io.reactivesw.payment.infrastructure.util.ReferenceTypes;

import java.math.BigDecimal;

/**
 * Created by Davis on 17/2/4.
 */
public final class PaymentMapper {
  /**
   * Instantiates a new Payment mapper.
   */
  private PaymentMapper() {
  }

  /**
   * map payment entity to model.
   *
   * @param entity payment entity
   * @return payment model
   */
  public static PaymentView toModel(Payment entity) {
    PaymentView model = new PaymentView();

    model.setId(entity.getId());
    model.setVersion(entity.getVersion());
    model.setCustomer(new Reference(ReferenceTypes.CUSTOMER.toString(), entity.getCustomerId()));
    model.setExternalId(entity.getExternalId());
    model.setInterfaceId(entity.getInterfaceId());
    model.setAmountPlanned(MoneyMapper.toModel(entity.getAmountPlanned()));
    model.setAmountAuthorized(MoneyMapper.toModel(entity.getAmountAuthorized()));
    model.setAuthorizedUntil(entity.getAuthorizedUntil());
    model.setAmountPaid(MoneyMapper.toModel(entity.getAmountPaid()));
    model.setAmountRefunded(MoneyMapper.toModel(entity.getAmountRefunded()));
    model.setPaymentMethodInfo(PaymentMethodInfoMapper.toModel(entity.getPaymentMethodInfo()));
    model.setPaymentStatus(PaymentStatusMapper.entityToModel(entity.getPaymentStatus()));
    model.setTransactions(TransactionMapper.toModel(entity.getTransactions()));
    model.setCreatedAt(entity.getCreatedAt());
    model.setLastModifiedAt(entity.getLastModifiedAt());

    return model;
  }

  /**
   * Build payment.
   *
   * @param amount      the amount
   * @param customerId  the customer id
   * @param transaction the transaction
   * @return the payment
   */
  public static Payment build(Money amount, String customerId, Transaction transaction) {
    Payment entity = new Payment();

    entity.setCustomerId(customerId);
    entity.setExternalId(null);
    entity.setInterfaceId(null);
    entity.setAmountPlanned(MoneyMapper.toEntity(amount));
    entity.setAmountAuthorized(null);
    entity.setAuthorizedUntil(null);
    entity.setAmountPaid(build(transaction));
    entity.setAmountRefunded(null);
    entity.setPaymentMethodInfo(PaymentMethodInfoMapper.build(transaction));
    entity.setPaymentStatus(PaymentStatusMapper.build(transaction));
    entity.setTransactions(Lists.newArrayList(TransactionMapper.build(transaction)));

    return entity;
  }

  /**
   * Build money value.
   *
   * @param transaction the transaction
   * @return the money value
   */
  private static MoneyValue build(Transaction transaction) {
    MoneyValue result = new MoneyValue();

    result.setCurrencyCode(transaction.getCurrencyIsoCode());

    BigDecimal amount = transaction.getAmount();

    Integer centAmount = amount.multiply(new BigDecimal("100")).intValue();

    result.setCentAmount(centAmount);

    return result;
  }
}