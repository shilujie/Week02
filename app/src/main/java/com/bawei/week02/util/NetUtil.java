package com.bawei.week02.util;
/*
 *@auther:史陆杰
 *@Date: 2019/12/30
 *@Time:14:05
 *@Description:${DESCRIPTION}
 **/


import android.os.Handler;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class NetUtil {
    private static NetUtil netUtil;
    private Handler handler;
    private OkHttpClient okHttpClient;

    private NetUtil() {
        handler = new Handler();

        //日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)

                //添加日志拦截器
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    public static NetUtil getInstance() {
        if (netUtil == null){
            synchronized (NetUtil.class){
                if (netUtil == null){
                    netUtil = new NetUtil();
                }
            }
        }
        return netUtil;
    }

    public void  getJsonGet(String httpUrl,MyCallBack myCallBack){
        Request builder = new Request.Builder()
                .get()
                .url(httpUrl)
                .build();
        Call call = okHttpClient.newCall(builder);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myCallBack.getError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()){
                    String string = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.getJsonGet(string);
                        }
                    });
                }else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.getError(new Exception("失败"));
                        }
                    });
                }
            }
        });

    }

    public void  getJsonPost(String httpUrl, Map<String,String> map ,MyCallBack myCallBack){
        FormBody.Builder formBody = new FormBody.Builder();
        for (String key: map.keySet()) {
            String value = map.get(key);
            formBody.add(key,value);
        }
        FormBody build = formBody.build();
        Request builder = new Request.Builder()
                .post(build)
                .url(httpUrl)
                .build();
        Call call = okHttpClient.newCall(builder);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myCallBack.getError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()){
                    String string = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.getJsonGet(string);
                        }
                    });
                }else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.getError(new Exception("失败"));
                        }
                    });
                }
            }
        });

    }
    public interface MyCallBack{
        void getJsonGet(String json);
        void getError(Throwable throwable);
    }
}
