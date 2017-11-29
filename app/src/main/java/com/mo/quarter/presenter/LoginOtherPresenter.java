package com.mo.quarter.presenter;

import android.content.Context;

import com.mo.quarter.bean.LoginOtherBean;
import com.mo.quarter.model.LoginOtherModel;
import com.mo.quarter.view.LoginOtherView;

/**
 * Created by 莫迎华 on 2017/11/28.9:15.
 */

public class LoginOtherPresenter extends BasePresenter<LoginOtherView> implements LoginOtherModel.LoginOtherInterface {
    private Context context;
    private LoginOtherModel model;

    public LoginOtherPresenter(LoginOtherView mview, Context context) {
        super(mview);
        this.context = context;
        model=new LoginOtherModel();
        model.setLoginOtherInterface(this);
    }
    public void LoginOther(String mob,String pwd){
        model.LoginOther(mob,pwd);
    }
    @Override
    public void LoginSuccess(LoginOtherBean loginOtherBean) {
        mview.LoginSuccess(loginOtherBean);
    }

    @Override
    public void LoginFailure(String msg) {
        mview.LoginFailure(msg);
    }

    @Override
    public void LoginError(String msg) {
        mview.LoginError(msg);
    }
}
