package com.prosjekt.prosjekt.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository1 cartRepository;

    @Autowired
    public CartService(CartRepository1 cartRepository){
        this.cartRepository = cartRepository;
    }

    public List<Cart> getCartItems(){
        return cartRepository.findAll();
    }

    public void addToCart(Long userId, Long itemId){
        Optional<Cart> cartItem = cartRepository.findCartByUserIdAndItemId(userId, itemId);
        if(cartItem.isPresent()){
            cartItem.get().incrementQuantity();
            cartRepository.save(cartItem.get());
        } else {
            Cart cartToAdd = new Cart(userId, itemId);
            cartRepository.save(cartToAdd);
        }
    }

    public void deleteCartItem(Long cartId) {
        Optional<Cart> cartToDelete = cartRepository.findCartById(cartId);
        if(cartToDelete.isPresent()){
            cartRepository.delete(cartToDelete.get());
        } else {
            //TODO: LOGGER?
            System.out.println("Item you are trying to delete does not exist");
        }
    }

    public void updateCartItemQuantity(Long cartId, Integer value) {
        Optional<Cart> cartItem = cartRepository.findCartById(cartId);
        if(cartItem.isPresent()){
            cartItem.get().setQuantity(value);
            cartRepository.save(cartItem.get());
        } else {
            //TODO: LOGGER
            System.out.println("item does not exist");
        }
    }

    public List<Cart> getUserCartItems(Long userId) {
        return cartRepository.findCartsByUserId(userId);
    }
}
