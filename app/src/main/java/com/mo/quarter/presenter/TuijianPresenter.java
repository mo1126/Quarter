package com.mo.quarter.presenter;

import android.content.Context;
import android.content.Intent;

import com.mo.quarter.LoginActivity;
import com.mo.quarter.bean.GetVideosBean;
import com.mo.quarter.bean.TuijianAdBean;
import com.mo.quarter.model.TuijianModel;
import com.mo.quarter.view.TuijianView;

/**
 * Created by 莫迎华 on 2017/12/3.19:36.
 */

public class TuijianPresenter extends BasePresenter<TuijianView> implements TuijianModel.TuijianModelInterFace {

    private Context context;
    private TuijianModel model;
    public TuijianPresenter(TuijianView mview, Context context) {
        super(mview);
        this.context = context;
        model=new TuijianModel();
        model.setTuijianModelInterFace(this);
    }

    public void getAd(){
        model.getAd();
    }
    public void getVideos(String uid,String type,String page){
        model.getVideos(uid,type,page);
    }

    @Override
    public void getAdSuccess(TuijianAdBean tuijianAdBean) {
        mview.getAdSuccess(tuijianAdBean);
    }

    @Override
    public void getAdFailure(String msg, String code) {
        if("1".equals(code)){
            mview.getAdFailure(msg);
        }else{
            context.startActivity(new Intent(context, LoginActivity.class));
        }
    }

    @Override
    public void getVideosSuccess(GetVideosBean getVideosBean) {
        mview.getVideosSuccess(getVideosBean);
    }

    @Override
    public void getVideosFailure(String msg, String code) {
        if("1".equals(code)){
            mview.getVideosFailure(msg);
        }else{
            context.startActivity(new Intent(context, LoginActivity.class));
        }
    }

    @Override
    public void Error(String msg) {
        mview.Error(msg);
    }
}
