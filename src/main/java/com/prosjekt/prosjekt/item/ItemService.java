package com.prosjekt.prosjekt.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Class - ItemService
 */
@Service
public class ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    private final ItemRepository itemRepository;

    /**
     * Constructor for Item Service class, initialize the item repository.
     *
     * @param itemRepository repository to be used.
     */
    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /**
     * Collect all items in the repository.
     *
     * @return a list containing all the items in the repository.
     */
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    /**
     * Get a specific item from the repository.
     *
     * @param id of the item to find.
     * @return the item found, or null if it does not exist.
     */
    public Item getItem(Long id) {
        try {
            Item foundItem = itemRepository.findItemById(id).get();
            logger.info("Item with id: " + id + " obtained successfully.");
            return foundItem;
        } catch (Exception e) {
            logger.error("Item with id: " + id + " does not exist.");
            logger.trace(e.getMessage());
            return null;
        }
    }

    /**
     * Adds new item to the repository.
     *
     * @param item to be added.
     */
    public void addNewItem(Item item) {
        Optional<Item> itemToAdd = itemRepository.findItemById(item.getId());
        if (itemToAdd.isPresent()) {
            logger.warn("Item: " + item + " already exists.");
        } else {
            itemRepository.save(item);
        }
    }

    /**
     * Delete item with item id itemId from the database.
     *
     * @param itemId of item to be deleted.
     */
    public void deleteItem(Long itemId) {
        try {
            Item item = itemRepository.findItemById(itemId).get();
            itemRepository.deleteById(itemId);
            logger.info("Successfully deleted item " + itemId + ".");
        } catch (Exception e) {
            logger.error("Could not find item with id " + itemId);
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Update price paramater of item. Can be used for sales, etc.
     *
     * @param itemId of item to be updated.
     * @param price  new price of item to be updated.
     */
    @Transactional
    public void updateItem(Long itemId, int price) {
        try {
            Item item = getItem(itemId);
            if (price != 0 && !Objects.equals(item.getPrice(), price)) {
                item.setPrice(price);
                logger.info(item + "has now updated price to: " + price);
            }
        } catch (Exception e) {
            logger.warn("Unable to find item or to change its price.");
        }
    }
}
