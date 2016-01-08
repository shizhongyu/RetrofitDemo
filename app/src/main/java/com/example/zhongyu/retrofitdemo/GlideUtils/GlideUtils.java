package com.example.zhongyu.retrofitdemo.GlideUtils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by zhongyu on 1/3/2016.
 */
public class GlideUtils {

    private Context context;
    public GlideUtils(Context context){
        this.context = context;
    }
    public void loadImage(String url,ImageView imageView){
        Glide.with(context).load(url).into(imageView);
    }
}
