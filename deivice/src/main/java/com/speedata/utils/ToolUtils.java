package com.speedata.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.speedata.deivice.R;

/**
 * 状态栏设置背景颜色美化，获取当前应用程序的版本号，对比机型型号
 *
 * Created by xu on 2017/5/2.
 */

public class ToolUtils {

    /**
     * 状态栏美化，设置背景颜色如363534，传入3个参数36、35、34.需要5.1以上
     *
     * @param activity
     * @param red  36
     * @param green 35
     * @param blue 34
     */
    private void initTitle(Activity activity,int red, int green, int blue) {

        Window window = activity.getWindow();
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }
        //设置状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.rgb(red, green, blue));
        }
        ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 预留出系统 View 的空间.
            ViewCompat.setFitsSystemWindows(mChildView, true);
        }
    }


    /**
     * 获取当前应用程序的版本号
     */
    private String getVersion(Context context) {
        PackageManager pm = context.getPackageManager();
        String version;
        String wrongVersion;
        try {
            PackageInfo packinfo = pm.getPackageInfo(context.getPackageName(), 0);
            version = packinfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            wrongVersion = context.getString(R.string.wrong_version);
            return wrongVersion;
        }
    }


    /**
     * 对比手持机机型与要比对的机型是否相同
     *
     * @param model 机型：kt40,kt55...
     * @return
     */
    private boolean modelComparison(String model){
        if (android.os.Build.MODEL.equalsIgnoreCase(model)) {
                return true;
        } else {
                return false;
               }
        }

}
