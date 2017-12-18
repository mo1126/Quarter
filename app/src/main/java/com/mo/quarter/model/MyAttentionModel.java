package com.mo.quarter.model;

import com.mo.quarter.bean.MyFollowUserBean;
import com.mo.quarter.utils.MyIntercepter;
import com.mo.quarter.utils.NetRequest;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 莫迎华 on 2017/12/16.10:06.
 */

public class MyAttentionModel {

    public void getFollowUsers(){
        new NetRequest.Buidler().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .builder().getApiService().getfollowUsers(MyIntercepter.uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyFollowUserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(MyFollowUserBean myFollowUserBean) {
                        if(myFollowUserBean.code.equals("0")){
                            myAttentionInterface.getFollowUserSuccess(myFollowUserBean);
                        }else{
                            myAttentionInterface.getFollowUserFailure(myFollowUserBean.msg);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        myAttentionInterface.getError(e.toString());
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    private myAttentionInterface myAttentionInterface;

    public void setMyAttentionInterface(MyAttentionModel.myAttentionInterface myAttentionInterface) {
        this.myAttentionInterface = myAttentionInterface;
    }

    public interface   myAttentionInterface{
        void getFollowUserSuccess(MyFollowUserBean myFollowUserBean);
        void getFollowUserFailure(String msg);
        void getError(String msg);
    }
}
