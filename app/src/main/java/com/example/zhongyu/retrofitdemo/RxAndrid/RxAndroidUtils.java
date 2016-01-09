package com.example.zhongyu.retrofitdemo.RxAndrid;

import android.content.Intent;
import android.util.Log;

import java.io.Serializable;

import retrofit.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.observers.Observers;
import rx.schedulers.Schedulers;

/**
 * Created by zhongyu on 1/2/2016.
 */
public class RxAndroidUtils {
    private static final String TAG = "RxAndroidUtils";
    private static final String COMPLETER = "Complete";

    public static void RxTest() {
        helloWorld();
        RxFrom();
        RxRange();
        RxRepeat(1, 2, 3);
        RxMerge();
        RxMergeDelayError();
    }

    public static void helloWorld(){
        Observable.just("Hello rx Android")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d(TAG, "call() called with: " + "s = [" + s + "]");
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

    public static void RxRange() {
        Observable.range(10, 3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted() returned: " + COMPLETER);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError() called with: " + "e = [" + e + "]");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext() returned: " + integer);
                    }
                });
    }

    public static void RxDefer() {
//        Observable<Integer> getInt() {
//            return Observable.create(sub);
//        }
    }

//    public static void RxCreate() {
//        Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//                if()
//            }
//        });
//    }
    
    public static void RxRepeat(int one, int two, int three) {
        Observable.just(one, two, three)
                .repeat(3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted() returned: " + COMPLETER); 
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError() called with: " + "e = [" + e + "]");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext() returned: " + integer);
                    }
                });
    }

    public static void RxRepeatWhen(int one, int two, int three) {


    }


    public static void RxMerge() {
        Observable<Integer> odds = Observable.just(1, 3, 5);
        Observable<Integer> evens = Observable.just(2, 4, 6);

        Observable.merge(odds, evens)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted() called with: " + COMPLETER);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError() called with: " + "e = [" + e + "]");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext() returned: " + integer);
                    }
                });
    }

    public static void RxMergeDelayError() {
        Observable<Integer> odds = Observable.just(1, 3, 5);
        Observable<Error> error = Observable.just(new Error("whoops"));
        Observable<Integer> evens = Observable.just(2, 4, 6);

        Observable.mergeDelayError(odds, error, evens)
             .subscribe(new Subscriber<Serializable>() {
                 @Override
                 public void onCompleted() {
                     Log.d(TAG, "onCompleted() returned: " + COMPLETER);
                 }

                 @Override
                 public void onError(Throwable e) {
                     Log.d(TAG, "onError() called with: " + "e = [" + e + "]");
                 }

                 @Override
                 public void onNext(Serializable serializable) {
                     Log.d(TAG, "onNext() returned: " + serializable);
                 }
             });
    }

    public static void RxRepeatDoWhile(int one, int two, int three) {

    }

}












