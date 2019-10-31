package com.umeng.soexample.model;

import android.app.Activity;
import android.content.Context;

import com.umeng.soexample.Callback.SqlCallback;



public interface SqlMode {

    void queryObjectId(Activity activity, String userId, SqlCallback.OnIdDataListeners onIdDataListeners);

    void getLineDataInfo(Activity activity, String objectId, SqlCallback.OnLineDataListeners onLineDataListeners);


    void queryPostInfo(Activity activity, String name, SqlCallback.OnPostListeners onPostListeners);

    void queryJsonInfo(Context context, SqlCallback.OnJsonListeners onJsonListeners);

    void getBannerDataInfo(Activity activity,SqlCallback.OnBannerListeners onBannerListeners);

    void addDataSql(Activity activity, String name, String imageUrl,String userId,SqlCallback.OnAddListeners onAddDataListeners);

    void uploadFileDataSql(String file, SqlCallback.OnuploadFileListeners uploadFile);
}
