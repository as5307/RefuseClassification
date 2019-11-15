package com.umeng.soexample.Callback;


import com.umeng.soexample.bean.Banner;
import com.umeng.soexample.bean.Guide;
import com.umeng.soexample.bean.Json;
import com.umeng.soexample.bean.Post;
import com.umeng.soexample.bean.User;


import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;


/*
数据库操作回调接口
*/
public class SqlCallback {

    //objectId索引
    public interface OnIdDataListeners {
        void onIdDataSuccess(List<User> t, BmobException e);
    }

    public interface OnPostListeners {
        void onSuccess(List<Post> t, BmobException e);
    }

    public interface OnJsonListeners {
        void onSuccess(String url, BmobException e);
    }

    public interface OnBannerListeners {
        void onSuccess(List<Banner> t, BmobException e);
    }

    //添加数据
    public interface OnAddListeners {
        void onAddSuccees(String s, BmobException e);
    }

    //上传文件
    public interface OnuploadFileListeners  {
        void onUploadSuccess(String imagurl,BmobException e);
        void onUploadProgress(Integer value);
        void onUploadError(int code, String msg);
    }
}
