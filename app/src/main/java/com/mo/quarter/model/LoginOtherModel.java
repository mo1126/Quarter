package com.mo.quarter.model;

import com.mo.quarter.bean.LoginOtherBean;
import com.mo.quarter.utils.ApiService;
import com.mo.quarter.utils.NetRequest;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 莫迎华 on 2017/11/28.8:30.
 */

public class LoginOtherModel {

    public void LoginOther(String mob,String pwd){
          new NetRequest.Buidler().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .builder().getApiService().loginOther(mob,pwd).subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<LoginOtherBean>() {
              @Override
              public void onSubscribe(Disposable d) {

              }

              @Override
              public void onNext(LoginOtherBean loginOtherBean) {
                  String code = loginOtherBean.code;
                  if("0".equals(code)){
                      loginOtherInterface.LoginSuccess(loginOtherBean);
                  } else{
                      loginOtherInterface.LoginFailure(loginOtherBean.msg);
                  }
              }

              @Override
              public void onError(Throwable e) {
                  loginOtherInterface.LoginError(e.toString());
              }

              @Override
              public void onComplete() {

              }
          });
    }

    private LoginOtherInterface loginOtherInterface;

    public void setLoginOtherInterface(LoginOtherInterface loginOtherInterface) {
        this.loginOtherInterface = loginOtherInterface;
    }

    public interface LoginOtherInterface{
        void LoginSuccess(LoginOtherBean loginOtherBean);
        void LoginFailure(String msg);
        void LoginError(String msg);
    }
}
