package com.mo.quarter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.meg7.widget.CustomShapeImageView;
import com.mo.quarter.adapter.UserVideoAdapter;
import com.mo.quarter.bean.UserInfoBean;
import com.mo.quarter.bean.UserVideosBean;
import com.mo.quarter.presenter.UserVideosPresenter;
import com.mo.quarter.view.UserVideosView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserVideosActivity extends BaseActivity<UserVideosPresenter> implements UserVideosView {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv)
    CustomShapeImageView head;
    @BindView(R.id.tv_fensi)
    TextView tvFensi;
    @BindView(R.id.tv_guanzhu)
    TextView tvGuanzhu;
    @BindView(R.id.uservideo_rv)
    XRecyclerView uservideoRv;
    @BindView(R.id.uservideo_dianzansum)
    TextView uservideoDianzansum;
    @BindView(R.id.bt_follow)
    Button btFollow;
    @BindView(R.id.bt_follow1)
    Button btFollow1;


    private int page = 0;
    private String uid;
    private UserVideoAdapter myadapter;
    private int fans;
    private int wid;

    @Override
    public int getLayoutid() {
        return R.layout.activity_user_videos;
    }

    @Override
    public UserVideosPresenter initPresenter() {
        return new UserVideosPresenter(this, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getUserInfo(uid);
        presenter.getUserVideos(uid, String.valueOf(page));
    }

    @Override
    public void Creat() {
        initView();
        initData();
        uservideoRv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 0;
                presenter.getUserVideos(uid, String.valueOf(page));
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.getUserVideos(uid, String.valueOf(page));
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
    }

    private void initView() {
        ButterKnife.bind(this);
        uservideoRv.setPullRefreshEnabled(true);
        uservideoRv.setLoadingMoreEnabled(true);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        uservideoRv.setLayoutManager(lm);
    }
    @OnClick({R.id.uservideo_back, R.id.uservideo_dianzan, R.id.bt_follow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.uservideo_back:
                finish();
                break;
            case R.id.uservideo_dianzan:
                System.out.println(uid +"     "+wid);
                presenter.dianzan(uid, String.valueOf(wid));
                break;
            case R.id.bt_follow:
                presenter.guanzhu(uid);
                break;
        }
    }

    @Override
    public void getUserVideosSuccess(UserVideosBean userVideosBean) {
        UserVideosBean.DataBean data = userVideosBean.data;
        wid = data.worksEntities.get(0).wid;
        if(data.user.follow){
            btFollow.setVisibility(View.GONE);
            btFollow1.setVisibility(View.VISIBLE);
        }else{
             btFollow.setVisibility(View.VISIBLE);
             btFollow1.setVisibility(View.GONE);
         }
        if (myadapter == null) {
            myadapter = new UserVideoAdapter(this, data);
            uservideoRv.setAdapter(myadapter);
        } else {
            if (page == 0) {
                myadapter.refresh(data);
            } else {
                myadapter.loadmore(data);
            }
        }
        System.out.println("getUserVideosSuccess---------->" + userVideosBean.msg);
    }

    @Override
    public void getUserVideosFailure(String msg) {
        System.out.println("getUserVideosFailure ---->" + msg);
    }

    @Override
    public void getUserInfoSuccess(UserInfoBean userInfoBean) {
        Glide.with(this).load(userInfoBean.data.icon).into(head);
        tvName.setText(userInfoBean.data.nickname);
        fans = userInfoBean.data.fans;
        tvFensi.setText(fans+ "   粉丝 |");
        tvGuanzhu.setText(userInfoBean.data.follow + "  关注");
        System.out.println("getUserInfoSuccess ---->" + userInfoBean.msg);
    }

    @Override
    public void getUserInfoFailure(String msg) {
        System.out.println("getUserInfoFailure ---->" + msg);
    }
    @Override
    public void guanzhuSuccess(String msg) {
        btFollow.setVisibility(View.GONE);
        btFollow1.setVisibility(View.VISIBLE);
        tvFensi.setText(fans+1+ "   粉丝 |");
        ShowToast(msg);
    }

    @Override
    public void guanzhuFailure(String msg) {
        System.out.println("guanzhuFailure------>" + msg);
    }

    @Override
    public void dianzanSuccess(String msg) {
        uservideoDianzansum.setText(1+"");
    }

    @Override
    public void dianzanFailure(String msg) {
        System.out.println("dianzanFailure"+msg);
    }

    @Override
    public void getError(String msg) {
        System.out.println("getError ---->" + msg);
    }



}
