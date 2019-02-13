package com.speedata.libutils;

import android.os.Build;

import com.speedata.bean.Gpio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brxu on 2017/3/23.
 */

public class GpioUtils {
    /**
     * 主板上电文件
     */
    public static String MAIN = "sys/class/misc/mtgpio/pin";
//    public static String MAIN = "/sys/bus/platform/drivers/mediatek-pinctrl/10005000.pinctrl/mt_gpio";

    /**
     * 读取制定文件 解析List<Gpio>返回
     * @param path 上电文件
     * @return List<Gpio>
     */
    public static List<Gpio> GetAllGPIO(String path) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List lists = new ArrayList();
        String line = null;
        try {
                while ((line = reader.readLine()) != null) {
                    lists.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        lists.remove(0);
        List<Gpio> gpios = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            String item = lists.get(i).toString();
            String gpio = item.substring(item.indexOf(":") + 1);
            gpio = gpio.replace("-", "");
            String num = item.substring(0, item.indexOf(":"));
            String s1 = gpio.substring(0, 1);
            String s2 = gpio.substring(1, 2);
            String s3 = gpio.substring(2, 3);
            String s4 = gpio.substring(3, 4);
            String s5 = gpio.substring(4, 5);
            String s6 = gpio.substring(5, 6);
            String s7 = gpio.substring(6, 7);
            String s8 = gpio.substring(7, 8);
            Gpio temp = new Gpio();
            temp.setNum(num);
            temp.setMode(s1);
            temp.setSel(s2);
            temp.setDin(s3);
            temp.setDout(s4);
            temp.setEn(s5);
            temp.setDir(s6);
            temp.setIes(s7);
            temp.setSmt(s8);
            gpios.add(temp);
        }
        return gpios;
    }
    // 判断上电路径
    public static String getMAIN(){
        if (Build.MODEL.equals("SD55")){
            MAIN = "/sys/bus/platform/drivers/mediatek-pinctrl/10005000.pinctrl/mt_gpio";
        }else {
            MAIN = "sys/class/misc/mtgpio/pin";
        }
        return MAIN;
    }

}
