package com.umeng.soexample.utils.api;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.umeng.soexample.ApiUrl;
import com.umeng.soexample.Constans;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitUtils {
    private static volatile RetrofitUtils retrofitUtils = null;

    private static ApiUrl apiUrl;

    //单例模式
    public static ApiUrl getInstance(String url) {
        if (retrofitUtils == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofitUtils == null) {
                    apiUrl = new RetrofitUtils().getApiUrl(url);
                }
            }
        }
        return apiUrl;
    }

    private RetrofitUtils(){

    }
    public static ApiUrl getApiUrl(String url) {
        ApiUrl apiUrl=initRetrofit(initOkHttp(),url).create(ApiUrl.class);
        return apiUrl;

    }

    public static Retrofit initRetrofit(OkHttpClient okHttpClient,String url) {
        Retrofit retrofit = new Retrofit.Builder().
                client(okHttpClient)
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static OkHttpClient initOkHttp(){
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .readTimeout(Constans.DEFAULT_TIME,TimeUnit.SECONDS)
                .connectTimeout(Constans.DEFAULT_TIME, TimeUnit.SECONDS)
                .writeTimeout(Constans.DEFAULT_TIME, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        return okHttpClient;
    }
}

