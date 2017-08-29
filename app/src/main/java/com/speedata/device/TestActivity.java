package com.speedata.device;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.speedata.ui.adapter.CommonAdapter;
import com.speedata.ui.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class TestActivity extends Activity {

    private GridView noScrollgridview;
    private CommonAdapter<String> adapter;
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        String path = "http://218.247.237.138:8083/jgweb/file/device/20170821133219615.jpg";
        list.add(path);
        noScrollgridview = (GridView) findViewById(R.id.noScrollgridview);
        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new CommonAdapter<String>(TestActivity.this, list, R.layout.adapter_bitmap) {
            @Override
            public void convert(ViewHolder helper, String item) {
                helper.setImageURL(R.id.img, item);
            }
        };

        noScrollgridview.setAdapter(adapter);
    }
}
