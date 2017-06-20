package io.reactivesw.payment.infrastructure.configuration;

import com.braintreegateway.BraintreeGateway;

import io.reactivesw.payment.application.model.mapper.BraintreeConfigMapper;
import io.reactivesw.payment.domain.model.BraintreeConfig;
import io.reactivesw.payment.domain.service.BraintreeConfigService;
import io.reactivesw.payment.infrastructure.util.BraintreeEnvironmentUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Braintree factory.
 */
@Configuration
public class BraintreeFactory {

  /**
   * The BraintreeConfigService.
   */
  @Autowired
  private transient BraintreeConfigService configService;

  /**
   * The AES configuration.
   */
  @Autowired
  private transient AesConfiguration aesConfiguration;

  /**
   * Gets braintree gateway.
   *
   * @return the braintree gateway
   */
  @Bean
  public BraintreeGateway getBraintreeGateway() {
    BraintreeConfig config = getConfig();
    BraintreeGateway gateway = null;
    if (config != null) {
      gateway = new BraintreeGateway(
          BraintreeEnvironmentUtils.getEnvironment(config.getEnvironment()),
          config.getMerchantId(),
          config.getPublicKey(),
          config.getPrivateKey());
    }
    return gateway;
  }

  /**
   * Get braintree config.
   *
   * @return the BraintreeConfig
   */
  private BraintreeConfig getConfig() {
    BraintreeConfig config = configService.getConfig();
    if (config != null) {
      config = BraintreeConfigMapper.decryptConfig(config, aesConfiguration.getSecretKey());
    }
    return config;
  }
}
