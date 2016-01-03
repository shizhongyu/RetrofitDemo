package com.example.zhongyu.retrofitdemo;

import android.util.Log;

import retrofit.Retrofit;
import rx.Observable;
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
}
