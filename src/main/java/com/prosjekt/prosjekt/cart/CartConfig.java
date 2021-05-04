package com.prosjekt.prosjekt.cart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CartConfig {

    @Bean(name = "initCart")
    CommandLineRunner commandLineRunner(CartRepository cartRepository){
        return args -> {
            Cart cartItem1 = new Cart(1L,1L);
//            Cart cartItem2 = new Cart(1L, 2L);

            cartRepository.saveAll(List.of(cartItem1));
        };
    }
}
