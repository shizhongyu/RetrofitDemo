package com.example.zhongyu.retrofitdemo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.zhongyu.retrofitdemo.Dao.News;
import com.example.zhongyu.retrofitdemo.Retrofit.*;
import com.example.zhongyu.retrofitdemo.Retrofit.Contributor;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.IOException;
import java.util.List;

import de.greenrobot.dao.query.Query;
import retrofit.Call;
import retrofit.Retrofit;


/**
 * Created by zhongyu on 1/2/2016.
 */
public class MainActivity extends Activity{
    private static final String TAG = "MainActivity";

    public static final String API_URL = "https://api.github.com";

    OkhttpUtils okhttpUtils = null;

    private ImageView imageView;
    private ImageView imageViewGlide;

    private static final String IMAGE_URL = "https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/fresco-logo.png";

    private Query<News> newsQuery;
    private List<News> newsList; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        init();
        frescoTest();
        picassoTest();
        glideTest();
//        retrofitTest();
    }

    public void init(){
        okhttpUtils = new OkhttpUtils();
        imageView = (ImageView) findViewById(R.id.ic_imageview);
        imageViewGlide = (ImageView) findViewById(R.id.ic_image_view_glide);
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


}







