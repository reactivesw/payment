package io.reactivesw.payment.application.model.mapper;

import io.reactivesw.model.Money;
import io.reactivesw.payment.domain.model.value.MoneyValue;

/**
 * Created by umasuo on 16/12/8.
 */
public final class MoneyMapper {

  /**
   * Instantiates a new Money mapper.
   */
  private MoneyMapper() {
  }

  /**
   * Model to entity money value.
   *
   * @param model the model
   * @return the money value
   */
  public static MoneyValue toEntity(Money model) {
    MoneyValue entity = null;
    if (model != null) {
      entity = new MoneyValue();
      entity.setCentAmount(model.getCentAmount());
      entity.setCurrencyCode(model.getCurrencyCode());
    }
    return entity;
  }

  /**
   * Entity to model money.
   *
   * @param entity the entity
   * @return the money
   */
  public static Money toModel(MoneyValue entity) {
    Money model = null;
    if (entity != null) {
      model = new Money();

      model.setCentAmount(entity.getCentAmount());
      model.setCurrencyCode(entity.getCurrencyCode());
    }
    return model;
  }

}
