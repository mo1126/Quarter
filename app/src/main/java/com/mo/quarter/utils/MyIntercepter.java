package com.mo.quarter.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.mo.quarter.myapp.MyApp;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 莫迎华 on 2017/11/14.18:25.
 */

public class MyIntercepter implements Interceptor {

    private int versionCode;
    public static String token;
    private Context context;
    private static int uid;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        try {
            context = MyApp.context;
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionCode = pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        SharedPreferences token1 = context.getSharedPreferences("token", Context.MODE_PRIVATE);
        token = token1.getString("token", "");
        uid = token1.getInt("uid", 0);


        if(request.method().equals("POST")){
            if(request.body() instanceof FormBody){
                System.out.println("开始添加公共参数");
                FormBody.Builder builder = new FormBody.Builder();
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    builder.add(body.encodedName(i),body.encodedValue(i));
                }
                body=builder.add("source","android")
                        .add("appVersion", String.valueOf(versionCode))
                        .add("token",token)
                        .build();
               request= request.newBuilder().post(body).build();
            }
        }

        Response proceed = chain.proceed(request);
        return proceed;
    }
}
