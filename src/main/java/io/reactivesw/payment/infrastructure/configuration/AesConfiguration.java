package io.reactivesw.payment.infrastructure.configuration;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Davis on 17/5/19.
 */
@Getter
@Setter
@Configuration
public class AesConfiguration {

  /**
   * The constant secretKey.
   */
  @Value("${aes.secret.key}")
  private String secretKey;
}
