package com.umeng.soexample.model;


import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.orhanobut.hawk.Hawk;
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
    private static final String TAG="SqlModeImpl" ;

    @Override
    public void queryObjectId(Activity activity, String userId, final SqlCallback.OnIdDataListeners onIdDataListeners) {
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("userId", userId);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                Log.d(TAG, "done: 查询objectid成功");
                onIdDataListeners.onIdDataSuccess(list,e);

            }
        });
    }

    @Override
    public void queryPostInfo(Activity activity,String field,SqlCallback.OnPostListeners onPostListeners) {
        User user=new User();
        user.setObjectId(Hawk.get("objectId"));
        BmobQuery<Post> bmobQuery = new BmobQuery<Post>();
        bmobQuery.order("-createdAt");
        if (field != null) {
            bmobQuery.addWhereEqualTo(field,user);
        }
        bmobQuery.include("user");
        bmobQuery.findObjects(new FindListener<Post>() {
            @Override
            public void done(List<Post> list, BmobException e) {
                    Log.d(TAG, "done: 查询帖子成功"+list.size());
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
                Log.d(TAG, "done: 查询json数据成功");
                onJsonListeners.onSuccess(list.get(0).getUrl(),e);
            }
        });
    }

    @Override
    public void queryBannerInfo(Activity activity, SqlCallback.OnBannerListeners onBannerListeners) {
        BmobQuery<Banner> query = new BmobQuery<>();
        query.findObjects(new FindListener<Banner>() {
            @Override
            public void done(List<Banner> list, BmobException e) {
                Log.d(TAG, "done: 查询轮播图数据成功");
                onBannerListeners.onSuccess(list,e);

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
                Log.d(TAG, "done: 添加数据成功");
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
                Log.d(TAG, "done: 上传文件成功");
                uploadFile.onUploadSuccess(bmobFile.getFileUrl(),e);
            }

            @Override
            public void doneError(int code, String msg) {
                Log.e(TAG, "done: 上传文件失败");
                uploadFile.onUploadError(code,msg);
            }


            @Override
            public void onProgress(Integer value) {
                Log.d(TAG, "done: 上传进度"+value);
                uploadFile.onUploadProgress(value);
            }
        });
    }
}
