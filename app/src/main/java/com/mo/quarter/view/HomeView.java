package com.mo.quarter.view;

import com.mo.quarter.bean.UserInfoBean;

/**
 * Created by 莫迎华 on 2017/11/28.14:12.
 */

public interface HomeView {
    void getinfoSuccess(UserInfoBean userInfoBean);
    void getinfoFailure(String msg);
    void onError(String e);
}
