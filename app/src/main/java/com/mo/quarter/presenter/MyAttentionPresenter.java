package com.mo.quarter.presenter;

import android.content.Context;

import com.mo.quarter.bean.MyFollowUserBean;
import com.mo.quarter.model.MyAttentionModel;
import com.mo.quarter.view.MyAttentionView;

/**
 * Created by 莫迎华 on 2017/12/16.10:25.
 */

public class MyAttentionPresenter extends BasePresenter<MyAttentionView> implements MyAttentionModel.myAttentionInterface {


    public Context context;
    public MyAttentionModel model;

    public MyAttentionPresenter(MyAttentionView mview, Context context) {
        super(mview);
        this.context = context;
        model = new MyAttentionModel();
        model.setMyAttentionInterface(this);
    }


    public void getFollowUsers(){
        model.getFollowUsers();
    }
    @Override
    public void getFollowUserSuccess(MyFollowUserBean myFollowUserBean) {
        mview.getFollowUserSuccess(myFollowUserBean);
    }

    @Override
    public void getFollowUserFailure(String msg) {
        mview.getFollowUserFailure(msg);
    }

    @Override
    public void getError(String msg) {
        mview.getError(msg);
    }
}
