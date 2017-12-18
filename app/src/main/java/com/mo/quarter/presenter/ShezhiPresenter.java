package com.mo.quarter.presenter;

import android.content.Context;

import com.mo.quarter.bean.GetVersionBean;
import com.mo.quarter.model.ShezhiModel;
import com.mo.quarter.view.ShezhiView;

/**
 * Created by 莫迎华 on 2017/12/14.19:30.
 */

public class ShezhiPresenter extends BasePresenter<ShezhiView> implements ShezhiModel.shezhiInterface {
    private Context context;
    private ShezhiModel model;
    public ShezhiPresenter(ShezhiView mview, Context context) {
        super(mview);
        this.context = context;
        model = new ShezhiModel();
        model.setShezhiInterface(this);
    }
    public void getVersion() {
        model.getVersion();
    }
    @Override
    public void getSuccess(GetVersionBean getVersionBean) {
        mview.getSuccess(getVersionBean);
    }
    @Override
    public void getFailure(String msg) {
        mview.getFailure(msg);
    }
    @Override
    public void Error(String msg) {
        mview.Error(msg);
    }
}
