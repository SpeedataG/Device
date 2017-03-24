package com.speedata.device;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.speedata.bean.Gpio;
import com.speedata.libutils.GpioUtils;
import com.speedata.ui.adapter.CommonAdapter;
import com.speedata.ui.adapter.ViewHolder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllGpiosAct extends BaseActivity {

    private ListView listView;
    private CommonAdapter<Gpio> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("GPIO View");
        setContentView(R.layout.activity_gpios);
        listView = (ListView) findViewById(R.id.list);
        List list = MainGPIO();
        list.remove(0);
        List<Gpio> gpios = GpioUtils.GetAllGPIO(GpioUtils.MAIN);
        adapter = new CommonAdapter<Gpio>(this, gpios, R.layout.adapter_gpios) {
            @Override
            public void convert(ViewHolder helper, final Gpio item) {
                helper.setText(R.id.tv_1, item.getNum() + "");
                helper.setText(R.id.tv_2, item.getMode() + "");
                helper.setText(R.id.tv_3, item.getSel() + "");
                helper.setText(R.id.tv_4, item.getDin() + "");
                helper.setText(R.id.tv_5, item.getDout() + "");
                helper.setText(R.id.tv_6, item.getEn() + "");
                helper.setText(R.id.tv_7, item.getDir() + "");
                helper.setText(R.id.tv_8, item.getIes() + "");
                helper.setText(R.id.tv_9, item.getSmt() + "");
                showColor(helper.getView(R.id.tv_5), false);
                helper.getView(R.id.tv_5).setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(View view) {
                        showColor(view, true);
                        Toast.makeText(AllGpiosAct.this, item.getNum() + "", Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }

            private void showColor(View view, boolean isChange) {
                TextView tv = (TextView) view;
                String replace = tv.getText().toString().replace(" ", "");
                if (isChange) {
                    if (replace.equals("1")) {

                        tv.setText("0");
                        view.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    } else {
                        tv.setText("1");
                        view.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    }
                } else {
                    if (replace.equals("1")) {
                        view.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    } else {
                        view.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    }
                }
            }

        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    public List<String> MainGPIO() {
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

    public class SetGpioDialog extends Dialog {

        public SetGpioDialog(Context context) {
            super(context);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialog_setgpio);
        }
    }
}
