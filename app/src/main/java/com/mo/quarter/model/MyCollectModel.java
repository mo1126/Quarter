package com.mo.quarter.model;

import com.mo.quarter.bean.MycollectBean;
import com.mo.quarter.utils.MyIntercepter;
import com.mo.quarter.utils.NetRequest;

import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 莫迎华 on 2017/12/18.20:36.
 */

public class MyCollectModel {
    public void getCollect(){
        new NetRequest.Buidler().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .builder().getApiService().getcolects(MyIntercepter.uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MycollectBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(MycollectBean mycollectBean) {
                        if(mycollectBean.code.equals("0")){
                            collectInterface.getCollectSuccess(mycollectBean);
                        }else{
                            collectInterface.getCollectFailure(mycollectBean.msg);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        collectInterface.getError(e.toString());
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
    public void dianzan(String wid){
        new NetRequest.Buidler().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).builder()
                .getApiService()
                .praise(MyIntercepter.uid, wid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            JSONObject jsonObject = new JSONObject(string);
                            String code = jsonObject.getString("code");
                            String msg = jsonObject.getString("msg");
                            if (code.equals("0")) {
                                collectInterface.dianzanSuccess(msg);
                            } else {
                                collectInterface.dianzanFailure(msg);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        collectInterface.getError(e.toString());
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void shoucang(String wid){
        new NetRequest.Buidler().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).builder()
                .getApiService().shoucang(MyIntercepter.uid,wid)
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
                            JSONObject jsonObject = new JSONObject(string);
                            String code = jsonObject.getString("code");
                            String msg = jsonObject.getString("msg");
                            if (code.equals("0")) {
                                collectInterface.shoucangSuccess(msg);
                            } else {
                                collectInterface.shoucangFailure(msg);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        collectInterface.getError(e.toString());
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void pinglun(String wid,String content){
        new NetRequest.Buidler().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).builder()
                .getApiService().pinglun(MyIntercepter.uid,wid,content)
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
                            JSONObject jsonObject = new JSONObject(string);
                            String code = jsonObject.getString("code");
                            String msg = jsonObject.getString("msg");
                            if (code.equals("0")) {
                                collectInterface.pinglunSuccess(msg);
                            } else {
                                collectInterface.pinglungFailure(msg);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        collectInterface.getError(e.toString());
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
    private collectInterface collectInterface;

    public void setCollectInterface(MyCollectModel.collectInterface collectInterface) {
        this.collectInterface = collectInterface;
    }

    public interface collectInterface{
        void getCollectSuccess(MycollectBean mycollectBean);
        void getCollectFailure(String msg);
        void dianzanSuccess(String msg);
        void dianzanFailure(String msg);
        void shoucangSuccess(String msg);
        void shoucangFailure(String msg);
        void pinglunSuccess(String msg);
        void pinglungFailure(String msg);
        void getError(String msg);
    }
}
