package com.prosjekt.prosjekt.item;

import javax.persistence.*;

/**
 * A class representing an item ...
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
            generator ="item_sequence"
    )

    private Long id;
    private String name;
    private String description; // Eksempel: Slimfit, 32inch waist - stor i størrelsen.
    private Integer price;
    private String category;
    private String colors;
    private String size;
    private String details;
    private String img;
    private Integer counter = 1;

    public Item() {
    }

    public Item(String name,
                Integer price,
                String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Item(
                String name,
                Integer price,
                String category,
                String details,
                String colors,
                String size,
                String description,
                String filePath) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.details = details;
        this.colors = colors;
        this.size = size;
        this.description = description;
        this.img = filePath;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String filePath) {
        this.img = filePath;
    }

    public void increaseCounter(){
        this.counter++;
    }

    public void decreaseCounter(){
        this.counter--;
    }

    public int getCounter(){
        return this.counter;
    }

    @Override
    public String toString() {
        return "Item{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", price=" + price +
               ", category='" + category + '\'' +
               ", details=" + details +
               ", colors='" + colors + '\'' +
               ", size=" + size +
               ", description='" + description + '\'' +
               ", filePath='" + img + '\'' +
               '}';
    }
}
