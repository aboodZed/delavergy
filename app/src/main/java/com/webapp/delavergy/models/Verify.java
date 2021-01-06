package com.webapp.delavergy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Verify extends Result {

    @SerializedName("access_token")
    @Expose
    private String access_token;

    public String getAccess_token() {
        return access_token;
    }

    @Override
    public String toString() {
        return "Verify{" +
                "access_token='" + access_token + '\'' +
                '}';
    }
}
