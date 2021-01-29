package com.webapp.delavergy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Orders extends Result{

    @SerializedName("orders")
    @Expose
    ArrayList<Order> orders = new ArrayList<>();

    @SerializedName("current_orders")
    @Expose
    ArrayList<Order> current_orders = new ArrayList<>();

    @SerializedName("previous_orders")
    @Expose
    ArrayList<Order> previous_orders = new ArrayList<>();

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public ArrayList<Order> getCurrent_orders() {
        return current_orders;
    }

    public ArrayList<Order> getPrevious_orders() {
        return previous_orders;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orders=" + orders +
                ", current_orders=" + current_orders +
                ", previous_orders=" + previous_orders +
                '}';
    }
}
