package com.speedata.device.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.speedata.bean.BaseBean;
import com.speedata.device.R;

import java.util.List;

/**
 * @author Jason
 */
public class ModuleAdapter extends BaseQuickAdapter<BaseBean, BaseViewHolder> {

    public ModuleAdapter(@Nullable List<BaseBean> data) {
        super(R.layout.layout_item_module, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_serialPort, item.getSerialPort());
        helper.setText(R.id.tv_braut, item.getBraut() + "");
        helper.setText(R.id.tv_power_type, item.getPowerType());
        String gpio = "";
        if (item.getGpio() != null && item.getGpio().size() > 0) {
            for (int i = 0; i < item.getGpio().size(); i++) {
                if (i == item.getGpio().size() - 1) {
                    gpio += item.getGpio().get(i);
                } else {
                    gpio += item.getGpio().get(i) + ",";
                }
            }
        }
        helper.setText(R.id.tv_gpio, gpio);
        helper.addOnClickListener(R.id.tv_name);
        helper.addOnClickListener(R.id.tv_serialPort);
        helper.addOnClickListener(R.id.tv_braut);
        helper.addOnClickListener(R.id.tv_power_type);
        helper.addOnClickListener(R.id.tv_add);
        helper.addOnClickListener(R.id.tv_delete);
    }
}