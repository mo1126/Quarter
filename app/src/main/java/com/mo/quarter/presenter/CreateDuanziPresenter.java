package com.mo.quarter.presenter;

import android.content.Context;

import com.mo.quarter.bean.CreatDuanziBean;
import com.mo.quarter.model.CreateDuanziModel;
import com.mo.quarter.view.CreateDuanziView;

import java.util.List;

/**
 * Created by 莫迎华 on 2017/11/29.15:50.
 */

public class CreateDuanziPresenter extends BasePresenter<CreateDuanziView> implements CreateDuanziModel.CreatDuanziInterface {

    public Context context;
    public CreateDuanziModel model;

    public CreateDuanziPresenter(CreateDuanziView mview, Context context) {
        super(mview);
        this.context = context;
        model=new CreateDuanziModel();
        model.setCreatDuanziInterface(this);
    }


    public void createDuanzi(String uid, String content, List<String> list){
        model.shareDuanzi(uid,content,list);
    }

    @Override
    public void shareSuccess(CreatDuanziBean creatDuanziBean) {
        mview.shareSuccess(creatDuanziBean);
    }

    @Override
    public void shareFailure(String msg, String code) {
        if("1".equals(code)){
            mview.shareFailure(msg);
        }else{
            mview.shareFailureToken(msg);
        }
    }

    @Override
    public void shareError(String msg) {
        mview.shareError(msg);
    }
}
