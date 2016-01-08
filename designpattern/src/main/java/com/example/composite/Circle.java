package com.example.composite;

/**
 * Created by zhongyu on 1/4/2016.
 */
public class Circle implements Graphic {
    @Override
    public void print() {
        System.out.print(getClass().getSimpleName());
    }
}
