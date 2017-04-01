package io.reactivesw.payment.domain.service

import io.reactivesw.payment.domain.model.CustomerRelationship
import io.reactivesw.payment.infrastructure.repository.CustomerRelationshipRepository
import spock.lang.Specification

/**
 * Created by Davis on 17/3/30.
 */
class CustomerRelationshipServiceTest extends Specification {
    CustomerRelationshipRepository repository = Mock()
    CustomerRelationshipService service = new CustomerRelationshipService(repository)

    def customerId = "customer-111"
    def braintreeId = "braintree-111"
    def customerRelationship = new CustomerRelationship(customerId: customerId, externalId: braintreeId)

    def "Test 1.1: get braintree id"() {
        given:
        repository.findByCustomerId(_) >> customerRelationship

        when:
        def result = service.getBrainTreeId(customerId)

        then:
        result == braintreeId
    }

    def "Test 1.2: get braintree id and get null entity"() {
        given:
        repository.findByCustomerId(_) >> null

        when:
        def result = service.getBrainTreeId(customerId)

        then:
        result == null
    }

    def "Test 2: save relationship"() {
        given:
        repository.save(_) >> customerRelationship

        when:
        def result = service.saveRelationship(customerRelationship)


        then:
        result == customerRelationship
    }
}
