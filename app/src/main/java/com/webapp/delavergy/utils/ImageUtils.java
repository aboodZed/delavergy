package com.webapp.delavergy.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.webapp.delavergy.R;

public class ImageUtils {

    public static RequestBuilder<Bitmap> loadImage(Activity activity, String url) {
        if (NetworkUtils.checkTheInternet()) {
            return Glide.with(activity)
                    .asBitmap()
                    .load(url)
                    .placeholder(R.drawable.img_user);
        } else {
            UIUtils.showShortToast(activity, activity.getString(R.string.no_connection));
            return null;
        }
    }

    public static void loadImageNormal(Activity activity, ProgressBar progressBar, String url, ImageView imgView) {
        if (url != null) {
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
    }


    public static Bitmap getBitmapFromImageView(ImageView imageView) throws Exception {
        if (imageView.getDrawable() != null) {
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            return bitmap;
        } else {
            throw new Exception();
        }
    }

}
