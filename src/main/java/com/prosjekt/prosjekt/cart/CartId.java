package com.prosjekt.prosjekt.cart;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class CartId implements Serializable {

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "item_id")
    private Long itemId;

    public CartId(Long userId, Long itemId){
        this.userId = userId;
        this.itemId = itemId;
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

    public void setItemId(Long itemID) {
        this.itemId = itemID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartId cartID = (CartId) o;
        return userId.equals(cartID.userId) && itemId.equals(cartID.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, itemId);
    }
}
