package com.example.zkw.blindview;

/*
* 根据之前算法所得到的结果去灰度化图片
* */

import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class DealPicture {

    public static double[][] W = {
            {0,0,1.0000},
            {0,0.1000,0.9000},
            {0,0.2000,0.8000},
            {0,0.3000,0.7000},
            {0,0.4000,0.6000},
            {0,0.5000,0.5000},
            {0,0.6000,0.4000},
            {0,0.7000,0.3000},
            {0,0.8000,0.2000},
            {0,0.9000,0.1000},
            {0,1.0000,0},
            {0.1000,0,0.9000},
            {0.1000,0.1000,0.8000},
            {0.1000,0.2000,0.7000},
            {0.1000,0.3000,0.6000},
            {0.1000,0.4000,0.5000},
            {0.1000,0.5000,0.4000},
            {0.1000,0.6000,0.3000},
            {0.1000,0.7000,0.2000},
            {0.1000,0.8000,0.1000},
            {0.1000,0.9000,0},
            {0.2000,0,0.8000},
            {0.2000,0.1000,0.7000},
            {0.2000,0.2000,0.6000},
            {0.2000,0.3000,0.5000},
            {0.2000,0.4000,0.4000},
            {0.2000,0.5000,0.3000},
            {0.2000,0.6000,0.2000},
            {0.2000,0.7000,0.1000},
            {0.2000,0.8000,0},
            {0.3000,0,0.7000},
            {0.3000,0.1000,0.6000},
            {0.3000,0.2000,0.5000},
            {0.3000,0.3000,0.4000},
            {0.3000,0.4000,0.3000},
            {0.3000,0.5000,0.2000},
            {0.3000,0.6000,0.1000},
            {0.3000,0.7000,0.0000},
            {0.4000,0,0.6000},
            {0.4000,0.1000,0.5000},
            {0.4000,0.2000,0.4000},
            {0.4000,0.3000,0.3000},
            {0.4000,0.4000,0.2000},
            {0.4000,0.5000,0.1000},
            {0.4000,0.6000,0.0000},
            {0.5000,0,0.5000},
            {0.5000,0.1000,0.4000},
            {0.5000,0.2000,0.3000},
            {0.5000,0.3000,0.2000},
            {0.5000,0.4000,0.1000},
            {0.5000,0.5000,0},
            {0.6000,0,0.4000},
            {0.6000,0.1000,0.3000},
            {0.6000,0.2000,0.2000},
            {0.6000,0.3000,0.1000},
            {0.6000,0.4000,0.0000},
            {0.7000,0,0.3000},
            {0.7000,0.1000,0.2000},
            {0.7000,0.2000,0.1000},
            {0.7000,0.3000,0.0000},
            {0.8000,0,0.2000},
            {0.8000,0.1000,0.1000},
            {0.8000,0.2000,0.0000},
            {0.9000,0,0.1000},
            {0.9000,0.1000,0.0000},
            {1.0000,0,0}
    };

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Bitmap getGray(Bitmap bitmap){
        GrayChoose grayChoose = new GrayChoose();
        int num = grayChoose.getw(bitmap);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] pixels = new int[width*height];
        bitmap.getPixels(pixels,0,width,0,0,width,height);
        int alpha = 0xFF << 24;
        for (int i = 0;i < height; i++){
            for (int j = 0; j < width; j++){
                int grey = pixels[width*i+j];
                int red = ((grey  & 0x00FF0000 ) >> 16);
                int green = ((grey & 0x0000FF00) >> 8);
                int blue = (grey & 0x000000FF);

                grey = (int)((float) red * W[num][0] + (float)green * W[num][1] + (float)blue * W[num][2]);
                grey = alpha | (grey << 16) | (grey << 8) | grey;
                pixels[width * i + j] = grey;
            }
        }
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        result.setPixels(pixels, 0, width, 0, 0, width, height);
        return result;
    }
}
