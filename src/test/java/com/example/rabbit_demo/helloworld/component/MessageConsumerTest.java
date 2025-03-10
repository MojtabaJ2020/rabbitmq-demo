package com.example.rabbit_demo.helloworld.component;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith (MockitoExtension.class)
public class MessageConsumerTest
{
  
  @Mock
  private NotificationService notificationService;
  @InjectMocks
  private MessageConsumer messageConsumer;
  
  @Test
  void testReceiveMessageCallsNotificationService()
  {
    String message = "Test Message!";
    messageConsumer.receiveMessage (message);
    verify(notificationService).notifyUser (message);
  }
  
}
