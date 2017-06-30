package com.speedata.device;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.speedata.device.gen.GPSSatellite;
import com.speedata.device.helper.HelperActivity;
import com.speedata.libutils.excel.ExcelUtils;

import java.util.ArrayList;
import java.util.List;

import jxl.write.Colour;

public class MenuActivity extends Activity implements View.OnClickListener {

    private TextView tvGpio;
    private TextView tvConfig;
    private TextView tvSerialport;
    private TextView tvI2C;
    private TextView tvGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        tvConfig = (TextView) findViewById(R.id.tv_config);
        tvGpio = (TextView) findViewById(R.id.tv_gpio);
        tvSerialport = (TextView) findViewById(R.id.tv_serial_port);
        tvI2C = (TextView) findViewById(R.id.tv_i2c);
        tvConfig.setOnClickListener(this);
        tvGpio.setOnClickListener(this);
        tvSerialport.setOnClickListener(this);
        tvI2C.setOnClickListener(this);
        tvGPS = (TextView) findViewById(R.id.tv_gps);
        tvGPS.setOnClickListener(this);

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
                        .createExcel(MenuActivity.this);
            }
        }).start();


    }

    @Override
    public void onClick(View view) {
        if (view == tvConfig) {
            startActivity(new Intent(this, ConfigAct.class));
        } else if (view == tvGpio) {
            startActivity(new Intent(this, MainGpiosAct.class));
        } else if (view == tvSerialport) {
            startActivity(new Intent(this, HelperActivity.class));
        } else if (view == tvI2C) {
            startActivity(new Intent(this, I2CActivity.class));
        } else if (view == tvGPS) {
            startActivity(new Intent(this, GPSAct.class));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Toast.makeText(this, keyCode + "", Toast.LENGTH_SHORT).show();
        return super.onKeyDown(keyCode, event);

    }
}
