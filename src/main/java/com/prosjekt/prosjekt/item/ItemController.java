package com.prosjekt.prosjekt.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/item")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Get all items in the database
     * @return
     */
    @GetMapping
    public List<Item> getItems(){
        return itemService.getItems();
    }

    /**
     * Find an item by its ID
     * @param id Id of the item to find
     * @return
     */
    @GetMapping(path="{itemId}")
    public Item getItem(@PathVariable("itemId") Long id){
        return itemService.getItem(id);
    }

    /**
     * Add a new item to the database
     * @param item
     */
    @PostMapping
    public void registerNewItem(@RequestBody Item item){
        itemService.addNewItem(item);
    }

    /**
     * Delte an item from the database
     * @param itemId
     */
    @DeleteMapping(path = "{itemId}")
    public void deleteItem(@PathVariable("itemId") Long itemId){
        itemService.deleteItem(itemId);
    }

    /**
     * Update the price of a given item
     * @param itemId
     * @param price
     */
    @PutMapping(path="{itemId}")
    public void updateItem(
            @PathVariable("itemId") Long itemId,
            @RequestParam(required = false) int price
    ){
        itemService.updateItem(itemId, price);
    }



}
