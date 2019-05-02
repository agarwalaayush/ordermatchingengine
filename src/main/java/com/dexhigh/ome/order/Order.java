package com.dexhigh.ome.order;

import java.util.concurrent.atomic.AtomicInteger;

public class Order {

    private static final AtomicInteger count = new AtomicInteger(0);

    private int id;

    private OrderType type;

    private int qty;

    private double price;

    public Order (OrderType type, int qty, double price) {

        this.id = count.incrementAndGet();
        this.type = type;
        this.qty = qty;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public OrderType getType() {
        return type;
    }

    public int getQty() {
        return qty;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", type=" + type +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}