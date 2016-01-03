package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyClass {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1000,"com.example.zhongyu.retrofitdemo.Dao");
        addNews(schema);
        new DaoGenerator().generateAll(schema,"../");
    }

    public static void addNews(Schema schema){
        Entity entity = schema.addEntity("News");
        entity.addIdProperty();
        entity.addStringProperty("title").notNull();
        entity.addStringProperty("content");
        entity.addDateProperty("publishDate");
        entity.addIntProperty("commentCount");
    }
}
