package io.reactivesw.payment.infrastructure.util;

import com.braintreegateway.Environment;

/**
 * The type Braintree environment utils.
 */
public final class BraintreeEnvironmentUtils {

  /**
   * Instantiates a new Braintree environment utils.
   */
  private BraintreeEnvironmentUtils() {
  }

  /**
   * get Braintree environment by env value.
   *
   * @param env the environment value
   * @return Environment environment
   */
  public static Environment getEnvironment(String env) {
    Environment environment = null;
    switch (env) {
      case "sanbox":
        environment = Environment.SANDBOX;
        break;
      case "production":
        environment = Environment.PRODUCTION;
        break;
      case "development":
        environment = Environment.DEVELOPMENT;
        break;
      default:
        environment = Environment.SANDBOX;
        break;
    }
    return environment;
  }
}
