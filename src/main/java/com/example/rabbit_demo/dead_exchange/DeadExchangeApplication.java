package com.example.rabbit_demo.dead_exchange;

import com.example.rabbit_demo.dead_exchange.component.MessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DeadExchangeApplication
{
  public static void main (String []args)
  {
    SpringApplication.run (DeadExchangeApplication.class);
  }
  
  @Bean
  public CommandLineRunner runner(MessageProducer producer)
  {
    return args -> {
      producer.sendEvent ("This is the first event");
      producer.sendEvent ("This is the second event");
      producer.sendEvent ("This is the third event");
    };
  }
}
