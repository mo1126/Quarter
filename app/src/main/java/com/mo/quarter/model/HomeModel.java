package com.mo.quarter.model;

import com.mo.quarter.bean.UserInfoBean;
import com.mo.quarter.utils.NetRequest;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 莫迎华 on 2017/11/28.11:29.
 */

public class HomeModel {
    public void getUserInfo(String uid){
        new NetRequest.Buidler()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .builder().getApiService().getUserInfo(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UserInfoBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UserInfoBean userInfoBean) {

                if("0".equals(userInfoBean.code)){
                    getUserInfoInterface.getinfoSuccess(userInfoBean);
                }else if("1".equals(userInfoBean.code)){
                    getUserInfoInterface.getinfoFailure(userInfoBean.msg);
                }else{
                    getUserInfoInterface.getinfoFailure(userInfoBean.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                getUserInfoInterface.onError(e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private GetUserInfoInterface getUserInfoInterface;

    public void setGetUserInfoInterface(GetUserInfoInterface getUserInfoInterface) {
        this.getUserInfoInterface = getUserInfoInterface;
    }

    public interface GetUserInfoInterface{
        void getinfoSuccess(UserInfoBean userInfoBean);
        void getinfoFailure(String msg);
        void onError(String e);
    }
}
