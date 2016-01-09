package com.example.zhongyu.retrofitdemo.AsyncTask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.zhongyu.retrofitdemo.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhongyu on 1/8/2016.
 */
public class AsyncTaskActivity extends Activity implements View.OnClickListener{

    private static final String TAG = "AsyncTaskActivity";

    private Button execute;
    private Button cancel;
    private ProgressBar progressBar;
    private TextView textView;
    private AsynvTashUtils asyvcTashUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);

        init();
    }

    public void init() {
        initViews();
        initActions();
    }

    public void initViews() {
        execute = (Button) findViewById(R.id.execute);
        cancel = (Button) findViewById(R.id.cancel);
        textView = (TextView) findViewById(R.id.text_view);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    public void initActions() {
        execute.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:
                break;
            case R.id.execute:
                asyvcTashUtils = new AsynvTashUtils();
                asyvcTashUtils.execute();
                break;
            default:
                break;
        }
    }

    public class AsynvTashUtils extends AsyncTask<Void, Long, Boolean> {
        private final String URL = "http://img.meilishuo.net/css/images/AndroidShare/Meilishuo_3.6.1_10006.apk";
        @Override
        protected Boolean doInBackground(Void... params) {
            OkHttpClient httpClient = new OkHttpClient();
            InputStream inputStream = null;
            Call call = httpClient.newCall(new Request.Builder().url(URL).get().build());
            try {
                Response response = call.execute();
                if(response.isSuccessful()) {
                    inputStream = response.body().byteStream();
                    byte[] buffer = new byte[1024];
                    long downloaded = 0;
                    long target = response.body().contentLength();
                    Log.d(TAG, "doInBackground() returned: " + target);
                    publishProgress(0L, target);
                    while (true) {
                        int readed = inputStream.read(buffer);
                        if(readed == -1) {
                            break;
                        }
                        downloaded += readed;
                        Log.d(TAG, "doInBackground() returned: " + downloaded);
                        publishProgress(downloaded, target);
                        if(isCancelled()) {
                            return false;
                        }
                    }
                    return downloaded == target;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        /*
        * onPreExecute方法用于在执行后台任务前做一些UI操作
        * */
        @Override
        protected void onPreExecute() {
            textView.setText("loading...");
        }

        /*
        * onProgressUpdate方法用于更新进度信息
        * */

        @Override
        protected void onProgressUpdate(Long... values) {
            progressBar.setMax(values[1].intValue());
            progressBar.setProgress(values[0].intValue());
            textView.setText("loading..." + Arith.div(values[0], values[1]) + "%");
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            textView.setText(aBoolean ? "Downloaded" : "Failed" );
        }
    }
}







