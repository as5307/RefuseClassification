package com.umeng.soexample.model;

import android.app.Activity;

import com.umeng.soexample.Callback.SqlCallback;



public interface SqlMode {

    void getIdUserInfo(Activity activity, String userID, SqlCallback.OnIdDataListeners onIdDataListeners);

    void getLineDataInfo(Activity activity, String objectId, SqlCallback.OnLineDataListeners onLineDataListeners);


    void getPostInfo(Activity activity, String name, SqlCallback.OnPostListeners onPostListeners);

    void getBannerDataInfo(Activity activity,SqlCallback.OnBannerListeners onBannerListeners);

    void addDataSql(Activity activity, String name, String imageUrl, String id, SqlCallback.OnAddDataListeners onAddDataListeners);

    void uploadFileDataSql(String file, SqlCallback.uploadFile uploadFile);
}
