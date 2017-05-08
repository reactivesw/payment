package io.reactivesw.payment.infrastructure.validator

import io.reactivesw.exception.ParametersException
import io.reactivesw.model.Money
import io.reactivesw.payment.application.model.PayRequest
import spock.lang.Specification

/**
 * Test for PayRequestValidator.
 */
class PayRequestValidatorTest extends Specification {
    PayRequestValidator validator = new PayRequestValidator()

    def "Test1: payrequest is ok"() {
        given:
        def creditCardId = "credit-card-id"
        def amount = new Money()
        amount.setCentAmount(1111)
        amount.setCurrencyCode("USD")
        PayRequest request = new PayRequest(creditCardId: creditCardId, amount: amount)

        when:
        PayRequestValidator.validate(request)

        then:
        true
    }

    def "Test2: payrequest is null"() {
        given:
        PayRequest request = null

        when:
        PayRequestValidator.validate(request)

        then:
        thrown(ParametersException)
    }

    def "Test3: credit card id is blank"() {
        given:
        def amount = new Money()
        amount.setCentAmount(1111)
        amount.setCurrencyCode("USD")
        PayRequest request = new PayRequest(amount: amount)

        when:
        PayRequestValidator.validate(request)

        then:
        thrown(ParametersException)
    }

    def "Test4.1: amount is null"() {
        given:
        def creditCardId = "credit-card-id"
        PayRequest request = new PayRequest(creditCardId: creditCardId)


        when:
        PayRequestValidator.validate(request)

        then:
        thrown(ParametersException)
    }

    def "Test4.2: currency code is null"() {
        given:
        def creditCardId = "credit-card-id"
        def amount = new Money()
        amount.setCentAmount(1111)
        PayRequest request = new PayRequest(creditCardId: creditCardId, amount: amount)

        when:
        PayRequestValidator.validate(request)

        then:
        thrown(ParametersException)
    }

    def "Test4.3: cent is null"() {
        given:
        def creditCardId = "credit-card-id"
        def amount = new Money()
        amount.setCurrencyCode("USD")
        PayRequest request = new PayRequest(creditCardId: creditCardId, amount: amount)

        when:
        PayRequestValidator.validate(request)

        then:
        thrown(ParametersException)
    }

    def "Test5.1: cent is 0"() {
        given:
        def creditCardId = "credit-card-id"
        def amount = new Money()
        amount.setCentAmount(0)
        amount.setCurrencyCode("USD")
        PayRequest request = new PayRequest(creditCardId: creditCardId, amount: amount)

        when:
        PayRequestValidator.validate(request)

        then:
        thrown(ParametersException)
    }

    def "Test5.2: cent is less than 0"() {
        given:
        def creditCardId = "credit-card-id"
        def amount = new Money()
        amount.setCentAmount(-1)
        amount.setCurrencyCode("USD")
        PayRequest request = new PayRequest(creditCardId: creditCardId, amount: amount)

        when:
        PayRequestValidator.validate(request)

        then:
        thrown(ParametersException)
    }
}
