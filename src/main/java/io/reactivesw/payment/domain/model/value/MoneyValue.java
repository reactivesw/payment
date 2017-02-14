package io.reactivesw.payment.domain.model.value;

import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by umasuo on 16/11/30.
 */
@Entity
@Table(name = "common_money")
public class MoneyValue {

  /**
   * Id
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  protected String id;

  /**
   * The Currency code.
   */
  private String currencyCode;

  /**
   * The Cent amount.
   */
  private Integer centAmount;

  /**
   * default constructor.
   */
  public MoneyValue() {

  }

  /**
   * constructor.
   *
   * @param currencyCode String
   * @param centAmount   Integer
   */
  public MoneyValue(String currencyCode, Integer centAmount) {
    this.currencyCode = currencyCode;
    this.centAmount = centAmount;
  }

  /**
   * Gets currency code.
   *
   * @return the currency code
   */
  public String getCurrencyCode() {
    return currencyCode;
  }

  /**
   * Sets currency code.
   *
   * @param currencyCode the currency code
   */
  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  /**
   * Gets cent amount.
   *
   * @return the cent amount
   */
  public Integer getCentAmount() {
    return centAmount;
  }

  /**
   * Sets cent amount.
   *
   * @param centAmount the cent amount
   */
  public void setCentAmount(Integer centAmount) {
    this.centAmount = centAmount;
  }

  /**
   * equals method.
   * if the currency and the currency code is the same, so the two object is equals.
   *
   * @param obj
   * @return
   */
  @Override
  public boolean equals(Object obj) {
    boolean result = false;

    if (this == obj) {
      result = true;
    }

    if (this != obj && obj instanceof MoneyValue) {
      MoneyValue that = (MoneyValue) obj;
      result = Objects.equals(this.getCentAmount(), that.getCentAmount())
          && Objects.equals(this.getCurrencyCode(), that.getCurrencyCode());
    }

    return result;
  }

  /**
   * hash code.
   *
   * @return
   */
  @Override
  public int hashCode() {
    int result = getCurrencyCode() == null ? 0 : getCurrencyCode().hashCode();
    result = 31 * result + (getCentAmount() == null ? 0 : getCentAmount().hashCode());
    return result;
  }
}
