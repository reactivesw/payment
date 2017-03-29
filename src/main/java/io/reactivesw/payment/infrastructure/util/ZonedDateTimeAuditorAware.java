package io.reactivesw.payment.infrastructure.util;

import org.springframework.data.domain.AuditorAware;

/**
 * Created by Davis on 17/3/7.
 */
public class ZonedDateTimeAuditorAware implements AuditorAware<String> {
  /**
   * get current auditor.
   *
   * @return null
   */
  @Override
  public String getCurrentAuditor() {
    return null;
  }
}