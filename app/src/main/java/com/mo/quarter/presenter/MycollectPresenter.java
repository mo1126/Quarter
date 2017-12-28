package com.mo.quarter.presenter;

import com.mo.quarter.bean.MycollectBean;
import com.mo.quarter.model.MyCollectModel;
import com.mo.quarter.utils.MyIntercepter;
import com.mo.quarter.view.MycollectView;

/**
 * Created by 莫迎华 on 2017/12/18.20:48.
 */

public class MycollectPresenter extends BasePresenter<MycollectView> implements MyCollectModel.collectInterface {

    private MyCollectModel model;

    public MycollectPresenter(MycollectView mview) {
        super(mview);
        model = new MyCollectModel();
        model.setCollectInterface(this);
    }

    public void getMycollects() {
        model.getCollect();
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
    public void getCollectSuccess(MycollectBean mycollectBean) {
        mview.getCollectSuccess(mycollectBean);
    }

    @Override
    public void getCollectFailure(String msg) {
        mview.getCollectFailure(msg);
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
    public void getError(String msg) {
        mview.getError(msg);
    }
}
