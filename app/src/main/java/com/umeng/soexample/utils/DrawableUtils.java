package com.umeng.soexample.utils;

import android.annotation.TargetApi;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;


public class DrawableUtils {

    private DrawableUtils() {
        throw new UnsupportedOperationException("Can not be instantiated.");
    }

    /**
     * 矩形
     * @param color
     * @param cornerRadius
     * @return
     */
    public static GradientDrawable createRectangleDrawable(int color, float cornerRadius) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(cornerRadius);
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }

    /**
     * 圆形
     * @param color
     * @return
     */
    public static GradientDrawable createOvalDrawable(int color) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.OVAL);
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }

}
