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
                    "https://i.imgur.com/PqfGMhn.png"

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
                    "https://i.imgur.com/PwoTdIy.jpg"

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
                    "https://down.imgspng.com/download/0720/sweater_PNG50.png"

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
                    "https://down.imgspng.com/download/0720/sweater_PNG50.png"

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
                    "https://down.imgspng.com/download/0720/sweater_PNG50.png"

            );

            Item i6 = new Item(
                    "T-SHIRT",
                    "feels good",
                    500,
                    "topwear",
                    "Tshirt",
                    "black",
                    "S",
                    "normal fit",
                    "https://images.unsplash.com/photo-1583743814966-8936f5b7be1a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80"
            );
            itemRepository.saveAll(List.of(i1, i2, i3, i4, i5, i6));
        };
    }
}
