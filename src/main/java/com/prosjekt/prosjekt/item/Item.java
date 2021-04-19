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
    private Integer price;
    private String category;
    private Long barcode;
    private String colors;
    private String size;
    private String description; // Eksempel: Slimfit, 32inch waist - stor i størrelsen.
    private String filePath;
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
                Long barcode,
                String colors,
                String size,
                String description,
                String filePath) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.barcode = barcode;
        this.colors = colors;
        this.size = size;
        this.description = description;
        this.filePath = filePath;
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

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
               ", barcode=" + barcode +
               ", colors='" + colors + '\'' +
               ", size=" + size +
               ", description='" + description + '\'' +
               ", filePath='" + filePath + '\'' +
               '}';
    }
}
