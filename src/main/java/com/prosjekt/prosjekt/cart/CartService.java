package com.prosjekt.prosjekt.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    public void addToCart(Long userId, Long itemId){
        Optional<Cart> cartItem = cartRepository.findCartByCartID(new CartId(userId, itemId));
        if(cartItem.isPresent()){
            cartItem.get().incrementQuantity();
            cartRepository.save(cartItem.get());
        } else {
            Cart cartToAdd = new Cart(userId, itemId);
            cartRepository.save(cartToAdd);
        }
    }

    public List<Cart> getCartItems(){
        return cartRepository.findAll();
    }

    public void deleteCartItem(Long userId, Long itemId) {
        if(cartRepository.existsById(new CartId(userId, itemId))){
            cartRepository.deleteById(new CartId(userId, itemId));
        } else {
            //TODO: LOGGER?
            System.out.println("Item you are trying to delete does not exist");
        }
    }

    public void updateCartItemQuantity(Long userId, Long itemId, Integer value) {
        Optional<Cart> cartItem = cartRepository.findCartByCartID(new CartId(userId, itemId));
        if(cartItem.isPresent()){
            cartItem.get().setQuantity(value);
            cartRepository.save(cartItem.get());
        } else {
            //TODO: LOGGER
            System.out.println("item does not exist");
        }
    }
}
