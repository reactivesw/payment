package io.reactivesw.payment.domain.service

import com.google.common.collect.Lists
import io.reactivesw.payment.domain.model.EventMessage
import io.reactivesw.payment.infrastructure.configuration.EventConfig
import io.reactivesw.payment.infrastructure.enums.EventStatus
import io.reactivesw.payment.infrastructure.repository.EventMessageRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import spock.lang.Specification

/**
 * Test for EventMessageService.
 */
class EventMessageServiceTest extends Specification {
    EventMessageRepository repository = Mock()
    EventConfig eventConfig = new EventConfig(paymentPayedVersion: 1, paymentPayedName: "eventTopicName")
    EventMessageService service = new EventMessageService(repository, eventConfig)

    def categoryId1 = "categoryId1"
    def categoryId2 = "categoryId2"
    List<String> categoryIds = Lists.newArrayList(categoryId1, categoryId2)

    def messageId = "messageId1"
    EventMessage savedMessage = new EventMessage(id: messageId, status: EventStatus.CREATED)


    def "Test1: save event message"() {
        given:
        def orderId = "orderId"
        def status = true
        def paymentId = "paymentId"
        repository.save(_) >> savedMessage

        when:
        service.savePaymentEvent(orderId, status, paymentId)

        then:
        true
    }

    def "Test2.1: get events"() {
        given:
        Page<EventMessage> page = new PageImpl<>(Lists.newArrayList(savedMessage), null, 1)
        repository.findAll(_, _) >> page

        when:
        def result = service.getEvents()

        then:
        result != null
    }

    def "Test2.2: get events and status is not created"() {
        given:
        savedMessage.status = EventStatus.PENDING
        Page<EventMessage> page = new PageImpl<>(Lists.newArrayList(savedMessage), null, 1)
        repository.findAll(_, _) >> page

        when:
        def result = service.getEvents()

        then:
        result != null
    }

    def "Test3: delete events"() {
        when:
        service.deleteEvents(Lists.newArrayList(savedMessage))

        then:
        true
    }
}
