package io.reactivesw.payment.application.controller;

import static io.reactivesw.payment.infrastructure.Router.PAYMENT_ROOT;

import io.reactivesw.payment.application.model.PayRequest;
import io.reactivesw.payment.application.model.PaymentView;
import io.reactivesw.payment.application.service.PaymentApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
   * Pay payment view.
   *
   * @param request the request
   * @return the payment view
   */
  @PostMapping(PAYMENT_ROOT)
  public PaymentView pay(@RequestBody @Valid PayRequest request) {

    LOG.debug("enter. pay request is: {}.", request);

    PaymentView result = paymentApplication.checkout(request);

    LOG.debug("end. result is : {}.", result);
    return result;
  }
}
