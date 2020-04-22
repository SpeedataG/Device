package com.speedata.utils;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import com.speedata.deivice.R;

import java.util.HashMap;
import java.util.jar.Attributes;

/**
 * 播放正确与错误的提示音
 */
public class PlaySoundPool {

    private static PlaySoundPool playSoundPool;
    private Context context;
    private SoundPool soundPool; // 定义SoundPool 对象
    private HashMap<Integer, Integer> soundPoolMap; // 定义HASH表

    private Vibrator vibrator;

    /**
     * 正常扫描声音
     */
    private static final int LASER = 1;
    /**
     * 错误声音
     */
    private static final int ERROR = 2;

    private AudioManager mgr;

    public static PlaySoundPool getPlaySoundPool(Context context) {
        if (playSoundPool == null) {
            playSoundPool = new PlaySoundPool(context);
        }
        return playSoundPool;
    }

    private PlaySoundPool(Context context) {
        this.context = context;
        initSounds();
        loadSounds();

        vibrator = (Vibrator) context
                .getSystemService(Context.VIBRATOR_SERVICE);
    }


    /**
     * 初始化
     */
    private void initSounds() {
        // 初始化soundPool 对象,
        // 第一个参数是允许有多少个声音流同时播放,
        // 第2个参数是声音类型,
        // 第三个参数是声音的品质
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(attributes)
                    .build();
        } else {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }
        // 初始化HASH表
        soundPoolMap = new HashMap<Integer, Integer>();

        mgr = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

    }

    /**
     * 把资源中的音效加载到指定的ID (播放的时候就对应到这个ID播放就行了)
     *
     * @param raw 声音资源ID
     * @param ID  自定义指定ID
     */
    private void loadSfx(int raw, int ID) {
        soundPoolMap.put(ID, soundPool.load(context, raw, ID));
    }

    /**
     * 播放声音
     *
     * @param sound
     */
    private void play(int sound) {
        soundPool.play(//
                soundPoolMap.get(sound), // 播放的音乐id
                1.0f, // 左声道音量
                1.0f, // 右声道音量
                1, // 优先级，0为最低
                0, // 循环次数，0 不循环，-1 永远循环
                1f); // 回放速度 ，该值在0.5-2.0之间，1为正常速度
    }

    /**
     * 加载声音
     */
    private void loadSounds() {
        loadSfx(R.raw.scan, LASER);
        loadSfx(R.raw.error, ERROR);
    }

    /**
     * 播放扫描成功声音
     */
    public void playLaser() {
        System.out.println("*******************LASER*******************");
        play(LASER);
    }

    /**
     * 播放错误声音
     */
    public void playError() {
        play(ERROR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, 255));
        } else {
            vibrator.vibrate(500);
        }
    }

}
