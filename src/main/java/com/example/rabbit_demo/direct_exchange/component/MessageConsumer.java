package com.example.rabbit_demo.direct_exchange.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer
{
  private static final Logger log = LoggerFactory.getLogger (MessageConsumer.class);
  @RabbitListener(queues = "error-queue")
  public void consumeError(String message)
  {
    log.info ("Received an error message: {}",message);
  }
  
  @RabbitListener(queues = "info-queue")
  public void consumeInfo(String message)
  {
    log.info ("Received an info message: {}",message);
  }
}
