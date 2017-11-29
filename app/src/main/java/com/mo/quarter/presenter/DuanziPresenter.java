package com.mo.quarter.presenter;

import android.content.Context;

import com.mo.quarter.bean.JokesBean;
import com.mo.quarter.model.DuanziModel;
import com.mo.quarter.view.DuanziView;

/**
 * Created by 莫迎华 on 2017/11/28.18:26.
 */

public class DuanziPresenter extends BasePresenter<DuanziView> implements DuanziModel.getJokesInterface {
    public Context context;
    private DuanziModel model;

    public DuanziPresenter(DuanziView mview, Context context) {
        super(mview);
        this.context = context;
        model=new DuanziModel();
        model.setGetJokesInterface(this);
    }

    public void getJokes(String page){
        model.getJokes(page);
    }
    @Override
    public void getJokesSuccess(JokesBean jokesBean) {
        mview.getJokesSuccess(jokesBean);
    }

    @Override
    public void getJokesFailure(String msg) {
        mview.getJokesFailure(msg);
    }

    @Override
    public void getError(String msg) {
        mview.getError(msg);
    }
}
