package com.prosjekt.prosjekt.item;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class ItemConfig {

    /**
     * DUMMY DATA IS FETCHED FROM https://www.pexels.com/ FREE IMAGE SOURCE.
     * @param itemRepository
     * @return
     */
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

            Item i7 = new Item(
                    "SINGLET",
                    "David bowie",
                    300,
                    "topwear",
                    "Singlet",
                    "White",
                    "L",
                    "loose fit",
                    "https://images.pexels.com/photos/5275179/pexels-photo-5275179.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260"
            );

            Item i8 = new Item(
                    "CARDIGANS",
                    "feels good",
                    500,
                    "topwear",
                    "Cardigans",
                    "Brown",
                    "M",
                    "Tight fit",
                    "https://images.pexels.com/photos/5638645/pexels-photo-5638645.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
            );

            Item i9 = new Item(
                    "JOGGERS",
                    "feels good",
                    500,
                    "bottomwear",
                    "Joggers",
                    "Grey",
                    "L",
                    "Large in size",
                    "https://images.pexels.com/photos/6311393/pexels-photo-6311393.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
            );

            Item i10 = new Item(
                    "SWEATPANTS",
                    "feels good",
                    500,
                    "bottomwear",
                    "Sweatpants",
                    "eggwhite",
                    "L",
                    "large in size",
                    "https://images.pexels.com/photos/6311394/pexels-photo-6311394.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
            );

            Item i11 = new Item(
                    "SHORTS",
                    "feels good",
                    500,
                    "bottomwear",
                    "shorts",
                    "red",
                    "S",
                    "normal fit",
                    "https://images.pexels.com/photos/5325560/pexels-photo-5325560.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
            );

            Item i20 = new Item(
                    "Weightlifting TSHIRT SPARTA",
                    "feels good",
                    500,
                    "Sport",
                    "Weightlifting",
                    "Blue",
                    "M",
                    "normal fit",
                    "https://images.pexels.com/photos/812733/pexels-photo-812733.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
            );

            Item i19 = new Item(
                    "Tennis HAT",
                    "feels good",
                    500,
                    "Sport",
                    "Tennis",
                    "Yellow",
                    "N",
                    "normal fit",
                    "https://images.pexels.com/photos/5069176/pexels-photo-5069176.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
            );

            Item i15 = new Item(
                    "Converse",
                    "classic converse",
                    700,
                    "Shoes",
                    "Sneakers",
                    "green",
                    "M",
                    "",
                    "https://cdn.pixabay.com/photo/2013/07/12/18/20/shoes-153310_960_720.png"
            );
            Item i12 = new Item(
                    "FootJoy SuperLites",
                    "Light golf shoes",
                    800,
                    "Shoes",
                    "golf",
                    "gray",
                    "L",
                    "normal is size",
                   " https://i.imgur.com/mx5ixSF.jpg");
            Item i13 = new Item(
                    "Merrel shoes",
                    "GORE-TEX",
                    1300,
                    "Shoes",
                    "lifestyle",
                    "brown",
                    "L",
                    "Perfect for hiking",
                    "https://cdn.pixabay.com/photo/2014/12/31/11/41/shoes-584850_960_720.jpg");

            Item i14 = new Item(
                    "Football jersey",
                    "2021 edition",
                    400,
                    "Sport",
                    "Football",
                    "yellow",
                    "M",
                    "Slim fit",
                    "https://i.imgur.com/Z0YZaCA.png");

            itemRepository.saveAll(List.of(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i19, i20));

        };
    }
}
