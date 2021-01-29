package com.webapp.delavergy.services.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.webapp.delavergy.feature.main.MainActivity;
import com.webapp.delavergy.utils.AppContent;
import com.webapp.delavergy.utils.NavigateUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.e("remoteMessage", "remote" + remoteMessage.getData().toString());
        try {
            Map<String, String> map = remoteMessage.getData();
            JSONObject mapJSON = new JSONObject(map);
            String jsonObject = mapJSON.getString(AppContent.FIREBASE_MESSAGE);

            NavigateUtils.openNotification(this, jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("error", "" + e.getMessage());
        }
        /*
        {moredata=dd, message={"data":{"msg":" إليك000000237  تم استناد طلب رقم:","title":" إليك000000237  تم استناد طلب رقم:",
        "type":"delivergy","order_id":4},"sound":"mySound","icon":"myIcon","title":"4station",
        "body":" إليك000000237  تم استناد طلب رقم:","click_action":"com.webapp.a4_order_station_driver.feture.home.MainActivity"}}
         */
    }
}
