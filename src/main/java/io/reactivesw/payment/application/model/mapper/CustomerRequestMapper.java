package io.reactivesw.payment.application.model.mapper;

import com.braintreegateway.CustomerRequest;

import io.reactivesw.payment.application.model.CreditCardDraft;

/**
 * Created by Davis on 17/2/3.
 */
public final class CustomerRequestMapper {
  public static CustomerRequest of(CreditCardDraft creditCart) {
    CustomerRequest result = new CustomerRequest()
        .creditCard()
        .cardholderName(creditCart.getCardholderName())
        .number(creditCart.getNumber())
        .expirationYear(creditCart.getExpirationYear())
        .expirationMonth(creditCart.getExpirationMonth())
        .cvv(creditCart.getCvv())
        .options()
        .verifyCard(true)
        .done()
        .done();

    return result;
  }
}
