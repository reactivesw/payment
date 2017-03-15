package io.reactivesw.payment.application.model.mapper;


import io.reactivesw.payment.application.model.CreditCardView;
import io.reactivesw.payment.domain.model.CreditCard;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Davis on 17/2/3.
 */
public final class CreditCardMapper {
  public static List<CreditCardView> mapToModel(List<CreditCard> models) {
    return models.parallelStream().map(
        m -> mapToModel(m)
    ).collect(Collectors.toList());
  }

  public static CreditCardView mapToModel(CreditCard entity) {
    CreditCardView model = new CreditCardView();
    model.setId(entity.getId());
    model.setBin(entity.getBin());
    model.setCardholderName(entity.getCardholderName());
    model.setCardType(entity.getCardType());
    model.setCommercial(entity.getCommercial().toString());
    model.setCustomerId(entity.getCustomerId());
    model.setExpirationMonth(entity.getExpirationMonth());
    model.setExpirationYear(entity.getExpirationYear());
    model.setExpired(entity.isExpired());
    model.setToken(entity.getToken());
    model.setLast4(entity.getLast4());
    return model;
  }

  public static CreditCard mapToEntity(String customerId, com.braintreegateway.CreditCard model) {
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
