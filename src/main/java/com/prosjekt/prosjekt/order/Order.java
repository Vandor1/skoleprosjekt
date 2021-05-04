//package com.prosjekt.prosjekt.order;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//
//@Entity
//@Table
//public class Order {
//
//    @SequenceGenerator(
//            name = "order_sequence",
//            sequenceName = "order_sequence",
//            allocationSize = 1
//    )
//    @Id
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "order_sequence"
//    )
//    private Long id;
//    private LocalDate date;
//    private OrderStatus orderStatus;
//
//    public Order(){
//        this.date = LocalDate.now();
//        this.orderStatus = OrderStatus.PENDING;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public LocalDate getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDate date) {
//        this.date = date;
//    }
//
//    public OrderStatus getOrderStatus() {
//        return orderStatus;
//    }
//
//    public void setOrderStatus(OrderStatus orderStatus) {
//        this.orderStatus = orderStatus;
//    }
//}
