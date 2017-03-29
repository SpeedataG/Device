package com.speedata.device;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.speedata.bean.Gpio;
import com.speedata.libutils.GpioUtils;

import java.util.List;

import xyz.reginer.baseadapter.BaseAdapterHelper;
import xyz.reginer.baseadapter.CommonRvAdapter;
import xyz.reginer.refresh.RRefreshLayout;

/**
 * Created by brxu on 2017/3/23.
 */

public class AllGpioFragment extends BaseFrag implements RRefreshLayout.OnRefreshListener,
        RRefreshLayout.OnLoadMoreListener {
    //    private ListView listView;
    private CommonRvAdapter<Gpio> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_gpios, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//            onRefresh();
//        }
//    }


    List<Gpio> gpios;
    private RRefreshLayout mRrlLayout;

    private void initView(View view) {
        RecyclerView mRvContent = (RecyclerView) view.findViewById(R.id.rv_content);
        mRrlLayout = (RRefreshLayout) view.findViewById(R.id.rrl_layout);
        mRvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvContent.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager
                .VERTICAL));
//        listView = (ListView) view.findViewById(R.id.list);
        gpios = GpioUtils.GetAllGPIO(GpioUtils.MAIN);
        adapter = new CommonRvAdapter<Gpio>(getActivity(), R.layout.adapter_gpios, gpios) {
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
                helper.getView(R.id.tv_5).setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), item.getNum() + "", Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
        };
        mRvContent.setAdapter(adapter);
        mRrlLayout.setLoadMoreColorSchemeResources(android.R.color.background_dark);
        mRrlLayout.setOnRefreshListener(this);
        mRrlLayout.setOnLoadMoreListener(this);
        mRrlLayout.measure(0, 0);
        mRrlLayout.setRefreshing(true);
        onRefresh();

//            private void showColor(View view, boolean isChange) {
//                TextView tv = (TextView) view;
//                String replace = tv.getText().toString().replace(" ", "");
//                if (isChange) {
//                    if (replace.equals("1")) {
//
//                        tv.setText("0");
//                        view.setBackgroundColor(getResources().getColor(R.color.colorRed));
//                    } else {
//                        tv.setText("1");
//                        view.setBackgroundColor(getResources().getColor(R.color.colorGreen));
//                    }
//                } else {
//                    if (replace.equals("1")) {
//                        view.setBackgroundColor(getResources().getColor(R.color.colorGreen));
//                    } else {
//                        view.setBackgroundColor(getResources().getColor(R.color.colorRed));
//                    }
//                }
//            }


    }


    @Override
    public void onLoadMore() {
//        gpios.clear();
//        gpios = GpioUtils.GetAllGPIO(GpioUtils.MAIN);
//        if (adapter != null)
//            adapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), "no more", Toast.LENGTH_SHORT).show();
        mRrlLayout.setRefreshing(false);
        return;
    }

    @Override
    public void onRefresh() {
        if (gpios != null) {
            gpios.clear();
            gpios.addAll(GpioUtils.GetAllGPIO(GpioUtils.MAIN));
            if (adapter != null)
                adapter.notifyDataSetChanged();
            mRrlLayout.setRefreshing(false);
        }
    }
}
