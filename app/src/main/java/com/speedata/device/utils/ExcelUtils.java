package com.speedata.device.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;


import com.speedata.device.gen.GPSSatellite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * Created by echo on 2017/5/18.
 */

public class ExcelUtils {
    public static WritableFont arial14font = null;

    public static WritableCellFormat arial14format = null;
    public static WritableFont arial10font = null;
    public static WritableCellFormat arial10format = null;
    public static WritableFont arial12font = null;
    public static WritableCellFormat arial12format = null;

    public final static String UTF8_ENCODING = "UTF-8";
    public final static String GBK_ENCODING = "GBK";

    public static void format() {
        try {
            arial14font = new WritableFont(WritableFont.ARIAL, 14,
                    WritableFont.BOLD);
            arial14font.setColour(jxl.format.Colour.LIGHT_BLUE);
            arial14format = new WritableCellFormat(arial14font);
            arial14format.setAlignment(jxl.format.Alignment.CENTRE);
            arial14format.setBorder(jxl.format.Border.ALL,
                    jxl.format.BorderLineStyle.THIN);
            arial14format.setBackground(jxl.format.Colour.VERY_LIGHT_YELLOW);
            arial10font = new WritableFont(WritableFont.ARIAL, 10,
                    WritableFont.BOLD);
            arial10format = new WritableCellFormat(arial10font);
            arial10format.setAlignment(jxl.format.Alignment.CENTRE);
            arial10format.setBorder(jxl.format.Border.ALL,
                    jxl.format.BorderLineStyle.THIN);
            arial10format.setBackground(jxl.format.Colour.LIGHT_BLUE);
            arial12font = new WritableFont(WritableFont.ARIAL, 12);
            arial12format = new WritableCellFormat(arial12font);
            arial12format.setBorder(jxl.format.Border.ALL,
                    jxl.format.BorderLineStyle.THIN);
        } catch (WriteException e) {

            e.printStackTrace();
        }
    }


