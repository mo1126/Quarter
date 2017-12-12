package com.mo.quarter.model;

import com.mo.quarter.bean.HotShipin;
import com.mo.quarter.utils.NetRequest;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 莫迎华 on 2017/12/9.11:27.
 */

public class ShipinModel {
    public void gethotVideos(String page) {
        new NetRequest.Buidler().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .builder().getApiService().gethotvideos(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotShipin>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(HotShipin getVideosBean) {
                        if ("0".equals(getVideosBean.code)) {
                            shipinModelInterFace.getVideosSuccess(getVideosBean);
                        } else if ("1".equals(getVideosBean.code)) {
                            shipinModelInterFace.getVideosFailure(getVideosBean.msg, getVideosBean.code);
                        } else {
                            shipinModelInterFace.getVideosFailure(getVideosBean.msg, getVideosBean.code);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        shipinModelInterFace.Error(e.toString());
                    }

                    @Override
                    public void onComplete() {
                    }
                });


    }

    private ShipinModelInterFace shipinModelInterFace;

    public void setShipinModelInterFace(ShipinModelInterFace shipinModelInterFace) {
        this.shipinModelInterFace = shipinModelInterFace;
    }

    public interface ShipinModelInterFace {
        void getVideosSuccess(HotShipin getVideosBean);
        void getVideosFailure(String msg, String code);
        void Error(String msg);
    }
}