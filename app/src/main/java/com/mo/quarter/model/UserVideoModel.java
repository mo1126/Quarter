package com.mo.quarter.model;

import com.mo.quarter.bean.GetVideosBean;
import com.mo.quarter.bean.UserInfoBean;
import com.mo.quarter.bean.UserVideosBean;
import com.mo.quarter.utils.MyIntercepter;
import com.mo.quarter.utils.NetRequest;

import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 莫迎华 on 2017/12/15.14:30.
 */

public class UserVideoModel {
    public void getUserVideos(String uid,String page){
        new NetRequest.Buidler().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .builder().getApiService().getUserVideos(uid,page).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UserVideosBean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(UserVideosBean userVideosBean) {
                if(userVideosBean.code.equals("0")){
                    userVideosInterface.getUserVideosSuccess(userVideosBean);
                }else if(userVideosBean.code.equals("1")){
                    userVideosInterface.getUserVideosFailure(userVideosBean.msg);
                }else{
                    userVideosInterface.getUserVideosFailure(userVideosBean.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                    userVideosInterface.getError(e.toString());
            }
            @Override
            public void onComplete() {
            }
        });
    }
    public void getUserInfo(String uid){
        new NetRequest.Buidler().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .builder().getApiService().getUserInfo(uid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserInfoBean userInfoBean) {
                        if(userInfoBean.code.equals("0")){
                            userVideosInterface.getUserInfoSuccess(userInfoBean);
                        }else{
                            userVideosInterface.getUserInfoFailure(userInfoBean.msg);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        userVideosInterface.getError(e.toString());
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
    public void guanzhu(String followId){
        new NetRequest.Buidler().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .builder().getApiService().guanzhu(MyIntercepter.uid,followId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            JSONObject jsonObject=new JSONObject(string);
                            String code =  jsonObject.getString("code");
                            String msg = jsonObject.getString("msg");
                            if(code.equals("0")){
                                userVideosInterface.guanzhuSuccess(msg);
                            }else{
                                userVideosInterface.guanzhuFailure(msg);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        userVideosInterface.getError(e.toString());
                    }
                    @Override
                    public void onComplete() {
                    }
                });

    }
    public void dianzan(String uid,String wid){
        new NetRequest.Buidler().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).builder().getApiService().praise(uid,wid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            JSONObject jsonObject=new JSONObject(string);
                            String code = jsonObject.getString("code");
                            String msg = jsonObject.getString("msg");
                            if("0".equals(code)){
                                userVideosInterface.dianzanSuccess(msg);
                            }else{
                                userVideosInterface.dianzanFailure(msg);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        userVideosInterface.getError(e.toString());
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }


    private userVideosInterface userVideosInterface;
    public void setUserVideosInterface(UserVideoModel.userVideosInterface userVideosInterface) {
        this.userVideosInterface = userVideosInterface;
    }
    public interface userVideosInterface{
        void getUserVideosSuccess(UserVideosBean userVideosBean);
        void getUserVideosFailure(String msg);
        void getUserInfoSuccess(UserInfoBean userInfoBean);
        void getUserInfoFailure(String msg);
        void guanzhuSuccess(String msg);
        void guanzhuFailure(String msg);
        void dianzanSuccess(String msg);
        void dianzanFailure(String msg);
        void getError(String msg);
    }
}
