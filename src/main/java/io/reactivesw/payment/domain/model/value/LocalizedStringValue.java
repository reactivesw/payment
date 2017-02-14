package io.reactivesw.payment.domain.model.value;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Davis on 16/11/16.
 */
@Entity
@Table(name = "common_localized_String")
@Data
@EqualsAndHashCode(callSuper = false)
public class LocalizedStringValue {

  /**
   * Id
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  protected String id;

  /**
   * language.
   */
  @Column
  protected String language;

  /**
   * text value.
   */
  @Column(columnDefinition = "text")
  protected String text;

  /**
   * Instantiates a new Localized string entity.
   */
  public LocalizedStringValue() {
    super();
  }

  /**
   * Instantiates a new Localized string entity.
   *
   * @param language the language
   * @param text     the text
   */
  public LocalizedStringValue(String language, String text) {
    this.language = language;
    this.text = text;
  }

}
