package com.mo.quarter.model;

import com.mo.quarter.bean.GetVersionBean;
import com.mo.quarter.utils.NetRequest;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 莫迎华 on 2017/12/14.19:15.
 */

public class ShezhiModel {

    public void getVersion(){
        new NetRequest.Buidler().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).builder()
                .getApiService().getversion().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<GetVersionBean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(GetVersionBean getVersionBean) {
                if(getVersionBean.code.equals("0")){
                    shezhiInterface.getSuccess(getVersionBean);
                }else if(getVersionBean.code.equals("1")){
                    shezhiInterface.getFailure(getVersionBean.msg);
                }else{
                    shezhiInterface.getFailure(getVersionBean.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                shezhiInterface.Error(e.toString());
            }
            @Override
            public void onComplete() {
            }
        });
    }
    private shezhiInterface shezhiInterface;
    public void setShezhiInterface(ShezhiModel.shezhiInterface shezhiInterface) {
        this.shezhiInterface = shezhiInterface;
    }

    public interface shezhiInterface{
        void getSuccess(GetVersionBean getVersionBean);
        void getFailure(String msg);
        void Error(String msg);
    }
}
