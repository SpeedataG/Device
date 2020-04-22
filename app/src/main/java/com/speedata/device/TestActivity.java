package com.speedata.device;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.speedata.ui.adapter.CommonAdapter;
import com.speedata.ui.adapter.ViewHolder;
import com.speedata.utils.PlaySoundPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        PlaySoundPool.getPlaySoundPool(this);
    }

    public void aClick1(View v) {
        PlaySoundPool.getPlaySoundPool(this).playLaser();
    }

    public void aClick2(View v) {
        PlaySoundPool.getPlaySoundPool(this).playError();
    }

    public void aClick3(View v) {
        PlaySoundPool.getPlaySoundPool(this).playLaser();
    }

    public void aClick4(View v) {

    }

    public void aClick5(View v) {

    }
}
