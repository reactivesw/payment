package io.reactivesw.payment.application.model.mapper;

import io.reactivesw.payment.application.model.CreditCardView;
import io.reactivesw.payment.domain.model.CreditCard;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Davis on 17/2/3.
 */
public final class CreditCardMapper {

  /**
   * Instantiates a new Credit card mapper.
   */
  private CreditCardMapper() {
  }

  /**
   * Map to model list.
   *
   * @param models the models
   * @return the list
   */
  public static List<CreditCardView> toModel(List<CreditCard> models) {
    return models.stream().map(
        creditCard -> toModel(creditCard)
    ).collect(Collectors.toList());
  }

  /**
   * Map to model credit card view.
   *
   * @param entity the entity
   * @return the credit card view
   */
  public static CreditCardView toModel(CreditCard entity) {
    CreditCardView model = new CreditCardView();
    model.setId(entity.getId());
    model.setBin(entity.getBin());
    model.setCardholderName(entity.getCardholderName());
    model.setCardType(entity.getCardType());
    model.setCustomerId(entity.getCustomerId());
    model.setExpired(entity.isExpired());
    model.setLast4(entity.getLast4());
    model.setSelected(entity.getSelected());
    return model;
  }

  /**
   * Map to entity credit card.
   *
   * @param customerId the customer id
   * @param model      the model
   * @return the credit card
   */
  public static CreditCard toEntity(String customerId, com.braintreegateway.CreditCard model) {
    CreditCard entity = new CreditCard();

    entity.setBin(model.getBin());
    entity.setCardholderName(model.getCardholderName());
    entity.setCardType(model.getCardType());
    entity.setCommercial(model.getCommercial().toString());
    entity.setCustomerId(customerId);
    entity.setExpirationMonth(model.getExpirationMonth());
    entity.setExpirationYear(model.getExpirationYear());
    entity.setExpired(model.isExpired());
    entity.setLast4(model.getLast4());
    entity.setToken(model.getToken());

    return entity;
  }
}
