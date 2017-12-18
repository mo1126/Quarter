package com.mo.quarter.view;

import com.mo.quarter.bean.GetVideosBean;
import com.mo.quarter.bean.UserInfoBean;
import com.mo.quarter.bean.UserVideosBean;

/**
 * Created by 莫迎华 on 2017/12/15.14:45.
 */

public interface UserVideosView {
    void getUserVideosSuccess(UserVideosBean userVideosBean);
    void getUserVideosFailure(String msg);
    void getUserInfoSuccess(UserInfoBean userInfoBean);
    void getUserInfoFailure(String msg);
    void guanzhuSuccess(String msg);
    void guanzhuFailure(String msg);
    void dianzanSuccess(String msg);
    void dianzanFailure(String msg);
    void getError(String msg);
}
