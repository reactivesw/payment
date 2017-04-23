package io.reactivesw.payment.application.service;

import com.braintreegateway.exceptions.BraintreeException;
import io.reactivesw.message.client.consumer.Consumer;
import io.reactivesw.message.client.core.DefaultConsumerFactory;
import io.reactivesw.message.client.core.Message;
import io.reactivesw.message.client.utils.serializer.JsonDeserializer;
import io.reactivesw.payment.application.model.OrderCreationEvent;
import io.reactivesw.payment.infrastructure.configuration.EventConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Order creation consumer.
 */
@Service
public class OrderCreationConsumer {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(OrderCreationConsumer.class);

  /**
   * Message consumer.
   */
  private transient Consumer consumer;

  /**
   * OrderCreationEvent handler.
   */
  @Autowired
  private transient OrderCreationEventHandler eventHandler;

  /**
   * Json deserializer.
   */
  private transient JsonDeserializer<OrderCreationEvent> jsonDeserializer =
      new JsonDeserializer<>(OrderCreationEvent.class);

  /**
   * Instantiates a new Order creation consumer.
   *
   * @param config the config
   */
  public OrderCreationConsumer(EventConfig config) {
    consumer = DefaultConsumerFactory.createGoogleConsumer(config.getGoogleCloudProjectId(),
        config.getOrderCreatedSubscriber());
  }

  /**
   * Executor.
   * Executes each 200 ms.
   */
  @Scheduled(fixedRate = 200)
  public void executor() {

    // Pull messages todo this should be configurable.
    List<Message> events = consumer.pullMessages(10);
    if (!events.isEmpty()) {
      LOG.debug("Handle events, size: {}.", events.size());
      events.stream().forEach(
          message -> {
            OrderCreationEvent event = jsonDeserializer.deserialize(message.getData().toString());
            try {
              eventHandler.handleOrderCreation(event);
              consumer.acknowledgeMessage(message.getExternalId());
            } catch (BraintreeException exception) {
              LOG.debug("Something wrong and pay failed.", exception);
            }
          }
      );
    }
  }
}