package com.prosjekt.prosjekt.item;

import java.time.LocalDate;
import javax.persistence.*;

/**
 * A class representing an item with the parameters needed for classifying the item.
 */
@Entity
@Table
public class Item {
    @Id
    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator ="items_sequence"
    )
    private Long id;
    private String name;
    private Integer price;
    private LocalDate createDate;
    private String category;

    public Item() {
    }

    public Item(Long id,
                String name,
                Integer price,
                LocalDate createDate,
                String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.createDate = createDate;
        this.category = category;
    }

    public Item(String name,
                Integer price,
                LocalDate createDate,
                String category) {
        this.name = name;
        this.price = price;
        this.createDate = createDate;
        this.category = category;
    }

    @Override
    public String toString() {
        return "item{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", price=" + price +
               ", createDate=" + createDate +
               ", category='" + category + '\'' +
               '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
