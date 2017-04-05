package io.reactivesw.payment.domain.service;

import com.google.common.collect.Lists;

import io.reactivesw.exception.NotExistException;
import io.reactivesw.payment.application.model.CreditCardView;
import io.reactivesw.payment.application.model.DefaultCardRequest;
import io.reactivesw.payment.application.model.mapper.CreditCardMapper;
import io.reactivesw.payment.domain.model.CreditCard;
import io.reactivesw.payment.infrastructure.repository.CreditCardRepository;
import io.reactivesw.payment.infrastructure.util.CreditCardUtils;
import io.reactivesw.payment.infrastructure.validator.CreditCardVersionValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
   * credit cart repository.
   */
  private transient CreditCardRepository creditCardRepository;

  /**
   * Instantiates a new Credit card service.
   *
   * @param creditCardRepository the credit card repository
   */
  @Autowired
  public CreditCardService(CreditCardRepository creditCardRepository) {
    this.creditCardRepository = creditCardRepository;
  }

  /**
   * Save credit card.
   *
   * @param entity the entity
   * @return the credit card
   */
  public CreditCard saveCreditCard(CreditCard entity) {
    LOG.debug("enter. credit card is: {}.", entity);

    CreditCard savedEntity = creditCardRepository.save(entity);

    LOG.debug("exit. saved credit card is: {}.", savedEntity);

    return savedEntity;
  }

  /**
   * Delete credit card.
   *
   * @param creditCardId the credit card id
   */
  public void deleteCreditCard(String creditCardId) {
    LOG.debug("enter. credit card id: {}.", creditCardId);
    creditCardRepository.delete(creditCardId);
    LOG.debug("exit.");
  }

  /**
   * get credit cards by customer id.
   *
   * @param customerId customer id
   * @return list build credit card
   */
  public List<CreditCardView> getCreditCards(String customerId) {
    LOG.debug("enter getCreditCards, customer id is : {}", customerId);

    List<CreditCardView> result = Lists.newArrayList();

    List<CreditCard> creditCards =
        creditCardRepository.getCreditCardsByCustomerIdOrderByCreatedAt(customerId);

    if (creditCards != null) {
      result = CreditCardMapper.toModel(creditCards);
    }

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
    CreditCard creditCard = getCreditCardEntity(creditCardId);

    String result = creditCard.getToken();

    LOG.debug("end getPaymentToken, token is : {}", result);

    return result;
  }

  /**
   * Gets credit card entity.
   *
   * @param creditCardId the credit card id
   * @return the credit card entity
   */
  public CreditCard getCreditCardEntity(String creditCardId) {
    CreditCard creditCard = creditCardRepository.findOne(creditCardId);

    if (creditCard == null) {
      LOG.debug("can not find credit card by id: {}", creditCardId);
      throw new NotExistException("Credit Card Not Found");
    }
    return creditCard;
  }

  /**
   * Sets default card.
   *
   * @param request the request
   */
  @Transactional
  public void setDefaultCreditCard(DefaultCardRequest request) {
    LOG.debug("enter. request is: {}.", request);

    List<CreditCard> creditCards = getEntityByCustomerId(request.getCustomerId());

    CreditCard defaultCreditCard = CreditCardUtils.getDefaultCreditCard(creditCards);
    clearDefaultCreditCard(defaultCreditCard);

    CreditCard requestCreditCard = CreditCardUtils.getCreditCardById(creditCards, request
        .getCreditCardId());
    CreditCardVersionValidator.validate(requestCreditCard, request.getVersion());
    setDefaultCreditCardEntity(requestCreditCard);

    LOG.info("exit.");
  }

  /**
   * set default credit card to database.
   *
   * @param requestCreditCard the CreditCard
   */
  private void setDefaultCreditCardEntity(CreditCard requestCreditCard) {
    requestCreditCard.setSelected(true);
    creditCardRepository.save(requestCreditCard);
  }

  /**
   * clear default setting.
   *
   * @param defaultCreditCard CreditCard
   */
  private void clearDefaultCreditCard(CreditCard defaultCreditCard) {
    if (defaultCreditCard != null) {
      defaultCreditCard.setSelected(false);
      creditCardRepository.save(defaultCreditCard);
    }
  }

  /**
   * get credit card entity by customer id.
   *
   * @param customerId the customer id
   * @return list of CreditCard
   */
  private List<CreditCard> getEntityByCustomerId(String customerId) {
    List<CreditCard> creditCards =
        creditCardRepository.getCreditCardsByCustomerIdOrderByCreatedAt(customerId);

    if (creditCards == null || creditCards.isEmpty()) {
      LOG.debug("can not find any credit card by customerId : {}", customerId);
      throw new NotExistException("Credit Cards Not Exist");
    }
    return creditCards;
  }
}