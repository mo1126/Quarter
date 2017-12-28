package com.mo.quarter.presenter;

import com.mo.quarter.bean.RandomFransBean;
import com.mo.quarter.model.RandomFrandsModel;
import com.mo.quarter.view.RandomFransView;

/**
 * Created by 莫迎华 on 2017/12/19.14:50.
 */

public class RandomFrandsPresenter extends BasePresenter<RandomFransView> implements RandomFrandsModel.RandomFrandsInterface {

    private RandomFrandsModel model;

    public RandomFrandsPresenter(RandomFransView mview) {
        super(mview);
        model = new RandomFrandsModel();
        model.setRandomFrandsInterface(this);
    }
    public void getRandomFrands(){
        model.raandomFrands();
    }

    @Override
    public void getRandomSuccess(RandomFransBean randomFransBean) {
        mview.getRandomSuccess(randomFransBean);
    }

    @Override
    public void getRandomFailure(String msg) {
        mview.getRandomFailure(msg);
    }

    @Override
    public void getError(String msg) {
        mview.getError(msg);
    }
}
