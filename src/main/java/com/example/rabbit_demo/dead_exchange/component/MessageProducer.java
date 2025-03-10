package com.example.rabbit_demo.dead_exchange.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
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
  
  public void sendEvent(String message) {
    rabbitTemplate.convertAndSend("main-exchange", "main-routing-key", message);
    log.info ("Sent Event to mainExchange with a routing key main-routing-key: {}", message);
    
  }
}
