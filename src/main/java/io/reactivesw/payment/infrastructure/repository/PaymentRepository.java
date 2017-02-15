package io.reactivesw.payment.infrastructure.repository;

import io.reactivesw.payment.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Davis on 17/2/4.
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
}
