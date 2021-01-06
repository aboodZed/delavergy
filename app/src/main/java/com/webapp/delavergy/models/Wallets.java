package com.webapp.delavergy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Wallets extends Result {

    @SerializedName("wallets")
    @Expose
    private ArrayList<Wallet> wallets = new ArrayList<>();

    @SerializedName("total_wallet")
    @Expose
    private int total_wallet;

    public ArrayList<Wallet> getWallets() {
        return wallets;
    }

    public int getTotal_wallet() {
        return total_wallet;
    }

    @Override
    public String toString() {
        return "Wallets{" +
                "wallets=" + wallets +
                ", total_wallet=" + total_wallet +
                '}';
    }
}

