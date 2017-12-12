package com.mo.quarter.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.mo.quarter.LoginActivity;
import com.mo.quarter.bean.HotShipin;
import com.mo.quarter.model.ShipinModel;
import com.mo.quarter.view.HotShipinView;

/**
 * Created by 莫迎华 on 2017/12/9.11:35.
 */

public class HotShipinPresenter extends BasePresenter<HotShipinView> implements ShipinModel.ShipinModelInterFace {


    private  Context context;
    private ShipinModel model;

    public HotShipinPresenter(HotShipinView mview, Context context) {
        super(mview);
        this.context = context;
        model=new ShipinModel();
        model.setShipinModelInterFace(this);
    }
    public void gethotvideo(String page){
        model.gethotVideos(page);
    }


    @Override
    public void getVideosSuccess(HotShipin getVideosBean) {
mview.getVideosSuccess(getVideosBean);
    }

    @Override
    public void getVideosFailure(String msg, String code) {
        mview.getVideosFailure(msg,code);

    }

    @Override
    public void Error(String msg) {
        mview.Error(msg);
    }
}
