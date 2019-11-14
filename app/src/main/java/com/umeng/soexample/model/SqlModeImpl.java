package com.umeng.soexample.model;


import android.app.Activity;
import android.content.Context;

import com.umeng.soexample.Callback.SqlCallback;
import com.umeng.soexample.bean.Banner;
import com.umeng.soexample.bean.Guide;
import com.umeng.soexample.bean.Json;
import com.umeng.soexample.bean.Post;
import com.umeng.soexample.bean.User;


import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;
import cn.bmob.v3.listener.UploadFileListener;

public  class SqlModeImpl implements SqlMode {

    @Override
    public void queryObjectId(Activity activity, String userId, final SqlCallback.OnIdDataListeners onIdDataListeners) {
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("userId", userId);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                onIdDataListeners.onIdDataSuccess(list,e);

            }
        });
    }

    @Override
    public void getLineDataInfo(Activity activity, String objectId, SqlCallback.OnLineDataListeners onLineDataListeners) {
        BmobQuery<User> bmobQuery = new BmobQuery<User>();
        bmobQuery.getObject(objectId, new QueryListener<User>() {
            @Override
            public void done(User user,BmobException e) {
                onLineDataListeners.onSuccess(user,e);
                }
        });
    }

    @Override
    public void queryPostInfo(Activity activity, String name, SqlCallback.OnPostListeners onPostListeners) {
        BmobQuery<Post> bmobQuery = new BmobQuery<Post>();
        bmobQuery.order("-createdAt");
        bmobQuery.addWhereEqualTo("userId",name);

        bmobQuery.findObjects(new FindListener<Post>() {
            @Override
            public void done(List<Post> list, BmobException e) {
                    onPostListeners.onSuccess(list,e);
                }
        });
    }

    @Override
    public void queryJsonInfo(Context context, SqlCallback.OnJsonListeners onJsonListeners) {
        BmobQuery<Json> bmobQuery = new BmobQuery<Json>();
        bmobQuery.findObjects(new FindListener<Json>() {
            @Override
            public void done(List<Json> list, BmobException e) {
                onJsonListeners.onSuccess(list.get(0).getUrl(),e);
            }
        });
    }

    @Override
    public void getBannerDataInfo(Activity activity, SqlCallback.OnBannerListeners onBannerListeners) {
        BmobQuery<Banner> query = new BmobQuery<>();
        query.findObjects(new FindListener<Banner>() {
            @Override
            public void done(List<Banner> list, BmobException e) {
                onBannerListeners.onBanner(list,e);

            }
        });
    }


    @Override
    public void addDataSql(Activity activity, String name, String imageUrl,String userId,final SqlCallback.OnAddListeners onAddDataListeners) {
        User user = new User();
        user.setName(name);
        user.setImageUrl(imageUrl);
        user.setUserId(userId);
        user.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                onAddDataListeners.onAddSuccees(s,e);
            }
        });
    }

    @Override
    public void uploadFileDataSql(String file, final SqlCallback.OnuploadFileListeners uploadFile) {
        BmobFile bmobFile=new BmobFile(new File(file));
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                uploadFile.onUploadSuccess(bmobFile.getFileUrl(),e);
            }

            @Override
            public void doneError(int code, String msg) {
                uploadFile.onUploadError(code,msg);
            }


            @Override
            public void onProgress(Integer value) {
                uploadFile.onUploadProgress(value);
            }
        });
    }
}
