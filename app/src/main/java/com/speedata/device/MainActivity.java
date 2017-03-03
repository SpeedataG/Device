package com.speedata.device;

import android.os.Bundle;
import android.serialport.SerialPort;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SerialPort mSerialPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSerialPort = new SerialPort();

    }
}
