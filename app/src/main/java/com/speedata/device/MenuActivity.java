package com.speedata.device;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        TextView tvConfig = (TextView) findViewById(R.id.tv_config);
        TextView tvGpio = (TextView) findViewById(R.id.tv_gpio);
        tvConfig.setOnClickListener(this);
        tvGpio.setOnClickListener(this);
        findViewById(R.id.tv_edit).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_config:
                startActivity(new Intent(this, ConfigAct.class));
                break;
            case R.id.tv_gpio:
                startActivity(new Intent(this, AllGpiosAct.class));
                break;
            case R.id.tv_edit:
                startActivity(new Intent(this, EditConfigActivity.class));
                break;

            default:
                break;
        }
    }
}
