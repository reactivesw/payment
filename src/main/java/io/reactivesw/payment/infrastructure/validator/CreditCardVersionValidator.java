package io.reactivesw.payment.infrastructure.validator;

import io.reactivesw.exception.ConflictException;
import io.reactivesw.payment.domain.model.CreditCard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * Created by Davis on 17/4/5.
 */
public final class CreditCardVersionValidator {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CreditCardVersionValidator.class);

  /**
   * Instantiates a new Credit card version validator.
   */
  private CreditCardVersionValidator() {
  }

  /**
   * Validate.
   *
   * @param entity  the entity
   * @param version the version
   */
  public static void validate(CreditCard entity, Integer version) {
    if (!Objects.equals(version, entity.getVersion())) {
      LOG.debug("credit card version not match, entity version: {}, request version: {}",
          entity.getVersion(), version);
      throw new ConflictException("Credit Card Version Not Match");
    }
  }
}
