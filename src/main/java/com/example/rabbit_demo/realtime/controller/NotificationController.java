package com.example.rabbit_demo.realtime.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
  
  private final RabbitTemplate rabbitTemplate;
  
  public NotificationController(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }
  
  @PostMapping("/send-notification")
  public String sendNotification(@RequestParam String message) {
    rabbitTemplate.convertAndSend("notification-exchange", "notification.alerts", message);
    return "Notification sent: " + message;
  }
}
