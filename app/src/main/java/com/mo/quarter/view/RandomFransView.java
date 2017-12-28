package com.mo.quarter.view;

import com.mo.quarter.bean.RandomFransBean;

/**
 * Created by 莫迎华 on 2017/12/19.14:51.
 */

public interface RandomFransView {
    void getRandomSuccess(RandomFransBean randomFransBean);
    void getRandomFailure(String msg);
    void getError(String msg);
}
