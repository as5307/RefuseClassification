package com.umeng.soexample.base;

import android.util.Log;

import org.json.JSONException;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<BaseReponse<T>> {
    private final static String TAG="BaseObserver";

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: "+e.getMessage());

    }

    @Override
    public void onNext(BaseReponse<T> tBadeReponse) {
        if (tBadeReponse.getCode()==200){
            try {
                onSuccess(tBadeReponse.getNewslist());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            onFailure(null,tBadeReponse.getMsg());
        }
    }

    public abstract void onSuccess(List<T>  list) throws JSONException;


    public abstract void onFailure(Throwable throwable,String errorMag );

}
