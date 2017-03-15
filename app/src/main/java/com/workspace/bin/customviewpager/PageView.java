package com.workspace.bin.customviewpager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PageView {

    private Context mContext;
    private LinearLayout llDots;
    private PageControl control;
    private int dotsize;//

    private ViewPager viewPager; // android-support-v4中的滑动组件

    private List<LinearLayout> imageViews; // 滑动的图片集合
    private List<GuangGao.ItemsBean.UserBean> gaos = new ArrayList<GuangGao.ItemsBean.UserBean>();

    private int currentItem = 0; // 当前图片的索引号
    private ScheduledExecutorService scheduledExecutorService;

    int[] ids = {R.drawable.ad2};

    // 切换当前显示的图片
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    viewPager.setCurrentItem(currentItem);// 切换当前显示的图片
                    viewPager.postInvalidate();
                    viewPager.setFocusable(true);
                    break;
                default:
                    break;
            }
        }

        ;
    };

    public PageView(Context mContext, LinearLayout llDots, ViewPager viewPager,
                    List<GuangGao.ItemsBean.UserBean> userBean) {
        this.mContext = mContext;
        this.llDots = llDots;
        this.viewPager = viewPager;
        this.gaos=userBean;
        init();
    }

    public void setDatas(List<GuangGao.ItemsBean.UserBean> gaos) {
        this.gaos = gaos;
        init();
    }

    //初始化
    private void init() {
        // TODO Auto-generated method stub
        if (gaos != null && gaos.size() > 0) {
            dotsize = gaos.size();
        } else {
            dotsize = ids.length;
        }

        llDots.removeAllViews();
        imageViews = new ArrayList<LinearLayout>();
        // 初始化图片资源
        for (int i = 0; i < dotsize; i++) {
            LinearLayout layout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.imageview_item, null);
            final ImageView imageView = (ImageView) layout.findViewById(R.id.imageview);
            if (gaos != null && gaos.size() > 0) {
                String format = String.format(Constant.URL_USER_ICON, gaos.get(i).getId() / 10000, gaos.get(i).getId(), gaos.get(i).getIcon());
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(format).build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        Toast.makeText(mContext, "网络连接失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        byte[] bytes = response.body().bytes();
                        final Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        ((Activity)mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bmp);
                            }
                        });
                    }
                });
                final int postion = i;
                imageView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        transHtml(postion);
                    }
                });
            } else {
                imageView.setImageResource(ids[0]);
            }
            imageViews.add(layout);
        }

        control = new PageControl(mContext, llDots, dotsize);
        control.selectPage(0);
        // 广告轮播的空间

        viewPager.setAdapter(new MyPagerAdapter(imageViews));// 设置填充ViewPager页面的适配器
        // 设置一个监听器，当ViewPager中的页面改变时调用
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
    }





    // 加载html页面
    public void transHtml(int postion) {
        String downLoadUrl = gaos.get(postion).getMedium();
        Intent intent = new Intent(mContext, WebActivity.class);
        intent.putExtra("URL", getPath(downLoadUrl));
        mContext.startActivity(intent);
        return;
    }

    // 组装全路径
    public static String getPath(String downLoadUrl) {
        StringBuffer result = new StringBuffer();
        result.append(Constant.BASE_URL);
        result.append(downLoadUrl);
        return result.toString();
    }


    /**
     * 当ViewPager中页面的状态发生改变时调用
     *
     * @author bin
     */
    private class MyPageChangeListener implements OnPageChangeListener {
        /**
         * This method will be invoked when a new page becomes selected.
         * position: Position index of the new selected page.
         */
        public void onPageSelected(int position) {
            currentItem = position;
            control.selectPage(currentItem);
        }

        public void onPageScrollStateChanged(int arg0) {

        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }
    }

    //开启动画
    public void start() {
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
        // 第一次进入3s后开始动画，之后每5s切换动画
        scheduledExecutorService.scheduleWithFixedDelay(new ScrollTask(), 0, 5,
                TimeUnit.SECONDS);
    }

    //结束动画
    public void end() {
        // TODO Auto-generated method stub
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
        }
    }

    /**
     * 换行切换任务
     *
     * @author bin
     */
    private class ScrollTask implements Runnable {

        public void run() {
            synchronized (viewPager) {
                currentItem = (currentItem + 1) % dotsize;
                handler.sendEmptyMessage(1);
            }
        }
    }

}
