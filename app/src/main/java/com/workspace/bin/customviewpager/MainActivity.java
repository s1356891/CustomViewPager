package com.workspace.bin.customviewpager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private PageView pageView;
    private GuangGao.ItemsBean.UserBean userBean;
    private LinearLayout linearLayout;
    List<GuangGao.ItemsBean.UserBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        viewPager = (ViewPager) findViewById(R.id.adviewpage);
        linearLayout = (LinearLayout) findViewById(R.id.llDots);
        pageView = new PageView(this, linearLayout, viewPager, list);
        NetworkInfo networkInfo = ((ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            initData();
        }
    }

    private void initData() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(Constant.TEXT_CONTENT).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(MainActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(Response response) throws IOException {
                Gson gson = new Gson();
                GuangGao guangGao = new Gson().fromJson(response.body().string(), GuangGao.class);
                for (int i = 0; i < 5; i++) {
                    list.add(guangGao.getItems().get(i).getUser());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pageView.setDatas(list);
                    }
                });
            }
        });
    }
}
