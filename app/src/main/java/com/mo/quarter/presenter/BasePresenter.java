package com.mo.quarter.presenter;

/**
 * Created by 莫迎华 on 2017/11/15.9:12.
 */

public class BasePresenter<V> {
    public  V mview;

    public BasePresenter(V mview) {
        this.mview = mview;
    }
    public void detach(){
        this.mview = null;
    }
}
