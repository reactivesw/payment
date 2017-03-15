package io.reactivesw.payment.application.controller;

import static io.reactivesw.payment.infrastructure.Router.PAYMENT_ROOT;

import io.reactivesw.payment.application.model.PaymentView;
import io.reactivesw.payment.application.service.PaymentApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Davis on 17/1/4.
 */
@RestController
public class PaymentController {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(PaymentController.class);

  /**
   * payment service.
   */
  @Autowired
  private transient PaymentApplication paymentApplication;

  /**
   * checkout.
   *
   * @param amount amount to paid
   * @param creditCardId  payment method token
   * @return Payment
   */
  @PostMapping(PAYMENT_ROOT)
  public PaymentView checkout(@RequestParam String customerId,
                              @RequestParam String amount,
                              @RequestParam String creditCardId) {

    LOG.debug("enter checkout, customer id is : {} amount is : {}, token is : {}",
        customerId, amount, creditCardId);

    PaymentView result = paymentApplication.checkout(customerId, amount, creditCardId);

    LOG.debug("end checkout, result is : {}", result);
    return result;
  }
}
