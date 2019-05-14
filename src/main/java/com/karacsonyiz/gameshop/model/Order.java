package com.karacsonyiz.gameshop.model;

import java.time.LocalDateTime;

public class Order {

    private long id;
    private int userId;
    private LocalDateTime date;
    private OrderStatus status;
    private long quantity;
    private long price;
    private String deliveryAddress;


    public Order(long id,int userId,LocalDateTime date,OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.status = status;
    }

    public Order(long id,int userId,LocalDateTime date,OrderStatus status,long quantity,long price) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.status = status;
        this.quantity = quantity;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
