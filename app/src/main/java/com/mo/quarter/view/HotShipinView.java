package com.mo.quarter.view;

import com.mo.quarter.bean.HotShipin;

/**
 * Created by 莫迎华 on 2017/12/9.11:37.
 */

public interface HotShipinView {
    void getVideosSuccess(HotShipin getVideosBean);
    void getVideosFailure(String msg, String code);
    void Error(String msg);
}
