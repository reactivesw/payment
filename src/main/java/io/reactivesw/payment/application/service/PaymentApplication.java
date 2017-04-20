package io.reactivesw.payment.application.service;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;

import io.reactivesw.exception.ParametersException;
import io.reactivesw.model.Money;
import io.reactivesw.payment.application.model.PayRequest;
import io.reactivesw.payment.application.model.PaymentView;
import io.reactivesw.payment.application.model.mapper.PaymentMapper;
import io.reactivesw.payment.application.model.mapper.TransactionRequestMapper;
import io.reactivesw.payment.domain.model.CreditCard;
import io.reactivesw.payment.domain.model.Payment;
import io.reactivesw.payment.domain.service.CreditCardService;
import io.reactivesw.payment.domain.service.PaymentService;
import io.reactivesw.payment.infrastructure.validator.ResultValidator;

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
   * @param gateway the gateway
   * @param paymentService the payment service
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
   * @param request the request
   * @return the payment view
   */
  public PaymentView checkout(PayRequest request) {

    LOG.debug("enter. pay request is: {}", request);

    BigDecimal decimalAmount = divideAmount(request.getAmount());

    CreditCard creditCart = creditCardService.getCreditCardEntity(request.getCreditCardId());

    TransactionRequest transactionRequest =
        TransactionRequestMapper.build(decimalAmount, creditCart.getToken());
    Result<Transaction> result = gateway.transaction().sale(transactionRequest);

    ResultValidator.validate(result);

    // TODO: 17/2/4 处理不同的结果

    Payment savedEntity = paymentService.savePayment(creditCart.getCustomerId(),
        request.getAmount(), result);

    return PaymentMapper.toModel(savedEntity);
  }

  /**
   * Divide amount big decimal.
   *
   * @param amount the amount
   * @return the big decimal
   */
  private BigDecimal divideAmount(Money amount) {
    BigDecimal decimalAmount = null;
    try {
      decimalAmount = new BigDecimal(amount.getCentAmount());
      decimalAmount = decimalAmount.divide(new BigDecimal("100"));
    } catch (NumberFormatException ex) {
      LOG.debug("can not parse amount : {} to BigDecimal", amount, ex);
      throw new ParametersException("Can Not Parse Amount to BigDecimal");
    }
    return decimalAmount;
  }
}
