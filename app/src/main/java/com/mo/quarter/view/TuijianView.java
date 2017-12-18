package com.mo.quarter.view;

import com.mo.quarter.bean.GetVideosBean;
import com.mo.quarter.bean.TuijianAdBean;

/**
 * Created by 莫迎华 on 2017/12/3.19:37.
 */

public interface TuijianView {
    void getAdSuccess(TuijianAdBean tuijianAdBean);
    void getAdFailure(String msg);
    void getVideosSuccess(GetVideosBean getVideosBean);
    void getVideosFailure(String msg);
    void dianzanSuccess(String msg);
    void dianzanFailure(String msg);
    void shoucangSuccess(String msg);
    void shoucangFailure(String msg);
    void pinglunSuccess(String msg);
    void pinglungFailure(String msg);
    void Error(String msg);
}
