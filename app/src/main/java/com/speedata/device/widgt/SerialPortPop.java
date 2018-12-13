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

    public enum STYLE {
        SERIAL_PORT,
        BRAUT,
        TYPE
    }

    private List<String> serialPortList = new ArrayList<>();
    private List<String> brautList = new ArrayList<>();
    private List<String> typeList = new ArrayList<>();

    private SerialPortAdapter serialPortAdapter;

    public interface OnButtonClickListener {
        void onClick(STYLE style, int position, String content);
    }

    OnButtonClickListener onButtonClickListener;

    public void setOnButtonClickListener(
            OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

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

    private void fillList() {
        serialPortList.add("ttyMT1");
        serialPortList.add("ttyG0");
        serialPortList.add("ttyG1");
        serialPortList.add("ttyG2");
        serialPortList.add("ttyG3");
        serialPortList.add("ttyO0");
        serialPortList.add("ttyO1");
        serialPortList.add("ttyO2");
        serialPortList.add("ttyO3");
        serialPortList.add("eser0");
        serialPortList.add("eser1");
        serialPortList.add("eser2");
        serialPortList.add("eser3");
        serialPortList.add("ttyMT0");
        serialPortList.add("ttyMT2");
        serialPortList.add("ttyMT3");
        serialPortList.add("ttyHSL0");
        serialPortList.add("ttyHSL1");
        serialPortList.add("ttyHSL2");
        serialPortList.add("ttyGS0");
        serialPortList.add("other");

        brautList.add("1200");
        brautList.add("2400");
        brautList.add("9600");
        brautList.add("19200");
        brautList.add("38400");
        brautList.add("57600");
        brautList.add("115200");

        typeList.add("ttyMT1");
        typeList.add("ttyG0");
        typeList.add("ttyG1");
        typeList.add("ttyG2");
        typeList.add("ttyG3");
        typeList.add("ttyO0");
        typeList.add("ttyO1");
        typeList.add("ttyO2");
        typeList.add("ttyO3");
        typeList.add("eser0");
        typeList.add("eser1");
        typeList.add("eser2");
        typeList.add("eser3");
        typeList.add("ttyMT0");
        typeList.add("ttyMT2");
        typeList.add("ttyMT3");
        typeList.add("ttyHSL0");
        typeList.add("ttyHSL1");
        typeList.add("ttyHSL2");
        typeList.add("ttyGS0");
        typeList.add("other");
    }
}
