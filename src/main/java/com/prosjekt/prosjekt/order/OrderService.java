package com.prosjekt.prosjekt.order;

import com.prosjekt.prosjekt.appuser.AppUser;
import com.prosjekt.prosjekt.appuser.UserRepository;
import com.prosjekt.prosjekt.item.Item;
import com.prosjekt.prosjekt.item.ItemRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Class - OrderService
 * Provides services for the Order Controller
 */
@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    /**
     * Deletes an item from a users cart
     *
     * @param userId users to delete from
     * @param itemId item to delete
     */
    public void deleteCartItem(Long userId, Long itemId) {
        try {
            Order cartItems = getCartItems(userId);
            Item itemToDelete = itemRepository.findItemById(itemId).get();
            if (cartItems.getItems().contains(itemToDelete)) {
                cartItems.deleteItemFromOrder(itemToDelete);
                orderRepository.save(cartItems);
                logger.info("Deleted item " + itemId + " from user "
                        + userId + "'s cart");
            } else {
                logger.warn("The item does not exist in the user's cart");
            }
        } catch (Exception e) {
            logger.error("The user or item does not exist");
            logger.trace(e.getMessage(), e);
        }
    }

    /**
     * Get the cart of a given user
     *
     * @param userId of the user ID.
     * @return the cart of the user
     */
    public Order getCartItems(Long userId) {
        Optional<Order> order = getUserCart(userId);
        return order.orElse(null);
    }

    /**
     * Checkout the shopping cart
     * Changes the order status from CART to ORDER
     * and sets the date of the order to today's date
     * After checkout a new empty cart is created to the user
     * @param userID of the user we want to checkout
     */
    public void checkOutOrder(Long userID) {
        try {
            Order cart = getUserCart(userID).get();
            if(cart.getItems().isEmpty()){
                throw new Exception("The shopping cart is empty");
            }
            cart.setOrderStatus(OrderStatus.ORDER);
            cart.setDate(LocalDate.now());
            orderRepository.save(cart);
            this.createCart(userID);
            logger.info("Successfully checked out user " + userID +
                    "'s cart");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Get a list of all orders of a given user
     *
     * @param userId the ID of the user to get orders from
     * @return a list of orders by the given user
     */
    public List<Order> getOrdersByUserId(Long userId) {
        try {
            AppUser appUser = userRepository.findAppUserById(userId).get();
            List<Order> orders = orderRepository.findOrdersByUserIdAndOrderStatus(userId, OrderStatus.ORDER);
            logger.info("Found " + orders.size() + " orders from user " + userId);
            return orders;
        } catch (Exception e){
            logger.error("The user does not exist");
            logger.error(e.getMessage(), e);
            return null;
        }
    }


    /**
     * Add an item to a given users cart.
     *
     * @param userId id of the user's cart to update.
     * @param itemId id of the item to add to the cart.
     */
    public void addToCart(Long userId, Long itemId) {
        try {
            Order cart = getUserCart(userId).get();
            Item item = itemRepository.findItemById(itemId).get();
            cart.addItemToOrder(item);
            orderRepository.save(cart);
            logger.info("Successfully added item " + itemId + " to user "
                    + userId + "'s cart");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Creates a new cart to a user if they do not already have one.
     * This method is being used after checking out a cart and after logging in.
     * @param userId
     */
    public void createCart(Long userId) {
        if (getUserCart(userId).isEmpty()) {
            Order order = new Order(userId);
            orderRepository.save(order);
        }
    }

    /**
     * Get the cart of a given user
     * Helper method to avoid duplicated code.
     *
     * @param userId ID of the user to get cart from
     * @return an Optional object containing the user's cart if it exist
     */
    private Optional<Order> getUserCart(Long userId) {
        return orderRepository.findOrderByUserIdAndOrderStatus(userId, OrderStatus.CART);
    }
}
