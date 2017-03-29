package com.speedata.device;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.speedata.device.adapter.SectionsPagerAdapter;
import com.speedata.device.ui.header.PageHeaderView;

import java.util.ArrayList;
import java.util.List;

public class MainGpiosAct extends BaseActivity {

    private ViewPager mVpContainer;
    private TabLayout mTabMain;
    public static PageHeaderView mPhvHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
//        setTitle("GPIO");
//        setTitleColor(R.color.colorWhite);
        setContentView(R.layout.activity_gpios);
        mPhvHeader = (PageHeaderView) findViewById(R.id.phv_header);
        mPhvHeader.setTitleColor(getResources().getColor(R.color.colorWhite));
        mPhvHeader.setTitle("主板GPIO");
        initView();
        initEvents();

    }

    /**
     * 初始化tabLayout事件.
     */
    private void initEvents() {
        mTabMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //选中
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVpContainer.setCurrentItem(tab.getPosition());
            }

            //取消选择
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            //重新选择
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * .
     */
    @SuppressWarnings("ConstantConditions")
    private void initView() {
        List<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(new AllGpioFragment());
        mFragmentList.add(new SelectGpioFragment());
        SectionsPagerAdapter mAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mVpContainer = (ViewPager) findViewById(R.id.vp_container);
        mTabMain = (TabLayout) findViewById(R.id.tab_main);
        mTabMain.setupWithViewPager(mVpContainer);
        mVpContainer.setAdapter(mAdapter);
        mTabMain.getTabAt(0).setText("预览").setIcon(R.drawable.showview_selector);
        mTabMain.getTabAt(1).setText("筛选").setIcon(R.drawable.shaixuan_selector);

    }
}
