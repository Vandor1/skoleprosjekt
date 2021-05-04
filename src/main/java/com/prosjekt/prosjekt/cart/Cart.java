package com.prosjekt.prosjekt.cart;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Cart {
    @EmbeddedId
    private CartId cartID;
    private Integer quantity;

    public Cart(Long userId, Long itemId){
        this.cartID = new CartId(userId, itemId);
        this.quantity = 1;
    }


    public CartId getCartID() {
        return cartID;
    }

    public void setCartID(CartId cartID) {
        this.cartID = cartID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        if(quantity >= 1){
            this.quantity = quantity;
        }
    }

    public void incrementQuantity(){
        this.quantity++;
    }

    public void decrementQuantity(){
        if(quantity <= 1){
            //TODO: LOGGER????
            System.out.println("Cant increment less than 1");
        } else {
            this.quantity++;
        }
    }

    public Long getUserId(){
        return cartID.getUserId();
    }

    public Long getItemId(){
        return cartID.getItemId();
    }
}


