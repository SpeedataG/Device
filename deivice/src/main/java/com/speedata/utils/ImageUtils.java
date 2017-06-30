package com.speedata.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;

/**
 * Created by echo on 2017/6/19.
 */

public class ImageUtils { public static byte[] compressBitmap(Bitmap bmp) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
    // 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
    if (baos.toByteArray().length > 2048) {
        baos.reset();// 重置baos即清空baos
        bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);// 这里压缩50%，把压缩后的数据存放到baos中
        System.out.println("compressBitmap len=" + baos.toByteArray().length);
    }
    final byte[] data = baos.toByteArray();
    System.out.println("compressBitmap len=" + data.length);
//        logger.d("pic len=" + data.length);
    return data;
}

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }


    public static byte[] getPicByte(Bitmap icon) {
        int width = 200;
        int height = 300;
        byte[] photo_data = ImageUtils.compressBitmap(ImageUtils.reduce(icon, width, height, true));

        Bitmap reduce = null;
        while (photo_data.length > 2046) {
            width = width - 20;
            height = height - 20;
            reduce = ImageUtils.reduce(icon, width, height, true);
            photo_data = ImageUtils.compressBitmap(reduce);
            System.out.println("===photo_data.size=" + photo_data.length);
        }
        return photo_data;
    }


    /**
     * 压缩图片 经测试ok
     *
     * @param bitmap   源图片
     * @param width    想要的宽度
     * @param height   想要的高度
     * @param isAdjust 是否自动调整尺寸, true图片就不会拉伸，false严格按照你的尺寸压缩
     * @return Bitmap
     */
    public static Bitmap reduce(Bitmap bitmap, int width, int height, boolean isAdjust) {
        // 如果想要的宽度和高度都比源图片小，就不压缩了，直接返回原图
        if (bitmap.getWidth() < width && bitmap.getHeight() < height) {
            return bitmap;
        }
        // 根据想要的尺寸精确计算压缩比例, 方法详解：public BigDecimal divide(BigDecimal divisor, int scale, int roundingMode);
        // scale表示要保留的小数位, roundingMode表示如何处理多余的小数位，BigDecimal.ROUND_DOWN表示自动舍弃
        float sx = new BigDecimal(width).divide(new BigDecimal(bitmap.getWidth()), 4, BigDecimal.ROUND_DOWN).floatValue();
        float sy = new BigDecimal(height).divide(new BigDecimal(bitmap.getHeight()), 4, BigDecimal.ROUND_DOWN).floatValue();
        if (isAdjust) {// 如果想自动调整比例，不至于图片会拉伸
            sx = (sx < sy ? sx : sy);
            sy = sx;// 哪个比例小一点，就用哪个比例
        }
        Matrix matrix = new Matrix();
        matrix.postScale(sx, sy);// 调用api中的方法进行压缩，就大功告成了
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }


    /**
     * 创建logo(给图片加水印),
     * @param bitmaps 原图片和水印图片
     * @param left 左边起点坐标
     * @param top 顶部起点坐标t
     * @return Bitmap
     */
//    public Bitmap createLogo(Bitmap[] bitmaps, int left, int top) {
//        Bitmap newBitmap = Bitmap.createBitmap(bitmaps[0].getWidth(), bitmaps[0].getHeight(), Config.ARGB_8888);
//        Canvas canvas = new Canvas(newBitmap);
//        for (int i = 0; i < bitmaps.length; i++) {
//            if (i == 0) {
//                canvas.drawBitmap(bitmaps[0], 0, 0, null);
//            } else {
//                canvas.drawBitmap(bitmaps[i], left, top, null);
//            }
//            canvas.save(Canvas.ALL_SAVE_FLAG);canvas.restore();
//        }
//        return newBitmap;
//    }

    /**
     * 在图片上印字
     * @param bitmap 源图片
     * @param text 印上去的字
     * @param param 字体参数分别为：颜色,大小,是否加粗,起点x,起点y; 比如：{color : 0xFF000000, size : 30, bold : true, x : 20, y : 20}
     * @return Bitmap
     */
//    public Bitmap printWord(Bitmap bitmap, String text, Map<String, Object> param) {
//        if (text.equals("") || null == param) {return bitmap;}
//        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
//        Canvas canvas = new Canvas(newBitmap);
//        canvas.drawBitmap(bitmap, 0, 0, null);canvas.save(Canvas.ALL_SAVE_FLAG);canvas.restore();
//        Paint paint = new Paint();
//        paint.setColor(null != param.get("color") ? (Integer) param.get("color") : Color.BLACK);
//        paint.setTextSize(null != param.get("size") ? (Integer) param.get("size") : 20);
//        paint.setFakeBoldText(null != param.get("bold") ? (Boolean) param.get("bold") : false);
//        canvas.drawText(text, null != param.get("x") ? (Integer) param.get("x") : 0, null != param.get("y") ? (Integer) param.get("y") : 0, paint);
//        canvas.save(Canvas.ALL_SAVE_FLAG);canvas.restore();
//        return newBitmap;
//    }

    /**
     * 放大或缩小图片
     * @param bitmap 源图片
     * @param ratio 放大或缩小的倍数，大于1表示放大，小于1表示缩小
     * @return Bitmap
     */
    public Bitmap zoom(Bitmap bitmap, float ratio) {
        if (ratio < 0f) {return bitmap;}
        Matrix matrix = new Matrix();
        matrix.postScale(ratio, ratio);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /**
     * 旋转图片
     * @param bitmap 源图片
     * @param angle 旋转角度(90为顺时针旋转,-90为逆时针旋转)
     * @return Bitmap
     */
    public Bitmap rotate(Bitmap bitmap, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}
