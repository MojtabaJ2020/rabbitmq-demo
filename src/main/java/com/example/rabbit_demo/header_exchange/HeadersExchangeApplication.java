package com.example.rabbit_demo.header_exchange;

import com.example.rabbit_demo.header_exchange.component.MessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HeadersExchangeApplication
{
  public static void main (String []args)
  {
    SpringApplication.run (HeadersExchangeApplication.class);
  }
  
  @Bean
  public CommandLineRunner runner(MessageProducer producer)
  {
    return args -> {
      producer.sendOrderEvent ("This is an order event");
      producer.sendHighPriorityNotification ("This is a high priority notification");
      producer.sendLowPriorityNotification ("This is a low priority notification");
    };
  }
}
