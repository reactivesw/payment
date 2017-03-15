package io.reactivesw.payment.application.controller;

import static io.reactivesw.payment.infrastructure.Router.PAYMENT_HEALTH_CHECK;

import com.braintreegateway.BraintreeGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by umasuo on 17/2/21.
 */
@RestController
public class IndexController {

  /**
   * service name.
   */
  @Value("${spring.application.name}")
  private String serviceName;

  /**
   * braintree gateway.
   */
  @Autowired
  private transient BraintreeGateway gateway;

  /**
   * this api is used for health check.
   *
   * @return service name.
   */
  @GetMapping(PAYMENT_HEALTH_CHECK)
  public String index() {
    return serviceName + ", system time: " + System.currentTimeMillis() + "  --  " + gateway
        .clientToken().generate();
  }
}
