package com.pediy.kanxue.util;

import android.content.Context;
import android.widget.Toast;

import com.pediy.kanxue.App;

public final class ToastUtils {

    private ToastUtils() {

    }

    public static void show(Context context, int resId, int duration) {
        Toast.makeText(context, resId, duration).show();
    }

    public static void show(Context context, String msg, int duration) {
        Toast.makeText(context, msg, duration).show();
    }

    public static void showShort(int resId) {
        showShort(App.getInstance().getString(resId));
    }

    public static void showShort(String msg) {
        Toast.makeText(App.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(int resId) {
        showLong(App.getInstance().getString(resId));
    }

    public static void showLong(String msg) {
        Toast.makeText(App.getInstance(), msg, Toast.LENGTH_LONG).show();
    }
}