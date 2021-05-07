package com.prosjekt.prosjekt.order;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {
    @Bean(name="testOrders")
    CommandLineRunner commandLineRunner(OrderRepository orderRepository){
        return args -> {
//            Order order = new Order(1L);
//            orderRepository.save(order);
        };
    }
}
