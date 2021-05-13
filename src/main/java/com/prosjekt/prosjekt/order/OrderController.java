package com.prosjekt.prosjekt.order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Order controller - Manages CRUD operations for the Order entity table
 */
@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/order")
public class OrderController {

    private final OrderService orderService;

    /**
     * The order controller constructor
     * @param orderService
     */
    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    /**
     * Get orders of a given user
     * @param userId
     * @return
     */
    @GetMapping(path = "/{user_id}")
    public List<Order> getOrdersByUserId(@PathVariable("user_id") Long userId){
        return this.orderService.getOrdersByUserId(userId);
    }

    /**
     * Get the cart of a given user
     * @param userId
     * @return
     */
    @GetMapping(path = "/cart/{user_id}")
    public Order getCartItems(@PathVariable("user_id") Long userId){
        return this.orderService.getCartItems(userId);
    }

    /**
     * Add an item to a given users cart
     * @param userId
     * @param itemId
     */
    @PostMapping(path = "/add/{user_id}/{item_id}")
    public void addToCart(@PathVariable("user_id") Long userId,
                          @PathVariable("item_id") Long itemId){
        orderService.addToCart(userId, itemId);
    }

    /**
     * Checkout shopping cart of a given user.
     * @param userId
     */
    @PutMapping(path = "/checkout/{userId}")
    public void checkOutOrder(@PathVariable("userId") Long userId){
        orderService.checkOutOrder(userId);
    }

    /**
     * Delete an item in a given user's cart.
     * @param userId
     * @param itemId
     */
    @PutMapping(path ="/cart/delete/{userId}/{itemId}")
    public void deleteCartItem(@PathVariable("userId") Long userId, @PathVariable("itemId") Long itemId){
        orderService.deleteCartItem(userId,itemId);
    }
}
