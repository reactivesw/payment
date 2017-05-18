package io.reactivesw.payment.domain.service;

import io.reactivesw.payment.application.model.BraintreeConfigModel;
import io.reactivesw.payment.application.model.mapper.BraintreeConfigMapper;
import io.reactivesw.payment.domain.model.BraintreeConfig;
import io.reactivesw.payment.infrastructure.repository.BraintreeConfigRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Braintree config service.
 */
@Service
public class BraintreeConfigService {

  /**
   * The BraintreeConfigRepository.
   */
  @Autowired
  private transient BraintreeConfigRepository repository;

  /**
   * Save braintree config.
   *
   * @param model the model
   * @return the braintree config
   */
  public BraintreeConfig save(BraintreeConfigModel model) {

    BraintreeConfig savedConfig = null;
    BraintreeConfig srcConfig = getConfig();

    if (srcConfig == null) {
      BraintreeConfig config = BraintreeConfigMapper.toEntity(model);
      savedConfig = repository.save(config);
    } else {
      srcConfig = BraintreeConfigMapper.merge(srcConfig, model);
      savedConfig = repository.save(srcConfig);
    }

    return savedConfig;
  }

  /**
   * Gets config.
   *
   * @return the config
   */
  public BraintreeConfig getConfig() {
    List<BraintreeConfig> configs = repository.findAll();
    BraintreeConfig result = null;
    if (!configs.isEmpty()) {
      result = configs.get(0);
    }
    return result;
  }
}
