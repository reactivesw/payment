package io.reactivesw.payment.domain.model.value;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;

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
@NoArgsConstructor
@EqualsAndHashCode(of = {"currencyCode", "centAmount"})
public class MoneyValue {

  /**
   * Id.
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
}
