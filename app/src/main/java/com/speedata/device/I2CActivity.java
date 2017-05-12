package com.speedata.device;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class I2CActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etShow; //显示窗口
    private Button mBusScan; //总线扫描按钮
    private Button mDeviceQuery; //设备查询按钮


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i2c);
        initView();
    }

    private void initView() {
        etShow = (EditText) findViewById(R.id.displaywindow_et);
        mBusScan = (Button) findViewById(R.id.i2c_detect_l_btn);
        mDeviceQuery = (Button) findViewById(R.id.i2c_detect_y1_btn);
        mBusScan.setOnClickListener(this);
        mDeviceQuery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.i2c_detect_l_btn:
                try {
                    execCommand("i2cdetect -l");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.i2c_detect_y1_btn:
                try {
                    execCommand("i2cdetect -y 1");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
        }
    }

    public void execCommand(String command) throws IOException {
        // start the ls command running
        //String[] args =  new String[]{"sh", "-c", command};
        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec(command);        //这句话就是shell与高级语言间的调用
        //如果有参数的话可以用另外一个被重载的exec方法
        //实际上这样执行时启动了一个子进程,它没有父进程的控制台
        //也就看不到输出,所以需要用输出流来得到shell执行后的输出
        InputStream inputstream = proc.getInputStream();
        InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
        BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
        // read the ls output
        String line = "";
        StringBuilder sb = new StringBuilder(line);
        while ((line = bufferedreader.readLine()) != null) {
            //System.out.println(line);
            sb.append(line);
            sb.append('\n');
        }
        //tv.setText(sb.toString());
        etShow.append(sb.toString() + "\n");
        //使用exec执行不会等执行成功以后才返回,它会立即返回
        //所以在某些情况下是很要命的(比如复制文件的时候)
        //使用wairFor()可以等待命令执行完成以后才返回
        try {
            if (proc.waitFor() != 0) {
                System.err.println("exit value = " + proc.exitValue());
            }
        }
        catch (InterruptedException e) {
            System.err.println(e);
        }
    }


}
