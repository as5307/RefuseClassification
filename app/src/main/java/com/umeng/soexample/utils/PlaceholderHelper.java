package com.umeng.soexample.utils;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.umeng.soexample.R;

import me.samlss.broccoli.PlaceholderParameter;

/**
 * 占位控件
 */
public class PlaceholderHelper {

    public static PlaceholderParameter getParameter(View view) {
        if (view == null) {
            return null;
        }
        return getParameter(view.getId(), view);
    }

    private static PlaceholderParameter getParameter(int viewId, View view) {
        int placeHolderColor = Color.parseColor("#DDDDDD");
        switch (viewId) {
            case R.id.tv_time:
                return getRectanglePlaceholder(view, placeHolderColor, 5);
            case R.id.tv_content:
                return getRectanglePlaceholder(view, placeHolderColor, 5);
            case R.id.iv_image:
                return getOvalPlaceholder(view, placeHolderColor);
            case R.id.tv_type:
                return getRectanglePlaceholder(view, placeHolderColor, 5);
            default:
                break;

        }
        return null;
    }

    /**
     * 圆形的占位
     *
     */
    private static PlaceholderParameter getOvalPlaceholder(View view, int placeHolderColor) {
        return getPlaceholder(view, DrawableUtils.createOvalDrawable(placeHolderColor));
    }

    /**
     * 矩形的占位
     */
    private static PlaceholderParameter getRectanglePlaceholder(View view, int placeHolderColor, int cornerRadius) {
        return getPlaceholder(view, DrawableUtils.createRectangleDrawable(placeHolderColor, cornerRadius));
    }

    private static PlaceholderParameter getPlaceholder(View view, GradientDrawable ovalDrawable) {
        return new PlaceholderParameter.Builder()
                .setView(view)
                .setDrawable(ovalDrawable)
                .build();
    }
}
