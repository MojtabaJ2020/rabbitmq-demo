package com.example.rabbit_demo.hybrid_exchange.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component public class NotificationConsumer
{
  private static final Logger log = LoggerFactory.getLogger (NotificationConsumer.class);
  
  @RabbitListener(queues = "email-queue")
  public void sendEmail(String message) {
    log.info ("Email sent: {}" , message);
  }
  
  @RabbitListener(queues = "sms-queue")
  public void sendSMS(String message) {
    log.info ("SMS sent: {}" , message);
  }
  
  @RabbitListener(queues = "logging-queue")
  public void logEvent(String message) {
    log.info ("Log entry: {}" , message);
  }
}
