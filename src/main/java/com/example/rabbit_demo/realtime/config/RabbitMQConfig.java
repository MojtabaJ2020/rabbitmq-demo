package com.example.rabbit_demo.realtime.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
  
  // Topic exchange for real-time notifications
  @Bean
  public TopicExchange notificationExchange() {
    return new TopicExchange("notification-exchange");
  }
  
  // Queue to receive messages for WebSocket streaming
  @Bean
  public Queue websocketQueue() {
    return new Queue("websocket-queue");
  }
  
  // Bind the queue to the exchange with a routing key pattern
  @Bean
  public Binding websocketBinding() {
    return BindingBuilder.bind(websocketQueue())
                         .to(notificationExchange())
                         .with("notification.#");
  }
}