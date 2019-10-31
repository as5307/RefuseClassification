package com.umeng.soexample.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.just.agentweb.core.AgentWeb;
import com.luck.picture.lib.PictureSelectionModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.umeng.soexample.Callback.SqlCallback;
import com.umeng.soexample.R;

import com.umeng.soexample.activity.AgentWebActivity;
import com.umeng.soexample.model.SqlMode;
import com.umeng.soexample.model.SqlModeImpl;
import com.umeng.soexample.utils.update.CustomUpdateFailureListener;
import com.xuexiang.xupdate.XUpdate;

import cn.bmob.v3.exception.BmobException;

public class Utils  {

    public static volatile Utils pictureConfig = null;

    private SqlMode sqlMode;

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

    /**
     * 请求浏览器
     */
    public void goWeb(Context context, Bundle bundle) {
        Intent intent = new Intent(context, AgentWebActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
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


    /**
     * 进行版本更新检查
     *
     * @param context
     */
    public  void checkUpdate(Context context,boolean needErrorTip) {
        sqlMode=new SqlModeImpl();
        sqlMode.queryJsonInfo(context, new SqlCallback.OnJsonListeners() {
            @Override
            public void onSuccess(String url, BmobException e) {
                XUpdate.newBuild(context).updateUrl(url).update();
                XUpdate.get().setOnUpdateFailureListener(new CustomUpdateFailureListener(needErrorTip));
            }
        });
    }
}
