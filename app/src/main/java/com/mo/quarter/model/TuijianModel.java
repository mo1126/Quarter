package com.mo.quarter.model;

import android.widget.Toast;

import com.mo.quarter.R;
import com.mo.quarter.bean.GetVideosBean;
import com.mo.quarter.bean.TuijianAdBean;
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
                                tuijianModelInterFace.dianzanSuccess(msg);
                            } else {
                                tuijianModelInterFace.dianzanFailure(msg);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
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
                                tuijianModelInterFace.shoucangSuccess(msg);
                            } else {
                                tuijianModelInterFace.shoucangFailure(msg);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
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
                                tuijianModelInterFace.pinglunSuccess(msg);
                            } else {
                                tuijianModelInterFace.pinglungFailure(msg);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
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
        void dianzanSuccess(String msg);
        void dianzanFailure(String msg);
        void shoucangSuccess(String msg);
        void shoucangFailure(String msg);
        void pinglunSuccess(String msg);
        void pinglungFailure(String msg);
        void Error(String msg);
    }
}
