package com.speedata.device;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvGpio;
    private TextView tvConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        tvConfig = (TextView) findViewById(R.id.tv_config);
        tvGpio = (TextView) findViewById(R.id.tv_gpio);
        tvConfig.setOnClickListener(this);
        tvGpio.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == tvConfig) {

            startActivity(new Intent(this, ConfigAct.class));
        } else if (view == tvGpio) {
            startActivity(new Intent(this, AllGpiosAct.class));
        }
    }
}
