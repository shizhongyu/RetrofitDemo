package com.example.composite;

/**
 * Created by zhongyu on 1/4/2016.
 */
public class Composite {

    public static void main(String [] args){
        Ellipse ellipse = new Ellipse();
        Rectongle rectongle = new Rectongle();
        Circle circle = new Circle();

        CompositeGraphic compositeGraphic = new CompositeGraphic();
        CompositeGraphic compositeGraphic1 = new CompositeGraphic();
        CompositeGraphic compositeGraphic2 = new CompositeGraphic();

        compositeGraphic1.addGraphic(ellipse);
        compositeGraphic1.addGraphic(ellipse);
        compositeGraphic1.addGraphic(rectongle);
        compositeGraphic2.addGraphic(circle);

        compositeGraphic.addGraphic(compositeGraphic1);
        compositeGraphic.addGraphic(compositeGraphic2);

        compositeGraphic.print();

    }
}
