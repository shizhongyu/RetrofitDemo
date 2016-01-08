package com.example.zhongyu.retrofitdemo.OkhttpUtils;

import android.app.DownloadManager;
import android.provider.MediaStore;
import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by zhongyu on 1/2/2016.
 */
public class OkhttpUtils {
    private static final String TAG = "OkhttpUtils";

    OkHttpClient okHttpClient = null;
    Response response = null;
    public static final String ENDPOINT = "https://api.github.com/repos/square/okhttp/contributors";
    public static final String KEY_VALUE_POST = "https://en.wikipedia.org/w/index.php";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    public void getOkhttp() {
        new Thread(new Runnable() {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(ENDPOINT)
                    .build();
            @Override
            public void run() {
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    Log.d(TAG, "run() called with: " + response.body().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void postOkhttp(final String url,final String json){
        new Thread(new Runnable() {
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = RequestBody.create(JSON,json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            @Override
            public void run() {
                try {
                    Response response =okHttpClient.newCall(request).execute();
                    Log.d(TAG, "run() called with: " + response.body().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void postOkhttpRequest(){
        new Thread(new Runnable() {
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("search","Jurassic park")
                    .build();

            Request request = new Request.Builder()
                    .url(KEY_VALUE_POST)
                    .post(requestBody)
                    .build();
            @Override
            public void run() {
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    Log.d(TAG, "postOkhttpRequest() called with: " + response.body().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}






