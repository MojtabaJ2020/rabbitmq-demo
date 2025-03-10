package com.example.rabbit_demo.helloworld.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig
{
  //The default exchange ("") will route messages to this queue using the queue name as the routing key.
  @Bean
  public Queue helloQueue()
  {
    return new Queue ("hello-queue");
  }
}
