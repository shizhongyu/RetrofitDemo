package com.example.zhongyu.retrofitdemo;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.zhongyu.retrofitdemo.Animation.AnimationUtils;
import com.example.zhongyu.retrofitdemo.AsyncTask.AsyncTaskActivity;
import com.example.zhongyu.retrofitdemo.Bluetooth.BouetoothActivity;
import com.example.zhongyu.retrofitdemo.Dao.News;
import com.example.zhongyu.retrofitdemo.GlideUtils.GlideUtils;
import com.example.zhongyu.retrofitdemo.GreenDaoUtils.GreenDaoUtils;
import com.example.zhongyu.retrofitdemo.OkhttpUtils.OkhttpUtils;
import com.example.zhongyu.retrofitdemo.PicassoUtils.PicassoUtils;
import com.example.zhongyu.retrofitdemo.RxAndrid.RxAndroidUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;


import java.util.List;
import java.util.zip.CheckedInputStream;

import de.greenrobot.dao.query.Query;

import static com.example.zhongyu.retrofitdemo.R.id.cancel;
import static com.example.zhongyu.retrofitdemo.R.id.decor_content_parent;
import static com.example.zhongyu.retrofitdemo.R.id.ic_image_anim;


/**
 * Created by zhongyu on 1/2/2016.
 */
public class MainActivity extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{
    private static final String TAG = "MainActivity";

    public static final String API_URL = "https://api.github.com";

    OkhttpUtils okhttpUtils = null;

    private ImageView imageView;
    private ImageView imageViewGlide;
    private Button btnScanBluetooth;
    private ImageView imageAnim;
    private ViewGroup viewGroup;
    private GridLayout gridLayout;
    private int mVal;
    private LayoutTransition layoutTransition;
    private CheckBox mAppear, mChangeApperar, mDisAppear, mChangeDisAppear;

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
        imageAnim = (ImageView) findViewById(R.id.ic_image_anim);
        viewGroup = (ViewGroup) findViewById(R.id.ic_container);
        mAppear = (CheckBox) findViewById(R.id.id_appear);
        mChangeApperar = (CheckBox) findViewById(R.id.id_change_appear);
        mDisAppear = (CheckBox) findViewById(R.id.id_disappear);
        mChangeDisAppear = (CheckBox) findViewById(R.id.id_change_disappear);
        /*
        * create GirdLayout
        * */
        gridLayout = new GridLayout(this);
        /*
        *
        * */
        gridLayout.setColumnCount(5);
        /*
        * 添加到布局文件中
        * */
        viewGroup.addView(gridLayout);
        layoutTransition = new LayoutTransition();
        gridLayout.setLayoutTransition(layoutTransition);
    }

    public void initActions() {
        btnScanBluetooth.setOnClickListener(this);
        imageAnim.setOnClickListener(this);
        mAppear.setOnClickListener(this);
        mChangeApperar.setOnClickListener(this);
        mDisAppear.setOnClickListener(this);
        mChangeDisAppear.setOnClickListener(this);
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

    public void addBtn(View view) {
        final Button button = new Button(this);
        button.setText((++mVal) + "");
        gridLayout.addView(button, Math.min(1, gridLayout.getChildCount()));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridLayout.removeView(button);
            }
        });
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
        greenDaoUtils.updateNews(1, "three");
        for (News news : newsList) {
            Log.d(TAG, "greenDaoTest() returned: " + news.getTitle());
        }
    }

    public void frescoTest(){
        Uri uri = Uri.parse(IMAGE_URL);
        Uri uri1 = Uri.parse("res://drawable/" + R.drawable.k);
        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.ic_image_view);
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setUri(uri1)
                .setAutoPlayAnimations(true)
                .build();
        draweeView.setController(draweeController);
//        draweeView.setImageURI(uri1);
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
                asyncTashTest();
                break;
            case R.id.ic_image_anim:
                animationTest(imageAnim);
                break;
            case R.id.btn_vertical:
                AnimationUtils.animSquence(imageAnim);
                break;
            case R.id.btn_para:
                animationTest(imageAnim);
                break;
            default:
                break;
        }
    }

    public void test() {
//        RxAndroidTest();
//        frescoTest();
    }

    public void RxAndroidTest() {
        RxAndroidUtils.RxTest();
    }

    public void asyncTashTest() {
        Intent intent = new Intent(this, AsyncTaskActivity.class);
        startActivity(intent);
        finish();
    }

    public void blueToolthTest() {
        Intent intent = new Intent(this, BouetoothActivity.class);
        startActivity(intent);
        finish();
    }


    private void animationTest(View view) {
//        AnimationUtils.rotateyAnimRun(view);
//        AnimationUtils.animationSet(view);
//        AnimationUtils.animationPara(view);
//        AnimationUtils.animFaceOut(view);
//        AnimationUtils.animTogerther(view);
//        AnimationUtils.animSquence(view);
//        AnimationUtils.scaleX(view, this);
        AnimationUtils.scaleXY(view, this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        layoutTransition = new LayoutTransition();
//        layoutTransition.setAnimator(
//                LayoutTransition.APPEARING,
//                (mAppear.isChecked() ? layoutTransition.getAnimator(layoutTransition.APPEARING) : null));

        layoutTransition.setAnimator(LayoutTransition.APPEARING, (mAppear
                .isChecked() ? ObjectAnimator.ofFloat(this, "scaleX", 0, 1) : null));

        layoutTransition.setAnimator(
                LayoutTransition.CHANGE_APPEARING,
                (mChangeApperar.isChecked() ? layoutTransition.getAnimator(LayoutTransition.CHANGE_APPEARING) : null));

        layoutTransition.setAnimator(
                LayoutTransition.CHANGE_DISAPPEARING,
                (mDisAppear.isChecked() ? layoutTransition.getAnimator(LayoutTransition.DISAPPEARING) : null));

        layoutTransition.setAnimator(
                LayoutTransition.DISAPPEARING,
                (mDisAppear.isChecked() ? layoutTransition.getAnimator(LayoutTransition.DISAPPEARING) : null));

        gridLayout.setLayoutTransition(layoutTransition);
    }
}









