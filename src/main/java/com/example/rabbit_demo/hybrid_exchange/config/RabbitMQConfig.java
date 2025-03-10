package com.example.rabbit_demo.hybrid_exchange.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig
{
  // Direct exchange for order placement
  @Bean
  public DirectExchange orderExchange() {
    return new DirectExchange("order-exchange");
  }
  
  // Queue for order processing
  @Bean
  public Queue orderQueue() {
    return new Queue("order-queue");
  }
  
  // Binding: order-queue → order-exchange (routing key = "order.placed")
  @Bean
  public Binding orderBinding() {
    return BindingBuilder.bind(orderQueue())
                         .to(orderExchange())
                         .with("order.placed");
  }
  
  // Topic exchange for order status updates
  @Bean
  public TopicExchange statusExchange() {
    return new TopicExchange ("status-exchange");
  }
  
  // Queue for inventory updates
  @Bean
  public Queue inventoryQueue() {
    return new Queue("inventory-queue");
  }
  
  // Queue for shipping updates
  @Bean
  public Queue shippingQueue() {
    return new Queue("shipping-queue");
  }
  
  // Bindings for status updates
  @Bean
  public Binding inventoryBinding() {
    return BindingBuilder.bind(inventoryQueue())
                         .to(statusExchange())
                         .with("order.*");
  }
  
  @Bean
  public Binding shippingBinding() {
    return BindingBuilder.bind(shippingQueue())
                         .to(statusExchange())
                         .with("order.shipped");
  }
  
  // Fanout exchange for notifications
  @Bean
  public FanoutExchange notificationExchange() {
    return new FanoutExchange("notification-exchange");
  }
  
  // Queues for notifications (email, SMS, logging)
  @Bean
  public Queue emailQueue() {
    return new Queue("email-queue");
  }
  
  @Bean
  public Queue smsQueue() {
    return new Queue("sms-queue");
  }
  
  @Bean
  public Queue loggingQueue() {
    return new Queue("logging-queue");
  }
  
  // Bindings for fanout exchange
  @Bean
  public Binding emailBinding() {
    return BindingBuilder.bind(emailQueue()).to(notificationExchange());
  }
  
  @Bean
  public Binding smsBinding() {
    return BindingBuilder.bind(smsQueue()).to(notificationExchange());
  }
  
  @Bean
  public Binding loggingBinding() {
    return BindingBuilder.bind(loggingQueue()).to(notificationExchange());
  }
  
  @Bean
  public Binding statusToNotificationBinding() {
    return BindingBuilder.bind(notificationExchange()) // Fanout exchange
                         .to(statusExchange())          // Topic exchange
                         .with("#"); // Wildcard pattern (matches all routing keys)
  }
  
 }
