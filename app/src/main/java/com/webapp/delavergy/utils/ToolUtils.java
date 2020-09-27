package com.webapp.delavergy.utils;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.InputType;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.webapp.delavergy.R;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class ToolUtils {

    public static boolean checkTheInternet() {
        try {
            ConnectivityManager cm = (ConnectivityManager) AppController
                    .getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnected();
        } catch (Exception e) {
            return false;
        }
    }

    public static void showLongToast(Activity activity, String s) {
        Toast.makeText(activity, s, Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(Activity activity, String s) {
        Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
    }


    public static void showError(Activity activity, ResponseBody s) {
        try {
            Log.e("errorInNoSuccess", s.string());
            JSONObject jObjError = new JSONObject(s.string()).getJSONObject("status");
            showLongToast(activity, jObjError.getString("message"));
        } catch (Exception e) {
            if (activity != null)
                showLongToast(activity, activity.getString(R.string.error));
        }
    }

    public static RequestBuilder<Bitmap> loadImage(Activity activity, String url) {
        if (checkTheInternet()) {
            return Glide.with(activity)
                    .asBitmap()
                    .load(url);
        } else {
            showShortToast(activity, activity.getString(R.string.no_connection));
            return null;
        }
    }

    public static void loadImageNormal(Activity activity, ProgressBar progressBar, String url, ImageView imgView) {
        RequestBuilder<Bitmap> builder = loadImage(activity, url);
        if (builder != null) {
            builder.listener(new RequestListener<Bitmap>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model
                        , Target<Bitmap> target, boolean isFirstResource) {
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }
                    return false;
                }

                @Override
                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target
                        , DataSource dataSource, boolean isFirstResource) {
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }
                    return false;
                }
            }).into(imgView);
        }
    }


    public static Bitmap getBitmapFromImageView(ImageView imageView) throws Exception {
        if (imageView.getDrawable() != null) {
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            return bitmap;
        } else {
            throw new Exception();
        }
    }

    public static String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;
    }

    public static String getTime(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("hh:mm", cal).toString();
        return date;
    }

    public static String formatTime(int i) {
        String x;
        if (i < 10) {
            x = "0" + i;
        } else {
            x = "" + i;
        }
        return x;
    }
}
