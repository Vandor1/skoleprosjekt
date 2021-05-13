package com.prosjekt.prosjekt.item;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/item")
public class ItemController {

    private final ItemService itemService;

    private Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Returning a List, returns a JSON element.
     * @return
     */
    @GetMapping
    public List<Item> getItems(){
        return itemService.getItems();
    }

    @GetMapping(path="{itemId}")
    public Item getItem(@PathVariable("itemId") Long id){
        return itemService.getItem(id);
    }

    @PostMapping
    public void registerNewItem(@RequestBody Item item){
        itemService.addNewItem(item);
    }

    @DeleteMapping(path = "{itemId}")
    public void deleteItem(@PathVariable("itemId") Long itemId){
        itemService.deleteItem(itemId);
    }

    @PutMapping(path="{itemId}")
    public void updateItem(
            @PathVariable("itemId") Long itemId,
            @RequestParam(required = false) int price
    ){
        itemService.updateItem(itemId, price);
    }



}
