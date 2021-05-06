package com.prosjekt.prosjekt.cart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CartConfig {

    @Bean(name = "initCart")
//    CommandLineRunner commandLineRunner(CartRepository cartRepository){
//        return args -> {
//            Cart cartItem1 = new Cart(1L,1L);
//            Cart cartItem2 = new Cart(1L, 2L);
//            Cart cartItem3 = new Cart(1L, 3L);
//
//
//            cartRepository.saveAll(List.of(cartItem1, cartItem2, cartItem3));
//        };
//    }
    CommandLineRunner commandLineRunner(CartRepository1 cartRepository1){
        return args -> {
            Cart cartItem1 = new Cart(1L,1L);
            Cart cartItem2 = new Cart(1L, 2L);
            Cart cartItem3 = new Cart(1L, 3L);


            cartRepository1.saveAll(List.of(cartItem1, cartItem2, cartItem3));
        };
    }
}
