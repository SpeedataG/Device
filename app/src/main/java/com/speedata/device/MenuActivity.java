package com.speedata.device;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.speedata.device.helper.HelperActivity;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvGpio;
    private TextView tvConfig;
    private TextView tvSerialport;
    private TextView tvI2C;

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
        }
    }

}
