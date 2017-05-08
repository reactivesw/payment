package io.reactivesw.payment.infrastructure.validator;

import io.reactivesw.exception.ParametersException;
import io.reactivesw.payment.application.model.PayRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Pay request validator.
 */
public final class PayRequestValidator {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(PayRequestValidator.class);

  /**
   * Instantiates a new Pay request validator.
   */
  private PayRequestValidator() {
  }

  /**
   * Validate.
   *
   * @param request the request
   */
  public static void validate(PayRequest request) {
    if (request == null) {
      LOG.debug("PayRequest can not be null.");
      throw new ParametersException("PayRequest is Null.");
    }

    if (StringUtils.isBlank(request.getCreditCardId())) {
      LOG.debug("Create card id should not be blank in PayRequest.");
      throw new ParametersException("Create Card Id is Blank.");
    }

    if (request.getAmount() == null
        || StringUtils.isBlank(request.getAmount().getCurrencyCode())
        || request.getAmount().getCentAmount() == null) {
      LOG.debug("Amount can not be null.");
      throw new ParametersException("Amount is null.");
    }

    if (request.getAmount().getCentAmount() <= 0) {
      LOG.debug("Amount must be greater than zero.");
      throw new ParametersException("Amount must be greater than zero.");
    }
  }
}
