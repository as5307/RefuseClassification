package com.umeng.soexample.Callback;

import com.umeng.soexample.bean.Definition;
import com.umeng.soexample.bean.Information;
import com.umeng.soexample.bean.Popular;

import org.json.JSONException;

import java.util.List;

/*
请求api获取实体类回调接口
*/
public class BeanCallback{
    public interface OnBasicGarbageListeners{
        void onBasicSuccess(List<Definition> list,String keyword,String score) throws JSONException;
        void onBasicError(Throwable throwable, String errorMag,String keyword,String score);
    }

    public interface OnPopularGarbageListeners {
        void onPopularSuccess(List<Popular> list) throws JSONException;
        void onPopularError(Throwable throwable, String errorMag);
    }

    public interface OnNewsListeners {
        void onSuccess(List<Information> list) throws JSONException;
        void onError(Throwable throwable, String errorMag);
    }
}
