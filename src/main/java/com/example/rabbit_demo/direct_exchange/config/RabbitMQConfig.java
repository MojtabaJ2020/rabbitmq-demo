package com.example.rabbit_demo.direct_exchange.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig
{
  @Bean
  public Queue errorQueue()
  {
    return new Queue ("error-queue");
  }
  
  @Bean
  public Queue infoQueue()
  {
    return new Queue ("info-queue");
  }
  
  @Bean
  public DirectExchange directExchange()
  {
    return new DirectExchange ("direct-exchange");
  }
  
  @Bean
  public Binding errorBinding(DirectExchange directExchange)
  {
    return BindingBuilder.bind (errorQueue ()).to (directExchange).with ("error");
  }
  
  @Bean
  public Binding infoBinding(DirectExchange directExchange)
  {
    return BindingBuilder.bind (infoQueue ()).to (directExchange).with ("info");
  }
}
