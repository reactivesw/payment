package io.reactivesw.payment.infrastructure.repository;

import io.reactivesw.payment.domain.model.CreditCard;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Davis on 17/3/15.
 */
public interface CreditCardRepository extends JpaRepository<CreditCard, String> {
  List<CreditCard> getCreditCardsByCustomerId(String customerId);
}
