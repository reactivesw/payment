package io.reactivesw.payment.application.model.mapper;

import io.reactivesw.payment.application.model.BraintreeConfigModel;
import io.reactivesw.payment.domain.model.BraintreeConfig;
import io.reactivesw.payment.infrastructure.util.AesUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * The type Braintree config mapper.
 */
public final class BraintreeConfigMapper {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(BraintreeConfigMapper.class);

  /**
   * Instantiates a new Braintree config mapper.
   */
  private BraintreeConfigMapper() {
  }

  /**
   * To entity braintree config.
   *
   * @param model the model
   * @param secretKey the secret key
   * @return the braintree config
   */
  public static BraintreeConfig toEntity(BraintreeConfigModel model, String secretKey) {
    BraintreeConfig entity = new BraintreeConfig();

    try {
      entity.setEnvironment(AesUtils.encrypt(model.getEnvironment(), secretKey));
      entity.setMerchantId(AesUtils.encrypt(model.getMerchantId(), secretKey));
      entity.setPublicKey(AesUtils.encrypt(model.getPublicKey(), secretKey));
      entity.setPrivateKey(AesUtils.encrypt(model.getPrivateKey(), secretKey));
    } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException
        | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException ex) {
      LOG.debug("Can not encrypt braintree config.", ex);
    }
    return entity;
  }

  /**
   * Merge braintree config.
   *
   * @param entity the target entity
   * @param model the model
   * @param secretKey the secret key
   * @return the braintree config
   */
  public static BraintreeConfig merge(BraintreeConfig entity, BraintreeConfigModel model,
      String secretKey) {
    try {
      entity.setEnvironment(AesUtils.encrypt(model.getEnvironment(), secretKey));
      entity.setMerchantId(AesUtils.encrypt(model.getMerchantId(), secretKey));
      entity.setPublicKey(AesUtils.encrypt(model.getPublicKey(), secretKey));
      entity.setPrivateKey(AesUtils.encrypt(model.getPrivateKey(), secretKey));
    }  catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException
        | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException ex) {
      LOG.debug("Can not encrypt braintree config.", ex);
    }
    return entity;
  }

  /**
   * Decrypt braintree config.
   *
   * @param entity the entity
   * @param secretKey the secret key
   * @return the braintree config
   */
  public static BraintreeConfig decryptConfig(BraintreeConfig entity, String secretKey) {
    BraintreeConfig decryptedConfig = null;

    try {
      decryptedConfig = new BraintreeConfig();
      decryptedConfig.setEnvironment(AesUtils.decrypt(entity.getEnvironment(), secretKey));
      decryptedConfig.setMerchantId(AesUtils.decrypt(entity.getMerchantId(), secretKey));
      decryptedConfig.setPublicKey(AesUtils.decrypt(entity.getPublicKey(), secretKey));
      decryptedConfig.setPrivateKey(AesUtils.decrypt(entity.getPrivateKey(), secretKey));
    } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException
        | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException ex) {
      LOG.debug("Can not decrypt braintree config.", ex);
    }
    return decryptedConfig;
  }
}
