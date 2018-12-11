package com.speedata.device;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.speedata.device.gen.GPSSatellite;
import com.speedata.device.helper.HelperActivity;
import com.speedata.device.telephony.BaseStationActivity;
import com.speedata.libutils.excel.ExcelUtils;
import com.umeng.analytics.MobclickAgent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.write.Colour;

public class MenuActivity extends Activity implements View.OnClickListener {

    private TextView tvGpio;
    private TextView tvConfig;
    private TextView tvSerialport;
    private TextView tvI2C;
    private TextView tvGPS;
    private TextView tvGsm;
    private TextView tvEditConfig;
    private Context mContext;
    private String mPageName = "speedata_tools";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        MobclickAgent.setDebugMode(true);
        // SDK在统计Fragment时，需要关闭Activity自带的页面统计，
        // 然后在每个页面中重新集成页面统计的代码(包括调用了 onResume 和 onPause 的Activity)。
        MobclickAgent.openActivityDurationTrack(false);
        MobclickAgent.setScenarioType(mContext, MobclickAgent.EScenarioType.E_UM_NORMAL);
        initView();
        //        excelTest();
    }

    private void initView() {
        setContentView(R.layout.activity_menu);
        tvConfig =  findViewById(R.id.tv_config);
        tvGpio =  findViewById(R.id.tv_gpio);
        tvSerialport =  findViewById(R.id.tv_serial_port);
        tvI2C =  findViewById(R.id.tv_i2c);
        tvConfig.setOnClickListener(this);
        tvGpio.setOnClickListener(this);
        tvSerialport.setOnClickListener(this);
        tvI2C.setOnClickListener(this);
        tvGPS =  findViewById(R.id.tv_gps);
        tvGsm =  findViewById(R.id.tv_gsmcell);
        tvGPS.setOnClickListener(this);
        tvGsm.setOnClickListener(this);
        tvEditConfig =  findViewById(R.id.tv_edit);
        tvEditConfig.setOnClickListener(this);
    }

    private void excelTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<GPSSatellite> list = new ArrayList<>();
                GPSSatellite satellite = new GPSSatellite();
                satellite.setCollectTime("12:30");
                satellite.setSnr(11);
                satellite.setPrn(123);
                satellite.setAzimuth(27);
                satellite.setAlmanac(true);
                satellite.setElevation(112);
                satellite.setId((long) 2);
                list.add(satellite);
                satellite = new GPSSatellite();
                satellite.setCollectTime("11:30");
                satellite.setSnr(1);
                satellite.setPrn(2);
                satellite.setAzimuth(3);
                satellite.setAlmanac(true);
                satellite.setElevation(4);
                satellite.setId((long) 5);
                list.add(satellite);
                ExcelUtils.getInstance()
                        .setSHEET_NAME("Test")//设置表格名称
                        .setFONT_COLOR(Colour.BLUE)//设置标题字体颜色
                        .setFONT_TIMES(8)//设置标题字体大小
                        .setFONT_BOLD(true)//设置标题字体是否斜体
                        .setBACKGROND_COLOR(Colour.GRAY_25)//设置标题背景颜色
                        .setContent_list_Strings(list)//设置excel内容
                        .setWirteExcelPath(Environment.getExternalStorageDirectory() + File.separator + "abc.xls")
                        .createExcel(MenuActivity.this);
            }
        }).start();
    }

    @Override
    public void onClick(View view) {
        if (view == tvConfig) {
            //配置文件
            startActivity(new Intent(this, ConfigAct.class));
//            SerialPortSpd serialPort=new SerialPortSpd();
//            try {
//                serialPort.OpenSerial("dev/ttyMT1",9600);
//                int fd = serialPort.getFd();
//                byte [] re=serialPort.writeThenRead(fd, "123123".getBytes(), 512, 50,
//                        9600);
//                Log.i("stw", "onClick: "+DataConversionUtils.byteArrayToAscii(re));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        } else if (view == tvGpio) {
            //GPIO控制
            startActivity(new Intent(this, MainGpiosAct.class));
        } else if (view == tvSerialport) {
            //串口助手
            startActivity(new Intent(this, HelperActivity.class));
        } else if (view == tvGPS) {
            startActivity(new Intent(this, GPSAct.class));
        } else if (view == tvGsm) {
            //基站
            startActivity(new Intent(this, BaseStationActivity.class));
        } else if (view == tvEditConfig) {
            Toast.makeText(this, "尚未开发", Toast.LENGTH_SHORT).show();
        }
        //        else if (view == tvI2C) {
        //            startActivity(new Intent(this, I2CActivity.class));
        //        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Toast.makeText(this, keyCode + "", Toast.LENGTH_SHORT).show();
        return super.onKeyDown(keyCode, event);

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(mPageName);
        MobclickAgent.onResume(mContext);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(mPageName);
        MobclickAgent.onPause(mContext);
    }
}
