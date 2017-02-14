package io.reactivesw.payment.application.model.mapper;

import io.reactivesw.model.LocalizedString;
import io.reactivesw.payment.domain.model.value.LocalizedStringValue;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Davis on 16/11/30.
 */
public final class LocalizedStringMapper {

  /**
   * Convert to localized string.
   * when localizedStringEntity is null return new LocalizedString.
   *
   * @param localizedStringEntities the localized string entity
   * @return the localized string
   */
  public static LocalizedString entityToModelDefaultNew(Set<LocalizedStringValue>
                                                            localizedStringEntities) {
    LocalizedString localizedString = new LocalizedString();
    if (localizedStringEntities != null) {
      localizedString = entityToModel(localizedStringEntities);
    }
    return localizedString;
  }

  /**
   * Convert to localized string.
   * when localizedStringEntity is null return null.
   *
   * @param localizedStringEntities the localized string entity
   * @return the localized string
   */
  public static LocalizedString entityToModelDefaultNull(Set<LocalizedStringValue>
                                                             localizedStringEntities) {
    LocalizedString localizedString = null;
    if (localizedStringEntities != null) {
      localizedString = entityToModel(localizedStringEntities);
    }
    return localizedString;
  }


  /**
   * Convert to Set of LocalizedStringEntity from LocalizedString.
   *
   * @param localizedString the LocalizedString
   * @return Set of LocalizedStringEntity
   */
  public static Set<LocalizedStringValue> modelToEntityDefaultNew(LocalizedString
                                                                      localizedString) {
    Set<LocalizedStringValue> localizedStringEntities = new HashSet<>();

    if (localizedString != null && !localizedString.getLocalized().isEmpty()) {
      Set<Map.Entry<String, String>> localizedStrings = localizedString.getLocalized().entrySet();
      for (Map.Entry localizedValue : localizedStrings) {
        localizedStringEntities.add(new LocalizedStringValue(localizedValue.getKey().toString(),
            localizedValue.getValue().toString()));
      }
    }

    return localizedStringEntities;
  }

  public static Set<LocalizedStringValue> modelToEntityDefaultNull(LocalizedString
                                                                       localizedString) {
    Set<LocalizedStringValue> localizedStringEntities = null;

    if (localizedString != null && !localizedString.getLocalized().isEmpty()) {
      localizedStringEntities = new HashSet<>();
      Set<Map.Entry<String, String>> localizeStrings = localizedString.getLocalized().entrySet();
      for (Map.Entry localizedValue : localizeStrings) {
        localizedStringEntities.add(new LocalizedStringValue(localizedValue.getKey().toString(),
            localizedValue.getValue().toString()));
      }
    }

    return localizedStringEntities;
  }

  /**
   * Convert to localized string.
   *
   * @param localizedStringEntities localizedStringEntities
   * @return LocalizedString
   */
  private static LocalizedString entityToModel(Set<LocalizedStringValue>
                                                   localizedStringEntities) {
    LocalizedString localizedString = new LocalizedString();
    for (LocalizedStringValue localizedStringEntity : localizedStringEntities) {
      localizedString.addKeyValue(localizedStringEntity.getLanguage(), localizedStringEntity
          .getText());
    }
    return localizedString;
  }

}
