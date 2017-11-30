package com.mo.quarter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.mo.quarter.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateActivity extends BaseActivity {

    @Override
    public int getLayoutid() {
        return R.layout.activity_create;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void Creat() {
        ButterKnife.bind(this);

    }


    @OnClick({R.id.creat_back, R.id.creat_shipin, R.id.creat_duanzi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.creat_back:
                finish();
                break;
            case R.id.creat_shipin:
                ShowToast("创作视频");
                break;
            case R.id.creat_duanzi:
                ShowToast("创作段子");
                startActivity(new Intent(this,DuanziActivity.class));
                finish();
                break;
        }
    }
}
