package com.webapp.delavergy.utils;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NavigateUtils {

    public static void replaceFragment(FragmentManager fragmentManager, Fragment fragment, int layout) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(layout, fragment);
        fragmentTransaction.commit();
    }

    public static void activityIntent(Context from, Class to, boolean back) {
        Intent intent = new Intent(from, to);
        if (!back) intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        from.startActivity(intent);
    }

    public static void activityIntentWithPage(Context from, Class to, boolean back, int pageNum) {
        Intent intent = new Intent(from, to);
        intent.putExtra(AppContent.PAGE, pageNum);
        if (!back) intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        from.startActivity(intent);
    }
}