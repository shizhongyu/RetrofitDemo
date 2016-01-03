package com.example.zhongyu.retrofitdemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.zhongyu.retrofitdemo.Dao.DaoMaster;
import com.example.zhongyu.retrofitdemo.Dao.DaoSession;
import com.example.zhongyu.retrofitdemo.Dao.News;
import com.example.zhongyu.retrofitdemo.Dao.NewsDao;

import java.util.Currency;
import java.util.Date;

import de.greenrobot.dao.query.Query;

/**
 * Created by zhongyu on 1/2/2016.
 */
public class GreenDaoUtils {

    private Context context;
    private SQLiteDatabase sqLiteDatabase;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private NewsDao newsDao;

    private Cursor cursor;

    private Query<News> queryById;

    public GreenDaoUtils(Context context){
        this.context = context;
    }
    public void createDB(){
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context,"news.db",null);
        sqLiteDatabase = devOpenHelper.getWritableDatabase();
        daoMaster = new DaoMaster(sqLiteDatabase);
        daoSession = daoMaster.newSession();
        newsDao = daoSession.getNewsDao();

        String textCloumn = NewsDao.Properties.Id.columnName;
        String orderBy = textCloumn + "COLLATE LOCALIZED ASC";
//        cursor = sqLiteDatabase.query(newsDao.getTablename(),newsDao.getAllColumns(),null,null,null,null,orderBy);
    }

    public void addNews(){
        News news = new News()
                .setId(null)
                .setTitle("one")
                .setContent("one like")
                .setCommentCount(1)
                .setPublishDate(new Date());
        newsDao.insert(news);
    }

    public Query<News> getQueryById(){
        if(queryById == null){
            queryById = newsDao.queryBuilder()
                    .orderAsc(NewsDao.Properties.Id)
                    .limit(20)
                    .build();
        }
        return queryById.forCurrentThread();
    }

    public void deleteById(long id){
        newsDao.deleteByKey(id);
    }

    public News updateNews(long id, String title){
         News news =  newsDao.queryBuilder()
                .where(NewsDao.Properties.Id.eq(1))
                .build()
                .unique();
        news.setTitle(title);
        newsDao.update(news);
        return news;
    }
}


















