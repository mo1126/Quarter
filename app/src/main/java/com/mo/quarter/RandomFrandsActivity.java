package com.mo.quarter;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mo.quarter.bean.RandomFransBean;
import com.mo.quarter.presenter.RandomFrandsPresenter;
import com.mo.quarter.view.RandomFransView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RandomFrandsActivity extends BaseActivity<RandomFrandsPresenter> implements RandomFransView {

    @BindView(R.id.random_search)
    SearchView randomSearch;
    @BindView(R.id.random_sousuo)
    XRecyclerView Sousuorv;
    @BindView(R.id.random_suiji)
    XRecyclerView Suijirv;

    @Override
    public int getLayoutid() {
        return R.layout.activity_random_frands;
    }

    @Override
    public RandomFrandsPresenter initPresenter() {
        return new RandomFrandsPresenter(this);
    }

    @Override
    public void Creat() {
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        Suijirv.setPullRefreshEnabled(false);
        Suijirv.setLoadingMoreEnabled(false);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        Suijirv.setLayoutManager(lm);
        Sousuorv.setPullRefreshEnabled(false);
        Sousuorv.setLoadingMoreEnabled(false);
        Sousuorv.setLayoutManager(lm);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getRandomFrands();
    }

    @Override
    public void getRandomSuccess(RandomFransBean randomFransBean) {

        //Suijirv.setAdapter();
    }

    @Override
    public void getRandomFailure(String msg) {
        System.out.println("getRandomFailure" + msg);
    }

    @Override
    public void getError(String msg) {
        System.out.println("getError" + msg);
    }

    @OnClick({R.id.random_back, R.id.random_huan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.random_back:
                finish();
                break;
            case R.id.random_huan:
                presenter.getRandomFrands();
                break;
        }
    }
}
