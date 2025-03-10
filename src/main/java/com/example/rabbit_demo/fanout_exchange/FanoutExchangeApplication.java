package com.example.rabbit_demo.fanout_exchange;

import com.example.rabbit_demo.fanout_exchange.component.MessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FanoutExchangeApplication
{
  public static void main (String []args)
  {
    SpringApplication.run (FanoutExchangeApplication.class);
  }
  
  @Bean
  public CommandLineRunner runner(MessageProducer producer)
  {
    return args -> {
      producer.sendNotification ("This is a notification!");
    };
  }
}
