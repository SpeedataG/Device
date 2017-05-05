package com.speedata.device;

import android.content.DialogInterface;
import android.os.Bundle;
import android.serialport.DeviceControl;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.speedata.bean.Gpio;
import com.speedata.libutils.GpioUtils;
import com.speedata.libutils.SharedXmlUtil;
import com.speedata.ui.adapter.CommonAdapter;
import com.speedata.ui.adapter.ViewHolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.serialport.DeviceControl.POWER_MAIN;

/**
 * Created by brxu on 2017/3/23.
 */

public class SelectGpioFragment extends BaseFrag {
    final String FIELD_GPIO = "gpios";

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
//        sharedXmlUtil.write(FIELD_GPIO, "22,88,94");
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
            MainGpiosAct.mPhvHeader.getmRightView().setVisibility(View.VISIBLE);
            MainGpiosAct.mPhvHeader.setRightIcon(R.mipmap.icon_add);
            MainGpiosAct.mPhvHeader.getmRightView().setOnClickListener(new View.OnClickListener() {
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
                                        if (gpios.get(i1).getNum().trim().replace(" ", "").equals
                                                (num + "")) {
                                            for (int i2 = 0; i2 < selectGpios.size(); i2++) {
                                                if (selectGpios.get(i2).getNum().trim().replace("" +
                                                        " ", "").equals(num + "")) {
                                                    Toast.makeText(getActivity(), "已添加该管脚", Toast
                                                            .LENGTH_SHORT).show();
                                                    return;
                                                }
                                            }
                                            selectGpios.add(gpios.get(i1));
                                            adapter.notifyDataSetChanged();
                                        }
                                    }
                                    sharedXmlUtil.write(FIELD_GPIO, getStringSelectGpio
                                            (selectGpios));
                                }
                            }).setNegativeButton("取消", null).show();
                }
            });
        } else {
            MainGpiosAct.mPhvHeader.setRightIcon(R.mipmap.icon_add);
            MainGpiosAct.mPhvHeader.getmRightView().setVisibility(View.INVISIBLE);
        }
    }

    private String getStringSelectGpio(List<Gpio> gpios) {
        String result = "";
        for (int i = 0; i < gpios.size(); i++) {
            result += gpios.get(i).getNum().trim() + ",";
        }
        String substring = result.substring(0, result.length() - 1);
        System.out.println("===result=" + substring);
        return substring.toString();
    }

    List<Gpio> selectGpios = new ArrayList<Gpio>();
    private SharedXmlUtil sharedXmlUtil;
    private CommonAdapter<Gpio> adapter;

    private void initData() {
        try {
            deviceControl = new DeviceControl(POWER_MAIN);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String gpios = sharedXmlUtil.read(FIELD_GPIO, "");
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
        }
        adapter = new CommonAdapter<Gpio>(getActivity(), selectGpios, R.layout
                .adapter_select_gpios) {
            @Override
            public void convert(final ViewHolder helper, final Gpio item) {
                helper.setText(R.id.tv_1, item.getNum());
                if (item.getMode().equals(0)) {
                    helper.setText(R.id.tv_2, "gpio");
                } else {
                    helper.setText(R.id.tv_2, item.getMode());
                }
                helper.setText(R.id.tv_3, item.getDout());
                helper.setText(R.id.tv_4, item.getDir());
                final String dout = item.getDout().trim().replace(" ", "");
                final String dir = item.getDir().trim().replace(" ", "");
                //上电
                helper.getView(R.id.tv_3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int num = Integer.parseInt(item.getNum().trim().replace(" ", ""));
                        try {
                            deviceControl = new DeviceControl(DeviceControl.PowerType.MAIN, num);
                            if (dout.equals("0")) {
                                deviceControl.PowerOnDevice();
                                helper.setText(R.id.tv_3, "1");
                                item.setDout("1");
                                helper.getView(R.id.tv_3).setBackgroundColor(getResources()
                                        .getColor(R.color
                                                .colorGreen));
                            } else if (dout.equals("1")) {
                                deviceControl.PowerOffDevice();
                                helper.setText(R.id.tv_3, "0");
                                item.setDout("0");
                                helper.getView(R.id.tv_3).setBackgroundColor(getResources()
                                        .getColor(R.color
                                                .colorBlack));
                            }
                            adapter.notifyDataSetChanged();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                helper.getView(R.id.tv_4).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int num = Integer.parseInt(item.getNum().trim().replace(" ", ""));
                        try {
                            deviceControl = new DeviceControl(DeviceControl.PowerType.MAIN);
                            if (dir.equals("0")) {
                                deviceControl.setDir(num, 1, POWER_MAIN);
                                helper.setText(R.id.tv_4, "1");
                                item.setDir("1");
                                helper.getView(R.id.tv_4).setBackgroundColor(getResources()
                                        .getColor(R.color
                                                .colorGreen));
                            } else if (dir.equals("1")) {
                                deviceControl.setDir(num, 0, POWER_MAIN);
                                helper.setText(R.id.tv_4, "0");
                                item.setDir("0");
                                helper.getView(R.id.tv_4).setBackgroundColor(getResources()
                                        .getColor(R.color
                                                .colorBlack));
                            }
                            adapter.notifyDataSetChanged();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                if (dout.equals("1")) {
                    helper.getView(R.id.tv_3).setBackgroundColor(getResources().getColor(R.color
                            .colorGreen));
                } else {
                    helper.getView(R.id.tv_3).setBackgroundColor(getResources().getColor(R.color
                            .colorBlack));
                }
                if (dir.equals("1")) {
                    helper.getView(R.id.tv_4).setBackgroundColor(getResources().getColor(R.color
                            .colorGreen));
                } else {
                    helper.getView(R.id.tv_4).setBackgroundColor(getResources().getColor(R.color
                            .colorBlack));
                }
            }
        };
        listView.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    ListView listView;

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.list);
        initData();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectGpios.remove(i);
                adapter.notifyDataSetChanged();
                sharedXmlUtil.write(FIELD_GPIO, getStringSelectGpio
                        (selectGpios));
                return false;
            }
        });
    }

    private DeviceControl deviceControl;//= new DeviceControl(DeviceControl.PowerType.MAIN);
}
