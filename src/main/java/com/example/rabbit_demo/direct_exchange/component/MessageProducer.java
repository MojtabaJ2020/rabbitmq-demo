package com.example.rabbit_demo.direct_exchange.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer
{
  private static final Logger log = LoggerFactory.getLogger (MessageProducer.class);
  private final RabbitTemplate rabbitTemplate;
  
  public MessageProducer (RabbitTemplate rabbitTemplate)
  {
    this.rabbitTemplate = rabbitTemplate;
  }
  
  public void sendError(String message)
  {
    rabbitTemplate.convertAndSend ("direct-exchange","error",message);
    log.info ("Sent Error Message: {}",message);
  }
  public void sendInfo(String message)
  {
    rabbitTemplate.convertAndSend ("direct-exchange","info",message);
    log.info ("Sent Info Message: {}",message);
  }
}
