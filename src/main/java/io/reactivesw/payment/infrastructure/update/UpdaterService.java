package io.reactivesw.payment.infrastructure.update;

import io.reactivesw.model.Updater;
import io.reactivesw.payment.domain.model.CreditCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * we may got two kind build update:
 * just use the data in action, or still use data from other service.
 * if we just use the data in action, we can only use action mapper to set the data.
 * if we need get data from other palace, we should use update service.
 * Created by umasuo on 16/12/7.
 */
@Service
public class UpdaterService implements Updater<CreditCard, UpdateAction> {

  /**
   * ApplicationContext for get update services.
   */
  @Autowired
  private transient ApplicationContext context;

  /**
   * put the value in action to entity.
   *
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(CreditCard entity, UpdateAction action) {
    Updater updater = getUpdateService(action);
    updater.handle(entity, action);
  }

  /**
   * get mapper.
   *
   * @param action UpdateAction
   * @return ZoneUpdateMapper
   */
  private Updater getUpdateService(UpdateAction action) {
    return (Updater) context.getBean(action.getActionName());
  }
}
