package io.reactivesw.payment.domain.service

import com.braintreegateway.Result
import com.braintreegateway.Transaction
import io.reactivesw.model.Money
import io.reactivesw.payment.domain.model.Payment
import io.reactivesw.payment.infrastructure.repository.PaymentRepository
import spock.lang.Specification

import java.time.ZoneOffset

/**
 * Created by Davis on 17/3/30.
 */
class PaymentServiceTest extends Specification {
    PaymentRepository repository = Mock()
    PaymentService service = new PaymentService(repository)

    Payment payment = new Payment()
    def customerId = "customer-111"

    def setup() {
        payment.customerId = customerId

    }

    def "Test 1: save payment"() {
        given:
        def amount = new Money(currencyCode: "USD", centAmount: 12300)
        Result<Transaction> transactionResult = new Result<>()
        Transaction transaction = Mock()
        transactionResult.target = transaction
        repository.save(_) >> payment
        transaction.getCurrencyIsoCode() >> "USD"
        transaction.getAmount() >> new BigDecimal("12.3")
        transaction.getCreatedAt() >> Calendar.getInstance()

        when:
        def result = service.savePayment(customerId, amount, transactionResult)

        then:
        result != null
        result.customerId == customerId
    }
}
