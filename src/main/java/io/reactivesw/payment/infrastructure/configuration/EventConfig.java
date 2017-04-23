package io.reactivesw.payment.infrastructure.configuration;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Event config.
 */
@Configuration
@Data
public class EventConfig {

  /**
   * Google cloud project id.
   */
  @Value("${io.reactivesw.message.google.project.id}")
  private String googleCloudProjectId;

  /**
   * Google cloud project id.
   */
  @Value("${io.reactivesw.message.topic.ordercreated.subscriber}")
  private String orderCreatedSubscriber;

  /**
   * Google cloud project id.
   */
  @Value("${io.reactivesw.message.topic.paymentpayed.name}")
  private String paymentPayedName;

  /**
   * Google cloud project id.
   */
  @Value("${io.reactivesw.message.topic.paymentpayed.version}")
  private Integer paymentPayedVersion;
}