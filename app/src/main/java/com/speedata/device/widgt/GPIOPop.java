package com.speedata.device.widgt;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.speedata.device.R;


public class GPIOPop extends PopupWindow {

    private EditText editText;
    private Button confirmBtn;

    public interface OnButtonClickListener {
        void onClick(int position, int content);
    }

    OnButtonClickListener onButtonClickListener;

    public void setOnButtonClickListener(
            OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    @SuppressWarnings("deprecation")
    public GPIOPop(final Context context, View parentView, final int position) {
        View view = View.inflate(context, R.layout.layout_pop_gpio, null);
        RelativeLayout ll = view.findViewById(R.id.ll);
        editText = view.findViewById(R.id.et_edit);
        confirmBtn = view.findViewById(R.id.btn_confirm);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onButtonClickListener != null) {
                    String content = editText.getText().toString();
                    try {
                        onButtonClickListener.onClick(position, Integer.parseInt(content));
                    } catch (Exception e) {
                        Toast.makeText(context, "输入非法，只能输入数字", Toast.LENGTH_SHORT).show();
                    }finally {
                        dismiss();
                    }
                }
            }
        });


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

        setBackgroundDrawable(new BitmapDrawable());
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
        showAtLocation(parentView, Gravity.CENTER, 0, 0);
        update();
    }

}
