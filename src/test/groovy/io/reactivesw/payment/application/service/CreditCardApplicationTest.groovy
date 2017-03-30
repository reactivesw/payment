package io.reactivesw.payment.application.service

import com.braintreegateway.*
import com.google.common.collect.Lists
import io.reactivesw.payment.application.model.CreditCardDraft
import io.reactivesw.payment.domain.model.CreditCard
import io.reactivesw.payment.domain.service.CreditCardService
import io.reactivesw.payment.domain.service.CustomerRelationshipService
import spock.lang.Specification

/**
 * Created by Davis on 17/3/30.
 */
class CreditCardApplicationTest extends Specification {
    BraintreeGateway gateway = Mock()
    CreditCardService creditCardService = Mock()
    CustomerRelationshipService relationshipService = Mock()

    CreditCardApplication application = new CreditCardApplication(gateway,  relationshipService, creditCardService)

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
        creditCardDraft.customerId = customerId

        creditCard.bin = bin
        creditCard.last4 = last4
        creditCard.commercial = commercial
        creditCard.id = creditCardId
        creditCard.token = paymentToken
    }


    def "Test 1.1: add credit card first time"() {
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

        creditCardService.saveCreditCard(_) >> creditCard

        when:
        def result = application.addCreditCard(creditCardDraft)

        then:
        result != null
    }

    def "Test 1.2: add credit card"() {
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

        relationshipService.getBrainTreeId(_) >> braintreeCustomerId
        relationshipService.saveRelationship(_) >> null

        creditCardService.saveCreditCard(_) >> creditCard

        when:
        def result = application.addCreditCard(creditCardDraft)

        then:
        result != null
    }

}
