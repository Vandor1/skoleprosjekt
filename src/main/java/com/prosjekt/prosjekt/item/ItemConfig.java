package com.prosjekt.prosjekt.item;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ItemConfig {

    @Bean
    CommandLineRunner commandLineRunner(ItemRepository itemRepository){
        return args -> {
            Item tshirt = new Item(
                    "les deux",
                    599,
                    "T-shirt"
            );

            Item pants = new Item(
                    "les deux",
                    999,
                    "Pants"
            );
           /* Item test = new Item(
                    "Test",
                    300,
                    "test",
                    3L,
                    "red",
                    'M',
                    "testDescription",
                    "C:/Mathias/test"
            );*/

            itemRepository.saveAll(List.of(tshirt,pants));
        };
    }
}
