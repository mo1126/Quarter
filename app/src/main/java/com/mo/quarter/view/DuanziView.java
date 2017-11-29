package com.mo.quarter.view;

import com.mo.quarter.bean.JokesBean;

/**
 * Created by 莫迎华 on 2017/11/28.18:25.
 */

public interface DuanziView {
    void getJokesSuccess(JokesBean jokesBean);
    void getJokesFailure(String msg);
    void getError(String msg);
}
