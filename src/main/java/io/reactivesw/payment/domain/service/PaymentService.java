package io.reactivesw.payment.domain.service;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;

import io.reactivesw.payment.application.model.PaymentView;
import io.reactivesw.payment.application.model.mapper.PaymentMapper;
import io.reactivesw.payment.application.model.mapper.TransactionRequestMapper;
import io.reactivesw.payment.domain.model.Payment;
import io.reactivesw.payment.infrastructure.repository.PaymentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by Davis on 16/12/27.
 */
@Service
public class PaymentService {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(PaymentService.class);

  /**
   * payment repository.
   */
  @Autowired
  private transient PaymentRepository paymentRepository;


  /**
   * Save payment payment.
   *
   * @param customerId the customer id
   * @param amount     the amount
   * @param result     the result
   * @return the payment
   */
  public Payment savePayment(String customerId, String amount, Result<Transaction> result) {
    Transaction transaction = result.getTarget();
    Payment entity = PaymentMapper.of(amount, customerId, transaction);
    return paymentRepository.save(entity);
  }
}
