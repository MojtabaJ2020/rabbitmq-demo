package com.example.rabbit_demo.dead_exchange.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.random.RandomGenerator;

@Component public class MessageConsumer
{
  private static final Logger log = LoggerFactory.getLogger (MessageConsumer.class);
  
  @RabbitListener (queues = "dead-letter-queue") public void handleDeadLetter (String message)
  {
    log.info ("Dead-letter queue: Received failed message - {}", message);
  }
  
  @RabbitListener (queues = "main-queue") public void handleEvent (String message)
  {
    log.info ("Processing message: {}", message);
    
    // Simulate a processing error
    if (RandomGenerator.getDefault ().nextBoolean ())
    {
      log.info ("Failed to process message: {}", message);
      throw new RuntimeException ("Simulated processing error");
    }
    
    log.info ("Message processed successfully: {}", message);
  }
  
}
