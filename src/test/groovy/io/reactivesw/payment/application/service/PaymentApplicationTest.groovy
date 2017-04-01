package io.reactivesw.payment.application.service

import com.braintreegateway.BraintreeGateway
import com.braintreegateway.Result
import com.braintreegateway.TransactionGateway
import com.google.common.collect.Sets
import io.reactivesw.model.Money
import io.reactivesw.payment.application.model.PayRequest
import io.reactivesw.payment.domain.model.Payment
import io.reactivesw.payment.domain.model.value.LocalizedStringValue
import io.reactivesw.payment.domain.model.value.PaymentMethodInfoValue
import io.reactivesw.payment.domain.model.value.PaymentStatusValue
import io.reactivesw.payment.domain.service.CreditCardService
import io.reactivesw.payment.domain.service.PaymentService
import spock.lang.Specification

/**
 * Created by Davis on 17/3/30.
 */
class PaymentApplicationTest extends Specification {
    CreditCardService creditCardService = Mock()
    BraintreeGateway gateway = Mock()
    PaymentService paymentService = Mock()

    PaymentApplication paymentApplication = new PaymentApplication(creditCardService, gateway, paymentService)

    def paymentToken = "payment-token-111"
    def amount = "10000"
    def paymentEntity = new Payment()

    def paymentId = "payment-111"
    def customerId = "customer-111"
    def creditCardId = "credit-card-111"
    def paymentMethodInfoValue = new PaymentMethodInfoValue()

    def paymentStatusValue = new PaymentStatusValue()


    def setup() {
        paymentMethodInfoValue.paymentInterface = "payment-interface-111"
        paymentMethodInfoValue.method = "method-111"
        paymentMethodInfoValue.name = Sets.newHashSet(LocalizedStringValue.build("en", "text"))

        paymentStatusValue.interfaceCode = "interface-code-111"
        paymentStatusValue.interfaceText = "interface-text-111"
        paymentStatusValue.stateId = "state-111"

        paymentEntity.id = paymentId
        paymentEntity.paymentMethodInfo = paymentMethodInfoValue
        paymentEntity.paymentStatus = paymentStatusValue
    }

    def "Test 1.1: checkout by customer id, amount and credit card id"() {
        given:
        creditCardService.getPaymentToken(_) >> paymentToken
        paymentService.savePayment(_, _, _) >> paymentEntity

        Result transactionResult = new Result()
        TransactionGateway transactionGateway = Mock()
        transactionGateway.sale(_) >> transactionResult
        gateway.transaction() >> transactionGateway

        PayRequest request = new PayRequest(customerId: customerId,
                amount: new Money(centAmount: 12300, currencyCode: "USD"),
                creditCardId: creditCardId)

        when:
        def result = paymentApplication.checkout(request)

        then:
        result != null
    }
}
