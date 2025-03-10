package com.example.rabbit_demo.direct_exchange;

import com.example.rabbit_demo.direct_exchange.component.MessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DirectExchangeApplication
{
  public static void main (String []args)
  {
    SpringApplication.run (DirectExchangeApplication.class);
  }
  
  @Bean
  public CommandLineRunner runner(MessageProducer producer)
  {
    return args -> {
      producer.sendError ("This is an error message!");
      producer.sendInfo ("This is an info message!");
    };
  }
}
