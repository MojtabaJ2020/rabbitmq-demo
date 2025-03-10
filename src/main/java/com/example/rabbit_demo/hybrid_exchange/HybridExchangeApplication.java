package com.example.rabbit_demo.hybrid_exchange;

import com.example.rabbit_demo.hybrid_exchange.component.OrderProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HybridExchangeApplication
{
  public static void main (String []args)
  {
    SpringApplication.run (HybridExchangeApplication.class);
  }
  
  @Bean
  public CommandLineRunner runner(OrderProducer orderProducer)
  {
    return args -> {
      // Place an order
      orderProducer.placeOrder("12345");
      
      // Simulate order status updates
      orderProducer.updateOrderStatus("12345", "created");
      orderProducer.updateOrderStatus("12345", "shipped");
    };
  }
}
