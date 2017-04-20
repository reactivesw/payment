package io.reactivesw.payment.infrastructure.repository;

import io.reactivesw.payment.domain.model.PayedOrder;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * The type Reserved order specification.
 */
public final class PayedOrderSpecification {

  /**
   * Instantiates a new Reserved order specification.
   */
  private PayedOrderSpecification() {
  }

  /**
   * Specification for fetch Events.
   *
   * @param orderId the order id
   * @return Specification specification
   */
  public static Specification<PayedOrder> exist(String orderId) {
    return new Specification<PayedOrder>() {
      /**
       * predicate builder.
       */
      public Predicate toPredicate(Root<PayedOrder> root, CriteriaQuery<?> query,
          CriteriaBuilder builder) {
        // Fetch events for two kind of conditions.
        return builder.equal(root.get("orderId"), orderId);
      }
    };
  }
}
