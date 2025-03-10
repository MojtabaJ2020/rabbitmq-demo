package com.example.rabbit_demo.fanout_exchange.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer
{
  private static final Logger log = LoggerFactory.getLogger (MessageConsumer.class);
  @RabbitListener(queues = "email-queue")
  public void handleEmail(String message) {
    log.info ("Email Handler received a notification: {}" , message);
  }
  
  @RabbitListener(queues = "sms-queue")
  public void handleSMS(String message) {
    log.info ("SMS Handler received a notification: {}" , message);
  }
  
  @RabbitListener(queues = "logging-queue")
  public void handleLogging(String message) {
    log.info ("Logging Handler received a notification: {}" , message);
  }
}
