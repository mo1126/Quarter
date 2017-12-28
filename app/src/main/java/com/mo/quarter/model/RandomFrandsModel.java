package com.mo.quarter.model;

import com.mo.quarter.bean.RandomFransBean;
import com.mo.quarter.utils.NetRequest;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 莫迎华 on 2017/12/19.14:30.
 */

public class RandomFrandsModel {

    public void raandomFrands(){
        new NetRequest.Buidler().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .builder().getApiService().getrandomfrands().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<RandomFransBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RandomFransBean randomFransBean) {
                if(randomFransBean.code.equals("0")){
                    randomFrandsInterface.getRandomSuccess(randomFransBean);
                }
                else{
                    randomFrandsInterface.getRandomFailure(randomFransBean.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                randomFrandsInterface.getError(e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private RandomFrandsInterface randomFrandsInterface;

    public void setRandomFrandsInterface(RandomFrandsInterface randomFrandsInterface) {
        this.randomFrandsInterface = randomFrandsInterface;
    }

    public interface RandomFrandsInterface{
        void getRandomSuccess(RandomFransBean randomFransBean);
        void getRandomFailure(String msg);
        void getError(String msg);
    }
}
