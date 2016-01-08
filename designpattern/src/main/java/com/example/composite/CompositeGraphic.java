package com.example.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongyu on 1/4/2016.
 */
public class CompositeGraphic implements Graphic {

    private List<Graphic> childGraphics = new ArrayList<>();

    @Override
    public void print() {
        for (Graphic graphic : childGraphics) {
            graphic.print();
        }
    }

    public void addGraphic(Graphic graphic){
        childGraphics.add(graphic);
    }

    public void removeGraphic(Graphic graphic){
        childGraphics.remove(graphic);
    }
}








