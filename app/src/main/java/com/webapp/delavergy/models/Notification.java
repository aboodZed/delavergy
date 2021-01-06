package com.webapp.delavergy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notification {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("notifiable_type")
    @Expose
    private String notifiable_type;

    @SerializedName("notifiable_id")
    @Expose
    private String notifiable_id;

    @SerializedName("data")
    @Expose
    private NotificationData notificationData;

    @SerializedName("read_at")
    @Expose
    private String read_at;

    @SerializedName("created_at")
    @Expose
    private long created_at;

    @SerializedName("updated_at")
    @Expose
    private String updated_at;

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getNotifiable_type() {
        return notifiable_type;
    }

    public String getNotifiable_id() {
        return notifiable_id;
    }

    public NotificationData getNotificationData() {
        return notificationData;
    }

    public String getRead_at() {
        return read_at;
    }

    public long getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", notifiable_type='" + notifiable_type + '\'' +
                ", notifiable_id='" + notifiable_id + '\'' +
                ", data=" + notificationData +
                ", read_at='" + read_at + '\'' +
                ", created_at=" + created_at +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}