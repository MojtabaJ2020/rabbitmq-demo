package com.example.rabbit_demo.helloworld.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService
{
  private static final Logger log= LoggerFactory.getLogger (NotificationService.class);
  
  public void notifyUser(String message)
  {
    log.info ("Notification sent: {}",message);
  }
}
