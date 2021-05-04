package com.prosjekt.prosjekt.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, CartId> {
    @Query("SELECT s FROM Cart s WHERE s.cartID = ?1")
    Optional<Cart> findCartByCartID(CartId cartId);
}
