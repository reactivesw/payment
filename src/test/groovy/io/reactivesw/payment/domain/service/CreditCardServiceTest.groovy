package io.reactivesw.payment.domain.service

import com.braintreegateway.BraintreeGateway
import com.braintreegateway.CreditCardGateway
import com.braintreegateway.Customer
import com.braintreegateway.CustomerGateway
import com.braintreegateway.Result
import com.google.common.collect.Lists
import io.reactivesw.exception.NotExistException
import io.reactivesw.payment.application.model.CreditCardDraft
import io.reactivesw.payment.domain.model.CreditCard
import io.reactivesw.payment.domain.model.CustomerRelationship
import io.reactivesw.payment.infrastructure.repository.CreditCardRepository
import io.reactivesw.payment.infrastructure.repository.CustomerRelationshipRepository
import spock.lang.Specification

/**
 * Created by Davis on 17/3/30.
 */
class CreditCardServiceTest extends Specification {
    BraintreeGateway gateway = Mock()
    CreditCardRepository creditCardRepository = Mock()
    CustomerRelationshipRepository customerRelationshipRepository = Mock()

    CreditCardService service = new CreditCardService(gateway, creditCardRepository, customerRelationshipRepository)

    def customerId = "customer-111"
    def creditCard = new CreditCard()
    def creditCardId = "credit-card-111"
    def paymentToken = "payment-token-111"
    def commercial = "commercial-111"
    def braintreeCustomerId = "braintree-customer-111"
    def last4 = "1111"
    def bin = "411111"

    def creditCardDraft = new CreditCardDraft()

    def setup() {
        creditCardDraft.cardholderName = "cardholder-name-111"
        creditCardDraft.commercial = commercial
        creditCardDraft.cvv = "123"
        creditCardDraft.expirationMonth = "09"
        creditCardDraft.expirationYear = "2019"
        creditCardDraft.number = "4111111111111111"

        creditCard.bin = bin
        creditCard.last4 = last4
        creditCard.commercial = commercial
        creditCard.id = creditCardId
        creditCard.token = paymentToken
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

    def "Test 3.1: add credit card"() {
        given:
        com.braintreegateway.CreditCard braintreeCard = Mock()
        braintreeCard.getLast4() >> last4
        braintreeCard.getBin() >> bin
        braintreeCard.getCommercial() >> com.braintreegateway.CreditCard.Commercial.YES
        braintreeCard.getToken() >> paymentToken

        Customer customer = Mock()
        customer.getId() >> braintreeCustomerId
        customer.getCreditCards() >> Lists.newArrayList(braintreeCard)
        Result<Customer> braintreeCustomer = new Result()
        braintreeCustomer.target = customer

        CustomerGateway customerGateway = Mock()
        gateway.customer() >> customerGateway
        customerGateway.create(_) >> braintreeCustomer

        creditCardRepository.save(_) >> creditCard

        when:
        def result = service.addCreditCard(customerId, creditCardDraft)

        then:
        result != null
    }

    def "Test 3.2: add credit card first time"() {
        given:
        com.braintreegateway.CreditCard btCreditCard = Mock()
        btCreditCard.getBin() >> bin
        btCreditCard.getLast4() >> last4
        btCreditCard.getToken() >> paymentToken
        btCreditCard.getCommercial() >> com.braintreegateway.CreditCard.Commercial.YES
        btCreditCard.getCreatedAt() >> Calendar.getInstance()

        Result<com.braintreegateway.CreditCard> braintreeCreditCard = new Result()
        braintreeCreditCard.target = btCreditCard

        CreditCardGateway creditCardGateway = Mock()
        gateway.creditCard() >> creditCardGateway
        creditCardGateway.create(_) >> braintreeCreditCard

        customerRelationshipRepository.findByCustomerId(_) >> new CustomerRelationship(externalId: braintreeCustomerId)
        customerRelationshipRepository.save(_) >> null

        creditCardRepository.save(_) >> creditCard

        when:
        def result = service.addCreditCard(customerId, creditCardDraft)

        then:
        result != null
    }

}
