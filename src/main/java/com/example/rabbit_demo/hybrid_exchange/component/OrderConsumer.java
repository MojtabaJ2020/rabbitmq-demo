package com.example.rabbit_demo.hybrid_exchange.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component public class OrderConsumer
{
  private static final Logger log = LoggerFactory.getLogger (OrderConsumer.class);
  
  @RabbitListener(queues = "order-queue")
  public void processOrder(String message) {
    log.info("Processing order: {}" , message);
  }
  
  @RabbitListener(queues = "inventory-queue")
  public void updateInventory(String message) {
    log.info("Updating inventory: {}" , message);
  }
  
  @RabbitListener(queues = "shipping-queue")
  public void handleShipping(String message) {
    log.info("Shipping order: {}" , message);
  }
}
