package com.example.rabbit_demo.fanout_exchange.component;

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
  
  public void sendNotification (String message)
  {
    rabbitTemplate.convertAndSend ("fanout-exchange", message);
    log.info ("Sent Notification to FanoutExchange: {}", message);
  }
}
