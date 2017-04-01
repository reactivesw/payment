package io.reactivesw.payment.infrastructure.util;

import io.reactivesw.exception.NotExistException;
import io.reactivesw.payment.domain.model.CreditCard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Created by Davis on 17/4/1.
 */
public final class CreditCardUtils {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CreditCardUtils.class);

  /**
   * Instantiates a new Credit card utils.
   */
  private CreditCardUtils() {
  }

  /**
   * Gets default credit card.
   *
   * @param creditCards the credit cards
   * @return the default credit card
   */
  public static CreditCard getDefaultCreditCard(List<CreditCard> creditCards) {

    Predicate<CreditCard> predicate =
        creditCard -> Objects.equals(creditCard.getSelected(), true);

    CreditCard creditCard = creditCards.stream().filter(predicate).findAny().orElse(null);

    return creditCard;
  }

  /**
   * Gets credit card by id.
   *
   * @param creditCards  the credit cards
   * @param creditCardId the credit card id
   * @return the credit card by id
   */
  public static CreditCard getCreditCardById(List<CreditCard> creditCards, String creditCardId) {
    Predicate<CreditCard> predicate = creditCard -> creditCard.getId().equals(creditCardId);

    CreditCard creditCard = creditCards.stream().filter(predicate).findAny().orElse(null);

    if (creditCard == null) {
      LOG.debug("can not find credit card by id: {}.", creditCardId);
      throw new NotExistException("Credit Card Not Exist");
    }

    return creditCard;
  }
}
