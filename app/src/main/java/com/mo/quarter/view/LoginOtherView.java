package com.mo.quarter.view;

import com.mo.quarter.bean.LoginOtherBean;

/**
 * Created by 莫迎华 on 2017/11/28.9:15.
 */

public interface LoginOtherView {
    void LoginSuccess(LoginOtherBean loginOtherBean);
    void LoginFailure(String msg);
    void LoginError(String msg);
}
