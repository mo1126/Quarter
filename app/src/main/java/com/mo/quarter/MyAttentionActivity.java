package com.mo.quarter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mo.quarter.adapter.MyAttentionAdapter;
import com.mo.quarter.bean.MyFollowUserBean;
import com.mo.quarter.presenter.MyAttentionPresenter;
import com.mo.quarter.view.MyAttentionView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAttentionActivity extends BaseActivity<MyAttentionPresenter> implements MyAttentionView {

    @BindView(R.id.myattention_lv)
    ListView myattentionLv;
    private MyAttentionAdapter myadapter;

    @Override
    public int getLayoutid() {
        return R.layout.activity_my_attention;
    }

    @Override
    public MyAttentionPresenter initPresenter() {
        return new MyAttentionPresenter(this, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getFollowUsers();
    }
    @Override
    public void Creat() {
        ButterKnife.bind(this);

    }

    @Override
    public void getFollowUserSuccess(final MyFollowUserBean myFollowUserBean) {
        ShowToast(myFollowUserBean.msg);
        List<MyFollowUserBean.DataBean> data = myFollowUserBean.data;
        if (myadapter == null) {
            myadapter = new MyAttentionAdapter(this, data);
            myattentionLv.setAdapter(myadapter);
        } else {
            myadapter.notifyDataSetChanged();
        }
        myattentionLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyAttentionActivity.this, UserVideosActivity.class);
                intent.putExtra("uid",myFollowUserBean.data.get(position).uid+"");
                startActivity(intent);
            }
        });

    }

    @Override
    public void getFollowUserFailure(String msg) {
        System.out.println("getFollowUserFailure------------------->" + msg);
    }

    @Override
    public void getError(String msg) {
        System.out.println("getError------------------->" + msg);
    }

    @OnClick({R.id.myattention_back, R.id.hot_myattention})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.myattention_back:
                finish();
                break;
            case R.id.hot_myattention:
                break;
        }
    }
}
