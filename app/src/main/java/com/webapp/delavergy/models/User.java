package com.webapp.delavergy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("mobile")
    @Expose
    private String mobile;

    @SerializedName("active")
    @Expose
    private String active;

    @SerializedName("is_online")
    @Expose
    private String is_online;

    @SerializedName("is_documented")
    @Expose
    private String is_documented;

    @SerializedName("fcm_token")
    @Expose
    private String fcm_token;

    @SerializedName("vehicle_type")
    @Expose
    private String vehicle_type;

    @SerializedName("vehicle_plate")
    @Expose
    private String vehicle_plate;

    @SerializedName("avatar_url")
    @Expose
    private String avatar_url;

    @SerializedName("Insurance_license_url")
    @Expose
    private String Insurance_license_url;

    @SerializedName("drive_license_url")
    @Expose
    private String drive_license_url;

    @SerializedName("vehicle_license_url")
    @Expose
    private String vehicle_license_url;

    @SerializedName("id_pic_url")
    @Expose
    private String id_pic_url;

    @SerializedName("vehicle_pic_url")
    @Expose
    private String vehicle_pic_url;

    @SerializedName("rate")
    @Expose
    private Double rate;

    private String password;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public boolean getActive() {
        if (active.equals("0"))
            return false;
        return true;
    }

    public boolean getIs_online() {
        if (is_online.equals("0"))
            return false;
        return true;
    }

    public boolean getIs_documented() {
        if (is_documented.equals("0"))
            return false;
        return true;
    }

    public String getFcm_token() {
        return fcm_token;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public String getVehicle_plate() {
        return vehicle_plate;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getInsurance_license_url() {
        return Insurance_license_url;
    }

    public String getDrive_license_url() {
        return drive_license_url;
    }

    public String getVehicle_license_url() {
        return vehicle_license_url;
    }

    public String getId_pic_url() {
        return id_pic_url;
    }

    public String getVehicle_pic_url() {
        return vehicle_pic_url;
    }

    public Double getRate() {
        return rate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIs_online(String is_online) {
        this.is_online = is_online;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", active='" + active + '\'' +
                ", is_online='" + is_online + '\'' +
                ", is_documented='" + is_documented + '\'' +
                ", fcm_token='" + fcm_token + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", Insurance_license_url='" + Insurance_license_url + '\'' +
                ", drive_license_url='" + drive_license_url + '\'' +
                ", vehicle_license_url='" + vehicle_license_url + '\'' +
                ", id_pic_url='" + id_pic_url + '\'' +
                ", vehicle_pic_url='" + vehicle_pic_url + '\'' +
                ", rate=" + rate +
                ", password='" + password + '\'' +
                '}';
    }
}