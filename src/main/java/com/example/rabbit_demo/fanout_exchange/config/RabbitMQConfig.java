package com.example.rabbit_demo.fanout_exchange.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig
{
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
  
  @Bean
  public FanoutExchange fanoutExchange()
  {
    return new FanoutExchange ("fanout-exchange");
  }
  
  @Bean
  public Binding emailBinding(FanoutExchange fanoutExchange)
  {
    
    return BindingBuilder.bind (emailQueue ()).to (fanoutExchange);
  }
   @Bean
  public Binding smsBinding(FanoutExchange fanoutExchange)
  {
    
    return BindingBuilder.bind (smsQueue ()).to (fanoutExchange);
  }
   @Bean
  public Binding loggingBinding(FanoutExchange fanoutExchange)
  {
    return BindingBuilder.bind (loggingQueue ()).to (fanoutExchange);
  }
  
 }
