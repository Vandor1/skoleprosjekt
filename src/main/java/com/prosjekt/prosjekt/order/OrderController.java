package com.prosjekt.prosjekt.order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/order")

public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrders(){
        return this.orderService.getOrders();
    }

    @GetMapping(path = "/{user_id}")
    public List<Order> getOrdersByUserId(@PathVariable("user_id") Long userId){
        return this.orderService.getOrdersByUserId(userId);
    }

    /**
     * Collects the cart items
     * @param userId
     * @return
     */
    @GetMapping(path = "/cart/{user_id}")
    public Order getCartItems(@PathVariable("user_id") Long userId){
        return this.orderService.getCartItems(userId);
    }

    /**
     * Add item to cart
     * @param userId
     * @param itemId
     */
    @PostMapping(path = "/add/{user_id}/{item_id}")
    public void addToOrder(@PathVariable("user_id") Long userId,
                          @PathVariable("item_id") Long itemId){
        orderService.addToOrder(userId, itemId);
    }

    @PostMapping("/createCart/{userId}")
    public void createCart(@PathVariable("userId") Long userId){
        orderService.createCart(userId);
    }
    /**
     * Checkout shopping cart.
     * @param userId
     */
    @PutMapping(path = "/checkout/{userId}")
    public void checkOutOrder(@PathVariable("userId") Long userId){
        orderService.checkOutOrder(userId);
    }

    @PutMapping(path ="/cart/delete/{userId}/{itemId}")
    public void deleteCartItem(@PathVariable("userId") Long userId, @PathVariable("itemId") Long itemId){
        orderService.deleteCartItem(userId,itemId);
    }
}
