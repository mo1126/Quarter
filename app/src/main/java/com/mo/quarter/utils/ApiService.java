package com.mo.quarter.utils;

import com.mo.quarter.bean.CreatDuanziBean;
import com.mo.quarter.bean.GetVersionBean;
import com.mo.quarter.bean.GetVideosBean;
import com.mo.quarter.bean.JokesBean;
import com.mo.quarter.bean.LoginOtherBean;
import com.mo.quarter.bean.HotShipin;
import com.mo.quarter.bean.MyFollowUserBean;
import com.mo.quarter.bean.MycollectBean;
import com.mo.quarter.bean.RandomFransBean;
import com.mo.quarter.bean.TuijianAdBean;
import com.mo.quarter.bean.UserInfoBean;
import com.mo.quarter.bean.UserVideosBean;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

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

    @GET("quarter/getJokes ")
    @Headers("cache:20")
    Observable<JokesBean> getJokes(@Query("page") String page);

    @POST("quarter/publishJoke")
    @Multipart
    Observable<CreatDuanziBean> shareDuanzi(@Part()List<MultipartBody.Part> file);

    @POST("quarter/getAd")
    Observable<TuijianAdBean> getAd();

    @POST("quarter/getVideos")
    @FormUrlEncoded
    Observable<GetVideosBean> getvideos(@Field("uid") String uid,@Field("type") String type,@Field("page") String page);

    @POST("quarter/getHotVideos")
    @FormUrlEncoded
    Observable<HotShipin> gethotvideos(@Field("page") String page);


    @POST("quarter/publishVideo")
    @Multipart
    Observable<ResponseBody> publish(@Part()List<MultipartBody.Part> parts);

    @POST("file/upload")
    @Multipart
    Observable<ResponseBody> setHead(@Part()List<MultipartBody.Part> parts);

    @POST("user/updateNickName")
    @FormUrlEncoded
    Observable<ResponseBody> setName(@Field("uid") String uid,@Field("nickname") String name);

    @POST("quarter/getVersion")
    Observable<GetVersionBean> getversion();

    @POST("quarter/getWorkInfo")
    @FormUrlEncoded
    Observable<UserVideosBean> getUserVideos(@Field("uid")String uid,@Field("page")String page);

    @POST("quarter/follow")
    @FormUrlEncoded
    Observable<ResponseBody> guanzhu(@Field("uid")String uid,@Field("followId")String followId);

    @POST("quarter/getFollowUsers")
    @FormUrlEncoded
    Observable<MyFollowUserBean> getfollowUsers(@Field("uid")String uid);

    @POST("quarter/praise")
    @FormUrlEncoded
    Observable<ResponseBody>  praise(@Field("uid")String uid,@Field("wid")String wid);



    @POST(" quarter/addFavorite")
    @FormUrlEncoded
    Observable<ResponseBody>  shoucang(@Field("uid")String uid,@Field("wid")String wid);



    @POST("quarter/comment")
    @FormUrlEncoded
    Observable<ResponseBody>  pinglun(@Field("uid")String uid,@Field("wid")String wid,@Field("content") String content);


    @POST("quarter/getFavorites")
    @FormUrlEncoded
    Observable<MycollectBean> getcolects(@Field("uid") String uid);


    @POST("quarter/randomFriends")
    Observable<RandomFransBean> getrandomfrands();
}
