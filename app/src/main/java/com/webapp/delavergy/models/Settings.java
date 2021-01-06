package com.webapp.delavergy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Settings {

    @SerializedName("settings")
    @Expose
    private Setting setting;

    public Setting getSetting() {
        return setting;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "setting=" + setting +
                '}';
    }
}

class Setting {


    @SerializedName("linkedin_link")
    @Expose
    private String linkedin_link;


    @SerializedName("tax")
    @Expose
    private String tax;


    @SerializedName("delivery_cost")
    @Expose
    private String delivery_cost;


    @SerializedName("driver_wallet_limit")
    @Expose
    private String driver_wallet_limit;


    @SerializedName("app_order_commission")
    @Expose
    private String app_order_commission;


    @SerializedName("app_delivery_commission")
    @Expose
    private String app_delivery_commission;

    public String getLinkedin_link() {
        return linkedin_link;
    }

    public String getTax() {
        return tax;
    }

    public String getDelivery_cost() {
        return delivery_cost;
    }

    public String getDriver_wallet_limit() {
        return driver_wallet_limit;
    }

    public String getApp_order_commission() {
        return app_order_commission;
    }

    public String getApp_delivery_commission() {
        return app_delivery_commission;
    }

    @Override
    public String toString() {
        return "Setting{" +
                "linkedin_link='" + linkedin_link + '\'' +
                ", tax='" + tax + '\'' +
                ", delivery_cost='" + delivery_cost + '\'' +
                ", driver_wallet_limit='" + driver_wallet_limit + '\'' +
                ", app_order_commission='" + app_order_commission + '\'' +
                ", app_delivery_commission='" + app_delivery_commission + '\'' +
                '}';
    }
}
