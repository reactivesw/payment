package io.reactivesw.payment.domain.model.value;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by Davis on 17/2/4.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "order_payment_method_info")
public class PaymentMethodInfoValue {

  /**
   * Id.
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  protected String id;

  /**
   * payment interface.
   */
  @Column(name = "payment_interface")
  private String paymentInterface;

  /**
   * method.
   */
  @Column(name = "method")
  private String method;

  /**
   * name.
   */
  /**
   * The Name.
   */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<LocalizedStringValue> name;
}
