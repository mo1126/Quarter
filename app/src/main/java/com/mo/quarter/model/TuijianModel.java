package com.mo.quarter.model;

import com.mo.quarter.bean.GetVideosBean;
import com.mo.quarter.bean.TuijianAdBean;
import com.mo.quarter.utils.NetRequest;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 莫迎华 on 2017/12/3.18:55.
 */

public class TuijianModel {

    public void getAd(){
        new NetRequest.Buidler().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .builder().getApiService().getAd().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<TuijianAdBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TuijianAdBean tuijianAdBean) {
                if("0".equals(tuijianAdBean.code)){
                    tuijianModelInterFace.getAdSuccess(tuijianAdBean);
                }else if("1".equals(tuijianAdBean.code)){
                    tuijianModelInterFace.getAdFailure(tuijianAdBean.msg,tuijianAdBean.code);
                }else{
                    tuijianModelInterFace.getAdFailure(tuijianAdBean.msg,tuijianAdBean.code);
                }
            }


            @Override
            public void onError(Throwable e) {
                tuijianModelInterFace.Error(e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getVideos(String uid,String type,String page){
        new NetRequest.Buidler().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .builder().getApiService().getvideos(uid,type,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetVideosBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GetVideosBean getVideosBean) {
                        if("0".equals(getVideosBean.code)){
                            tuijianModelInterFace.getVideosSuccess(getVideosBean);
                        }else if("1".equals(getVideosBean.code)){
                            tuijianModelInterFace.getVideosFailure(getVideosBean.msg,getVideosBean.code);
                        }else{
                            tuijianModelInterFace.getVideosFailure(getVideosBean.msg,getVideosBean.code);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        tuijianModelInterFace.Error(e.toString());
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    private  TuijianModelInterFace tuijianModelInterFace;

    public void setTuijianModelInterFace(TuijianModelInterFace tuijianModelInterFace) {
        this.tuijianModelInterFace = tuijianModelInterFace;
    }

    public interface  TuijianModelInterFace{
        void getAdSuccess(TuijianAdBean tuijianAdBean);
        void getAdFailure(String msg,String code);
        void getVideosSuccess(GetVideosBean getVideosBean);
        void getVideosFailure(String msg,String code);
        void Error(String msg);
    }
}
