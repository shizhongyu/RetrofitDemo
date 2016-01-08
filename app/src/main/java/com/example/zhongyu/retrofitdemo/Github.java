package com.example.zhongyu.retrofitdemo;

import com.example.zhongyu.retrofitdemo.Retrofit.Contributor;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by zhongyu on 1/2/2016.
 */
public interface Github {

    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributors>> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo
    );
}
