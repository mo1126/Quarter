package com.mo.quarter.utils;

import com.mo.quarter.bean.CreatDuanziBean;
import com.mo.quarter.bean.JokesBean;
import com.mo.quarter.bean.LoginOtherBean;
import com.mo.quarter.bean.UserInfoBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by 莫迎华 on 2017/11/15.8:17.
 */

public interface ApiService {
    @POST("user/login")
    @FormUrlEncoded
    Observable<LoginOtherBean> loginOther(@Field("mobile") String moblie, @Field("password")String pwd);


    @POST("user/getUserInfo")
    @FormUrlEncoded
    Observable<UserInfoBean> getUserInfo(@Field("uid") String uid);

    @POST("quarter/getJokes ")
    @FormUrlEncoded
    Observable<JokesBean> getJokes(@Field("page") String page);

    @POST("quarter/publishJoke")
    @Multipart
    Observable<CreatDuanziBean> shareDuanzi(@Part()List<MultipartBody.Part> file);
}
