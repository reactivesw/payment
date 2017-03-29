package io.reactivesw.payment.application.model.mapper;

import com.braintreegateway.CustomerRequest;

import io.reactivesw.payment.application.model.CreditCardDraft;

/**
 * Created by Davis on 17/2/3.
 */
public final class CustomerRequestMapper {
  /**
   * Instantiates a new Customer request mapper.
   */
  private CustomerRequestMapper() {
  }

  /**
   * Build customer request.
   *
   * @param creditCart the credit cart
   * @return the customer request
   */
  public static CustomerRequest build(CreditCardDraft creditCart) {
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
