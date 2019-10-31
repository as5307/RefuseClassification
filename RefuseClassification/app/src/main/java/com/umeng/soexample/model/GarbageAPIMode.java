package com.umeng.soexample.model;

import android.content.Context;

import com.umeng.soexample.Callback.BeanCallback;

public interface GarbageAPIMode {

    void reuqestBasicGarbage(Context context, String keyword,String score , BeanCallback.OnBasicGarbageListeners basicGarbageListeners);

    void reuqestPopularGarbage(Context context,BeanCallback.OnPopularGarbageListeners popularGarbageListeners);

    void reuqestNewsData(Context context,String word,int num,BeanCallback.OnNewsListeners newsGarbageListeners);

}

