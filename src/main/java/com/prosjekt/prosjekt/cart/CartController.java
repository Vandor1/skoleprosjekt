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

    @GetMapping
    public List<Cart> getCart(){
        return cartService.getCartItems();
    }

    @PostMapping(path = "/add/{user_id}/{item_id}")
    public void addToCart(@PathVariable("user_id") Long userId,
                          @PathVariable("item_id") Long itemId){
        cartService.addToCart(userId, itemId);
    }

    @DeleteMapping(path = "/delete/{user_id}/{item_id}")
    public void deleteCartItem(@PathVariable("user_id") Long userId,
                               @PathVariable("item_id") Long itemId){
        cartService.deleteCartItem(userId, itemId);
    }

    @PutMapping(path = "/update/{user_id}/{item_id}/{value}")
    public void updateCartItemQuantity(@PathVariable("user_id") Long userId,
                                      @PathVariable("item_id") Long itemId,
                                      @PathVariable("value") Integer value){
        cartService.updateCartItemQuantity(userId, itemId, value);
    }
}
