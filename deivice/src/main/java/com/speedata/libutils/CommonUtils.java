package com.speedata.libutils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
 * 联系方式:QQ:282921012
 * 功能描述:文件流
 */
public class CommonUtils {
    public static final String FILE_PATH = "/storage/emulated/0/speedata.config";

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


    /**
     * 写文件
     *
     * @return 文件内容
     */
    public static boolean writeTxtFile(String content) {

        ShellUtils.execCmd("adb shell", false);
        ShellUtils.execCmd("mount -o rw,remount -t yaffs2 /dev/block/mtdblock3 /system", false);
        try {
            File file = new File(FILE_PATH);
            //            if (!file.exists()) {
            file.createNewFile();
            //            }
            FileOutputStream fout = new FileOutputStream(file);
            byte[] bytes = content.getBytes();
            fout.write(bytes);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public static boolean isExists() {
        File file = new File(FILE_PATH);

        return file.exists();
    }

    public static String getFromAssets(Context context) {
        String result = "";
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources()
                    .getAssets().open(getFile()));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line;
            while ((line = bufReader.readLine()) != null) {
                result += line;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ("".equals(result)) {
            try {
                InputStreamReader inputReader = new InputStreamReader(context.getResources()
                        .getAssets().open("KT50.config"));
                BufferedReader bufReader = new BufferedReader(inputReader);
                String line;
                result = "";
                while ((line = bufReader.readLine()) != null) {
                    result += line;
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getFile() {
        String subDeviceType = ConfigUtils.getModel().toUpperCase();
        return subDeviceType + ".config";

//        if (subDeviceType.contains("40")) {
//            if (subDeviceType.contains("40Q")) {
//                return "KT40Q.config";
//            } else {
//                return "KT40.config";
//            }
//        } else if (subDeviceType.contains("45")) {
//            if (subDeviceType.contains("45Q")) {
//                return "KT45Q.config";
//            } else {
//                return "KT45.config";
//            }
//        } else if (subDeviceType.contains("50")) {
//            return "KT50.config";
//        } else if (subDeviceType.contains("55")) {
//            return "KT55.config";
//        } else if (subDeviceType.contains("80")) {
//            return "KT80.config";
//        } else if (subDeviceType.contains("SD35")) {
//            return "SD35.config";
//        } else if ("SD55L".equals(subDeviceType)) {
//            return "SD55L.config";
//        } else if (subDeviceType.contains("SD55")) {
//            return "SD55.config";
//        } else if (subDeviceType.contains("SD55L")) {
//            return "SD55L.config";
//        } else if (subDeviceType.contains("SD60")) {
//            return "SD60.config";
//        } else if (subDeviceType.contains("SD80")) {
//            return "SD80.config";
//        } else if ("SD100".equals(subDeviceType)) {
//            return "SD100.config";
//        } else if ("SD100T".equals(subDeviceType)) {
//            return "SD100T.config";
//        } else {
//            return "KT50.config";
//        }


        //        switch (subDeviceType) {
        //            case "kt40":
        //            case "kt40_":
        //                return "KT40.config";
        //            case "kt40q":
        //            case "kt40q_":
        //                return "KT40Q.config";
        //            case "kt45":
        //            case "kt45_":
        //                return "KT45.config";
        //            case "kt45q":
        //            case "kt45q_":
        //                return "KT45Q.config";
        //            case "kt50":
        //            case "kt50_":
        //            case "t50":
        //            case "t50_":
        //                return "KT50.config";
        //            case "kt55":
        //            case "kt55_":
        //                return "KT55.config";
        //            case "kt80":
        //            case "kt80_":
        //            case "t80":
        //            case "t80_":
        //                return "KT80.config";
        //            default:
        //                return "KT55.config";
        //        }
    }

    public static String subDeviceType() {
        //        String model = Build.MODEL;
        //        if (model.length() < 4) {
        //            return model.toLowerCase();
        //        }
        //        if (model.length() > 4) {
        //            return model.toLowerCase().subSequence(0, 5).toString();
        //        } else {
        //            return model.toLowerCase().subSequence(0, 4).toString();
        //        }

        return ConfigUtils.getModel().toLowerCase();
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
