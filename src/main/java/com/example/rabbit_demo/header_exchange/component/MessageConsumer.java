package com.example.rabbit_demo.header_exchange.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer
{
  private static final Logger log = LoggerFactory.getLogger (MessageConsumer.class);
  @RabbitListener(queues = "order-queue")

  @RabbitListener(queues = "order-queue")
  public void handleOrderEvent(String message) {
    log.info ("Order Service: Processing order event: {}" , message);
  }
  
  @RabbitListener(queues = "notification-queue")
  public void handleNotificationEvent(String message) {
    log.info ("Notification Service: Processing notification: {}" , message);
  }
  
}
