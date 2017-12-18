package com.mo.quarter.model;

import com.mo.quarter.myapp.MyApp;
import com.mo.quarter.utils.MyIntercepter;
import com.mo.quarter.utils.NetRequest;
import com.mo.quarter.utils.NetWorkUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 莫迎华 on 2017/12/14.11:49.
 */

public class MyCenterModel {

    public void setHead(List<MultipartBody.Part> parts){
        new NetRequest.Buidler().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .builder().getApiService().setHead(parts).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(ResponseBody requestBody) {
                try {
                    String s = requestBody.string();
                    JSONObject object=new JSONObject(s);
                    String code = object.getString("code");
                    String msg = object.getString("msg");
                    if(code.equals("0")){
                        setMyCenter.setHeadSuccess(msg);
                    }else if(code.equals("1")){
                        setMyCenter.setHeadFailure(msg);
                    }else{
                        setMyCenter.setHeadFailure(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                setMyCenter.mycenterError(e.toString());
            }

            @Override
            public void onComplete() {
            }
        });
    }
    public void setName(String name){
        new NetRequest.Buidler().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).builder()
                .getApiService().setName(MyIntercepter.uid,name).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String s = responseBody.string();
                    JSONObject object=new JSONObject(s);
                    String code = object.getString("code");
                    String msg = object.getString("msg");
                    if(code.equals("0")){
                        setMyCenter.setNameSuccess(msg);
                    }else if(code.equals("1")){
                        setMyCenter.setNameFailure(msg);
                    }else{
                        setMyCenter.setNameFailure(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(Throwable e) {
                    setMyCenter.mycenterError(e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }
    private setHeadInterface setMyCenter;

    public void setSetHeadInterface(MyCenterModel.setHeadInterface setHeadInterface) {
        this.setMyCenter = setHeadInterface;
    }

    public interface setHeadInterface{
        void setHeadSuccess(String msg);
        void setHeadFailure(String msg);
        void setNameSuccess(String msg);
        void setNameFailure(String msg);
        void mycenterError(String msg);
    }
}
