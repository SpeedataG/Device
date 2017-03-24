package com.speedata.device;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.speedata.bean.Gpio;
import com.speedata.libutils.GpioUtils;
import com.speedata.libutils.SharedXmlUtil;
import com.speedata.ui.adapter.CommonAdapter;
import com.speedata.ui.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brxu on 2017/3/23.
 */

public class SelectGpioFragment extends BaseFrag {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_gpios, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedXmlUtil = SharedXmlUtil.getInstance(getActivity(), Contants.SharedXmlName);
        sharedXmlUtil.write("gpios", "22,88,94");
        initView(view);

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            GpiosAct.mPhvHeader.getmRightView().setVisibility(View.VISIBLE);
            GpiosAct.mPhvHeader.setRightIcon(R.mipmap.icon_add);
            GpiosAct.mPhvHeader.getmRightView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final EditText edv = new EditText(getActivity());
                    edv.setInputType(InputType.TYPE_CLASS_NUMBER);
                    edv.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
                    new AlertDialog.Builder(getActivity()).setView(edv).setTitle("请输入管脚号")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    int num = Integer.parseInt(edv.getText().toString());
                                    if (num > 240) {
                                        Toast.makeText(getActivity(), "非法管脚", Toast.LENGTH_SHORT)
                                                .show();
                                        return;
                                    }
                                    List<Gpio> gpios = GpioUtils.GetAllGPIO(GpioUtils.MAIN);
                                    for (int i1 = 0; i1 < gpios.size(); i1++) {
                                        if (gpios.get(i1).getNum().trim().equals(num + "")) {
                                            selectGpios.add(gpios.get(i1));
                                            adapter.notifyDataSetChanged();
                                        }
                                    }
                                }
                            }).setNegativeButton("取消", null).show();
                }
            });
        } else {
            GpiosAct.mPhvHeader.setRightIcon(R.mipmap.icon_add);
            GpiosAct.mPhvHeader.getmRightView().setVisibility(View.INVISIBLE);
        }
    }

    List<Gpio> selectGpios = new ArrayList<Gpio>();
    private SharedXmlUtil sharedXmlUtil;
    private CommonAdapter<Gpio> adapter;

    private void initData() {
        String gpios = sharedXmlUtil.read("gpios", "");
        if (!gpios.equals("")) {
            List<Gpio> temp = GpioUtils.GetAllGPIO(GpioUtils.MAIN);
            String[] gpioss = gpios.split(",");
            for (int i = 0; i < gpioss.length; i++) {
                for (int i1 = 0; i1 < temp.size(); i1++) {
                    String num = temp.get(i1).getNum().trim();
                    String gpios1 = gpioss[i];
                    if (gpios1.equals(num)) {
                        selectGpios.add(temp.get(i1));
                    }
                }
            }
            adapter = new CommonAdapter<Gpio>(getActivity(), selectGpios, R.layout
                    .adapter_select_gpios) {
                @Override
                public void convert(ViewHolder helper, Gpio item) {
                    helper.setText(R.id.tv_1, item.getNum());
                    helper.setText(R.id.tv_2, item.getMode());
                    helper.setText(R.id.tv_3, item.getDout());
                    helper.setText(R.id.tv_4, item.getDir());
                }
            };
            listView.setAdapter(adapter);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    ListView listView;

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.list);
        initData();
    }
}
