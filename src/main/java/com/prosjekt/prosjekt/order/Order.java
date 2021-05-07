package com.prosjekt.prosjekt.order;

import com.prosjekt.prosjekt.item.Item;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
// Order is reserved by Postgres, need to define new name
@NoArgsConstructor
@Table(name = "Orders")
public class Order {

    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "orders_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    private Long id;
    private Long userId;
    private LocalDate date;
    private OrderStatus orderStatus;

    // Order_item join table
    @JoinTable(name = "orderItem")
    @ManyToMany(targetEntity = Item.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "itemId", referencedColumnName = "id")
    private List<Item> items;

    public Order(Long userId){
        this.userId = userId;
        this.orderStatus = OrderStatus.CART;
        this.items = new ArrayList<>();
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItemToOrder(Item item){
        this.items.add(item);
    }

    public int getQuantity(Item item){
        return Collections.frequency(this.items, item);
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
