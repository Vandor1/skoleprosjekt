package com.prosjekt.prosjekt.item;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ItemConfig {

    @Bean(name = "initItems")
    CommandLineRunner commandLineRunner(ItemRepository itemRepository){
        return args -> {
            Item test = new Item(
                    "Levi's jeans",
                    "undertittlel",
                    1200,
                    "bottomwear",
                    "jeans",
                    "blue",
                    "M",
                    "slim fit",
                    "https://i.imgur.com/PqfGMhn.png",
                    3

            );
            Item test2 = new Item(
                    "Nike ultimate running",
                    "made for running",
                    1700,
                    "shoes",
                    "running",
                    "black",
                    "s",
                    "200g",
                    "https://i.imgur.com/PwoTdIy.jpg",
                    5

            );

            itemRepository.saveAll(List.of(test, test2));
        };
    }
}
