package com.example.rabbit_demo.topic_exchange.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig
{
  @Bean
  public Queue orderQueue() {
    return new Queue("order-queue");
  }
  
  @Bean
  public Queue userQueue() {
    return new Queue("user-queue");
  }
  
  @Bean
  public Queue logQueue() {
    return new Queue("log-queue");
  }
  
  @Bean
  public TopicExchange topicExchange()
  {
    return new TopicExchange ("topic-exchange");
  }
  
  
  //* => Matches exactly one word in the routing key (e.g., order.* matches order.created but not order.created.paid).
  @Bean
  public Binding orderBinding(TopicExchange topicExchange) {
    return BindingBuilder.bind(orderQueue ())
                         .to(topicExchange)
                         .with("order.*");
  }
  
  //# => Matches zero or more words (e.g., user.# matches user, user.profile, user.profile.updated).
  @Bean
  public Binding userBinding(TopicExchange topicExchange) {
    return BindingBuilder.bind(userQueue ())
                         .to(topicExchange)
                         .with("user.#"); // Matches user, user.profile, user.profile.updated, etc.
  }
  
  @Bean
  public Binding logBinding(TopicExchange topicExchange) {
    return BindingBuilder.bind(logQueue ())
                         .to(topicExchange)
                         .with("*.log");
  }
 }
