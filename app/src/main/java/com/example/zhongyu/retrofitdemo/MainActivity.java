package com.example.zhongyu.retrofitdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.zhongyu.retrofitdemo.Dao.News;

import java.util.List;

import de.greenrobot.dao.query.Query;
import rx.Observable;


/**
 * Created by zhongyu on 1/2/2016.
 */
public class MainActivity extends Activity{
    private static final String TAG = "MainActivity";

    public static final String API_URL = "https://api.github.com";

    OkhttpUtils okhttpUtils = null;

    private Query<News> newsQuery;
    private List<News> newsList; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        greenDaoTest();
    }

    public void init(){
        okhttpUtils = new OkhttpUtils();
    }

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
        greenDaoUtils.updateNews(1,"three");
        for (News news : newsList) {
            Log.d(TAG, "greenDaoTest() returned: " + news.getTitle());
        }
    }

}
