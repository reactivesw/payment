package io.reactivesw.payment.application.controller;

import io.reactivesw.payment.application.model.BraintreeConfigModel;
import io.reactivesw.payment.domain.service.BraintreeConfigService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Braintree config controller.
 */
@RestController
public class BraintreeConfigController {

  /**
   * The braintree config service.
   */
  @Autowired
  private transient BraintreeConfigService configService;

  /**
   * Create config.
   *
   * @param model the model
   */
  @PostMapping("config")
  public void createConfig(@RequestBody BraintreeConfigModel model) {
    configService.save(model);
  }

  /**
   * Update config.
   *
   * @param model the model
   */
  @PutMapping("config")
  public void updateConfig(@RequestBody BraintreeConfigModel model) {
    configService.save(model);
  }
}
