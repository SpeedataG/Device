package com.speedata.device.adapter;

import android.content.Context;
import android.util.Log;

import com.speedata.bean.Gpio;
import com.speedata.device.R;

import java.util.List;

import xyz.reginer.baseadapter.BaseAdapterHelper;
import xyz.reginer.baseadapter.CommonRvAdapter;

/**
 * Created by brxu on 2017/3/24.
 */

public class GpioAdapter extends CommonRvAdapter<Gpio> {

    public GpioAdapter(Context context, int layoutResId, List<Gpio> data) {
        super(context, layoutResId, data);
    }

    @Override
    public void convert(BaseAdapterHelper helper, Gpio item, int position) {

        Log.d("Reginer", ":  " + position);
        helper.setText(R.id.tv_1, item.getNum() + "");
        helper.setText(R.id.tv_2, item.getMode() + "");
        helper.setText(R.id.tv_3, item.getSel() + "");
        helper.setText(R.id.tv_4, item.getDin() + "");
        helper.setText(R.id.tv_5, item.getDout() + "");
        helper.setText(R.id.tv_6, item.getEn() + "");
        helper.setText(R.id.tv_7, item.getDir() + "");
        helper.setText(R.id.tv_8, item.getIes() + "");
        helper.setText(R.id.tv_9, item.getSmt() + "");
    }
}
