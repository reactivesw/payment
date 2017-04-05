package io.reactivesw.payment.application.controller;

import static io.reactivesw.payment.infrastructure.Router.CREDIT_CARDS_ROOT;
import static io.reactivesw.payment.infrastructure.Router.CUSTOMER_ID;

import io.reactivesw.payment.application.model.CreditCardDraft;
import io.reactivesw.payment.application.model.CreditCardView;
import io.reactivesw.payment.application.model.DefaultCardRequest;
import io.reactivesw.payment.application.service.CreditCardApplication;
import io.reactivesw.payment.domain.service.CreditCardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

/**
 * Created by Davis on 17/3/15.
 */
@RestController
@CrossOrigin
public class CreditCardController {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CreditCardController.class);

  /**
   * credit card application.
   */
  private transient CreditCardApplication creditCardApplication;

  /**
   * credit card service.
   */
  private transient CreditCardService creditCardService;

  /**
   * Instantiates a new Credit card controller.
   *
   * @param creditCardApplication the credit card application
   * @param creditCardService     the credit card service
   */
  @Autowired
  public CreditCardController(CreditCardApplication creditCardApplication,
                              CreditCardService creditCardService) {
    this.creditCardApplication = creditCardApplication;
    this.creditCardService = creditCardService;
  }

  /**
   * Gets all credit cards.
   *
   * @param customerId the customer id
   * @return the all credit cards
   */
  @GetMapping(value = CREDIT_CARDS_ROOT)
  public List<CreditCardView> getAllCreditCards(@RequestParam(name = CUSTOMER_ID) String
                                                    customerId) {

    LOG.debug("enter getAllCreditCards, customer id is : {}", customerId);

    List<CreditCardView> result = creditCardService.getCreditCards(customerId);

    LOG.debug("end getAllCreditCards, get credit card : {}", result);

    return result;
  }

  /**
   * Add credit card view.
   *
   * @param creditCardDraft the credit card draft
   * @return the credit card view
   */
  @PostMapping(value = CREDIT_CARDS_ROOT)
  public CreditCardView addCreditCard(@RequestBody @Valid CreditCardDraft creditCardDraft) {
    LOG.info("enter. credit card draft is: {}.", creditCardDraft);

    CreditCardView result = creditCardApplication.addCreditCard(creditCardDraft);

    LOG.info("exit. new credit card is: {}.", result);

    return result;
  }

  /**
   * Sets default credit card.
   *
   * @param request the request
   * @return the default credit card
   */
  @PutMapping(CREDIT_CARDS_ROOT)
  public List<CreditCardView> setDefaultCreditCard(@RequestBody @Valid DefaultCardRequest request) {
    LOG.info("enter. request is: {}.", request);

    List<CreditCardView> result = creditCardService.setDefaultCreditCard(request);

    LOG.info("exit.");

    return result;
  }
}
