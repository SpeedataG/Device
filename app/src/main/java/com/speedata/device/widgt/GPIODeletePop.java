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
import com.speedata.device.adapter.GPIODeleteAdapter;

import java.util.List;


public class GPIODeletePop extends PopupWindow {

    private GPIODeleteAdapter gPIODeleteAdapter;

    public interface OnButtonClickListener {
        void onClick(int position, int deletePosition);
    }

    OnButtonClickListener onButtonClickListener;

    public void setOnButtonClickListener(
            OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    @SuppressWarnings("deprecation")
    public GPIODeletePop(Context context, View parentView, final int position, List<Integer> list) {
        View view = View.inflate(context, R.layout.layout_pop, null);
        RelativeLayout ll = view.findViewById(R.id.ll);
        ListView listView = view.findViewById(R.id.listView);

        gPIODeleteAdapter = new GPIODeleteAdapter(context, list);
        listView.setAdapter(gPIODeleteAdapter);

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
                    onButtonClickListener.onClick(position, i);
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

    }
}
