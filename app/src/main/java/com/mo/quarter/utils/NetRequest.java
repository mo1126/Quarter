package com.mo.quarter.utils;

import com.mo.quarter.myapp.MyApp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 莫迎华 on 2017/11/14.18:06.
 */

public class NetRequest {

    public  ApiService apiService;
    public  static NetRequest netRequest;

    public NetRequest(ApiService apiService) {
        this.apiService = apiService;
    }

    public ApiService getApiService() {
        return apiService;
    }

    public static class Buidler{
        List<Converter.Factory> converterFactory=new ArrayList<>();
        List<CallAdapter.Factory> adapterFactory=new ArrayList<>();
        public Buidler addConverterFactory(Converter.Factory factory){
            converterFactory.add(factory);
            return this;
        }
        public Buidler addCallAdapterFactory(CallAdapter.Factory factory){
            adapterFactory.add(factory);
            return this;
        }

        public NetRequest builder(){
            OkHttpClient okHttpClient=new OkHttpClient.Builder()
                    .addInterceptor(new MyIntercepter())
                    .addNetworkInterceptor(new NetWorkIntercepte())
                    .cache(new CacheProvide(MyApp.context).provideCache())
                    .connectTimeout(8, TimeUnit.SECONDS)
                    .readTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(5, TimeUnit.SECONDS).build();
            Retrofit.Builder retrofit=new Retrofit.Builder().client(okHttpClient).baseUrl("https://www.zhaoapi.cn/");

            if(converterFactory.size()>0&&converterFactory!=null){
                for (Converter.Factory factory : converterFactory) {
                    retrofit.addConverterFactory(factory);
                }
            }else{
                retrofit.addConverterFactory(GsonConverterFactory.create());
            }

            if(adapterFactory!=null&&adapterFactory.size()>0){
                for (CallAdapter.Factory factory : adapterFactory) {
                    retrofit.addCallAdapterFactory(factory);
                }
            }else{
                retrofit.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            }

            ApiService apiService=retrofit.build().create(ApiService.class);
            netRequest=new NetRequest(apiService);
            return netRequest;
        }
    }

}

