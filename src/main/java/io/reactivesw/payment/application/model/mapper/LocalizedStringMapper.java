package io.reactivesw.payment.application.model.mapper;

import io.reactivesw.model.LocalizedString;
import io.reactivesw.payment.domain.model.value.LocalizedStringValue;

import java.util.Set;

/**
 * Created by Davis on 16/11/30.
 */
public final class LocalizedStringMapper {

  /**
   * Instantiates a new Localized string mapper.
   */
  private LocalizedStringMapper() {
  }

  /**
   * Convert to localized string.
   * when localizedStringEntity is null return null.
   *
   * @param localizedStringEntities the localized string entity
   * @return the localized string
   */
  public static LocalizedString toModelDefaultNull(Set<LocalizedStringValue>
                                                       localizedStringEntities) {
    LocalizedString localizedString = null;
    if (localizedStringEntities != null) {
      localizedString = toModel(localizedStringEntities);
    }
    return localizedString;
  }

  /**
   * Convert to localized string.
   *
   * @param localizedStringEntities localizedStringEntities
   * @return LocalizedString
   */
  private static LocalizedString toModel(Set<LocalizedStringValue> localizedStringEntities) {
    LocalizedString localizedString = new LocalizedString();
    for (LocalizedStringValue localizedStringEntity : localizedStringEntities) {
      localizedString.addKeyValue(localizedStringEntity.getLanguage(), localizedStringEntity
          .getText());
    }
    return localizedString;
  }
}
