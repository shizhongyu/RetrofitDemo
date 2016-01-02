package com.example.zhongyu.retrofitdemo;

import android.app.Activity;
import android.os.Bundle;
import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.Retrofit;

/**
 * Created by zhongyu on 1/2/2016.
 */
public class MainActivity extends Activity{

    public static final String API_URL = "https://api.github.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            getApi();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getApi() throws IOException {
        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .build();

        // Create an instance of our GitHub API interface.
        GitHub github = retrofit.create(GitHub.class);

        // Create a call instance for looking up Retrofit contributors.
        Call<List<Contributor>> call = github.contributors("square", "retrofit");

        // Fetch and print a list of the contributors to the library.
        List<Contributor> contributors = call.execute().body();
        for (Contributor contributor : contributors) {
            System.out.println(contributor.login + " (" + contributor.contributions + ")");
        }
    }

}
