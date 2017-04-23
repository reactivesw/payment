package io.reactivesw.payment.application.service

import com.google.common.collect.Lists
import io.reactivesw.exception.ParametersException
import io.reactivesw.model.Money
import io.reactivesw.payment.application.model.InventoryRequest
import io.reactivesw.payment.application.model.OrderCreationEvent
import io.reactivesw.payment.application.model.PaymentView
import io.reactivesw.payment.domain.service.EventMessageService
import io.reactivesw.payment.domain.service.PayedOrderService
import spock.lang.Specification

/**
 * Test for OrderCreationEventHandler.
 */
class OrderCreationEventHandlerTest extends Specification {
    PaymentApplication paymentApplication = Mock()
    PayedOrderService payedOrderService = Mock()
    EventMessageService eventMessageService = Mock()

    OrderCreationEventHandler handler = new OrderCreationEventHandler(eventMessageService, payedOrderService, paymentApplication)

    def orderCreationEvent = new OrderCreationEvent()
    def orderId = "orderId"
    def paymentMethodId = "paymentMethodId"
    Money totalAmount = new Money(centAmount: 1000, currencyCode: "USD")
    def paymentId = "paymentId"
    def paymentView = new PaymentView(id: paymentId)

    def setup() {
        orderCreationEvent.orderId = orderId
        orderCreationEvent.totalAmount = totalAmount
        orderCreationEvent.paymentMethodId = paymentMethodId
    }

    def "Test1.1: handle Order Creation Event"() {
        given:
        paymentApplication.checkout(_) >> paymentView
        payedOrderService.isPayedOrder(_) >> false

        when:
        handler.handleOrderCreation(orderCreationEvent)

        then:
        true
    }

    def "Test1.2: handle Order Creation Event and order id is exist"() {
        given:
        payedOrderService.isPayedOrder(_) >> true

        when:
        handler.handleOrderCreation(orderCreationEvent)

        then:
        true
    }
//
//    def "Test1.3: handle Order Creation Event and sku quantity is not enough"() {
//        given:
//        payedOrderService.isPayedOrder(_) >> false
//
//        when:
//        handler.handleOrderCreation(orderCreationEvent)
//
//        then:
//        true
//    }
}
