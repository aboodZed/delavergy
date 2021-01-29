package com.webapp.delavergy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {

    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("from_state")
    @Expose
    private Address from_state;

    @SerializedName("to_state")
    @Expose
    private Address to_state;

    @SerializedName("from_city")
    @Expose
    private Address from_city;

    @SerializedName("to_city")
    @Expose
    private Address to_city;

    @SerializedName("sender_name")
    @Expose
    private String sender_name;

    @SerializedName("sender_address")
    @Expose
    private String sender_address;

    @SerializedName("receiver_name")
    @Expose
    private String receiver_name;

    @SerializedName("receiver_address")
    @Expose
    private String receiver_address;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("type_translation")
    @Expose
    private String type_translation;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("status_translation")
    @Expose
    private String status_translation;

    @SerializedName("invoice_number")
    @Expose
    private String invoice_number;

    @SerializedName("delivery_cost")
    @Expose
    private double delivery_cost;

    @SerializedName("driver_commission")
    @Expose
    private double driver_commission;

    @SerializedName("driver_revenue")
    @Expose
    private double driver_revenue;

    @SerializedName("app_revenue")
    @Expose
    private double app_revenue;

    @SerializedName("total")
    @Expose
    private double total;

    @SerializedName("delivered_date")
    @Expose
    private String delivered_date;

    @SerializedName("created_timestamp")
    @Expose
    private long created_timestamp;

    @SerializedName("receiver_mobile")
    @Expose
    private String receiver_mobile;

    @SerializedName("sender_mobile")
    @Expose
    private String sender_mobile;

    @SerializedName("details")
    @Expose
    private String details;

    public long getId() {
        return id;
    }

    public Address getFrom_state() {
        return from_state;
    }

    public Address getTo_state() {
        return to_state;
    }

    public Address getFrom_city() {
        return from_city;
    }

    public Address getTo_city() {
        return to_city;
    }

    public String getSender_name() {
        return sender_name;
    }

    public String getSender_address() {
        return sender_address;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public String getReceiver_address() {
        return receiver_address;
    }

    public String getType() {
        return type;
    }

    public String getType_translation() {
        return type_translation;
    }

    public String getStatus() {
        return status;
    }

    public String getStatus_translation() {
        return status_translation;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public double getDelivery_cost() {
        return delivery_cost;
    }

    public double getDriver_commission() {
        return driver_commission;
    }

    public double getDriver_revenue() {
        return driver_revenue;
    }

    public double getApp_revenue() {
        return app_revenue;
    }

    public double getTotal() {
        return total;
    }

    public String getDelivered_date() {
        return delivered_date;
    }

    public long getCreated_timestamp() {
        return created_timestamp;
    }

    public String getReceiver_mobile() {
        return receiver_mobile;
    }

    public String getSender_mobile() {
        return sender_mobile;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", from_state=" + from_state +
                ", to_state=" + to_state +
                ", from_city=" + from_city +
                ", to_city=" + to_city +
                ", sender_name='" + sender_name + '\'' +
                ", sender_address='" + sender_address + '\'' +
                ", receiver_name='" + receiver_name + '\'' +
                ", receiver_address='" + receiver_address + '\'' +
                ", type='" + type + '\'' +
                ", type_translation='" + type_translation + '\'' +
                ", status='" + status + '\'' +
                ", status_translation='" + status_translation + '\'' +
                ", invoice_number='" + invoice_number + '\'' +
                ", delivery_cost=" + delivery_cost +
                ", driver_commission=" + driver_commission +
                ", driver_revenue=" + driver_revenue +
                ", app_revenue=" + app_revenue +
                ", total=" + total +
                ", delivered_date='" + delivered_date + '\'' +
                ", created_timestamp=" + created_timestamp +
                ", receiver_mobile='" + receiver_mobile + '\'' +
                ", sender_mobile='" + sender_mobile + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
