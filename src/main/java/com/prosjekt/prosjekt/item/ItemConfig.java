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
            Item i1 = new Item(
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
            Item i2 = new Item(
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
            Item i3 = new Item(
                    "GAP sweater",
                    "Comfy cotton top",
                    600,
                    "topwear",
                    "sweater",
                    "gray, blue",
                    "L",
                    "normal fit",
                    "https://down.imgspng.com/download/0720/sweater_PNG50.png",
                    5
            );

            Item i4 = new Item(
                    "GAP sweater",
                    "Comfy cotton top",
                    600,
                    "topwear",
                    "sweater",
                    "gray, blue",
                    "L",
                    "normal fit",
                    "https://down.imgspng.com/download/0720/sweater_PNG50.png",
                    5
            );

            Item i5 = new Item(
                    "GAP sweater",
                    "Comfy cotton top",
                    600,
                    "topwear",
                    "sweater",
                    "gray, blue",
                    "L",
                    "normal fit",
                    "https://down.imgspng.com/download/0720/sweater_PNG50.png",
                    5
            );

            itemRepository.saveAll(List.of(i1, i2, i3, i4, i5));
        };
    }
}
