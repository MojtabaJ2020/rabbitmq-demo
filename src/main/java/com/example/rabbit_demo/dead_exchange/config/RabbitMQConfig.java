package com.example.rabbit_demo.dead_exchange.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig
{
  @Bean
  public DirectExchange deadLetterExchange() {
    return new DirectExchange("dead-letter-exchange");
  }
  
  // Dead-letter queue
  @Bean
  public Queue deadLetterQueue() {
    return new Queue("dead-letter-queue");
  }
  
  // Binding between dead-letter exchange and queue
  @Bean
  public Binding deadLetterBinding() {
    return BindingBuilder.bind(deadLetterQueue())
                         .to(deadLetterExchange())
                         .with("dead-letter-routing-key");
  }
  
  // Main queue with dead-letter settings
  @Bean
  public Queue mainQueue() {
    Map<String, Object> args = new HashMap<>();
    args.put("x-dead-letter-exchange", "dead-letter-exchange"); // DLX
    args.put("x-dead-letter-routing-key", "dead-letter-routing-key"); // DLQ routing key
    args.put("x-message-ttl", 10000); // Message TTL (10 seconds)
    return new Queue("main-queue", true, false, false, args);
  }
  
  @Bean
  public DirectExchange mainExchange() {
    return new DirectExchange("main-exchange");
  }
  
  @Bean
  public Binding mainBinding() {
    return BindingBuilder.bind(mainQueue())
                         .to(mainExchange())
                         .with("main-routing-key");
  }
 }
