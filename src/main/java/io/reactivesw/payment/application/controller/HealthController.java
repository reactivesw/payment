package io.reactivesw.payment.application.controller;

import static io.reactivesw.payment.infrastructure.Router.PAYMENT_HEALTH_CHECK;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by umasuo on 17/2/21.
 */
@RestController
public class HealthController {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(HealthController.class);

  /**
   * service name.
   */
  private transient String serviceName;

  /**
   * Instantiates a new Health controller.
   *
   * @param serviceName the service name
   */
  public HealthController(@Value("${spring.application.name}") String serviceName) {
    this.serviceName = serviceName;
  }

  /**
   * this api is used for health check.
   *
   * @return service name.
   */
  @GetMapping(PAYMENT_HEALTH_CHECK)
  public String healthCheck() {
    LOG.debug("enter healthCheck");

    return serviceName + ", system time: " + System.currentTimeMillis();
  }
}
