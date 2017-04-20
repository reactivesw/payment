package io.reactivesw.payment.infrastructure.repository;

import io.reactivesw.payment.domain.model.PayedOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * The interface Payed order repository.
 */
public interface PayedOrderRepository extends JpaRepository<PayedOrder, String>,
    JpaSpecificationExecutor<PayedOrder> {

}
