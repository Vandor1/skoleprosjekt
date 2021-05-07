package com.prosjekt.prosjekt.cart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    CommandLineRunner commandLineRunner(CartService cartService){
        return args -> {
//            Cart cartItem1 = new Cart(1L,1L);
//            Cart cartItem2 = new Cart(1L, 2L);
//            Cart cartItem3 = new Cart(1L, 3L);


//            cartService.addToCart(1L, 2L);
        };
    }
}
