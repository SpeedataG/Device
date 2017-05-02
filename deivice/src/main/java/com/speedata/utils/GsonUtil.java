package com.speedata.utils;

import android.text.TextUtils;

import com.google.gson.Gson;

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
 * @author Reginer on 2016/7/22 16:40.
 *         Description:GsonUtil
 */

public class GsonUtil {


    /**
     * 将json映射成bean对象
     *
     * @param result json字符串
     * @param clazz  bean对象字节码
     */
    public static <T> T json2Bean(String result, Class<T> clazz) {
        if (TextUtils.isEmpty(result))
            return null;
        Gson gs = new Gson();
        return gs.fromJson(result, clazz);

    }


}
