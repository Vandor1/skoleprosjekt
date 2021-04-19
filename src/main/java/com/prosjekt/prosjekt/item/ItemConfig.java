package com.prosjekt.prosjekt.item;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class ItemConfig {

    @Bean
    CommandLineRunner commandLineRunner(ItemRepository itemRepository){
        return args -> {
            Item tshirt = new Item(
                    "les deux",
                    599,
                    LocalDate.of(2020,1,10),
                    "T-shirt"
            );

            Item pants = new Item(
                    "les deux",
                    999,
                    LocalDate.of(2020,1,10),
                    "Pants"
            );

            itemRepository.saveAll(List.of(tshirt,pants));
        };
    }
}
