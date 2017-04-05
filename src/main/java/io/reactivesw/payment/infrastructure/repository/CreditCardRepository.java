package io.reactivesw.payment.infrastructure.repository;

import io.reactivesw.payment.domain.model.CreditCard;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Davis on 17/3/15.
 */
public interface CreditCardRepository extends JpaRepository<CreditCard, String> {
  /**
   * Gets credit cards by customer id.
   *
   * @param customerId the customer id
   * @return the credit cards by customer id
   */
  List<CreditCard> getCreditCardsByCustomerIdOrderByCreatedAt(String customerId);
}
