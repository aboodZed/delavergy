package com.webapp.delavergy.services.api;

import com.webapp.delavergy.services.api.fun.AuthData;
import com.webapp.delavergy.services.api.fun.NotificationData;
import com.webapp.delavergy.services.api.fun.OrderData;
import com.webapp.delavergy.services.api.fun.PasswordData;
import com.webapp.delavergy.services.api.fun.SettingData;

public class GetAPIData {

    private AuthData authData;
    private NotificationData notificationData;
    private SettingData settingData;
    private PasswordData passwordData;
    private OrderData orderData;
    public GetAPIData() {
        authData = new AuthData();
        notificationData = new NotificationData();
        settingData = new SettingData();
        passwordData = new PasswordData();
        orderData = new OrderData();
    }

    public AuthData getAuthData() {
        return authData;
    }

    public NotificationData getNotificationData() {
        return notificationData;
    }

    public SettingData getSettingData() {
        return settingData;
    }

    public PasswordData getPasswordData() {
        return passwordData;
    }

    public OrderData getOrderData() {
        return orderData;
    }
}
