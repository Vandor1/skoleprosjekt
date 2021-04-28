package com.prosjekt.prosjekt.appuser;

import com.prosjekt.prosjekt.item.Item;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppUserTest {

    @Test
    void addItem() {
        AppUser user = new AppUser();
        user.addItem(new Item(
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
        ));
        assertFalse(user.getItems().isEmpty());
    }
}