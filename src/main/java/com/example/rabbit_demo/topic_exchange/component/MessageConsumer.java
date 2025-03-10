package com.example.rabbit_demo.topic_exchange.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer
{
  private static final Logger log = LoggerFactory.getLogger (MessageConsumer.class);
  @RabbitListener(queues = "order-queue")
  public void handleOrderEvent(String message) {
    log.info ("Order Service: Processing order event: {}" , message);
  }
  
  @RabbitListener(queues = "user-queue")
  public void handleUserEvent(String message) {
    log.info ("User Service: Processing user event: {}" , message);
  }
  
  @RabbitListener(queues = "log-queue")
  public void handleLogEvent(String message) {
    log.info ("Log Service: Processing log event: {}" , message);
  }
}
