package com.example.rabbit_demo.helloworld.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer
{
  private static final Logger log = LoggerFactory.getLogger (MessageConsumer.class);
  
  private final NotificationService notificationService;
  
  public MessageConsumer (NotificationService notificationService)
  {
    this.notificationService = notificationService;
  }
  
  @RabbitListener (queues = "hello-queue")
  public void receiveMessage (String message)
  {
    log.info ("Received a message: {}", message);
    notificationService.notifyUser (message);
  }
}
