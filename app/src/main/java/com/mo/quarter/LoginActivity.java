package com.mo.quarter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
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
        SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
        String uid = token.getString("uid", null);
        String mobile = token.getString("mobile", null);
        String pwd = token.getString("pwd", null);
        if(uid!=null&&mobile!=null&&pwd!=null){
            EMClient.getInstance().login(mobile,pwd,new EMCallBack() {//回调
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
            startActivity(new Intent(this,HomeActivity.class));
            finish();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

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
                break;
        }
    }
}

