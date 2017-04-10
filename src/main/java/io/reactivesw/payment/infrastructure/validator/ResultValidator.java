package io.reactivesw.payment.infrastructure.validator;

import com.braintreegateway.Result;

import io.reactivesw.exception.ParametersException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Davis on 17/4/10.
 */
public final class ResultValidator {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ResultValidator.class);

  /**
   * Instantiates a new Credit card validator.
   */
  private ResultValidator() {
  }

  /**
   * Validate.
   */
  public static void validate(Result result) {
    if (result.getTarget() == null) {
      LOG.debug("Something wrong when call braintree server: {}", result.getMessage());
      throw new ParametersException("Something wrong when call braintree server: "
          + result.getMessage());
    }
  }
}
