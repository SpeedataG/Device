package com.speedata.device.utils;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author :Reginer in  2017/11/20 17:13.
 *         联系方式:QQ:282921012
 *         功能描述:AlertDialog工具类
 */
@SuppressWarnings("AlibabaAvoidPatternCompileInMethod")
public class AlertUtils {


    /**
     * 弹出对话框.
     *
     * @param context context
     * @param msg     对话框内容
     * @return dialog
     */
    public static AlertDialog dialog(Context context, String msg) {
        return dialog(context, "", msg);
    }

    /**
     * 弹出对话框.
     *
     * @param context context
     * @param title   对话框标题
     * @param msg     对话框内容
     * @return dialog
     */
    public static AlertDialog dialog(Context context, String title, String msg) {
        return dialog(context, title, msg, null);
    }

    /**
     * 弹出对话框.
     *
     * @param context       context
     * @param title         对话框标题
     * @param msg           对话框内容
     * @param okBtnListener 确定按钮点击事件
     * @return dialog
     */
    public static AlertDialog dialog(Context context, String title, String msg,
                                     OnClickListener okBtnListener) {
        return dialog(context, title, msg, okBtnListener, null);
    }

    /**
     * 弹出对话框.
     *
     * @param context           context
     * @param title             对话框标题
     * @param msg               对话框内容
     * @param okBtnListener     确定按钮点击事件
     * @param cancelBtnListener 取消按钮点击事件
     * @return dialog
     */
    public static AlertDialog dialog(Context context, String title, String msg,
                                     OnClickListener okBtnListener, OnClickListener cancelBtnListener) {
        return dialog(context, null, title, msg, okBtnListener, cancelBtnListener);
    }


    /**
     * 弹出对话框.
     *
     * @param context context
     * @param icon    图标
     * @param title   对话框标题
     * @param msg     对话框内容
     * @return dialog
     */
    public static AlertDialog dialog(Context context, Drawable icon, String title, String msg) {
        return dialog(context, icon, title, msg, null);
    }

    /**
     * 弹出对话框.
     *
     * @param context       context
     * @param icon          对话框图标
     * @param title         对话框标题
     * @param msg           对话框内容
     * @param okBtnListener 确定按钮点击事件
     * @return dialog
     */
    public static AlertDialog dialog(Context context, Drawable icon, String title, String msg,
                                     OnClickListener okBtnListener) {
        return dialog(context, icon, title, msg, okBtnListener, null);
    }


    /**
     * 弹出对话框 .
     *
     * @param context           context
     * @param icon              标题图标
     * @param title             对话框标题
     * @param msg               对话框内容
     * @param okBtnListener     确定按钮点击事件
     * @param cancelBtnListener 取消按钮点击事件
     * @return dialog
     */
    public static AlertDialog dialog(Context context, Drawable icon, String title, String msg,
                                     OnClickListener okBtnListener, OnClickListener cancelBtnListener) {
        Builder dialogBuilder = new Builder(context);
        if (null != icon) {
            dialogBuilder.setIcon(icon);
        }
        if (!TextUtils.isEmpty(title)) {
            dialogBuilder.setTitle(title);
        }
        dialogBuilder.setMessage(msg);
        if (null != okBtnListener) {
            dialogBuilder.setPositiveButton(android.R.string.ok, okBtnListener);
        }
        if (null != cancelBtnListener) {
            dialogBuilder.setNegativeButton(android.R.string.cancel, cancelBtnListener);
        }
        dialogBuilder.create();
        return dialogBuilder.show();
    }

    /**
     * 弹出一个自定义布局对话框.
     *
     * @param context 上下文
     * @param view    自定义布局View
     * @return dialog
     */
    public static AlertDialog dialog(Context context, View view) {
        final Builder builder = new Builder(context);
        builder.setView(view);
        return builder.show();
    }

    /**
     * 弹出一个自定义布局对话框.
     *
     * @param context 上下文
     * @param resId   自定义布局View对应的layout id
     * @return dialog
     */
    public static AlertDialog dialog(Context context, int resId) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resId, null);
        Builder builder = new Builder(context);
        builder.setView(view);
        return builder.show();
    }


    /**
     * 弹出一个自定义布局对话框.
     *
     * @param context       上下文
     * @param title         标题
     * @param editText      输入框
     * @param okBtnListener 确定事件
     * @return dialog
     */
    public static AlertDialog dialog(Context context, @DrawableRes int drawable, @StringRes int title, EditText editText, OnClickListener okBtnListener) {
        Builder dialogBuilder = new Builder(context);
        if (title != 0) {
            dialogBuilder.setTitle(title);
        }
        if (drawable != 0) {
            dialogBuilder.setIcon(drawable);
        }
        if (null != okBtnListener) {
            dialogBuilder.setPositiveButton(android.R.string.ok, okBtnListener);
        }
        dialogBuilder.setNegativeButton(android.R.string.cancel, null);
        dialogBuilder.setView(editText);
        dialogBuilder.create();

        return dialogBuilder.show();
    }


    public static boolean isNumeric(String str) {
        //这个是对的
        Pattern pattern= Pattern.compile("^(\\-|\\+)?\\d+(\\.\\d+)?$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

}
