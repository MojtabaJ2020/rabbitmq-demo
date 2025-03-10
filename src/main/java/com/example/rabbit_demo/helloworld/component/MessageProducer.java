package com.example.rabbit_demo.helloworld.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer
{
  private static final Logger log= LoggerFactory.getLogger (MessageProducer.class);
  
  private final RabbitTemplate rabbitTemplate;
  
  public MessageProducer (RabbitTemplate rabbitTemplate)
  {
    this.rabbitTemplate = rabbitTemplate;
  }
  
  public void sendMessage(String message)
  {
    rabbitTemplate.convertAndSend ("hello-queue", message);
    log.info ("Send message: {}",message);
  }
}
