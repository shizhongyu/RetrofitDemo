package com.example.zhongyu.retrofitdemo.RxAndrid;

import android.content.Intent;
import android.util.Log;

import retrofit.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.observers.Observers;

/**
 * Created by zhongyu on 1/2/2016.
 */
public class RxAndroidUtils {
    private static final String TAG = "RxAndroidUtils";

    public static void helloWorld(){
        Observable.just("Hello rx Android")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d(TAG, "call() called with: " + "s = [" + s + "]");
                    }
                });
    }

    public static void rxCreate() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {

            }
        });
    }
    
    public static void RxFrom() {
        Integer[] items = {0,1,2,3,4,5};
        Observable.from(items)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "call() returned: " + integer);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.d(TAG, "call() called with: " + "throwable = [" + throwable + "]");
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        Log.d(TAG, "call() returned: " + "complete");
                    }
                });
    }



    


}












