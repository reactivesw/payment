package io.reactivesw.payment.infrastructure.validator

import com.google.common.collect.Lists
import io.reactivesw.exception.ConflictException
import io.reactivesw.payment.application.model.CreditCardView
import spock.lang.Specification

/**
 * Created by Davis on 17/4/5.
 */
class CreditCardNumberValidatorTest extends Specification {
    CreditCardNumberValidator validator = new CreditCardNumberValidator()

    def "Test 1: number is exist"() {
        given:
        def number = "411111111111111"
        CreditCardView creditCardView = new CreditCardView(bin: "411111", last4: "1111")

        when:
        CreditCardNumberValidator.validate(Lists.newArrayList(creditCardView), number)

        then:
        thrown(ConflictException)
    }

    def "Test 2: number is not exist"() {
        given:
        def number = "111111111111111"
        CreditCardView creditCardView = new CreditCardView(bin: "411111", last4: "1111")

        when:
        CreditCardNumberValidator.validate(Lists.newArrayList(creditCardView), number)

        then:
        true
    }
}
