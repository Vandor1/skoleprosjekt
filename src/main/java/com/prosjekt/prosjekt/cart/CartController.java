package com.prosjekt.prosjekt.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService){
        this.cartService = cartService;
    }


    /**
     * Get all cart items
     * @return a list of all cart items in the database
     */
    //TODO: maybe useless?
    @GetMapping
    public List<Cart> getCart(){
        return cartService.getCartItems();
    }

    /**
     * Get a list of a given users cart items.
     * @param userId to get cart items of.
     * @return List of cart items by a given user.
     */
    @GetMapping(path = "/{user_id}")
    public List<Cart> getUserCartItems(
            @PathVariable("user_id") Long userId){
        return cartService.getUserCartItems(userId);
    }

    @PostMapping(path = "/add/{user_id}/{item_id}")
    public void addToCart(@PathVariable("user_id") Long userId,
                          @PathVariable("item_id") Long itemId){
        cartService.addToCart(userId, itemId);
    }

    @DeleteMapping(path = "/delete/{cart_id}")
    public void deleteCartItem(@PathVariable("cart_id") Long cartId){
        cartService.deleteCartItem(cartId);
    }

    @PutMapping(path = "/update/{cart_id}/{value}")
    public void updateCartItemQuantity(@PathVariable("cart_id") Long cartId,
                                      @PathVariable("value") Integer value){
        cartService.updateCartItemQuantity(cartId, value);
    }
}
