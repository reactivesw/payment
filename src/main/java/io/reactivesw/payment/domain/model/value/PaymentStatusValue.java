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
 * Created by Davis on 17/2/4.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "order_payment_status")
public class PaymentStatusValue {

  /**
   * Id.
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  protected String id;

  /**
   * interface code.
   */
  @Column(name = "interface_code")
  private String interfaceCode;

  /**
   * interface text.
   */
  @Column(name = "interface_text")
  private String interfaceText;

  /**
   * reference to state.
   */
  @Column(name = "state_id")
  private String stateId;
}
