package com.webapp.delavergy.models;

public class MyLocation {

    private double lat;
    private double lng;
    private int driver_id;
    private String status;
    private String name;
    private String mobile;

    public MyLocation() {
    }


    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    @Override
    public String toString() {
        return "MyLocation{" +
                "driver_id=" + driver_id +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}

