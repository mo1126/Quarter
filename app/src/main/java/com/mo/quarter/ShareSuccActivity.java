package com.mo.quarter;

import android.os.Bundle;
import android.view.View;

import com.mo.quarter.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareSuccActivity extends BaseActivity {

    @Override
    public int getLayoutid() {
        return R.layout.activity_share_succ;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void Creat() {
        ButterKnife.bind(this);
    }
    @OnClick({R.id.tv_quxiao, R.id.gogogo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_quxiao:
                finish();
                break;
            case R.id.gogogo:
                finish();
                break;
        }
    }
}
