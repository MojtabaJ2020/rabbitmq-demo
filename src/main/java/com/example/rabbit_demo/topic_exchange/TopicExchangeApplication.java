package com.example.rabbit_demo.topic_exchange;

import com.example.rabbit_demo.topic_exchange.component.MessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TopicExchangeApplication
{
  public static void main (String []args)
  {
    SpringApplication.run (TopicExchangeApplication.class);
  }
  
  @Bean
  public CommandLineRunner runner(MessageProducer producer)
  {
    return args -> {
      producer.sendEvent ("This is an order creation event","order.created");
      producer.sendEvent ("This is an user update event","user.profile.updated");
      producer.sendEvent ("This is a log event","app.log");
      producer.sendEvent ("This is an other event","other.app.log");
    };
  }
}
