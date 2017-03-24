package com.speedata.device.ui.header;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.speedata.device.BaseActivity;
import com.speedata.device.R;

/**
 * ----------Dragon be here!----------/
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 * 创   建:Reginer in  2017/2/17 14:24.
 * 联系方式:QQ:282921012
 * 功能描述:页面头布局
 */
public class PageHeaderView extends RelativeLayout implements View.OnClickListener {


    public  ImageView mLeftView;
    private TextView mMiddleView;
    private ImageView mRightView;

    public PageHeaderView(Context context) {
        this(context, null);
    }

    public PageHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PageHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_page_header, this, true);
        mLeftView = (ImageView) findViewById(R.id.iv_view_left);
        mMiddleView = (TextView) findViewById(R.id.tv_view_title);
        mRightView = (ImageView) findViewById(R.id.tv_view_right);


        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.PageHeaderView);

        if (attributes != null) {
            int titleDrawable = attributes.getResourceId(R.styleable
                    .PageHeaderView_title_left_icon, R.drawable.bg_back);
            setLeftIcon(titleDrawable);
            String title = attributes.getString(R.styleable.PageHeaderView_title_middle_word);
            if (!TextUtils.isEmpty(title))
                mMiddleView.setText(title);
            int titleColor = attributes.getColor(R.styleable
                    .PageHeaderView_title_middle_word_color, Color.WHITE);
            mMiddleView.setTextColor(titleColor);
            String rightTitle = attributes.getString(R.styleable.PageHeaderView_title_right_word);
//            if (!TextUtils.isEmpty(rightTitle))
//                mRightView.setText(rightTitle);
            int rightColor = attributes.getColor(R.styleable
                    .PageHeaderView_title_right_word_color, Color.WHITE);
//            mRightView.setTextColor(rightColor);

            mLeftView.setOnClickListener(this);
            mLeftView.setOnClickListener(this);
            attributes.recycle();
        }
    }

    public ImageView getmRightView() {
        return mRightView;
    }

    public void setmRightView(ImageView mRightView) {
        this.mRightView = mRightView;
    }

    public void setLeftIcon(int leftIcon) {
        mLeftView.setImageResource(leftIcon);
    }

    public void setTitle(String title) {
        mMiddleView.setText(title);
    }

    public void setTitleColor(@ColorInt int titleColor) {
        mMiddleView.setTextColor(titleColor);
    }
    public void setRightIcon(int leftIcon) {
        mRightView.setImageResource(leftIcon);
    }
//    public void setRightTitle(String title) {
//        mRightView.setText(title);
//    }
//
//    public void setRightTitleColor(@ColorInt int titleColor) {
//        mRightView.setTextColor(titleColor);
//    }

    @Override
    public void onClick(View v) {
        ((BaseActivity) this.getContext()).finish();
    }

    public void setLeftVisible(boolean visible) {
        mLeftView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
