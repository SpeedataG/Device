package com.speedata.device.widgt;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.speedata.device.R;
import com.speedata.device.adapter.SerialPortAdapter;

import java.util.ArrayList;
import java.util.List;


public class SerialPortPop extends PopupWindow {

    OnButtonClickListener onButtonClickListener;
    private List<String> serialPortList = new ArrayList<>();
    private List<String> brautList = new ArrayList<>();
    private List<String> typeList = new ArrayList<>();

    private SerialPortAdapter serialPortAdapter;

    @SuppressWarnings("deprecation")
    public SerialPortPop(Context context, View parentView, final int position, final STYLE style) {
        View view = View.inflate(context, R.layout.layout_pop, null);
        RelativeLayout ll = view.findViewById(R.id.ll);
        ListView listView = view.findViewById(R.id.listView);

        fillList();
        switch (style) {
            case SERIAL_PORT:
                serialPortAdapter = new SerialPortAdapter(context, serialPortList);
                break;
            case BRAUT:
                serialPortAdapter = new SerialPortAdapter(context, brautList);
                break;
            case TYPE:
                serialPortAdapter = new SerialPortAdapter(context, typeList);
                break;
        }
        listView.setAdapter(serialPortAdapter);

        setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        //        view.startAnimation(AnimationUtils.loadAnimation(context,
        //                R.anim.popup_in));

        ll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                if (onButtonClickListener != null) {
                    switch (style) {
                        case SERIAL_PORT:
                            onButtonClickListener.onClick(style, position, serialPortList.get(i));
                            break;
                        case BRAUT:
                            onButtonClickListener.onClick(style, position, brautList.get(i));
                            break;
                        case TYPE:
                            onButtonClickListener.onClick(style, position, typeList.get(i));
                            break;
                    }
                }
                dismiss();
            }
        });

        setBackgroundDrawable(new BitmapDrawable());
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
        showAtLocation(parentView, Gravity.CENTER, 0, 0);
        update();
    }

    public void setOnButtonClickListener(
            OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    private void fillList() {
        serialPortList.add("/dev/ttyMT1");
        serialPortList.add("/dev/ttyG0");
        serialPortList.add("/dev/ttyG1");
        serialPortList.add("/dev/ttyG2");
        serialPortList.add("/dev/ttyG3");
        serialPortList.add("/dev/ttyO0");
        serialPortList.add("/dev/ttyO1");
        serialPortList.add("/dev/ttyO2");
        serialPortList.add("/dev/ttyO3");
        //        serialPortList.add("eser0");
        //        serialPortList.add("eser1");
        //        serialPortList.add("eser2");
        //        serialPortList.add("eser3");
        serialPortList.add("/dev/ttyMT0");
        serialPortList.add("/dev/ttyMT2");
        serialPortList.add("/dev/ttyMT3");
        serialPortList.add("/dev/ttyHSL0");
        serialPortList.add("/dev/ttyHSL1");
        serialPortList.add("/dev/ttyHSL2");
        serialPortList.add("/dev/ttyGS0");
        //        serialPortList.add("other");

        brautList.add("1200");
        brautList.add("2400");
        brautList.add("9600");
        brautList.add("19200");
        brautList.add("38400");
        brautList.add("57600");
        brautList.add("115200");

        typeList.add("MAIN");
        typeList.add("EXPAND");
        typeList.add("MAIN_AND_EXPAND");
        typeList.add("NEW_MAIN");
        typeList.add("EXPAND2");
        typeList.add("MAIN_AND_EXPAND2");
        typeList.add("GAOTONG_MAIN");


    }

    public enum STYLE {
        SERIAL_PORT,
        BRAUT,
        TYPE
    }

    public interface OnButtonClickListener {
        void onClick(STYLE style, int position, String content);
    }
}
