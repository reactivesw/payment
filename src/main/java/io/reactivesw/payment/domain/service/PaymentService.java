package io.reactivesw.payment.domain.service;

import com.braintreegateway.Result;
import com.braintreegateway.Transaction;

import io.reactivesw.payment.application.model.mapper.PaymentMapper;
import io.reactivesw.payment.domain.model.Payment;
import io.reactivesw.payment.infrastructure.repository.PaymentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  private transient PaymentRepository paymentRepository;
  
  /**
   * Instantiates a new Payment service.
   *
   * @param paymentRepository the payment repository
   */
  @Autowired
  public PaymentService(PaymentRepository paymentRepository) {
    this.paymentRepository = paymentRepository;
  }

  /**
   * Save payment payment.
   *
   * @param customerId the customer id
   * @param amount     the amount
   * @param result     the result
   * @return the payment
   */
  public Payment savePayment(String customerId, String amount, Result<Transaction> result) {
    LOG.debug("enter. customer id is: {}, amount is: {}", customerId, amount);

    Transaction transaction = result.getTarget();
    Payment entity = PaymentMapper.build(amount, customerId, transaction);
    Payment savedEntity = paymentRepository.save(entity);

    LOG.debug("exit.");

    return savedEntity;
  }
}
