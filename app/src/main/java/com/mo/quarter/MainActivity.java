package com.mo.quarter;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import com.mo.quarter.presenter.BasePresenter;


public class MainActivity extends BaseActivity {


    @Override
    public int getLayoutid() {
        return R.layout.activity_main;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void Creat() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int time=msg.what;
                if(time==3){
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    finish();
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 3; i++) {
                    try {
                        Thread.sleep(1000);
                        Message message = handler.obtainMessage();
                        message.what=i;
                        handler.sendMessage(message);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }


}
