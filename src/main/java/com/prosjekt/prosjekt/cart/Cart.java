package com.prosjekt.prosjekt.cart;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @SequenceGenerator(
            name = "cart_sequence",
            sequenceName = "cart_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_sequence"
    )
    private Long id;
    private Long userId;
    private Long itemId;
    private Integer quantity;

    public Cart(Long userId, Long itemId) {
        this.userId = userId;
        this.itemId = itemId;
        this.quantity = 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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
}
