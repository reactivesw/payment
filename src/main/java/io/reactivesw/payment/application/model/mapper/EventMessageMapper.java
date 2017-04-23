package io.reactivesw.payment.application.model.mapper;

import io.reactivesw.message.client.utils.serializer.JsonSerializer;
import io.reactivesw.payment.application.model.PaymentEvent;
import io.reactivesw.payment.domain.model.EventMessage;
import io.reactivesw.payment.infrastructure.configuration.EventConfig;
import io.reactivesw.payment.infrastructure.enums.EventStatus;

/**
 * EventMessage Mapper.
 */
public final class EventMessageMapper {

  /**
   * Json serializer.
   */
  private static JsonSerializer jsonSerializer = new JsonSerializer();

  /**
   * Instantiates a new Event message mapper.
   */
  private EventMessageMapper() {
  }

  /**
   * Build event message.
   *
   * @param orderId the order id
   * @param status  the status
   * @return the event message
   */
  public static EventMessage build(String orderId, boolean status, String paymentId,
                                   EventConfig eventConfig) {
    EventMessage message = new EventMessage();

    // TODO: 17/4/17 change version code to config
    message.setVersion(eventConfig.getPaymentPayedVersion());
    message.setCreatedAt(System.currentTimeMillis());
    message.setStatus(EventStatus.CREATED);
    message.setTopic(eventConfig.getPaymentPayedName());
    message.setData(jsonSerializer.serialize(PaymentEvent.build(orderId, status, paymentId)));

    return message;
  }
}
