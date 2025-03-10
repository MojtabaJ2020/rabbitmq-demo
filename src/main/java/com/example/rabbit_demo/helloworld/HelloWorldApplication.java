package com.example.rabbit_demo.helloworld;

import com.example.rabbit_demo.helloworld.component.MessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication public class HelloWorldApplication
{
  public static void main (String[] args)
  {
    SpringApplication.run (HelloWorldApplication.class);
  }
  
//  @Bean public CommandLineRunner runner (MessageProducer producer)
//  {
//    return args -> producer.sendMessage ("Hello Mojtaba");
//  }
}
