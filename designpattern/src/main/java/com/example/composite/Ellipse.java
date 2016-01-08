package com.example.composite;

/**
 * Created by zhongyu on 1/4/2016.
 */
public class Ellipse implements Graphic {
    @Override
    public void print() {
        System.out.println(getClass().getSimpleName());
    }
}
