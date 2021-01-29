package com.webapp.delavergy.utils;

public interface AppContent {
    String BASE_URL = "https://delivergy.rankeera.com/api/v1/";
    String PAGE = "page";
    String processing_orders = "processing orders";
    String finished_orders = "finished orders";
    String ORDER_Id = "order_id";

    String DELIVERY_APP_TRACKING = "delivery_app_tracking";

    //DRIVER STATUS
    String DRIVER_STATUS_BUSY = "busy";
    String DRIVER_STATUS_ONLINE = "online";
    String DRIVER_STATUS_OFFLINE = "offline";

    String ORDER_STATUS_COMPLETE = "complete";
    String ORDER_STATUS_PROCESSING = "processing";

    String ORDER_TYPE_MANAGEMENT = "management";
    String ORDER_TYPE_OFFICER = "officer";

    int PHONE_CALL_CODE = 1000;

    //FIREBASE
    String FIREBASE_MESSAGE = "message";
    String FIREBASE_DATA_BODY = "data";
    String NOTIFICATION_TYPE = "type";
    String NOTIFICATION_MESSAGE = "msg";
}
