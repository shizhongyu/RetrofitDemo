package com.example.zhongyu.retrofitdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.zhongyu.retrofitdemo.Bluetooth.BouetoothActivity;
import com.example.zhongyu.retrofitdemo.Dao.News;
import com.example.zhongyu.retrofitdemo.GlideUtils.GlideUtils;
import com.example.zhongyu.retrofitdemo.GreenDaoUtils.GreenDaoUtils;
import com.example.zhongyu.retrofitdemo.OkhttpUtils.OkhttpUtils;
import com.example.zhongyu.retrofitdemo.PicassoUtils.PicassoUtils;
import com.example.zhongyu.retrofitdemo.RxAndrid.RxAndroidUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;


import java.util.List;

import de.greenrobot.dao.query.Query;


/**
 * Created by zhongyu on 1/2/2016.
 */
public class MainActivity extends Activity implements View.OnClickListener{
    private static final String TAG = "MainActivity";

    public static final String API_URL = "https://api.github.com";

    OkhttpUtils okhttpUtils = null;

    private ImageView imageView;
    private ImageView imageViewGlide;
    private Button btnScanBluetooth;

    private static final String IMAGE_URL = "https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/fresco-logo.png";

    private Query<News> newsQuery;
    private List<News> newsList; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        init();
        test();
    }

    public void initViews() {
        btnScanBluetooth = (Button) findViewById(R.id.btn_scan_blurtooth);
        imageView = (ImageView) findViewById(R.id.ic_imageview);
        imageViewGlide = (ImageView) findViewById(R.id.ic_image_view_glide);
    }

    public void initActions() {
        btnScanBluetooth.setOnClickListener(this);
    }

    public void init(){
        okhttpUtils = new OkhttpUtils();
        initViews();
        initActions();
    }

//    private void retrofitTest(){
//        Retrofit retrofit = new Retrofit
//                .Builder()
//                .baseUrl(API_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        Github github = retrofit.create(Github.class);
//
//        Call<List<Contributors>> call = github.contributors("suqare", "retrofit");
//        try {
//            List<Contributors> contributorses = call.execute().body();
//            for (int i = 0; i < contributorses.size(); i++) {
//                Log.d(TAG, "retrofitTest() called with: " + contributorses.toString());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void okHttptest(){
        okhttpUtils.getOkhttp();
        okhttpUtils.postOkhttpRequest();
    }

    private void rxAndroidTest(){
        RxAndroidUtils.helloWorld();
    }

    public void greenDaoTest(){
        GreenDaoUtils greenDaoUtils = new GreenDaoUtils(this);
        greenDaoUtils.createDB();
        greenDaoUtils.addNews();
        newsQuery = greenDaoUtils.getQueryById();
        newsList = newsQuery.list();

        for (News news : newsList) {
            Log.d(TAG, "greenDaoTest() returned: " + news.getId());
        }
        
        greenDaoUtils.deleteById(3);
        greenDaoUtils.updateNews(1, "three");
        for (News news : newsList) {
            Log.d(TAG, "greenDaoTest() returned: " + news.getTitle());
        }
    }

    public void frescoTest(){
        Uri uri = Uri.parse(IMAGE_URL);
        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.ic_image_view);
        draweeView.setImageURI(uri);
    }

    public void picassoTest(){
        PicassoUtils picassoUtils = new PicassoUtils(this);
        picassoUtils.loadImage(IMAGE_URL, imageView);
    }

    private void glideTest(){
        GlideUtils glideUtils = new GlideUtils(this);
        glideUtils.loadImage(IMAGE_URL,imageViewGlide);
    }

    private void lombokTest(){
        Contributors contributors = new Contributors("",1);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_scan_blurtooth:
                Intent intent = new Intent(this, BouetoothActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    public void test() {
        RxAndroidTest();
    }

    public void RxAndroidTest() {
        RxAndroidUtils.helloWorld();
        RxAndroidUtils.RxFrom();
    }

}







