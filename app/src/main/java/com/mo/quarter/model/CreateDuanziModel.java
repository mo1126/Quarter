package com.mo.quarter.model;

import com.mo.quarter.bean.CreatDuanziBean;
import com.mo.quarter.utils.NetRequest;

import java.io.File;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 莫迎华 on 2017/11/29.15:26.
 */

public class CreateDuanziModel {

    public void shareDuanzi(String uid, String content, List<String> list){
        MultipartBody.Builder builder=new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("uid",uid)
                .addFormDataPart("content",content);
        if(list!=null){
            for (String s : list) {
                File file=new File(s.toString());
                RequestBody requstbody=RequestBody.create(MediaType.parse("multipart/form-data"),file);
                builder.addFormDataPart("jokeFiles",file.getName(),requstbody);
            }
        }

        new NetRequest.Buidler().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).builder().getApiService()
                .shareDuanzi(builder.build().parts()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CreatDuanziBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(CreatDuanziBean creatDuanziBean) {
                        if("0".equals(creatDuanziBean.code)){
                            CreatDuanziInterface.shareSuccess(creatDuanziBean);
                        }else if("1".equals(creatDuanziBean.code)){
                            CreatDuanziInterface.shareFailure(creatDuanziBean.msg,creatDuanziBean.code);
                        }else{
                            CreatDuanziInterface.shareFailure(creatDuanziBean.msg,creatDuanziBean.code);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        CreatDuanziInterface.shareError(e.toString());
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    private CreatDuanziInterface CreatDuanziInterface;

    public void setCreatDuanziInterface(CreateDuanziModel.CreatDuanziInterface creatDuanziInterface) {
        CreatDuanziInterface = creatDuanziInterface;
    }

    public interface CreatDuanziInterface{
        void shareSuccess(CreatDuanziBean creatDuanziBean);
        void shareFailure(String msg,String code);
        void shareError(String msg);
    }
}
