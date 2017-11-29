package com.mo.quarter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mo.quarter.presenter.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WangjiPwdActivity extends BaseActivity {

    @BindView(R.id.wangji_phone)
    EditText wangjiPhone;
    @BindView(R.id.wangji_getcode)
    TextView wangjiGetcode;
    @BindView(R.id.wangji_code)
    EditText wangjiCode;
    @BindView(R.id.wangji_next)
    Button wangjiNext;
    @BindView(R.id.wangji_youke)
    TextView wangjiYouke;

    @Override
    public int getLayoutid() {
        return R.layout.activity_wangji_pwd;
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


    @OnClick({R.id.wangji_back, R.id.wangji_yiyou, R.id.wangji_getcode, R.id.wangji_next, R.id.wangji_youke})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wangji_back:
                finish();
                break;
            case R.id.wangji_yiyou:
                finish();
                break;
            case R.id.wangji_getcode:
                ShowToast("获取验证码");
                break;
            case R.id.wangji_next:
                ShowToast("下一步");
                break;
            case R.id.wangji_youke:
                ShowToast("游客登录");
                startActivity(new Intent(this,HomeActivity.class));
                break;
        }
    }
}
