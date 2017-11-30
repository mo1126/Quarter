package com.mo.quarter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.meg7.widget.CustomShapeImageView;
import com.mo.quarter.bean.UserInfoBean;
import com.mo.quarter.fragment.DuanziFragment;
import com.mo.quarter.fragment.LeftFragment;
import com.mo.quarter.fragment.ShiPinFragment;
import com.mo.quarter.fragment.TuijianFragment;
import com.mo.quarter.presenter.BasePresenter;
import com.mo.quarter.presenter.HomePresenter;
import com.mo.quarter.view.HomeView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity<HomePresenter> implements HomeView {

    @BindView(R.id.home_head)
    CustomShapeImageView homeHead;
    @BindView(R.id.home_title)
    TextView homeTitle;
    @BindView(R.id.home_fl)
    FrameLayout homefl;
    @BindView(R.id.home_left)
    FrameLayout homeLeft;
    @BindView(R.id.home_draw)
    DrawerLayout homeDraw;
    @BindView(R.id.home_tuijian_img)
    ImageView homeTuijianImg;
    @BindView(R.id.home_tuijian_text)
    TextView homeTuijianText;
    @BindView(R.id.home_duanzi_img)
    ImageView homeDuanziImg;
    @BindView(R.id.home_duanzi_text)
    TextView homeDuanziText;
    @BindView(R.id.home_shipin_img)
    ImageView homeShipinImg;
    @BindView(R.id.home_shipin_text)
    TextView homeShipinText;
    private TuijianFragment tuijianFragment;
    private DuanziFragment duanziFragment;
    private ShiPinFragment shiPinFragment;
    private UserInfoBean userInfo;
    private boolean isYouke;

    @Override
    public int getLayoutid() {
        return R.layout.activity_home;
    }

    @Override
    public HomePresenter initPresenter() {
        return new HomePresenter(this,this);
    }

    @Override
    public void Creat() {
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
        isYouke = intent.getBooleanExtra("isYouke", false);
        if(!isYouke){
            presenter.getUserInfo();
        }

    }

    private void initView() {
        tuijianFragment = new TuijianFragment();
        duanziFragment = new DuanziFragment();
        shiPinFragment = new ShiPinFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.home_fl, tuijianFragment).show(tuijianFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.home_fl, duanziFragment).hide(duanziFragment) .commit();
        getSupportFragmentManager().beginTransaction().add(R.id.home_fl, shiPinFragment).hide(shiPinFragment).commit();

        getSupportFragmentManager().beginTransaction().replace(R.id.home_left, new LeftFragment()).commit();

    }

    @SuppressLint("ResourceAsColor")
    @OnClick({R.id.home_head,R.id.home_chuangzuo, R.id.home_tuijian, R.id.home_duanzi, R.id.home_shipin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_head:
                homeDraw.openDrawer(Gravity.LEFT);
                break;
            case R.id.home_chuangzuo:
                startActivity(new Intent(this,CreateActivity.class));
                ShowToast("创作");
                break;
            case R.id.home_tuijian:
                ShowToast("推荐");
                getSupportFragmentManager().beginTransaction().show(tuijianFragment).commit();
                getSupportFragmentManager().beginTransaction().hide(duanziFragment) .commit();
                getSupportFragmentManager().beginTransaction().hide(shiPinFragment).commit();
                homeTitle.setText("推荐");
                homeTuijianImg.setImageResource(R.mipmap.raw_1500085367);
                homeTuijianText.setTextColor(getResources().getColor(R.color.colorBlue));
                homeDuanziImg.setImageResource(R.mipmap.raw_1500085327);
                homeDuanziText.setTextColor(getResources().getColor(R.color.colorHui));
                homeShipinImg.setImageResource(R.mipmap.raw_1500083686);
                homeShipinText.setTextColor(getResources().getColor(R.color.colorHui));
                break;
            case R.id.home_duanzi:
                ShowToast("段子");
                getSupportFragmentManager().beginTransaction().hide(tuijianFragment).commit();
                getSupportFragmentManager().beginTransaction().show(duanziFragment) .commit();
                getSupportFragmentManager().beginTransaction().hide(shiPinFragment).commit();
                homeTitle.setText("段子");
                homeTuijianImg.setImageResource(R.mipmap.raw_1500083878);
                homeTuijianText.setTextColor(getResources().getColor(R.color.colorHui));
                homeDuanziImg.setImageResource(R.mipmap.raw_1500085899);
                homeDuanziText.setTextColor(getResources().getColor(R.color.colorBlue));
                homeShipinImg.setImageResource(R.mipmap.raw_1500083686);
                homeShipinText.setTextColor(getResources().getColor(R.color.colorHui));
                break;
            case R.id.home_shipin:
                ShowToast("视频");
                getSupportFragmentManager().beginTransaction().hide(tuijianFragment).commit();
                getSupportFragmentManager().beginTransaction().hide(duanziFragment) .commit();
                getSupportFragmentManager().beginTransaction().show(shiPinFragment).commit();
                homeTitle.setText("段子");
                homeTuijianImg.setImageResource(R.mipmap.raw_1500083878);
                homeTuijianText.setTextColor(getResources().getColor(R.color.colorHui));
                homeDuanziImg.setImageResource(R.mipmap.raw_1500085327);
                homeDuanziText.setTextColor(getResources().getColor(R.color.colorHui));
                homeShipinImg.setImageResource(R.mipmap.raw_1500086067);
                homeShipinText.setTextColor(getResources().getColor(R.color.colorBlue));
                break;
        }
    }

    @Override
    public void getinfoSuccess(UserInfoBean userInfoBean) {
        ShowToast("获取用户信息成功");
        Glide.with(this).load(userInfoBean.data.icon).into(homeHead);
        userInfo=userInfoBean;
        EventBus.getDefault().post(userInfo);
    }

    @Override
    public void getinfoFailure(String msg) {
        ShowToast("msg");
    }

    @Override
    public void onError(String e) {
        ShowToast(e);
    }
}
