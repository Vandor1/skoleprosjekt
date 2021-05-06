package com.prosjekt.prosjekt.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository1 extends JpaRepository<Cart, Long> {
    @Query("SELECT s FROM Cart s WHERE s.userId =?1")
    List<Cart> findCartsByUserId(Long userId);
    Optional<Cart> findCartById(Long cartId);
    Optional<Cart> findCartByUserIdAndItemId(Long userId, Long itemId);

}
