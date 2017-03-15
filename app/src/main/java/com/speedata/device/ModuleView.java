package com.speedata.device;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

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
 *         联系方式:QQ:282921012
 *         功能描述:
 */
public class ModuleView extends LinearLayout {

    private TextView mTvModule;
    private TextView mTvSerialPort;
    private TextView mTvBraut;
    private TextView mTvPowerType;
    private TextView mTvGpio;

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
        mTvSerialPort = (TextView) findViewById(R.id.tv_serial_port);
        mTvBraut = (TextView) findViewById(R.id.tv_braut);
        mTvPowerType = (TextView) findViewById(R.id.tv_power_type);
        mTvGpio = (TextView) findViewById(R.id.tv_gpio);
    }

    public void setModule(String module) {
        mTvModule.setText(module);
    }

    public void setSerialPort(String serialPort) {
        mTvSerialPort.setText(serialPort);
    }

    public void setBraut(String braut) {
        mTvBraut.setText(braut);
    }

    public void setPowerType(String powerType) {
        mTvPowerType.setText(powerType);
    }

    public void setGpio(String gpio) {
        mTvGpio.setText(gpio.replace("[","").replace("]",""));
    }
}
