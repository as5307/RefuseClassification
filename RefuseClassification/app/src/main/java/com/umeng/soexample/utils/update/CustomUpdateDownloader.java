package com.umeng.soexample.utils.update;



import androidx.annotation.NonNull;


import com.xuexiang.xupdate.entity.UpdateEntity;
import com.xuexiang.xupdate.proxy.impl.DefaultUpdateDownloader;
import com.xuexiang.xupdate.service.OnFileDownloadListener;
import com.xuexiang.xutil.app.ActivityUtils;

import io.reactivex.annotations.Nullable;

public class CustomUpdateDownloader extends DefaultUpdateDownloader {

    private boolean mIsStartDownload;

    @Override
    public void startDownload(@NonNull UpdateEntity updateEntity, @Nullable OnFileDownloadListener downloadListener) {
        super.startDownload(updateEntity, downloadListener);
        mIsStartDownload = true;

    }

    @Override
    public void cancelDownload() {
        super.cancelDownload();
        if (mIsStartDownload) {
            mIsStartDownload = false;
            ActivityUtils.startActivity(UpdateTipDialog.class);
        }
    }

}
