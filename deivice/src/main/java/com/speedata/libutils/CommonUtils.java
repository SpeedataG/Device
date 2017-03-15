package com.speedata.libutils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * ----------Dragon be here!----------/
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 *
 * @author :Reginer in  2017/2/22 10:33.
 *         联系方式:QQ:282921012
 *         功能描述:文件流
 */
public class CommonUtils {
    private static final String FILE_PATH = "/system/speedata.config";

    /**
     * 读取文本文件中的内容 .
     *
     * @return 文件内容
     */
    public static String readTxtFile() {
        String content = "";
        File file = new File(FILE_PATH);
        try {
            InputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            //分行读取
            while ((line = bufferedReader.readLine()) != null) {
                content += line + "\n";
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    public static boolean isExists() {
        File file = new File(FILE_PATH);

        return file.exists();
    }

    public static String getFromAssets(Context context) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(getFile()));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line;
            String result = "";
            while ((line = bufReader.readLine()) != null)
                result += line;
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getFile() {
        switch (subDeviceType()) {
            case "kt40":
                return "kt40.config";
            case "kt40q":
                return "kt40q.config";
            case "kt45":
                return "kt45.config";
            case "kt45q":
                return "kt45q.config";
            case "kt50":
                return "kt50.config";
            case "kt55":
                return "kt55.config";
            case "kt80":
                return "kt80.config";
            default:
                return "kt55.config";
        }
    }

    public static String subDeviceType() {
        if (android.os.Build.MODEL.length() < 4) {
            return "kt45";
        }
        if (android.os.Build.MODEL.length() > 4) {
            return android.os.Build.MODEL.toLowerCase().subSequence(0, 5).toString();
        } else {
            return android.os.Build.MODEL.toLowerCase().subSequence(0, 4).toString();
        }

    }

    /**
     * 获取App版本号.
     *
     * @param context 上下文
     * @return App版本号
     */
    public static String getAppVersionName(Context context) {
        return getAppVersionName(context, context.getPackageName());
    }

    /**
     * 获取App版本号.
     *
     * @param context     上下文
     * @param packageName 包名
     * @return App版本号
     */
    public static String getAppVersionName(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return null;
        }
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? null : pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
