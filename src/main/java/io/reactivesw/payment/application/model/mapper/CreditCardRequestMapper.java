package io.reactivesw.payment.application.model.mapper;

import com.braintreegateway.CreditCardRequest;

import io.reactivesw.payment.application.model.CreditCardDraft;

/**
 * Created by Davis on 17/3/15.
 */
public final class CreditCardRequestMapper {

  /**
   * Instantiates a new Credit card request mapper.
   */
  private CreditCardRequestMapper() {
  }

  /**
   * Build credit card request.
   *
   * @param braintreeId the braintree id
   * @param creditCart  the credit cart
   * @return the credit card request
   */
  public static CreditCardRequest build(String braintreeId, CreditCardDraft creditCart) {
    CreditCardRequest result = new CreditCardRequest()
        .customerId(braintreeId)
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
