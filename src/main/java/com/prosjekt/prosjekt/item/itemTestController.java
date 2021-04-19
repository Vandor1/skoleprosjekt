package com.prosjekt.prosjekt.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;

@RestController
@RequestMapping(path="api/v1/image")
public class itemTestController {

    private final ItemService itemService;

    @Autowired
    public itemTestController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public Path getImage(String filepath){
        return itemService.findImage(filepath);
    }

}
