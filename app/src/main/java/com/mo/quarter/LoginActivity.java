package com.mo.quarter;

import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mo.quarter.presenter.BasePresenter;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_wx)
    LinearLayout loginWx;
    @BindView(R.id.login_qq)
    LinearLayout loginQq;
    @BindView(R.id.login_other)
    TextView loginOther;

    @Override
    public int getLayoutid() {
        return R.layout.activity_login;
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

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    @OnClick({R.id.login_back,R.id.login_wx, R.id.login_qq, R.id.login_other})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.login_wx:
                ShowToast("微信登录");
                break;
            case R.id.login_qq:
                ShowToast("QQ登录");
                break;
            case R.id.login_other:
                ShowToast("其他登录");
                startActivity(new Intent(this,LoginOtherActivity.class));
                finish();
                break;
        }
    }
}

