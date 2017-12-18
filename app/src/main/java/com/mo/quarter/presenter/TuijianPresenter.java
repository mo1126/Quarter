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
        model = new TuijianModel();
        model.setTuijianModelInterFace(this);
    }

    public void getAd() {
        model.getAd();
    }

    public void getVideos(String uid, String type, String page) {
        model.getVideos(uid, type, page);
    }

    public void dianzan(String wid){
        model.dianzan(wid);
    }
    public void shoucang(String wid){
        model.shoucang(wid);
    }
    public void pinglun(String wid,String content){
        model.pinglun(wid,content);
    }

    @Override
    public void getAdSuccess(TuijianAdBean tuijianAdBean) {
        mview.getAdSuccess(tuijianAdBean);
    }

    @Override
    public void getAdFailure(String msg, String code) {
        if ("1".equals(code)) {
            mview.getAdFailure(msg);
        } else {
            context.startActivity(new Intent(context, LoginActivity.class));
        }
    }

    @Override
    public void getVideosSuccess(GetVideosBean getVideosBean) {
        mview.getVideosSuccess(getVideosBean);
    }

    @Override
    public void getVideosFailure(String msg, String code) {
        if ("1".equals(code)) {
            mview.getVideosFailure(msg);
        } else {
            context.startActivity(new Intent(context, LoginActivity.class));
        }
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
    public void shoucangSuccess(String msg) {
        mview.shoucangSuccess(msg);
    }

    @Override
    public void shoucangFailure(String msg) {
        mview.shoucangFailure(msg);
    }

    @Override
    public void pinglunSuccess(String msg) {
        mview.pinglunSuccess(msg);
    }

    @Override
    public void pinglungFailure(String msg) {
        mview.pinglungFailure(msg);
    }

    @Override
    public void Error(String msg) {
        mview.Error(msg);
    }
}
