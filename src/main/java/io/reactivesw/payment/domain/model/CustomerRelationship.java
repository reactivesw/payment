package io.reactivesw.payment.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Created by Davis on 17/3/15.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "customer_relation_ship")
@EntityListeners(AuditingEntityListener.class)
public class CustomerRelationship {
  /**
   * id.
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  private String id;

  /**
   * The Created at.
   */
  @CreatedDate
  @Column(name = "created_at")
  protected ZonedDateTime createdAt;

  /**
   * The Last modified at.
   */
  @LastModifiedDate
  @Column(name = "last_modified_at")
  protected ZonedDateTime lastModifiedAt;

  /**
   * version.
   */
  @Version
  @Column(name = "version")
  private Integer version;

  /**
   * the system customer id.
   */
  @Column(name = "customer_id", nullable = false)
  private String customerId;

  /**
   * external id for braintree.
   */
  @Column(name = "external_id", nullable = false)
  private String externalId;
  
  /**
   * Build customer relationship.
   *
   * @param customerId the customer id
   * @param externalId the external id
   * @return the customer relationship
   */
  public static CustomerRelationship build(String customerId, String externalId) {
    CustomerRelationship customerRelationship = new CustomerRelationship();
    customerRelationship.setCustomerId(customerId);
    customerRelationship.setExternalId(externalId);
    return customerRelationship;
  }
}

