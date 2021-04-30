package com.prosjekt.prosjekt.appuser;

import com.prosjekt.prosjekt.item.Item;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppUserConfig {
    List<Item> orderItems1;
    List<Item> orderItems2;


    List<Item> testItems;
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
            3);

    List<Item> testItems2;
    Item test2 = new Item(
            "gggggg",
            "undertittlel",
            1200,
            "bottomwear",
            "jeans",
            "blue",
            "M",
            "slim fit",
            "https://i.imgur.com/PqfGMhn.png",
            3);

    public AppUserConfig(){
        testItems = new ArrayList<>();
//        testItems.add(test);
//        testItems.add(test2);
        testItems2 = new ArrayList<>();
//        testItems2.add(test2);
//        testItems2.add(test);

        orderItems1 = new ArrayList<>();
        orderItems2 = new ArrayList<>();

    }


    @Bean(name = "initUsers")
    CommandLineRunner commandLineRunner(AppUserService appUserService){
        return args -> {
            AppUser user1 = new AppUser(
                    "mathias",
                    "gmail",
                    "jegErHomo",
                    AppUserRole.USER,
                    this.testItems,
                    this.orderItems1);

            AppUser user2 = new AppUser(
                    "a",
                    "b",
                    "c",
                    AppUserRole.USER);

            appUserService.signUpUser(user1);
            appUserService.signUpUser(user2);
        };
    }
}
