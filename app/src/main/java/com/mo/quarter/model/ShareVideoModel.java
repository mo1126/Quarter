package com.mo.quarter.model;

import com.mo.quarter.utils.NetRequest;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 莫迎华 on 2017/12/6.16:22.
 */

public class ShareVideoModel {

    public void publish(List<MultipartBody.Part> parts){
        new NetRequest.Buidler()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .builder().getApiService().publish(parts).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            JSONObject jsonObject=new JSONObject(string);
                            String code = (String) jsonObject.get("code");
                            String msg = (String) jsonObject.get("msg");
                            if("0".equals(code)){
                                shareVideoInterface.shareVideoSuccess(msg);
                            }else if("1".equals(code)){
                                shareVideoInterface.shareVideoFailure(msg,code);
                            } else{
                                shareVideoInterface.shareVideoFailure(msg,code);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        shareVideoInterface.shareError(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private shareVideoInterface shareVideoInterface;

    public void setShareVideoInterface(ShareVideoModel.shareVideoInterface shareVideoInterface) {
        this.shareVideoInterface = shareVideoInterface;
    }

    public interface shareVideoInterface{
        void shareVideoSuccess(String msg);
        void shareVideoFailure(String msg,String code);
        void shareError(String msg);
    }
}
