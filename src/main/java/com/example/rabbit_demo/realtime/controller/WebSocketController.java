package com.example.rabbit_demo.realtime.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
  
  // Handle messages sent to "/app/notify"
  @MessageMapping("/notify")
  public void handleClientNotification(String message) {
    System.out.println("Received from client: " + message);
  }
}