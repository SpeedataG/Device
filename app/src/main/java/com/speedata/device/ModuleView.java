package com.speedata.device;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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
 * @author :Reginer in  2017/3/10 10:16.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
public class ModuleView extends LinearLayout {

    private TextView mTvModule;
    private AutoCompleteTextView mTvSerialPort;
    private AutoCompleteTextView mTvBraut;
    private AutoCompleteTextView mTvPowerType;
    private AutoCompleteTextView mTvGpio;
    //测试

    public ModuleView(Context context) {
        this(context, null);
    }

    public ModuleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ModuleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_item, this, true);
        mTvModule = (TextView) findViewById(R.id.tv_module);
        mTvSerialPort = (AutoCompleteTextView) findViewById(R.id.tv_serial_port);
        mTvSerialPort.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item,
                new String[]{"/dev/ttyMT0", "/dev/ttyMT1", "/dev/ttyMT2", "/dev/ttyMT3"}));

        mTvBraut = (AutoCompleteTextView) findViewById(R.id.tv_braut);
        mTvBraut.setInputType(InputType.TYPE_CLASS_NUMBER);
        mTvBraut.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item,
                new String[]{"1200", "2400", "9600", "19200", "38400", "57600", "115200"}));

        mTvPowerType = (AutoCompleteTextView) findViewById(R.id.tv_power_type);
        mTvPowerType.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item,
                new String[]{"MAIN", "EXPAND", "MAIN_AND_EXPAND", "NEW_MAIN", "EXPAND2", "MAIN_AND_EXPAND2"}));

        mTvGpio = (AutoCompleteTextView) findViewById(R.id.tv_gpio);
        mTvGpio.setInputType(InputType.TYPE_CLASS_NUMBER);

    }

    public void setModule(String module) {
        mTvModule.setText(module);
    }


    public String getSerialPort() {
        String serial = mTvSerialPort.getText().toString();
        if (serial.isEmpty()) {
            return "NA";
        } else {
            return serial;
        }
    }

    public void setSerialPort(String serialPort) {
        mTvSerialPort.setText(serialPort);
    }

    public int getBraut() {
        String result = mTvBraut.getText().toString();
        if (result.isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(result);
        }
    }

    public void setBraut(String braut) {
        mTvBraut.setText(braut);
    }

    public String getPowerType() {
        String result = mTvPowerType.getText().toString();
        if (result.isEmpty()) {
            return "NA";
        } else {
            return result;
        }
    }

    public void setPowerType(String powerType) {
        mTvPowerType.setText(powerType);
    }

    public List<Integer> getGpio() {
        String result = mTvGpio.getText().toString();
        ArrayList<Integer> integers = new ArrayList<>();
        if (result.isEmpty()) {
            return integers;
        } else {
            String[] data = result.split(",");
            for (String datum : data) {
                datum.trim();
                datum = datum.replace(" ", "");
                integers.add(Integer.parseInt(datum));
            }
            return integers;
        }
    }

    public void setGpio(String gpio) {
        mTvGpio.setText(gpio.replace("[", "").replace("]", ""));
    }
}
