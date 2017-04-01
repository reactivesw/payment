package io.reactivesw.payment.domain.service

import com.google.common.collect.Lists
import io.reactivesw.exception.NotExistException
import io.reactivesw.payment.application.model.CreditCardDraft
import io.reactivesw.payment.application.model.DefaultCardRequest
import io.reactivesw.payment.domain.model.CreditCard
import io.reactivesw.payment.infrastructure.repository.CreditCardRepository
import spock.lang.Specification

/**
 * Created by Davis on 17/3/30.
 */
class CreditCardServiceTest extends Specification {
    CreditCardRepository creditCardRepository = Mock()

    CreditCardService service = new CreditCardService(creditCardRepository)

    def customerId = "customer-111"
    def creditCard = new CreditCard()
    def creditCardId = "credit-card-111"
    def paymentToken = "payment-token-111"
    def commercial = "commercial-111"
    def last4 = "1111"
    def bin = "411111"
    def requestCreditCardId = "credit-card-222"

    def creditCardDraft = new CreditCardDraft()

    def setup() {
        creditCardDraft.cardholderName = "cardholder-name-111"
        creditCardDraft.cvv = "123"
        creditCardDraft.expirationMonth = "09"
        creditCardDraft.expirationYear = "2019"
        creditCardDraft.number = "4111111111111111"

        creditCard.bin = bin
        creditCard.last4 = last4
        creditCard.commercial = commercial
        creditCard.id = creditCardId
        creditCard.token = paymentToken
        creditCard.selected = true
    }

    def "Test 1.1: get credit cards by customer id"() {
        given:
        creditCardRepository.getCreditCardsByCustomerId(_) >> Lists.newArrayList(creditCard)

        when:
        def result = service.getCreditCards(customerId)

        then:
        result != null
    }

    def "Test 1.2: get credit cards by customer id, get null credit card list"() {
        given:
        creditCardRepository.getCreditCardsByCustomerId(_) >> null

        when:
        service.getCreditCards(customerId)

        then:
        thrown(NotExistException)
    }

    def "Test 1.3: get credit cards by customer id, get empty credit card list"() {
        given:
        creditCardRepository.getCreditCardsByCustomerId(_) >> Lists.newArrayList()

        when:
        service.getCreditCards(customerId)

        then:
        thrown(NotExistException)
    }

    def "Test 2.1: get payment token by credit card id"() {
        given:
        creditCardRepository.findOne(_) >> creditCard

        when:
        def result = service.getPaymentToken(creditCardId)

        then:
        result != null
        result == creditCard.token
    }

    def "Test 2.2: get payment token by credit card id, and get null credit card"() {
        given:
        creditCardRepository.findOne(_) >> null

        when:
        def result = service.getPaymentToken(creditCardId)

        then:
        thrown(NotExistException)
    }

    def "Test 3.1: set default credit card"() {
        given:
        def requestCreditCard = creditCard
        requestCreditCard.id = requestCreditCardId
        requestCreditCard.selected = false
        creditCardRepository.getCreditCardsByCustomerId(_) >> Lists.newArrayList(creditCard, requestCreditCard)
        DefaultCardRequest request = new DefaultCardRequest(customerId: customerId, creditCardId: requestCreditCardId)

        when:
        def result = service.setDefaultCreditCard(request)

        then:
        result != null
    }
}
