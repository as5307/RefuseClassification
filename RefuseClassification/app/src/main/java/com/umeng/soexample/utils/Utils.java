package com.umeng.soexample.utils;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.luck.picture.lib.PictureSelectionModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.umeng.soexample.R;

public class Utils {

    public static volatile Utils pictureConfig = null;

    public static Utils getInstance() {
        if (pictureConfig == null) {
            synchronized (Utils.class) {
                if (pictureConfig == null) {
                    pictureConfig = new Utils();
                }
            }
        }
        return pictureConfig;
    }


    /*
图片选择
*/
    public PictureSelectionModel getPictureSelector(Activity activity) {
        return PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())
                .theme(R.style.picture_default_style)
                .selectionMode(PictureConfig.SINGLE)
                .minSelectNum(1)
                .isCamera(true)
                .enableCrop(false)
                .compress(false)
                .previewEggs(true);
    }

    public PictureSelectionModel getPictureSelector(Fragment fragment) {
        return PictureSelector.create(fragment)
                .openGallery(PictureMimeType.ofImage())
                .theme(R.style.picture_default_style)
                .minSelectNum(1)
                .selectionMode(PictureConfig.SINGLE)
                .setOutputCameraPath("/CustomPath")
                .isCamera(true)
                .enableCrop(false)
                .compress(false)
                .previewEggs(true);
    }
}
