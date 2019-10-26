package com.umeng.soexample.model;

import android.content.Context;

import com.umeng.soexample.Callback.BeanCallback;

public interface GarbageAPIMode {

    void reuqestBasicGarbage(Context context, String keyword,String score , BeanCallback.OnBasicGarbageListeners beanCallback);

    void reuqestExamData(Context context,int type,BeanCallback.OnPopularGarbageListeners beanCallback);

    void reuqestPopularGarbage(Context context,BeanCallback.OnPopularGarbageListeners beanCallback);

    void reuqestInformationData(Context context,String word,int num,BeanCallback.OnInformationGarbageListeners beanCallback);

  /*  void reuqestImageGarbage(String access_token,String img, Context context, BeanCallback<Goods> beanCallback);*/
}

