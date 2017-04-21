package io.reactivesw.payment.domain.service

import io.reactivesw.payment.application.model.PaymentView
import io.reactivesw.payment.domain.model.PayedOrder
import io.reactivesw.payment.infrastructure.repository.PayedOrderRepository
import spock.lang.Specification

/**
 * Test for PayedOrderService.
 */
class PayedOrderServiceTest extends Specification {
    PayedOrderRepository repository = Mock()
    PayedOrderService service = new PayedOrderService(repository)

    def orderId = "orderId"
    def paymentView = new PaymentView()
    def payedOrder = new PayedOrder()

    def setup() {
        payedOrder.id = "payedOrderId"
        paymentView.id = "paymentId"
    }

    def "Test1: save payed order"() {
        given:
        repository.save(_) >> payedOrder

        when:
        service.savePayedOrder(orderId, paymentView)

        then:
        true
    }

    def "Test2.1: get payed order"() {
        given:
        payedOrder.orderId = "testId"
        repository.findOne(_) >> payedOrder

        when:
        def result = service.isPayedOrder(orderId)

        then:
        result == false
    }

    def "Test2.2: payed order not exist"() {
        given:
        repository.findOne(_) >> null

        when:
        def result = service.isPayedOrder(orderId)

        then:
        result == true
    }
}
