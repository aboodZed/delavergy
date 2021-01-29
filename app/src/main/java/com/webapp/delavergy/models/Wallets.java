package com.webapp.delavergy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Wallets extends Result {

    @SerializedName("wallet")
    @Expose
    private double wallet;

    @SerializedName("app_commission")
    @Expose
    private double app_commission;

    @SerializedName("total_delivery_cost")
    @Expose
    private double total_delivery_cost;

    @SerializedName("total_driver_revenue")
    @Expose
    private double total_driver_revenue;

    @SerializedName("app_revenue")
    @Expose
    private double app_revenue;

    @SerializedName("wallet_details")
    @Expose
    private ArrayList<Wallet> wallet_details = new ArrayList<>();

    public double getWallet() {
        return wallet;
    }

    public double getApp_commission() {
        return app_commission;
    }

    public double getTotal_delivery_cost() {
        return total_delivery_cost;
    }

    public double getTotal_driver_revenue() {
        return total_driver_revenue;
    }

    public double getApp_revenue() {
        return app_revenue;
    }

    public ArrayList<Wallet> getWallet_details() {
        return wallet_details;
    }

    @Override
    public String toString() {
        return "Wallets{" +
                "wallet=" + wallet +
                ", app_commission=" + app_commission +
                ", total_delivery_cost=" + total_delivery_cost +
                ", total_driver_revenue=" + total_driver_revenue +
                ", app_revenue=" + app_revenue +
                ", wallet_details=" + wallet_details +
                '}';
    }
}

