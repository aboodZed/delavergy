package com.webapp.delavergy.models;

import java.io.Serializable;

public class Order implements Serializable {

    private long id;
    private long create_at;
    private String status;
    private String type;

    public Order(long id, long create_at, String status, String type) {
        this.id = id;
        this.create_at = create_at;
        this.status = status;
        this.type = type;
    }

    public Order() {
    }

    public long getId() {
        return id;
    }

    public long getCreate_at() {
        return create_at;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }
}
