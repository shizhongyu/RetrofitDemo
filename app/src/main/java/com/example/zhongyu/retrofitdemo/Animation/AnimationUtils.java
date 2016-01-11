package com.example.zhongyu.retrofitdemo.Animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.bumptech.glide.load.model.ImageVideoWrapper;
import com.example.zhongyu.retrofitdemo.R;
import com.example.zhongyu.retrofitdemo.Utils.SingletonUtils;

/**
 * Created by zhongyu on 1/10/2016.
 */
public class AnimationUtils extends SingletonUtils<AnimationUtils>{

    private Context context;

    public AnimationUtils(Context context) {
        this.context = context;
    }

    public static void rotateyAnimRun(View view) {
        ObjectAnimator
                .ofFloat(view, "rotationY", 0.0F, 360.0F )
                .setDuration(500)
                .start();
    }

    public static void animationSet(final View view) {
        ObjectAnimator objectAnimator = ObjectAnimator
                .ofFloat(view, "lzy", 1.0F, 0.0F)
                .setDuration(500);

        objectAnimator.start();
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (float) animation.getAnimatedValue();
                view.setAlpha(cVal);
                view.setScaleX(cVal);
                view.setScaleY(cVal);
            }
        });
    }

    /*
    * 抛物线
    * param view
    * */
    public static void animationPara(final View view) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(500);
        valueAnimator.setObjectValues(new PointF(0, 0));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                PointF point = new PointF();
                point.x = 200 * fraction * 3;
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return point;
            }
        });

        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                view.setX(point.x);
                view.setY(point.y);
            }
        });
    }

    public static void animFaceOut(final View view) {
        ObjectAnimator objectAnimation = ObjectAnimator.ofFloat(view, "alpha", 0.5f);
        objectAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ViewGroup parent = (ViewGroup) view.getParent();
                if (parent != null) {
                    parent.removeView(view);
                }
            }
        });
        objectAnimation.start();
    }

    public static void animTogerther(View view) {
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 2f);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 2f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(2000);
        animSet.setInterpolator(new LinearInterpolator());
        /*
        * objectAnimation1 objectAnimation2 together execute
        * */
        animSet.playTogether(objectAnimator1, objectAnimator2);
        animSet.start();
    }

    public static void animSquence(View view) {
        float cx = view.getX();
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 2f);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 2f);
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(view, "x", cx, 0f);
        ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(view, "x", cx);

        /*
        *
        * */
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(objectAnimator1).with(objectAnimator2);
        animSet.play(objectAnimator2).with(objectAnimator3);
        animSet.play(objectAnimator4).after(objectAnimator3);

        animSet.setDuration(1000);
        animSet.start();
    }

    public static void scaleX(View view,Context context) {
        Animator animator = AnimatorInflater.loadAnimator(context, R.animator.scalex);
        animator.setTarget(view);
        animator.start();
    }

    public static void scaleXY(View view, Context context) {
        Animator animator = AnimatorInflater.loadAnimator(context, R.animator.scalexy);
        view.setPivotX(0);
        view.setPivotY(0);

       /*
       * 显示的调用invalidate
       * */
        view.invalidate();
        animator.setTarget(view);
        animator.start();
    }

    @Override
    protected AnimationUtils newInstance() {
        return getInstance();
    }
}

















