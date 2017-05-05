package com.speedata.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2015/11/22.
 *
 * 时间相关方法的封装
 */


public class MyDateAndTime {
    int year;
    int month;
    int day;
    int hour;
    int min;
    int second;
    String yearAndMonth = "";
    String beforAMonth = "";

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static int GetItemId() {
        Calendar calendar = Calendar.getInstance();
        return (int) calendar.getTimeInMillis();
    }

    public String getBeforAMonth() {
        return beforAMonth;
    }

    public void setBeforAMonth(String beforAMonth) {
        this.beforAMonth = beforAMonth;
    }

    public static String getBatchCode() {
        //yyyy-MM-dd HH:mm:ss SSS
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String time = dateFormat.format(new Date());
        return time;
    }

    public static String getMakerDate() {
        //yyyy-MM-dd HH:mm:ss SSS
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String time = dateFormat.format(new Date());
        return time;
    }

    public static String getTimeString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        String time = dateFormat.format(new Date());
        return time;
    }

    public static String getTimeStringYMD() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = dateFormat.format(new Date());
        return time;
    }

    public static String getTimeString(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String time = dateFormat.format(new Date());
        return time;
    }

    public String getYearAndMonth() {
        return yearAndMonth;
    }

    public void setYearAndMonth(String yearAndMonth) {
        this.yearAndMonth = yearAndMonth;
    }

    public static MyDateAndTime praseDateAndTime(String dateandtime) {
        MyDateAndTime result = new MyDateAndTime();
        String[] date = null;// = new String[3];
        String[] time = null;// = new String[3];
        date = dateandtime.substring(0, 10).split("-");
        time = dateandtime.substring(11).split(":");
        if (date.length < 3 || time.length < 3) {
            return null;
        }
        try {
            result.setYear(Integer.parseInt(date[0]));
            result.setMonth(Integer.parseInt(date[1]));
            result.setDay(Integer.parseInt(date[2]));

            result.setHour(Integer.parseInt(time[0]));
            result.setMin(Integer.parseInt(time[1]));
            result.setSecond(Integer.parseInt(time[2]));
            if (result.getMonth() < 0) {
                result.setYearAndMonth(result.getYear() + "-0" + result.getMonth());
                result.setBeforAMonth(result.getYear() + "-0" + (result.getMonth() - 1) + "-" +
                        result.getDay());
            } else {
                result.setYearAndMonth(result.getYear() + "-" + result.getMonth());
                result.setBeforAMonth(result.getYear() + "-" + (result.getMonth() - 1) + "-" +
                        result
                        .getDay());
            }

        } catch (NumberFormatException e) {
            return null;
        }
        return result;
    }
}
