package io.reactivesw.payment.application.model.mapper;

import io.reactivesw.payment.application.model.BraintreeConfigModel;
import io.reactivesw.payment.domain.model.BraintreeConfig;

/**
 * The type Braintree config mapper.
 */
public final class BraintreeConfigMapper {

  /**
   * Instantiates a new Braintree config mapper.
   */
  private BraintreeConfigMapper() {
  }

  /**
   * To entity braintree config.
   *
   * @param model the model
   * @return the braintree config
   */
  public static BraintreeConfig toEntity(BraintreeConfigModel model) {
    BraintreeConfig entity = new BraintreeConfig();

    entity.setEnvironment(model.getEnvironment());
    entity.setMerchantId(model.getMerchantId());
    entity.setPublicKey(model.getPublicKey());
    entity.setPrivateKey(model.getPrivateKey());

    return entity;
  }

  /**
   * Merge braintree config.
   *
   * @param target the target
   * @param model the model
   * @return the braintree config
   */
  public static BraintreeConfig merge(BraintreeConfig target, BraintreeConfigModel model) {
    target.setEnvironment(model.getEnvironment());
    target.setMerchantId(model.getMerchantId());
    target.setPublicKey(model.getPublicKey());
    target.setPrivateKey(model.getPrivateKey());

    return target;
  } 
}
