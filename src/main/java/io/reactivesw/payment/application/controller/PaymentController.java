package io.reactivesw.payment.application.controller;

import static io.reactivesw.payment.infrastructure.Router.CUSTOMER_ID;
import static io.reactivesw.payment.infrastructure.Router.PAYMENT_ROOT;
import static io.reactivesw.payment.infrastructure.Router.PAYMENT_WITH_CUSTOMER_ID;

import io.reactivesw.payment.application.model.CreditCardView;
import io.reactivesw.payment.application.model.PaymentView;
import io.reactivesw.payment.application.model.action.AddCreditCardAction;
import io.reactivesw.payment.domain.service.PaymentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
  private transient PaymentService paymentService;

  /**
   * get credit cards by customer id.
   *
   * @param customerId customer id
   * @return list of credit cards
   */
  @GetMapping(PAYMENT_WITH_CUSTOMER_ID)
  public List<CreditCardView> getCreditCards(@PathVariable(CUSTOMER_ID) String customerId) {
    LOG.debug("enter getCreditCards, customer id is : {}", customerId);

    List<CreditCardView> result = paymentService.getCreditCards(customerId);

    LOG.debug("end getCreditCards, result size is : {}", result.size());
    return result;
  }

  /**
   * Add credit cards list.
   *
   * @param customerId          the customer id
   * @param addCreditCardAction the add credit card action
   * @return the list
   */
  @PutMapping(PAYMENT_WITH_CUSTOMER_ID)
  public List<CreditCardView> addCreditCards(@PathVariable(CUSTOMER_ID) String customerId,
                                             @RequestBody @Valid AddCreditCardAction
                                                 addCreditCardAction) {
    LOG.debug("enter updateCreditCards, customer id is : {}", customerId);

    List<CreditCardView> result = paymentService.addCreditCard(customerId, addCreditCardAction);

    LOG.debug("end updateCreditCards");

    return result;
  }

  /**
   * checkout.
   *
   * @param amount amount to paid
   * @param token  payment method token
   * @return Payment
   */
  @PostMapping(PAYMENT_ROOT)
  public PaymentView checkout(@RequestParam String customerId,
                              @RequestParam String amount,
                              @RequestParam String token) {

    LOG.debug("enter checkout, customer id is : {} amount is : {}, token is : {}",
        customerId, amount, token);

    PaymentView result = paymentService.checkout(customerId, amount, token);

    LOG.debug("end checkout, result is : {}", result);
    return result;
  }
}
