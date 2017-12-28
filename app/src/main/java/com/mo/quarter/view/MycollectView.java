package com.mo.quarter.view;

import com.mo.quarter.bean.MycollectBean;

/**
 * Created by 莫迎华 on 2017/12/18.20:48.
 */

public interface MycollectView {
    void getCollectSuccess(MycollectBean mycollectBean);
    void getCollectFailure(String msg);
    void dianzanSuccess(String msg);
    void dianzanFailure(String msg);
    void shoucangSuccess(String msg);
    void shoucangFailure(String msg);
    void pinglunSuccess(String msg);
    void pinglungFailure(String msg);
    void getError(String msg);
}
