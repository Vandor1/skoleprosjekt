package com.prosjekt.prosjekt.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getItems(){
        return itemRepository.findAll();
    }

    public void addNewItem(Item item) {
       Optional<Item> itemByName = itemRepository.findItemByName(item.getName());
       if(itemByName.isPresent()){
           throw new IllegalStateException("Item already exists");
       }
       itemRepository.save(item);
    }


    public void deleteItem(Long itemId) {
        boolean exists = itemRepository.existsById(itemId);
       if(!exists){
           throw new IllegalStateException("Student with id: " + itemId + " does not exist.");
       }
       itemRepository.deleteById(itemId);
    }


    @Transactional
    public void updateItem(Long itemId, int price) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException(
                        "item with id "+itemId + "does not exist"));
        if (price != 0 && !Objects.equals(item.getPrice(), price)){
            item.setPrice(price);
        }
    }
}
