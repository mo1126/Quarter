package com.mo.quarter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.mo.quarter.bean.LoginOtherBean;
import com.mo.quarter.presenter.BasePresenter;
import com.mo.quarter.presenter.LoginOtherPresenter;
import com.mo.quarter.view.LoginOtherView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginOtherActivity extends BaseActivity<LoginOtherPresenter> implements LoginOtherView {


    @BindView(R.id.login_other_back)
    ImageView loginOtherBack;
    @BindView(R.id.other_zhuce)
    TextView otherZhuce;
    @BindView(R.id.other_login)
    Button otherLogin;
    @BindView(R.id.other_wangji)
    TextView otherWangji;
    @BindView(R.id.other_youke)
    TextView otherYouke;
    @BindView(R.id.other_name)
    EditText otherName;
    @BindView(R.id.other_pwd)
    EditText otherPwd;

    @Override
    public int getLayoutid() {
        return R.layout.activity_login_other;
    }

    @Override
    public LoginOtherPresenter initPresenter() {
        return new LoginOtherPresenter(this,this);
    }

    @Override
    public void Creat() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.login_other_back, R.id.other_zhuce, R.id.other_login, R.id.other_wangji, R.id.other_youke})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_other_back:
                finish();
                break;
            case R.id.other_zhuce:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.other_login:
                presenter.LoginOther(otherName.getText().toString(),otherPwd.getText().toString());
                EMClient.getInstance().login(otherName.getText().toString(),otherPwd.getText().toString(),new EMCallBack() {//回调
                    @Override
                    public void onSuccess() {
                        EMClient.getInstance().groupManager().loadAllGroups();
                        EMClient.getInstance().chatManager().loadAllConversations();
                        Log.d("main", "登录聊天服务器成功！");
                        EMClient.getInstance().chatManager().loadAllConversations();
                        EMClient.getInstance().groupManager().loadAllGroups();
                    }
                    @Override
                    public void onProgress(int progress, String status) {
                    }
                    @Override
                    public void onError(int code, String message) {
                        Log.d("main", "登录聊天服务器失败！");
                    }
                });
                ShowToast("登录");
                break;
            case R.id.other_wangji:
                ShowToast("忘记密码");
                startActivity(new Intent(this, WangjiPwdActivity.class));

                break;
            case R.id.other_youke:
                ShowToast("游客登录");
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("isYouke",true);
                startActivity(intent);
                finish();
                break;
        }
    }



    @Override
    public void LoginSuccess(LoginOtherBean loginOtherBean) {
        ShowToast(loginOtherBean.msg);
        SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
        SharedPreferences.Editor edit = token.edit();
        edit.putString("token",loginOtherBean.data.token);
        edit.putString("uid", String.valueOf(loginOtherBean.data.uid));
        edit.putString("mobile",otherName.getText().toString());
        edit.putString("pwd",otherPwd.getText().toString());
        edit.commit();
        startActivity(new Intent(this, HomeActivity.class));
        System.out.println(loginOtherBean.data.token);
        finish();
    }

    @Override
    public void LoginFailure(String msg) {
        ShowToast(msg);
    }

    @Override
    public void LoginError(String msg) {
        ShowToast(msg);
    }
}
