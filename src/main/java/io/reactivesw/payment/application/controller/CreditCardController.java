package io.reactivesw.payment.application.controller;

import static io.reactivesw.payment.infrastructure.Router.CREDIT_CART_WITH_CUSTOMER_ID;
import static io.reactivesw.payment.infrastructure.Router.CUSTOMER_ID;

import io.reactivesw.payment.application.model.CreditCardDraft;
import io.reactivesw.payment.application.model.CreditCardView;
import io.reactivesw.payment.domain.service.CreditCardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Davis on 17/3/15.
 */
@RestController
public class CreditCardController {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CreditCardController.class);

  /**
   * credit card service.
   */
  @Autowired
  private transient CreditCardService creditCardService;

  /**
   * Gets all credit cards.
   *
   * @param customerId the customer id
   * @return the all credit cards
   */
  @GetMapping(value = CREDIT_CART_WITH_CUSTOMER_ID)
  public List<CreditCardView> getAllCreditCards(@PathVariable(name = CUSTOMER_ID) String
                                                    customerId) {

    LOG.debug("enter getAllCreditCards, customer id is : {}", customerId);

    List<CreditCardView> result = creditCardService.getCreditCards(customerId);

    LOG.debug("end getAllCreditCards, get credit card : {}", result.toString());

    return result;
  }

  /**
   * Add credit card credit card view.
   *
   * @param customerId      the customer id
   * @param creditCardDraft the credit card draft
   * @return the credit card view
   */
  @PutMapping(value = CREDIT_CART_WITH_CUSTOMER_ID)
  public CreditCardView addCreditCard(@PathVariable(name = CUSTOMER_ID) String customerId,
                                      @RequestBody CreditCardDraft creditCardDraft) {
    LOG.debug("enter addCreditCard, customer id is : {}, credit card draft is : {}", customerId,
        creditCardDraft);

    CreditCardView result = creditCardService.addCreditCard(customerId, creditCardDraft);

    LOG.debug("end addCreditCard, credit card is : {}", result.toString());

    return result;
  }
}
