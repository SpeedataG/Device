package com.speedata.device;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.speedata.libutils.ConfigUtils;
import com.speedata.libutils.ReadBean;

import xyz.reginer.baseadapter.BaseAdapterHelper;
import xyz.reginer.baseadapter.CommonRvAdapter;

public class DoConfigAct extends AppCompatActivity {

    RecyclerView configView;
    ReadBean mRead;
    private CommonRvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_config);
        initData();
        initUI();

    }

    private void initData() {
        mRead = ConfigUtils.readConfig(this);
    }


    private void initUI() {
        configView = (RecyclerView) findViewById(R.id.config_data);
//        adapter = new CommonRvAdapter(this, R.layout.adapter_config,) {
//            @Override
//            public void convert(BaseAdapterHelper helper, Object item, int position) {
//
//            }
//        };
        configView.setAdapter(adapter);
    }
}
