package com.mo.quarter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.igexin.sdk.PushManager;
import com.mo.quarter.presenter.BasePresenter;
import com.mo.quarter.utils.DemoIntentService;
import com.mo.quarter.utils.DemoPushService;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by 莫迎华 on 2017/11/15.8:25.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    public P presenter;
    public  Toast toast;

    public  abstract int getLayoutid();
    public abstract P initPresenter();
    public abstract void Creat();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutid());
        //个推注册
        PushManager.getInstance().initialize(this.getApplicationContext(), DemoPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);
        presenter= initPresenter();
        Creat();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.detach();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public void ShowToast(String s){
        if(toast==null){
            toast=Toast.makeText(this,s,Toast.LENGTH_SHORT);
        }else{
            toast.setText(s);
        }
        toast.show();
    }


}
