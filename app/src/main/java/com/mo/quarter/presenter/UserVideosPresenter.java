package com.mo.quarter.presenter;

import android.content.Context;

import com.mo.quarter.bean.GetVideosBean;
import com.mo.quarter.bean.UserInfoBean;
import com.mo.quarter.bean.UserVideosBean;
import com.mo.quarter.model.UserVideoModel;
import com.mo.quarter.view.UserVideosView;

/**
 * Created by 莫迎华 on 2017/12/15.14:44.
 */

public class UserVideosPresenter extends BasePresenter<UserVideosView> implements UserVideoModel.userVideosInterface {

    private Context context;
    private UserVideoModel model;

    public UserVideosPresenter(UserVideosView mview, Context context) {
        super(mview);
        this.context = context;
        model = new UserVideoModel();
        model.setUserVideosInterface(this);
    }

    public void getUserInfo(String uid) {
        model.getUserInfo(uid);
    }

    public void getUserVideos(String uid, String page) {
        model.getUserVideos(uid, page);
    }

    public void guanzhu(String fid) {
        model.guanzhu(fid);
    }

    public void dianzan(String uid, String wid) {
        model.dianzan(uid, wid);
    }

    @Override
    public void getUserVideosSuccess(UserVideosBean userVideosBean) {
        mview.getUserVideosSuccess(userVideosBean);
    }

    @Override
    public void getUserVideosFailure(String msg) {
        mview.getUserVideosFailure(msg);
    }

    @Override
    public void getUserInfoSuccess(UserInfoBean userInfoBean) {
        mview.getUserInfoSuccess(userInfoBean);
    }

    @Override
    public void getUserInfoFailure(String msg) {
        mview.getUserInfoFailure(msg);
    }

    @Override
    public void guanzhuSuccess(String msg) {
        mview.guanzhuSuccess(msg);
    }

    @Override
    public void guanzhuFailure(String msg) {
        mview.guanzhuFailure(msg);
    }

    @Override
    public void dianzanSuccess(String msg) {
        mview.dianzanSuccess(msg);
    }

    @Override
    public void dianzanFailure(String msg) {
        mview.dianzanFailure(msg);
    }

    @Override
    public void getError(String msg) {
        mview.getError(msg);
    }
}
