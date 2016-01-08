package com.example.zhongyu.retrofitdemo.PicassoUtils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by zhongyu on 1/3/2016.
 */
public class PicassoUtils {

    private Context mContext;
    public PicassoUtils(Context context){
        this.mContext = context;
    }
    public void loadImage(String url,ImageView imageView){
        Picasso.with(mContext).load(url).into(imageView);
    }
}
