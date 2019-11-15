package com.umeng.soexample.utils.api;


import android.content.Context;

import com.umeng.soexample.Constans;
import com.umeng.soexample.base.BaseObserver;
import com.umeng.soexample.base.BaseReponse;
import com.umeng.soexample.bean.Definition;
import com.umeng.soexample.bean.Information;
import com.umeng.soexample.bean.Popular;

import io.reactivex.Observer;

public class  RequestUtils {
    public static void getDefinitionData(Context context, String key, String word, BaseObserver<Definition> observer){
        RetrofitUtils.getApiUrl(Constans.BaseUrl).getAllData(key,word)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(observer);
    }

    public static void getPopularData(Context context, String key,int type,BaseObserver<Popular> observer){
        RetrofitUtils.getApiUrl(Constans.BaseUrl).getPopularData(key,type)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(observer);
    }

    public static void getPopularData(Context context, String key,BaseObserver<Popular> observer){
        RetrofitUtils.getApiUrl(Constans.BaseUrl).getPopularData(key)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(observer);
    }

    public static void getInformationData(Context context, String key,String word,int num,BaseObserver<Information> observer){
        RetrofitUtils.getApiUrl(Constans.BaseUrl).getInformationData(key,word,num)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(observer);
    }
}
