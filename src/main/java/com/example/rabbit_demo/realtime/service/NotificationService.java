package com.example.rabbit_demo.realtime.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/*
The NotificationService acts as a message bridge between two layers:

RabbitMQ (Backend Messaging): Handles message queuing, persistence, and delivery between backend services.

WebSocket (Frontend Real-Time Updates): Manages bidirectional communication with clients (e.g., browsers, mobile apps).
 */


@Service
public class NotificationService {
  
  //A Spring helper class for sending messages to WebSocket clients.
  private final SimpMessagingTemplate messagingTemplate;
  
  public NotificationService(SimpMessagingTemplate messagingTemplate) {
    this.messagingTemplate = messagingTemplate;
  }
  
  // Listen to RabbitMQ messages and forward to WebSocket clients
  @RabbitListener(queues = "websocket-queue")
  public void handleNotification(String message) {
    // Send the message to all clients subscribed to /topic/notifications
    messagingTemplate.convertAndSend("/topic/notifications", message);
  }
}
