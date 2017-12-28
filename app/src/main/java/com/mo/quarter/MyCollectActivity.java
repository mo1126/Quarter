package com.mo.quarter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mo.quarter.adapter.MyCollectAdapter;
import com.mo.quarter.bean.MycollectBean;
import com.mo.quarter.presenter.MycollectPresenter;
import com.mo.quarter.view.MycollectView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCollectActivity extends BaseActivity<MycollectPresenter> implements MycollectView {

    @BindView(R.id.mycollect_rv)
    XRecyclerView mycollectRv;
    private MyCollectAdapter myadapter;

    @Override
    public int getLayoutid() {
        return R.layout.activity_my_collect;
    }

    @Override
    public MycollectPresenter initPresenter() {
        return new MycollectPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getMycollects();
    }

    @Override
    public void Creat() {
        ButterKnife.bind(this);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        mycollectRv.setLayoutManager(lm);
        mycollectRv.setLoadingMoreEnabled(false);
        mycollectRv.setPullRefreshEnabled(false);

    }

    @OnClick({R.id.mycollect_back, R.id.mycollect_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mycollect_back:
                finish();
                break;
            case R.id.mycollect_delete:
                break;
        }
    }

    @Override
    public void getCollectSuccess(MycollectBean mycollectBean) {
        List<MycollectBean.DataBean> data = mycollectBean.data;
        myadapter = new MyCollectAdapter(this, data);
        mycollectRv.setAdapter(myadapter);
        myadapter.setIclick(new MyCollectAdapter.Iclick() {
            @Override
            public void dianzan(String wid) {
                presenter.dianzan(wid);
            }

            @Override
            public void shoucang(String wid) {
                presenter.shoucang(wid);
            }

            @Override
            public void pinglun(final String wid) {
                final EditText et = new EditText(MyCollectActivity.this);
                AlertDialog.Builder ab = new AlertDialog.Builder(MyCollectActivity.this)
                        .setTitle("请输入评论内容")
                        .setView(et)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                presenter.pinglun(wid, et.getText().toString().trim());
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                ab.create().show();
            }
        });
    }

    @Override
    public void getCollectFailure(String msg) {
        ShowToast(msg);
    }

    @Override
    public void dianzanSuccess(String msg) {

    }

    @Override
    public void dianzanFailure(String msg) {

    }

    @Override
    public void shoucangSuccess(String msg) {

    }

    @Override
    public void shoucangFailure(String msg) {

    }

    @Override
    public void pinglunSuccess(String msg) {

    }

    @Override
    public void pinglungFailure(String msg) {

    }

    @Override
    public void getError(String msg) {
        ShowToast(msg);
    }
}
