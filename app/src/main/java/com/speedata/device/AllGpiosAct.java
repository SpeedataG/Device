package com.speedata.device;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.speedata.ui.adapter.CommonAdapter;
import com.speedata.ui.adapter.ViewHolder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllGpiosAct extends AppCompatActivity {

    private ListView listView;
    private CommonAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_gpios);
        listView = (ListView) findViewById(R.id.list);
        List list = MainGPIO();
        list.remove(0);
        adapter = new CommonAdapter<String>(this, list, R.layout.adapter_gpios) {
            @Override
            public void convert(ViewHolder helper, String item) {
                String gpio = item.substring(item.indexOf(":") + 1);
                gpio=gpio.replace("-","");
                String num = item.substring(0, item.indexOf(":"));
                String s1 = gpio.substring(0, 1);
                String s2 = gpio.substring(1, 2);
                String s3 = gpio.substring(2, 3);
                String s4 = gpio.substring(3, 4);
                String s5 = gpio.substring(4, 5);
                String s6 = gpio.substring(5, 6);
                String s7 = gpio.substring(6, 7);
                String s8 = gpio.substring(7, 8);
                helper.setText(R.id.tv_1, num);
                helper.setText(R.id.tv_2, s1);
                helper.setText(R.id.tv_3, s2);
                helper.setText(R.id.tv_4, s3);
                helper.setText(R.id.tv_5, s4);
                helper.setText(R.id.tv_6, s5);
                helper.setText(R.id.tv_7, s6);
                helper.setText(R.id.tv_8, s7);
                helper.setText(R.id.tv_9, s8);
            }
        };
        listView.setAdapter(adapter);
    }

    public List MainGPIO() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("sys/class/misc/mtgpio/pin"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List lists = new ArrayList();
        String line = null;
        try {
            for (int i = 1; i < 203; i++) {
                if ((line = reader.readLine()) != null) {
                    lists.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lists;
    }
}
