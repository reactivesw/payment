package io.reactivesw.payment.infrastructure.repository;

import io.reactivesw.payment.domain.model.CreditCard;
import io.reactivesw.payment.domain.model.CustomerRelationship;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Davis on 17/3/15.
 */
public interface CustomerRelationshipRepository extends JpaRepository<CustomerRelationship,
    String> {
  /**
   * Gets credit cards by customer id.
   *
   * @param customerId the customer id
   * @return the credit cards by customer id
   */
  CustomerRelationship findByCustomerId(String customerId);
}
