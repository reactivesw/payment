package io.reactivesw.payment.infrastructure.validator

import io.reactivesw.exception.ConflictException
import io.reactivesw.payment.domain.model.CreditCard
import spock.lang.Specification

/**
 * Created by Davis on 17/4/5.
 */
class CreditCardVersionValidatorTest extends Specification {
    CreditCardVersionValidator validator = new CreditCardVersionValidator()

    def "Test 1: version not match and throw ConflictException"() {
        given:
        CreditCard entity = new CreditCard()
        entity.version = 1
        def version = 2

        when:
        CreditCardVersionValidator.validate(entity, version)

        then:
        thrown(ConflictException)
    }

    def "Test 2: version match"() {
        given:
        def version = 2
        CreditCard entity = new CreditCard()
        entity.version = version

        when:
        CreditCardVersionValidator.validate(entity, version)

        then:
        true
    }
}
