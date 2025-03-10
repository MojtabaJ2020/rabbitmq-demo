package com.example.rabbit_demo.topic_exchange.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component public class MessageProducer
{
  private static final Logger log = LoggerFactory.getLogger (MessageProducer.class);
  private final RabbitTemplate rabbitTemplate;
  
  public MessageProducer (RabbitTemplate rabbitTemplate)
  {
    this.rabbitTemplate = rabbitTemplate;
  }
  
  public void sendEvent(String message, String routingKey) {
    rabbitTemplate.convertAndSend("topic-exchange", routingKey, message);
    log.info ("Sent Event to TopicExchange with a routing key {}: {}", routingKey, message);
  }
}
