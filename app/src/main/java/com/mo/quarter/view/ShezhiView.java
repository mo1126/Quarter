package com.mo.quarter.view;

import com.mo.quarter.bean.GetVersionBean;

/**
 * Created by 莫迎华 on 2017/12/14.19:31.
 */

public interface ShezhiView {
    void getSuccess(GetVersionBean getVersionBean);
    void getFailure(String msg);
    void Error(String msg);
}
