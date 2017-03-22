package com.speedata.device;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class OperationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnShoudown;
    private Button btnReboot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);
        btnShoudown = (Button) findViewById(R.id.btn_shutdown);
        btnReboot = (Button) findViewById(R.id.btn_reboot);
        btnShoudown.setOnClickListener(this);
        btnShoudown.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnReboot) {
            try {
                Runtime.getRuntime().exec("su -c \"/system/bin/reboot\"");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (v == btnShoudown) {
            try {
                Runtime.getRuntime().exec("su -c \"/system/bin/shutdown\"");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
