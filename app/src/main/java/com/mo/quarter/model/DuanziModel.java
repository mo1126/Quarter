package com.mo.quarter.model;

import com.mo.quarter.bean.JokesBean;
import com.mo.quarter.utils.NetRequest;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 莫迎华 on 2017/11/28.18:15.
 */

public class DuanziModel {
    public void getJokes(String page){
        new NetRequest.Buidler().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .builder().getApiService().getJokes(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<JokesBean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(JokesBean jokesBean) {
                if("0".equals(jokesBean.code)){
                    getJokesInterface.getJokesSuccess(jokesBean);
                }else if("1".equals(jokesBean.code)){
                    getJokesInterface.getJokesFailure(jokesBean.msg);
                }else{
                    getJokesInterface.getJokesFailure(jokesBean.msg);
                }
            }
            @Override
            public void onError(Throwable e) {
                getJokesInterface.getError(e.toString());
            }
            @Override
            public void onComplete() {
            }
        });
    }
    private getJokesInterface getJokesInterface;
    public void setGetJokesInterface(DuanziModel.getJokesInterface getJokesInterface) {
        this.getJokesInterface = getJokesInterface;
    }
    public interface getJokesInterface{
        void getJokesSuccess(JokesBean jokesBean);
        void getJokesFailure(String msg);
        void getError(String msg);
    }
}
