package com.mo.quarter.view;

import com.mo.quarter.bean.MyFollowUserBean;

/**
 * Created by 莫迎华 on 2017/12/16.10:26.
 */

public interface MyAttentionView {
    void getFollowUserSuccess(MyFollowUserBean myFollowUserBean);
    void getFollowUserFailure(String msg);
    void getError(String msg);
}
