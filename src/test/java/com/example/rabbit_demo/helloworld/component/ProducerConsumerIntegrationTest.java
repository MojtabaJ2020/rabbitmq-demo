package com.example.rabbit_demo.helloworld.component;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.verify;

@SpringBootTest
@Testcontainers
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ProducerConsumerIntegrationTest {
  
  @Container
  static RabbitMQContainer rabbitMQ = new RabbitMQContainer("rabbitmq:3-management").withReuse (true);
  
  @Autowired
  private RabbitTemplate rabbitTemplate;
  
  @Autowired
  private MessageProducer producer;
  
  @SpyBean
  private NotificationService notificationService;
  
  @DynamicPropertySource
  static void configureRabbitMQ(DynamicPropertyRegistry registry) {
    registry.add("spring.rabbitmq.host", rabbitMQ::getHost);
    registry.add("spring.rabbitmq.port", rabbitMQ::getAmqpPort);
    registry.add("spring.rabbitmq.connection-timeout", () -> "60000"); // 60 seconds
  }
  
  @BeforeAll
  static void startContainer() {
    rabbitMQ.start();
  }
  
  @BeforeEach
  void setUp() {
    try {
      rabbitTemplate.execute(channel -> {
        channel.queueDeclare("hello-queue", true, false, false, null); // Declare queue if it doesn't exist
        channel.queuePurge("hello-queue");
        return null;
      });
    } catch (Exception e) {
      System.err.println("Queue purge failed: " + e.getMessage());
    }
  }
  
  @AfterEach
  void tearDown() {
    try {
      rabbitTemplate.execute(channel -> {
        channel.queuePurge("hello-queue");
        return null;
      });
    } catch (Exception e) {
      System.err.println("Queue purge failed: " + e.getMessage());
    }
  }
  
  @Test
  void testMessageFlowFromProducerToConsumer() {
    String message = "Hello, Integration Test!";
    
    System.out.println("RabbitMQ Host: " + rabbitMQ.getHost());
    System.out.println("RabbitMQ Port: " + rabbitMQ.getAmqpPort());
    
    producer.sendMessage(message);
    
    // Wait for the consumer to process it (async)
    Awaitility.await()
              .atMost(30, TimeUnit.SECONDS)
              .untilAsserted(() -> {
                verify(notificationService).notifyUser(message);
              });
  }
}