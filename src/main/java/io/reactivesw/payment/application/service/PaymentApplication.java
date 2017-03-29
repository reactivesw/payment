package io.reactivesw.payment.application.service;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;

import io.reactivesw.payment.application.model.PaymentView;
import io.reactivesw.payment.application.model.mapper.PaymentMapper;
import io.reactivesw.payment.application.model.mapper.TransactionRequestMapper;
import io.reactivesw.payment.domain.model.Payment;
import io.reactivesw.payment.domain.service.CreditCardService;
import io.reactivesw.payment.domain.service.PaymentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by Davis on 17/3/15.
 */
@Service
public class PaymentApplication {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(PaymentApplication.class);

  /**
   * credit card service.
   */
  private transient CreditCardService creditCardService;

  /**
   * braintree gateway.
   */
  private transient BraintreeGateway gateway;

  /**
   * payment service.
   */
  private transient PaymentService paymentService;

  /**
   * Instantiates a new Payment application.
   *
   * @param creditCardService the credit card service
   * @param gateway           the gateway
   * @param paymentService    the payment service
   */
  @Autowired
  public PaymentApplication(CreditCardService creditCardService, BraintreeGateway gateway,
                            PaymentService paymentService) {
    this.creditCardService = creditCardService;
    this.gateway = gateway;
    this.paymentService = paymentService;
  }

  /**
   * Checkout payment view.
   *
   * @param customerId   the customer id
   * @param amount       the amount
   * @param creditCardId the credit card id
   * @return the payment view
   */
  public PaymentView checkout(String customerId, String amount, String creditCardId) {

    LOG.debug("enter checkout, customer id is {}, amount is : {}, credit card id : {}",
        customerId, amount, creditCardId);

    BigDecimal decimalAmount = divideAmount(amount);

    String token = getPaymentToken(creditCardId);

    TransactionRequest request = TransactionRequestMapper.build(decimalAmount, token);
    Result<Transaction> result = gateway.transaction().sale(request);

    // TODO: 17/2/4 处理不同的结果

    Payment savedEntity = paymentService.savePayment(customerId, amount, result);

    return PaymentMapper.toModel(savedEntity);
  }

  /**
   * Gets payment token.
   *
   * @param creditCardId the credit card id
   * @return the payment token
   */
  private String getPaymentToken(String creditCardId) {
    LOG.debug("enter getPaymentToken, credit card id is : {}", creditCardId);

    return creditCardService.getPaymentToken(creditCardId);
  }

  /**
   * Divide amount big decimal.
   *
   * @param amount the amount
   * @return the big decimal
   */
  private BigDecimal divideAmount(String amount) {
    BigDecimal decimalAmount = null;
    try {
      decimalAmount = new BigDecimal(amount);
      decimalAmount = decimalAmount.divide(new BigDecimal("100"));
    } catch (NumberFormatException ex) {
      LOG.debug("can not parse amount : {} to BigDecimal", amount, ex);
    }
    return decimalAmount;
  }
}
