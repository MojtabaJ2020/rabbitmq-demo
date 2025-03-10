package com.example.rabbit_demo.helloworld.component;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.verify;

@ExtendWith (MockitoExtension.class)
public class MessageProducerTest
{
  
  @Mock
  private RabbitTemplate rabbitTemplate;
  
  @InjectMocks
  private MessageProducer messageProducer;

  @Test
  void testSendMessage()
  {
    String message = "Test Message!";
    messageProducer.sendMessage (message);
    verify(rabbitTemplate).convertAndSend ("hello-queue",message);
  }

}
