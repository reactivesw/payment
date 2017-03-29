package io.reactivesw.payment.domain.service;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.CreditCardRequest;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Result;

import io.reactivesw.exception.NotExistException;
import io.reactivesw.payment.application.model.CreditCardDraft;
import io.reactivesw.payment.application.model.CreditCardView;
import io.reactivesw.payment.application.model.mapper.CreditCardMapper;
import io.reactivesw.payment.application.model.mapper.CreditCardRequestMapper;
import io.reactivesw.payment.application.model.mapper.CustomerRequestMapper;
import io.reactivesw.payment.domain.model.CreditCard;
import io.reactivesw.payment.domain.model.CustomerRelationship;
import io.reactivesw.payment.infrastructure.repository.CreditCardRepository;
import io.reactivesw.payment.infrastructure.repository.CustomerRelationshipRepository;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Davis on 17/3/15.
 */
@Service
public class CreditCardService {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CreditCardService.class);

  /**
   * braintree gateway.
   */
  private transient BraintreeGateway gateway;

  /**
   * credit cart repository.
   */
  private transient CreditCardRepository creditCardRepository;

  /**
   * CustomerRelationshipRepository.
   */
  private transient CustomerRelationshipRepository customerRelationshipRepository;

  /**
   * Instantiates a new Credit card service.
   *
   * @param gateway                        the gateway
   * @param creditCardRepository           the credit card repository
   * @param customerRelationshipRepository the customer relationship repository
   */
  @Autowired
  public CreditCardService(BraintreeGateway gateway, CreditCardRepository creditCardRepository,
                           CustomerRelationshipRepository customerRelationshipRepository) {
    this.gateway = gateway;
    this.creditCardRepository = creditCardRepository;
    this.customerRelationshipRepository = customerRelationshipRepository;
  }

  /**
   * get credit cards by customer id.
   *
   * @param customerId customer id
   * @return list build credit card
   */
  public List<CreditCardView> getCreditCards(String customerId) {
    LOG.debug("enter getCreditCards, customer id is : {}", customerId);

    List<CreditCard> creditCards = creditCardRepository.getCreditCardsByCustomerId(customerId);

    if (creditCards == null || creditCards.isEmpty()) {
      LOG.debug("can not find any credit card by customerId : {}", customerId);
      throw new NotExistException("Credit Cards Not Exist");
    }

    List<CreditCardView> result = CreditCardMapper.toModel(creditCards);

    LOG.debug("end getCreditCards, get result : {}", result);
    return result;
  }

  /**
   * Gets payment token.
   *
   * @param creditCardId the credit card id
   * @return the payment token
   */
  public String getPaymentToken(String creditCardId) {
    LOG.debug("enter getPaymentToken, credit card id is : {}", creditCardId);
    CreditCard creditCard = creditCardRepository.findOne(creditCardId);
    String result = creditCard.getToken();
    LOG.debug("end getPaymentToken, token is : {}", result);
    return result;
  }

  /**
   * Add credit card.
   *
   * @param customerId      the customer id
   * @param creditCardDraft the credit card view
   * @return the list
   */
  public CreditCardView addCreditCard(String customerId,
                                      CreditCardDraft creditCardDraft) {

    LOG.debug("enter addCreditCard, customer id is : {}, credit card is : {}", customerId,
        creditCardDraft.toString());

    String brainttreeId = getBrainTreeId(customerId);

    com.braintreegateway.CreditCard braintreeCard = null;

    if (StringUtils.isBlank(brainttreeId)) {
      braintreeCard = createCustomerWithCreditCard(customerId, creditCardDraft);
    } else {
      braintreeCard = addCreditCard(creditCardDraft, brainttreeId);
    }

    CreditCard creditCard = CreditCardMapper.toEntity(customerId, braintreeCard);

    CreditCard savedCreditCard = creditCardRepository.save(creditCard);

    LOG.debug("end addCreditCard, saved credit card is : {}", savedCreditCard.toString());

    return CreditCardMapper.toModel(savedCreditCard);
  }

  /**
   * Add credit card com . braintreegateway . credit card.
   *
   * @param creditCardDraft the credit card draft
   * @param brainttreeId    the brainttree id
   * @return the com . braintreegateway . credit card
   */
  private com.braintreegateway.CreditCard addCreditCard(CreditCardDraft creditCardDraft, String
      brainttreeId) {
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
   * @param customerId      the customer id
   * @param creditCardDraft the credit card draft
   * @return the com . braintreegateway . credit card
   */
  private com.braintreegateway.CreditCard createCustomerWithCreditCard(String customerId,
                                                                       CreditCardDraft
                                                                           creditCardDraft) {
    LOG.debug("enter createCustomerWithCreditCard, customer is is : {}, credit card is : {}",
        customerId, creditCardDraft);

    com.braintreegateway.CreditCard braintreeCard;
    CustomerRequest customerRequest = CustomerRequestMapper.build(creditCardDraft);
    Result<Customer> braintreeCustomer = gateway.customer().create(customerRequest);

    String brainttreeId = braintreeCustomer.getTarget().getId();

    customerRelationshipRepository.save(CustomerRelationship.build(customerId, brainttreeId));

    braintreeCard = braintreeCustomer.getTarget().getCreditCards().stream()
        .filter(
            creditCard ->
                creditCardDraft.getNumber().endsWith(creditCard.getLast4())
                    && creditCardDraft.getNumber().startsWith(creditCard.getBin())
        ).findFirst().orElse(null);
    return braintreeCard;
  }

  /**
   * get braintree id by customer id.
   *
   * @param customerId the customer id
   * @return braintree id
   */
  private String getBrainTreeId(String customerId) {
    LOG.debug("enter getBrainTreeId, customer id is : {}", customerId);

    String brainttreeId = null;
    CustomerRelationship result = customerRelationshipRepository.findByCustomerId(customerId);
    if (result == null) {
      LOG.debug("can not find relationship by customer id : {}", customerId);
    } else {
      brainttreeId = result.getExternalId();
      LOG.debug("find braintree id : {} by customer id : {}", brainttreeId, customerId);
    }

    return brainttreeId;
  }
}