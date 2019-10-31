package com.umeng.soexample.model;


import android.content.Context;

import com.umeng.soexample.Callback.BeanCallback;
import com.umeng.soexample.Constans;
import com.umeng.soexample.base.BaseObserver;
import com.umeng.soexample.bean.Definition;
import com.umeng.soexample.bean.Information;
import com.umeng.soexample.bean.Popular;
import com.umeng.soexample.utils.api.RequestUtils;

import org.json.JSONException;

import java.util.List;


public class GarbageAPIModeImpl implements GarbageAPIMode {

    @Override
    public void reuqestBasicGarbage(Context context,String keyword,String score,final BeanCallback.OnBasicGarbageListeners beanCallback) {
        RequestUtils.getDefinitionData(context, Constans.Key, keyword, new BaseObserver<Definition>() {
            @Override
            public void onSuccess(List<Definition> list) throws JSONException {
                beanCallback.onBasicSuccess(list,keyword,score);
            }

            @Override
            public void onFailure(Throwable throwable, String errorMag) {
                beanCallback.onBasicError(throwable, errorMag,keyword,score);

            }
        });
    }

    @Override
    public void reuqestPopularGarbage(Context context,BeanCallback.OnPopularGarbageListeners beanCallback) {
        RequestUtils.getPopularData(context, Constans.Key,new BaseObserver<Popular>() {
            @Override
            public void onSuccess(List<Popular> list) throws JSONException {
                beanCallback.onPopularSuccess(list);
            }

            @Override
            public void onFailure(Throwable throwable, String errorMag) {
                beanCallback.onPopularError(throwable, errorMag);

            }
        });
    }


    @Override
    public void reuqestNewsData(Context context,String word,int num, BeanCallback.OnNewsListeners newsListeners) {
        RequestUtils.getInformationData(context, Constans.Key,word,num ,new BaseObserver<Information>() {
            @Override
            public void onSuccess(List<Information> list) throws JSONException {
                newsListeners.onSuccess(list);
            }

            @Override
            public void onFailure(Throwable throwable, String errorMag) {
                newsListeners.onError(throwable, errorMag);

            }
        });
    }

}





