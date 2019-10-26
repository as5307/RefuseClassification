package com.umeng.soexample.model;


import android.content.Context;

import com.umeng.soexample.Callback.BeanCallback;
import com.umeng.soexample.Constans;
import com.umeng.soexample.base.BaseObserver;
import com.umeng.soexample.bean.Definition;
import com.umeng.soexample.bean.Information;
import com.umeng.soexample.bean.Popular;
import com.umeng.soexample.utils.RequestUtils;

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
    public void reuqestExamData(Context context,int type, BeanCallback.OnPopularGarbageListeners beanCallback) {
        RequestUtils.getPopularData(context, Constans.Key, type,new BaseObserver<Popular>() {
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
    public void reuqestInformationData(Context context,String word,int num, BeanCallback.OnInformationGarbageListeners beanCallback) {
        RequestUtils.getInformationData(context, Constans.Key,word,num ,new BaseObserver<Information>() {
            @Override
            public void onSuccess(List<Information> list) throws JSONException {
                beanCallback.onInformationSuccess(list);
            }

            @Override
            public void onFailure(Throwable throwable, String errorMag) {
                beanCallback.onInformationError(throwable, errorMag);

            }
        });
    }

    /*@Override
    public void reuqestImageGarbage(String access_token,String img, Context context, BeanCallback<Goods> beanCallback) {
        RequestUtils.getGoodsData(context, access_token,img, new BaseObserver<Goods>() {
            @Override
            public void onSuccess(List<Goods> list) {
                beanCallback.onSuccess(list);
            }

            @Override
            public void onFailure(Throwable throwable, String errorMag) {
                beanCallback.onError(throwable, errorMag);

            }
        });
    }*/
}





