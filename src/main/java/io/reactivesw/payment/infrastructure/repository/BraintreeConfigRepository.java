package io.reactivesw.payment.infrastructure.repository;

import io.reactivesw.payment.domain.model.BraintreeConfig;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BraintreeConfig repository.
 */
public interface BraintreeConfigRepository extends JpaRepository<BraintreeConfig, String> {

}
