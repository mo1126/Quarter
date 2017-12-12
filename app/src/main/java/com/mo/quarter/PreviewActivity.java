package com.mo.quarter;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.mo.quarter.presenter.BasePresenter;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PreviewActivity extends BaseActivity implements View.OnClickListener {

    private PlayerView player;
    private String videourl;
    private View rootView;
    private TextView back;
    private TextView cun;
    private Button next;

    @Override
    public int getLayoutid() {
        return R.layout.activity_preview;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void Creat() {
        initView();
        player = new PlayerView(this, rootView)
                .setScaleType(PlayStateParams.wrapcontent)
                .hideMenu(true)
                .forbidTouch(false)
                .setNetWorkTypeTie(false)
                .hideAllUI()
                .autoPlay(videourl)
                .startPlay();
        //隐藏返回键，true 隐藏，false 为显示
        player.hideBack(true);
//隐藏菜单键，true 隐藏，false 为显示
        player.hideMenu(true);
//隐藏分辨率按钮，true 隐藏，false 为显示
        player.hideSteam(true);
//隐藏旋转按钮，true 隐藏，false 为显示
        player.hideRotation(true);
//隐藏全屏按钮，true 隐藏，false 为显示
        player.hideFullscreen(true);
//隐藏中间播放按钮,ture 为隐藏，false 为不做隐藏处理，但不是显示
        player.hideCenterPlayer(false);
        //隐藏全屏按钮，true 隐藏，false 为显示
        player.hideFullscreen(true);
        //设置是否禁止双击
        player.setForbidDoulbeUp(true);
        //隐藏全屏按钮，true 隐藏，false 为显示
        player.hideFullscreen(true);
        //是否隐藏上下 bar，true 为隐藏，false 为不隐藏，但不一定是显示
        player.hideControlPanl(true);


    }

    private void initView() {
        ButterKnife.bind(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent intent = getIntent();
        videourl = intent.getStringExtra("video");

        rootView = getLayoutInflater().from(this).inflate(R.layout.activity_preview, null);
        setContentView(rootView);
        back = rootView.findViewById(R.id.preview_back);
        cun = rootView.findViewById(R.id.preview_cun);
        next = rootView.findViewById(R.id.preview_next);
        back.setOnClickListener(this);
        cun.setOnClickListener(this);
        next.setOnClickListener(this);
    }


    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.preview_back:
                File file=new File(videourl);
                file.delete();
                finish();
                break;
            case R.id.preview_cun:
                ShowToast("已存储到本地");
                break;
            case R.id.preview_next:
                Intent intent = new Intent(this, ShareVideoActivity.class);
                intent.putExtra("video", videourl);
                startActivity(intent);
                finish();
                break;
        }
    }
}
