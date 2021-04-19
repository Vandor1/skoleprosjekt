package com.prosjekt.prosjekt.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * NOTE: Explore JpaRepository to view methods.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT s from Item s WHERE s.name = ?1")
    Optional<Item> findItemByName(String name);
}
