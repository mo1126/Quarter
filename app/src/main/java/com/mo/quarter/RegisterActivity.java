package com.mo.quarter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.mo.quarter.presenter.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.reg_phone)
    EditText regPhone;
    @BindView(R.id.reg_pwd)
    EditText regPwd;

    @Override
    public int getLayoutid() {
        return R.layout.activity_register;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void Creat() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

    }
    @OnClick({R.id.reg_back, R.id.reg_yiyou, R.id.reg_reg, R.id.reg_youke})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reg_back:
                finish();
                break;
            case R.id.reg_yiyou:
                finish();
                break;
            case R.id.reg_reg:
                ShowToast("注册");
                break;
            case R.id.reg_youke:
                ShowToast("游客登录");
                startActivity(new Intent(this,HomeActivity.class));
                break;
        }
    }
}
