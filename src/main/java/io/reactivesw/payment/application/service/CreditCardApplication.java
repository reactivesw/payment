package io.reactivesw.payment.application.service;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.CreditCardRequest;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Result;

import io.reactivesw.payment.application.model.CreditCardDraft;
import io.reactivesw.payment.application.model.CreditCardView;
import io.reactivesw.payment.application.model.mapper.CreditCardMapper;
import io.reactivesw.payment.application.model.mapper.CreditCardRequestMapper;
import io.reactivesw.payment.application.model.mapper.CustomerRequestMapper;
import io.reactivesw.payment.domain.model.CreditCard;
import io.reactivesw.payment.domain.model.CustomerRelationship;
import io.reactivesw.payment.domain.service.CreditCardService;
import io.reactivesw.payment.domain.service.CustomerRelationshipService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Davis on 17/3/30.
 */
@Service
public class CreditCardApplication {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CreditCardApplication.class);

  /**
   * braintree gateway.
   */
  private transient BraintreeGateway gateway;

  /**
   * the customer relationship service.
   */
  private transient CustomerRelationshipService relationshipService;

  /**
   * the credit card service.
   */
  private transient CreditCardService creditCardService;

  /**
   * Instantiates a new Credit card application.
   *
   * @param gateway the gateway
   */
  @Autowired
  public CreditCardApplication(BraintreeGateway gateway,
                               CustomerRelationshipService relationshipService,
                               CreditCardService creditCardService) {
    this.gateway = gateway;
    this.relationshipService = relationshipService;
    this.creditCardService = creditCardService;
  }

  /**
   * Add credit card credit card view.
   *
   * @param creditCardDraft the credit card draft
   * @return the credit card view
   */
  public CreditCardView addCreditCard(CreditCardDraft creditCardDraft) {
    LOG.debug("enter. credit card draft is: {}.", creditCardDraft);

    String braintreeId = relationshipService.getBrainTreeId(creditCardDraft.getCustomerId());

    com.braintreegateway.CreditCard braintreeCard = null;

    if (StringUtils.isBlank(braintreeId)) {
      braintreeCard = createCustomerWithCreditCard(creditCardDraft);
    } else {
      braintreeCard = addCreditCard(creditCardDraft, braintreeId);
    }

    CreditCard creditCard = CreditCardMapper.toEntity(creditCardDraft.getCustomerId(),
        braintreeCard);

    CreditCard saveCreditCard = creditCardService.saveCreditCard(creditCard);

    CreditCardView result = CreditCardMapper.toModel(saveCreditCard);

    LOG.debug("exit. new credit card is: {}.", result);

    return result;
  }

  /**
   * Add credit card com . braintreegateway . credit card.
   *
   * @param creditCardDraft the credit card draft
   * @param brainttreeId    the brainttree id
   * @return the com . braintreegateway . credit card
   */
  private com.braintreegateway.CreditCard addCreditCard(CreditCardDraft creditCardDraft,
                                                        String brainttreeId) {
    // TODO: 17/3/15  validate card number is exist
    com.braintreegateway.CreditCard braintreeCard;
    CreditCardRequest request = CreditCardRequestMapper.build(brainttreeId, creditCardDraft);
    Result<com.braintreegateway.CreditCard> result = gateway.creditCard().create(request);
    braintreeCard = result.getTarget();
    return braintreeCard;
  }

  /**
   * Create customer with credit card com . braintreegateway . credit card.
   *
   * @param creditCardDraft the credit card draft
   * @return the com . braintreegateway . credit card
   */
  private com.braintreegateway.CreditCard createCustomerWithCreditCard(CreditCardDraft
                                                                           creditCardDraft) {
    LOG.debug("enter.credit card draft is: {}.", creditCardDraft);

    com.braintreegateway.CreditCard braintreeCard;
    CustomerRequest customerRequest = CustomerRequestMapper.build(creditCardDraft);
    Result<Customer> braintreeCustomer = gateway.customer().create(customerRequest);

    String brainttreeId = braintreeCustomer.getTarget().getId();

    relationshipService.saveRelationship(CustomerRelationship.build(creditCardDraft.getCustomerId(),
        brainttreeId));

    braintreeCard = braintreeCustomer.getTarget().getCreditCards().get(0);
    return braintreeCard;
  }
}
