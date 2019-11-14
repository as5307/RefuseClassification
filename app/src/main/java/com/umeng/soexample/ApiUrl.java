package com.umeng.soexample;



import com.umeng.soexample.base.BaseReponse;
import com.umeng.soexample.bean.Definition;
import com.umeng.soexample.bean.Information;
import com.umeng.soexample.bean.Popular;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiUrl {
    @GET(Constans.Retrofit)
    Observable<BaseReponse<Definition>> getAllData(@Query("key") String key, @Query("word") String word);

    @GET(Constans.Retrofit2)
    Observable<BaseReponse<Popular>>  getPopularData(@Query("key") String key,@Query("type")int type);

    @GET(Constans.Retrofit2)
    Observable<BaseReponse<Popular>>  getPopularData(@Query("key") String key);

    @GET(Constans.Retrofit3)
    Observable<BaseReponse<Information>>  getInformationData(@Query("key") String key,@Query("word") String word,@Query("num")int num);

}
