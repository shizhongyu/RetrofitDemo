package com.example.zhongyu.retrofitdemo;

import android.app.Activity;
import android.os.Bundle;


/**
 * Created by zhongyu on 1/2/2016.
 */
public class MainActivity extends Activity{
    private static final String TAG = "MainActivity";

    public static final String API_URL = "https://api.github.com";

    OkhttpUtils okhttpUtils = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        okHttptest();
    }


    public void init(){
        okhttpUtils = new OkhttpUtils();
    }

    private void okHttptest(){
//        okhttpUtils.getOkhttp();

        okhttpUtils.postOkhttpRequest();
    }

}
