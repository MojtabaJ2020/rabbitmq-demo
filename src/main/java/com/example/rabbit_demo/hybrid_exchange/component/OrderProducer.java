package com.example.rabbit_demo.hybrid_exchange.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component public class OrderProducer
{
  private static final Logger log = LoggerFactory.getLogger (OrderProducer.class);
  private final RabbitTemplate rabbitTemplate;
  
  public OrderProducer (RabbitTemplate rabbitTemplate)
  {
    this.rabbitTemplate = rabbitTemplate;
  }
  
  // Send order to order-exchange
  public void placeOrder(String orderId) {
    String message = "Order ID: " + orderId;
    rabbitTemplate.convertAndSend("order-exchange", "order.placed", message);
    log.info("Order placed: {}" , orderId);
  }
  
  // Send status update to status-exchange
  public void updateOrderStatus(String orderId, String status) {
    String message = "Order ID: " + orderId + " | Status: " + status;
    rabbitTemplate.convertAndSend("status-exchange", "order." + status, message);
    log.info("Status updated: {}" , status);
  }
}
