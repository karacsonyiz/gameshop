package com.karacsonyiz.gameshop.model;

public class Product {

    private String productId;
    private String name;
    private String producer;
    private long price;
    private long quantity;

    public Product() {
    }

    public Product(String productId, String name, String producer, long price, long quantity) {
        this.productId = productId;
        this.name = name;
        this.producer = producer;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId){
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
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
