package io.reactivesw.payment.domain.service;

import io.reactivesw.payment.domain.model.CustomerRelationship;
import io.reactivesw.payment.infrastructure.repository.CustomerRelationshipRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Davis on 17/3/30.
 */
@Service
public class CustomerRelationshipService {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CustomerRelationshipService.class);

  /**
   * CustomerRelationshipRepository.
   */
  private transient CustomerRelationshipRepository repository;

  /**
   * Instantiates a new Customer relationship service.
   *
   * @param repository the customer relationship repository
   */
  @Autowired
  public CustomerRelationshipService(CustomerRelationshipRepository repository) {
    this.repository = repository;
  }

  /**
   * get braintree id by customer id.
   *
   * @param customerId the customer id
   * @return braintree id
   */
  public String getBrainTreeId(String customerId) {
    LOG.debug("enter. customer id is : {}.", customerId);

    String brainttreeId = null;
    CustomerRelationship result = repository.findByCustomerId(customerId);
    if (result == null) {
      LOG.debug("can not find relationship by customer id: {}.", customerId);
    } else {
      brainttreeId = result.getExternalId();
      LOG.debug("find braintree id: {} by customer id: {}.", brainttreeId, customerId);
    }

    LOG.debug("exit.");
    return brainttreeId;
  }

  /**
   * Save relationship customer relationship.
   *
   * @param entity the entity
   * @return the customer relationship
   */
  public CustomerRelationship saveRelationship(CustomerRelationship entity) {
    LOG.debug("enter. CustomerRelationship is: {}.", entity);

    CustomerRelationship savedEntity = repository.save(entity);

    LOG.debug("exit. saved relationship is: {}.", savedEntity);

    return savedEntity;
  }
}
