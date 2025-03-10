package com.example.rabbit_demo.header_exchange.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig
{
  @Bean
  public Queue orderQueue() {
    return new Queue("order-queue");
  }

  @Bean
  public Queue notificationQueue() {
    return new Queue("notification-queue");
  }

  @Bean
  public HeadersExchange headerExchange()
  {
    return new HeadersExchange ("headers-exchange");
  }


  @Bean
  public Binding orderBinding(HeadersExchange headersExchange, Queue orderQueue) {
    Map <String, Object> headers = new HashMap <> ();
    headers.put("type", "order"); // Match messages with header "type=order"
    headers.put("x-match", "all"); // All headers must match
    return BindingBuilder.bind(orderQueue)
                         .to(headersExchange)
                         .whereAll(headers).match();
  }

  @Bean
  public Binding notificationBinding(HeadersExchange headersExchange, Queue notificationQueue) {
    Map<String, Object> headers = new HashMap<>();
    headers.put("priority", "high"); // Match messages with header "priority=high"
    headers.put("x-match", "any"); // Any header can match
    return BindingBuilder.bind(notificationQueue)
                         .to(headersExchange)
                         .whereAny(headers).match();
  }
 }
