package io.reactivesw.payment.infrastructure.validator;

import io.reactivesw.exception.ConflictException;
import io.reactivesw.payment.application.model.CreditCardView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Davis on 17/4/5.
 */
public final class CreditCardNumberValidator {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CreditCardNumberValidator.class);

  /**
   * Instantiates a new Credit card number validator.
   */
  private CreditCardNumberValidator() {
  }

  /**
   * Validate.
   *
   * @param creditCards      the credit cards
   * @param creditCardNumber the credit card number
   */
  public static void validate(List<CreditCardView> creditCards, String creditCardNumber) {
    Predicate<CreditCardView> predicate = creditCardView ->
        creditCardNumber.startsWith(creditCardView.getBin())
            && creditCardNumber.endsWith(creditCardView.getLast4());
    boolean mathcResult = creditCards.stream().anyMatch(predicate);
    if (mathcResult) {
      LOG.debug("Credit Card: {} Already Exist.", creditCardNumber);
      throw new ConflictException("Credit Card Exist");
    }
  }
}
