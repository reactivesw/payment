package io.reactivesw.payment.application.model.mapper;

import com.braintreegateway.CreditCardRequest;

import io.reactivesw.payment.application.model.CreditCardDraft;

/**
 * Created by Davis on 17/3/15.
 */
public class CreditCardRequestMapper {
  public static CreditCardRequest of(String customerId, CreditCardDraft creditCart) {
    CreditCardRequest result = new CreditCardRequest()
        .customerId(customerId)
        .number(creditCart.getNumber())
        .cardholderName(creditCart.getCardholderName())
        .cvv(creditCart.getCvv())
        .expirationYear(creditCart.getExpirationYear())
        .expirationMonth(creditCart.getExpirationMonth())
        .options()
        .verifyCard(true)
        .done();

    return result;
  }

}
