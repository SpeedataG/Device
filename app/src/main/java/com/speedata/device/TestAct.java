package com.speedata.device;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.speedata.bean.Gpio;
import com.speedata.libutils.GpioUtils;

import java.util.List;

import xyz.reginer.baseadapter.BaseAdapterHelper;
import xyz.reginer.baseadapter.CommonRvAdapter;
import xyz.reginer.refresh.RRefreshLayout;

public class TestAct extends BaseActivity implements RRefreshLayout.OnRefreshListener,
        RRefreshLayout.OnLoadMoreListener{

    List<Gpio> gpios;
    private RRefreshLayout mRrlLayout;
    private CommonRvAdapter<Gpio> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        RecyclerView mRvContent = (RecyclerView) findViewById(R.id.rv_content);
        mRrlLayout = (RRefreshLayout) findViewById(R.id.rrl_layout);
        mRvContent.setLayoutManager(new LinearLayoutManager(this.getBaseContext()));
        mRvContent.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        listView = (ListView) findViewById(R.id.list);
        gpios = GpioUtils.GetAllGPIO(GpioUtils.MAIN);
        adapter = new CommonRvAdapter<Gpio>(this, R.layout.adapter_gpios, gpios) {
            @Override
            public void convert(BaseAdapterHelper helper, final Gpio item, int position) {
                helper.setText(R.id.tv_1, item.getNum() + "");
                helper.setText(R.id.tv_2, item.getMode() + "");
                helper.setText(R.id.tv_3, item.getSel() + "");
                helper.setText(R.id.tv_4, item.getDin() + "");
                helper.setText(R.id.tv_5, item.getDout() + "");
                helper.setText(R.id.tv_6, item.getEn() + "");
                helper.setText(R.id.tv_7, item.getDir() + "");
                helper.setText(R.id.tv_8, item.getIes() + "");
                helper.setText(R.id.tv_9, item.getSmt() + "");
//                showColor(helper.getView(R.id.tv_5), false);
            }
        };
        mRvContent.setAdapter(adapter);
        mRrlLayout.setLoadMoreColorSchemeResources(android.R.color.background_dark);
        mRrlLayout.setOnRefreshListener(this);
        mRrlLayout.setOnLoadMoreListener(this);
        mRrlLayout.measure(0, 0);
        mRrlLayout.setRefreshing(true);
        onRefresh();



    }


    @Override
    public void onLoadMore() {
        gpios.clear();
        gpios = GpioUtils.GetAllGPIO(GpioUtils.MAIN);
        if (adapter != null)
            adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        if (gpios != null) {
            gpios.clear();
            gpios = GpioUtils.GetAllGPIO(GpioUtils.MAIN);
            if (adapter != null)
                adapter.notifyDataSetChanged();
            mRrlLayout.setRefreshing(false);
        }
    }
}
