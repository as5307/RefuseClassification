package com.umeng.soexample.model;

import android.app.Activity;
import android.content.Context;

import com.umeng.soexample.Callback.SqlCallback;
import com.umeng.soexample.bean.User;


public interface SqlMode {

    void queryObjectId(Activity activity, String userId, SqlCallback.OnIdDataListeners onIdDataListeners);

    void queryPostInfo(Activity activity,String field, SqlCallback.OnPostListeners onPostListeners);

    void queryJsonInfo(Context context, SqlCallback.OnJsonListeners onJsonListeners);

    void queryBannerInfo(Activity activity,SqlCallback.OnBannerListeners onBannerListeners);

    void addDataSql(Activity activity, String name, String imageUrl,String userId,SqlCallback.OnAddListeners onAddDataListeners);

    void uploadFileDataSql(String file, SqlCallback.OnuploadFileListeners uploadFile);
}
