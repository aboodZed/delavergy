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
