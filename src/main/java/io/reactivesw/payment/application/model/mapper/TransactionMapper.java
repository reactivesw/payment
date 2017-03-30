package io.reactivesw.payment.application.model.mapper;

import com.google.common.collect.Lists;

import com.braintreegateway.Transaction;

import io.reactivesw.model.Money;
import io.reactivesw.payment.application.model.TransactionModel;
import io.reactivesw.payment.domain.model.value.MoneyValue;
import io.reactivesw.payment.domain.model.value.TransactionValue;
import io.reactivesw.payment.infrastructure.enums.TransactionState;
import io.reactivesw.payment.infrastructure.enums.TransactionType;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Davis on 17/1/5.
 */
public final class TransactionMapper {
  /**
   * conversion factor.
   */
  private static BigDecimal conversionFactor = new BigDecimal("100");

  /**
   * Instantiates a new TransactionModel mapper.
   */
  private TransactionMapper() {
  }


  /**
   * Entity to model list.
   *
   * @param entities the entities
   * @return the list
   */
  public static List<TransactionModel> toModel(List<TransactionValue> entities) {
    List<TransactionModel> models = Lists.newArrayList();

    if (entities != null) {
      models = entities.parallelStream().map(
          entity -> {
            return toModel(entity);
          }
      ).collect(Collectors.toList());
    }

    return models;
  }

  /**
   * Entity to model transaction model.
   *
   * @param entity the entity
   * @return the transaction model
   */
  public static TransactionModel toModel(TransactionValue entity) {
    TransactionModel model = new TransactionModel();

    model.setId(entity.getId());
    model.setTimestamp(entity.getTimestamp());
    model.setType(entity.getType());
    model.setAmount(MoneyMapper.toModel(entity.getAmount()));
    model.setInteractionId(entity.getInteractionId());
    model.setState(entity.getState());

    return model;
  }

  /**
   * Entity to model transaction.
   *
   * @param entity the entity
   * @return the transaction
   */
  public static TransactionModel toModel(Transaction entity) {
    TransactionModel model = new TransactionModel();

    model.setId(entity.getId());
    model.setTimestamp(ZonedDateTime.ofInstant(entity.getCreatedAt().toInstant(),
        ZoneId.systemDefault()));

    model.setState(getTransactionState(entity));
    model.setAmount(getAmount(entity));
    model.setType(getTransactionType(entity));
    model.setInteractionId(null);

    return model;
  }

  /**
   * Build transaction value.
   *
   * @param transaction the transaction
   * @return the transaction value
   */
  public static TransactionValue build(Transaction transaction) {
    // TODO: 17/2/6
    TransactionValue result = new TransactionValue();

    Calendar time = transaction.getCreatedAt();

    ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(time.toInstant(), ZoneOffset.UTC);

    result.setTimestamp(zonedDateTime);


    BigDecimal amount = transaction.getAmount();
    Integer centAmount = Integer.valueOf(amount.multiply(new BigDecimal("100")).intValue());

    result.setAmount(new MoneyValue(transaction.getCurrencyIsoCode(), centAmount));
    result.setInteractionId(transaction.getId());

    result.setType(TransactionType.Charge);
    result.setState(TransactionState.Success);

    return result;
  }

  /**
   * get transaction state from Transaction.
   *
   * @param entity the Transaction
   * @return TransactionState
   */
  private static TransactionState getTransactionState(Transaction entity) {
    TransactionState transactionState = TransactionState.Pending;
    if (entity.getStatus().equals(Transaction.Status
        .SUBMITTED_FOR_SETTLEMENT)) {
      transactionState = TransactionState.Success;
    } else if (entity.getStatus().equals(Transaction.Status.FAILED)) {
      transactionState = TransactionState.Failure;
    }
    return transactionState;
  }

  /**
   * get amount from Transaction.
   *
   * @param entity the Transaction
   * @return Money
   */
  private static Money getAmount(Transaction entity) {
    Money amount = new Money();

    amount.setCurrencyCode(entity.getCurrencyIsoCode());
    amount.setCentAmount(entity.getAmount().multiply(conversionFactor).intValue());

    return amount;
  }

  /**
   * get TransactionType from Transaction.
   *
   * @param entity the Transaction
   * @return TransactionType
   */
  private static TransactionType getTransactionType(Transaction entity) {
    TransactionType transactionType = null;

    // TODO: 17/1/5 set transaction type.
    switch (entity.getStatus()) {
      case AUTHORIZED:
        transactionType = TransactionType.Authorization;
        break;
      case SUBMITTED_FOR_SETTLEMENT:
        transactionType = TransactionType.Charge;
        break;
      default:
        break;
    }

    return transactionType;
  }
}
