package com.mo.quarter.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.mo.quarter.bean.UserInfoBean;
import com.mo.quarter.model.HomeModel;
import com.mo.quarter.view.HomeView;

/**
 * Created by 莫迎华 on 2017/11/28.14:12.
 */

public class HomePresenter extends BasePresenter<HomeView> implements HomeModel.GetUserInfoInterface {

    private Context context;
    private HomeModel model;
    public HomePresenter(HomeView mview, Context context) {
        super(mview);
        this.context = context;
        model=new HomeModel();
        model.setGetUserInfoInterface(this);
    }


    public void getUserInfo(){
        SharedPreferences token = context.getSharedPreferences("token", Context.MODE_PRIVATE);
        String uid = token.getString("uid","");
        model.getUserInfo(uid);
    }
    @Override
    public void getinfoSuccess(UserInfoBean userInfoBean) {
        mview.getinfoSuccess(userInfoBean);
    }

    @Override
    public void getinfoFailure(String msg) {
        mview.getinfoFailure(msg);
    }

    @Override
    public void onError(String e) {
        mview.onError(e);
    }
}
