package com.speedata.ui.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Handler;
import android.widget.ImageView;

import com.speedata.deivice.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * //                            _ooOoo_
 * //                           o8888888o
 * //                           88" . "88
 * //                           (| -_- |)
 * //                            O\ = /O
 * //                        ____/`---'\____
 * //                      .   ' \\| |// `.
 * //                       / \\||| : |||// \
 * //                     / _||||| -:- |||||- \
 * //                       | | \\\ - /// | |
 * //                     | \_| ''\---/'' | |
 * //                      \ .-\__ `-` ___/-. /
 * //                   ___`. .' /--.--\ `. . __
 * //                ."" '< `.___\_<|>_/___.' >'"".
 * //               | | : `- \`.;`\ _ /`;.`/ - ` : | |
 * //                 \ \ `-. \_ __\ /__ _/ .-` / /
 * //         ======`-.____`-.___\_____/___.-`____.-'======
 * //                            `=---='
 * //
 * //         .............................................
 * //                  佛祖镇楼                  BUG辟易
 *
 * @author :EchoXBR in  2017/8/21 下午2:56.
 *         功能描述:TODO
 */

public class AsyncImageLoader {

    // 为了加快速度，在内存中开启缓存（主要应用于重复图片较多时，或者同一个图片要多次被访问，比如在ListView时来回滚动）
    public Map<String, SoftReference<Drawable>> imageCache = new HashMap<String, SoftReference<Drawable>>();
    private ExecutorService executorService = Executors.newFixedThreadPool(5); // 固定五个线程来执行任务
    private final Handler handler = new Handler();
    // SD卡上图片储存地址
    private final String path = Environment.getExternalStorageDirectory()
            .getPath() + "/maiduo";

    /**
     *
     * @param imageUrl
     *            图像url地址
     * @param callback
     *            回调接口
     * @return 返回内存中缓存的图像，第一次加载返回null
     */
    public Drawable loadDrawable(final String imageUrl,
                                 final ImageCallback callback) {
        // 如果缓存过就从缓存中取出数据
        if (imageCache.containsKey(imageUrl)) {
            SoftReference<Drawable> softReference = imageCache.get(imageUrl);
            if (softReference.get() != null) {
                return softReference.get();
            }
        } else if (useTheImage(imageUrl) != null) {
            return useTheImage(imageUrl);
        }
        // 缓存中没有图像，则从网络上取出数据，并将取出的数据缓存到内存中
        executorService.submit(new Runnable() {
            public void run() {
                try {
                    final Drawable drawable = Drawable.createFromStream(
                            new URL(imageUrl).openStream(), "image.jpg");
                    imageCache.put(imageUrl, new SoftReference<Drawable>(
                            drawable));
                    handler.post(new Runnable() {
                        public void run() {
                            callback.imageLoaded(drawable);
                        }
                    });
                    saveFile(drawable, imageUrl);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return null;
    }

    // 从网络上取数据方法
    public Drawable loadImageFromUrl(String imageUrl) {
        try {

            return Drawable.createFromStream(new URL(imageUrl).openStream(),
                    "image.jpg");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 对外界开放的回调接口
    public interface ImageCallback {
        // 注意 此方法是用来设置目标对象的图像资源
        public void imageLoaded(Drawable imageDrawable);
    }

    // 引入线程池，并引入内存缓存功能,并对外部调用封装了接口，简化调用过程
    public void LoadImage(final String url, final ImageView iv) {
        if (iv.getImageMatrix() == null) {
//            iv.setImageResource(null);
    }
        // 如果缓存过就会从缓存中取出图像，ImageCallback接口中方法也不会被执行
        Drawable cacheImage = loadDrawable(url,
                new AsyncImageLoader.ImageCallback() {
                    // 请参见实现：如果第一次加载url时下面方法会执行
                    public void imageLoaded(Drawable imageDrawable) {
                        iv.setImageDrawable(imageDrawable);
                    }
                });
        if (cacheImage != null) {
            iv.setImageDrawable(cacheImage);
        }
    }

    /**
     * 保存图片到SD卡上
     * @param dw
     * @param url
     */
    public void saveFile(Drawable dw, String url) {
        try {
            BitmapDrawable bd = (BitmapDrawable) dw;
            Bitmap bm = bd.getBitmap();

            // 获得文件名字
            final String fileNa = url.substring(url.lastIndexOf("/") + 1,
                    url.length()).toLowerCase();
            File file = new File(path + "/image/" + fileNa);
            // 创建图片缓存文件夹
            boolean sdCardExist = Environment.getExternalStorageState().equals(
                    android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
            if (sdCardExist) {
                File maiduo = new File(path);
                File ad = new File(path + "/image");
                // 如果文件夹不存在
                if (!maiduo.exists()) {
                    // 按照指定的路径创建文件夹
                    maiduo.mkdir();
                    // 如果文件夹不存在
                } else if (!ad.exists()) {
                    // 按照指定的路径创建文件夹
                    ad.mkdir();
                }
                // 检查图片是否存在
                if (!file.exists()) {
                    file.createNewFile();
                }
            }

            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(file));
            bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * 使用SD卡上的图片
     *
     */
    public Drawable useTheImage(String imageUrl) {

        Bitmap bmpDefaultPic = null;

        // 获得文件路径
        String imageSDCardPath = path
                + "/image/"
                + imageUrl.substring(imageUrl.lastIndexOf("/") + 1,
                imageUrl.length()).toLowerCase();
        File file = new File(imageSDCardPath);
        // 检查图片是否存在
        if (!file.exists()) {
            return null;
        }
        bmpDefaultPic = BitmapFactory.decodeFile(imageSDCardPath, null);

        if (bmpDefaultPic != null || bmpDefaultPic.toString().length() > 3) {
            Drawable drawable = new BitmapDrawable(bmpDefaultPic);
            return drawable;
        } else
            return null;
    }
}
