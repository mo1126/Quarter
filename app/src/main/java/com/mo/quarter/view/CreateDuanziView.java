package com.mo.quarter.view;

import com.mo.quarter.bean.CreatDuanziBean;

/**
 * Created by 莫迎华 on 2017/11/29.15:51.
 */

public interface CreateDuanziView {
    void shareSuccess(CreatDuanziBean creatDuanziBean);
    void shareFailure(String msg);
    void shareFailureToken(String msg);
    void shareError(String msg);
}
