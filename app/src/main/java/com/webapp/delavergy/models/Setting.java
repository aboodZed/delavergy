package com.webapp.delavergy.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Setting {

    @SerializedName("linkedin")
    @Expose
    private String linkin;

    @SerializedName("instagram")
    @Expose
    private String instagram;

    @SerializedName("facebook")
    @Expose
    private String facebook;

    @SerializedName("twitter")
    @Expose
    private String twitter;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("privacy")
    @Expose
    private String privacy;


    public String getLinkin() {
        return linkin;
    }

    public String getInstagram() {
        return instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPrivacy() {
        return privacy;
    }

    @Override
    public String toString() {
        return "Setting{" +
                "linkedin_link='" + linkin + '\'' +
                ", instagram='" + instagram + '\'' +
                ", facebook='" + facebook + '\'' +
                ", twitter='" + twitter + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", privacy='" + privacy + '\'' +
                '}';
    }
}