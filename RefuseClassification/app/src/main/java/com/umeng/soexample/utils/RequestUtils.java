package com.umeng.soexample.utils;


import android.content.Context;

import com.umeng.soexample.Constans;
import com.umeng.soexample.base.BaseObserver;
import com.umeng.soexample.bean.Definition;
import com.umeng.soexample.bean.Information;
import com.umeng.soexample.bean.Popular;

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

   /* public static void getGoodsData(Context context, String access_token,String image,BaseObserver<Goods> observer){
     RetrofitUtils.getApiUrl(Constans.BaseUrl2).getGoodsData(access_token,image)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(observer);
    }*/
}
