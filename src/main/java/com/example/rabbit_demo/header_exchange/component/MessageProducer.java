package com.example.rabbit_demo.header_exchange.component;

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
  
  public void sendEvent(String message, String routingKey) {
    rabbitTemplate.convertAndSend("topic-exchange", routingKey, message);
    log.info ("Sent Event to TopicExchange with a routing key {}: {}", routingKey, message);
  }
  
  public void sendOrderEvent(String message) {
    MessageProperties properties = new MessageProperties();
    properties.setHeader("type", "order");
    Message rabbitMessage = new Message(message.getBytes(), properties);
    rabbitTemplate.send("headers-exchange", "", rabbitMessage);
    log.info ("Sent Order Event to HeadersExchange: {}", rabbitMessage);
    
  }
  
  public void sendHighPriorityNotification(String message) {
    MessageProperties properties = new MessageProperties();
    properties.setHeader("priority", "high");
    Message rabbitMessage = new Message(message.getBytes(), properties);
    rabbitTemplate.send("headers-exchange", "", rabbitMessage);
    log.info ("Sent high priority notification to HeadersExchange: {}", rabbitMessage);
    
  }
  
  public void sendLowPriorityNotification(String message) {
    MessageProperties properties = new MessageProperties();
    properties.setHeader("priority", "low"); // Add header "priority=low"
    Message rabbitMessage = new Message(message.getBytes(), properties);
    rabbitTemplate.send("headers-exchange", "", rabbitMessage);
    log.info ("Sent low priority notification to HeadersExchange: {}", rabbitMessage);
    
  }
}
