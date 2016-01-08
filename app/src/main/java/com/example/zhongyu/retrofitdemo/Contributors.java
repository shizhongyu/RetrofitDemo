package com.example.zhongyu.retrofitdemo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by zhongyu on 1/2/2016.
 */

public class Contributors {

    public String login;
    public int contributions;

    public Contributors(String login, int contributions){
        this.login = login;
        this.contributions = contributions;
    }


}