    public static void initExcel(String fileName, String[] colName) {
        format();
        WritableWorkbook workbook = null;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            workbook = Workbook.createWorkbook(file);
            WritableSheet sheet = workbook.createSheet("gpsdata", 0);
            sheet.addCell((WritableCell) new Label(0, 0, fileName,
                    arial14format));
            for (int col = 0; col < colName.length; col++) {
                sheet.addCell(new Label(col, 0, colName[col], arial10format));
            }
            workbook.write();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @SuppressWarnings("unchecked")
    public static <T> void writeObjListToExcel(List<T> objList,
                                               String fileName, Context c, String className) {
        WritableWorkbook workbook = null;
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            workbook = Workbook.createWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        WritableSheet sheet = workbook.createSheet("家庭帐务表", 0);
        if (objList != null && objList.size() > 0) {
            try {
                WorkbookSettings setEncode = new WorkbookSettings();
                setEncode.setEncoding(UTF8_ENCODING);
//                Class d = Class.forName(className); //要包名+类名
//                Object o = d.newInstance();
//                Field[] fields = d.getDeclaredFields();//拿到数据成员
//                Method[] methods = d.getMethods();//拿到函数成员

                for (int ii = 0; ii < objList.size(); ii++) {
                    Object model = objList.get(ii);
                    Field[] field = model.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                    for (int j = 0; j < field.length; j++) {     //遍历所有属性
                        String name = field[j].getName();    //获取属性的名字

                        name = name.substring(0, 1).toUpperCase() + name.substring(1); //将属性的首字符大写，方便构造get，set方法
                        String type = field[j].getGenericType().toString();    //获取属性的类型
                        String final_value = "";
                        System.out.println("attribute name:" + name + "  type=" + type);
                        if (type.equals("String")) {   //如果type是类类型，则前面包含"class "，后面跟类名
                            Method m = model.getClass().getMethod("get" + name);
                            String value = (String) m.invoke(model);    //调用getter方法获取属性值
                            if (value != null) {
                                System.out.println("attribute value:" + value);
                                final_value = value;
                            }
                        }
                        if (type.equalsIgnoreCase("Integer")) {
                            Method m = model.getClass().getMethod("get" + name);
                            Integer value = (Integer) m.invoke(model);
                            if (value != null) {
                                System.out.println("attribute value:" + value);
                            }
                            final_value = value
                                    + "";
                        }
                        if (type.equalsIgnoreCase("Short")) {
                            Method m = model.getClass().getMethod("get" + name);
                            Short value = (Short) m.invoke(model);
                            if (value != null) {
                                System.out.println("attribute value:" + value);
                            }
                            final_value = value +
                                    "";
                        }
                        if (type.equalsIgnoreCase("float")) {
                            Method m = model.getClass().getMethod("get" + name);
                            Short value = (Short) m.invoke(model);
                            if (value != null) {
                                System.out.println("attribute value:" + value);
                            }
                            final_value = value +
                                    "";
                        }
                        if (type.equalsIgnoreCase("Double")) {
                            Method m = model.getClass().getMethod("get" + name);
                            Double value = (Double) m.invoke(model);
                            if (value != null) {
                                System.out.println("attribute value:" + value);
                            }
                            final_value = value + "";
                        }
                        if (type.equalsIgnoreCase("Boolean")) {
                            Method m = model.getClass().getMethod("get" + name);
                            Boolean value = (Boolean) m.invoke(model);
                            if (value != null) {
                                System.out.println("attribute value:" + value);
                            }
                            if (value)
                                final_value = "yes";
                            else
                                final_value = "no";
                        }
                        if (type.equalsIgnoreCase("Date")) {
                            Method m = model.getClass().getMethod("get" + name);
                            Date value = (Date) m.invoke(model);
                            if (value != null) {
                                System.out.println("attribute value:" + value.toLocaleString());
                            }
                            final_value = value.toLocaleString() + "";
                        }
                        sheet.addCell(new Label(ii + 1, j, final_value,
                                arial12format));
                    }

                }
//                }
                workbook.write();
                Toast.makeText(c, "导出文件成功", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (workbook != null) {
                    try {
                        workbook.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }

        }
    }


    @SuppressWarnings("unchecked")
    public static void writeListToExcel(List<GPSSatellite> objList,
                                        String fileName, Context c, String className, String[] colName) {
        format();
        WritableWorkbook workbook = null;
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            workbook = Workbook.createWorkbook(file);
            WritableSheet sheet = workbook.createSheet("gpsdata", 0);
            try {
                sheet.addCell((WritableCell) new Label(0, 0, fileName,
                        arial14format));
                for (int col = 0; col < colName.length; col++) {
                    sheet.addCell(new Label(col, 0, colName[col], arial10format));
                }
                sheet.addCell((WritableCell) new Label(0, 0, fileName,
                        arial14format));
                for (int col = 0; col < colName.length; col++) {
                    sheet.addCell(new Label(col, 0, colName[col], arial10format));
                }


                if (objList != null && objList.size() > 0) {
                    try {
                        WorkbookSettings setEncode = new WorkbookSettings();
                        setEncode.setEncoding(UTF8_ENCODING);
                        for (int ii = 0; ii < objList.size(); ii++) {
                            GPSSatellite model = objList.get(ii);
//                    Field[] field = model.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                            sheet.addCell(new Label(0, ii + 1, model.getId() + "",
                                    arial12format));
                            sheet.addCell(new Label(1, ii + 1, model.getSnr() + "",
                                    arial12format));
                            sheet.addCell(new Label(2, ii + 1, model.getPrn() + "",
                                    arial12format));
                            sheet.addCell(new Label(3, ii + 1, model.getElevation() + "",
                                    arial12format));

                            sheet.addCell(new Label(4, ii + 1, model.getAzimuth() + "",
                                    arial12format));

                            sheet.addCell(new Label(5, ii + 1, model.getCollectTime() + "",
                                    arial12format));

                        }
//                }
                        workbook.write();
                        Toast.makeText(c, "导出文件成功至根目录 test.xls", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (workbook != null) {
                            try {
                                workbook.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                    }

                }
            } catch (WriteException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        WritableSheet sheet = workbook.createSheet("gpsdata", 0);


    }


    public static Object getValueByRef(Class cls, String fieldName) {
        Object value = null;
        fieldName = fieldName.replaceFirst(fieldName.substring(0, 1), fieldName
                .substring(0, 1).toUpperCase());
        String getMethodName = "get" + fieldName;
        try {
            Method method = cls.getMethod(getMethodName);
            value = method.invoke(cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();
        }
        String dir = sdDir.toString();
        return dir;

    }
}

