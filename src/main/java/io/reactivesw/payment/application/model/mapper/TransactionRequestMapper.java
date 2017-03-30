package io.reactivesw.payment.application.model.mapper;

import com.braintreegateway.TransactionRequest;

import java.math.BigDecimal;

/**
 * Created by Davis on 17/2/3.
 */
public final class TransactionRequestMapper {
  /**
   * Instantiates a new Transaction request mapper.
   */
  private TransactionRequestMapper() {
  }
  
  /**
   * Build transaction request.
   *
   * @param decimalAmount the decimal amount
   * @param token         the token
   * @return the transaction request
   */
  public static TransactionRequest build(BigDecimal decimalAmount, String token) {
    return new TransactionRequest()
        .amount(decimalAmount)
        .paymentMethodToken(token)
        .options()
        .submitForSettlement(true)
        .done();
  }
}
