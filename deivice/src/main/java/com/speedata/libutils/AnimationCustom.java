package com.speedata.libutils;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import com.speedata.deivice.R;

/**
 * Created by brxu on 2017/3/21.
 */

public class AnimationCustom {
    public static void showLogoAnimation(Context context){
        Animation operatingAnim = AnimationUtils.loadAnimation(context, R.anim.logo);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
    }
}
