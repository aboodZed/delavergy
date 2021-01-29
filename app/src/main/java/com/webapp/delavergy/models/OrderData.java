package com.webapp.delavergy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderData extends Result{

    @SerializedName("order")
    @Expose
    private Order order;

    public Order getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "OrderData{" +
                "order=" + order +
                '}';
    }
}
