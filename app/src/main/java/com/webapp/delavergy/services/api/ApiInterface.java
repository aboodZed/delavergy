package com.webapp.delavergy.services.api;

import com.webapp.delavergy.models.Code;
import com.webapp.delavergy.models.LoginResult;
import com.webapp.delavergy.models.Nothing;
import com.webapp.delavergy.models.Notifications;
import com.webapp.delavergy.models.Orders;
import com.webapp.delavergy.models.Privacy;
import com.webapp.delavergy.models.Result;
import com.webapp.delavergy.models.Settings;
import com.webapp.delavergy.models.User;
import com.webapp.delavergy.models.Verify;
import com.webapp.delavergy.models.Wallets;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {


    //auth
    @POST("auth/driverLogin")
    Call<LoginResult> login(@QueryMap Map<String, String> map);

    @GET("auth/user")
    Call<User> getProfile();

    @POST("delivery/isOnline")
    Call<Result> setOnline(@Query("is_online") int is_online);

    @POST("delivery/updateDriverLocation")
    Call<Nothing> updateLocation(@QueryMap Map<String, String> map);

    @POST("auth/updateProfile")
    Call<User> updateProfile(@QueryMap Map<String, String> map);

    //reset password
    @POST("auth/forgetPasswordRequest")
    Call<Code> forgetPassword(@Query("mobile") String mobile);

    @POST("auth/forgetPasswordVerify")
    Call<Verify> verifyCode(@Query("mobile") String mobile,
                            @Query("code") String code);

    @POST("auth/resetPassword")
    Call<Result> resetPassword(@Query("password") String password,
                               @Query("password_confirmation") String password_confirmation);

    //setting
    @GET("driverPrivacy")
    Call<Privacy> getPrivacy();

    @GET("settings")
    Call<Settings> getSetting();


    //notification
    @GET("notifications")
    Call<Notifications> getNotification();

    @POST("fcmToken")
    Call<String> sendFcmToken(@Query("token") String token);

    @POST("contact")
    Call<Result> contactUs(@QueryMap Map<String, String> map);

    //order
    @GET("delivery/myOrders")
    Call<Orders> getOrders();

    @GET("delivery/walletDetails")
    Call<Wallets> getWallets();
}
