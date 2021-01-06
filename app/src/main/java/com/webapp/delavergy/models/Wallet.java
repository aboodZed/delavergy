package com.webapp.delavergy.models;

public class Wallet {

    private long order_id;
    private long create_at;
    private double value;

    public Wallet(long order_id, long create_at, double value) {
        this.order_id = order_id;
        this.create_at = create_at;
        this.value = value;
    }

    public long getOrder_id() {
        return order_id;
    }

    public long getCreate_at() {
        return create_at;
    }

    public double getValue() {
        return value;
    }
}
