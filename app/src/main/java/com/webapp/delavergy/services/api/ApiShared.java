package com.webapp.delavergy.services.api;

public class ApiShared {

    private static final ApiShared instance = new ApiShared();

    public static ApiShared getInstance(){
        return instance;
    }


    public ApiShared() {

    }
}
