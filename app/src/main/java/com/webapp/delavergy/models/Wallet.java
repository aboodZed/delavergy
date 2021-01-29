package com.webapp.delavergy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wallet {

    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("invoice_number")
    @Expose
    private String invoice_number;

    @SerializedName("app_revenue")
    @Expose
    private double app_revenue;

    @SerializedName("created_timestamp")
    @Expose
    private long created_timestamp;

    public long getId() {
        return id;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public double getApp_revenue() {
        return app_revenue;
    }

    public long getCreated_timestamp() {
        return created_timestamp;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", invoice_number=" + invoice_number +
                ", app_revenue=" + app_revenue +
                ", created_timestamp=" + created_timestamp +
                '}';
    }
}
