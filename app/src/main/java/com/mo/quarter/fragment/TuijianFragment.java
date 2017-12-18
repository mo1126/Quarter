package com.mo.quarter.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mo.quarter.R;
import com.mo.quarter.adapter.TuijianAdapter;
import com.mo.quarter.bean.GetVideosBean;
import com.mo.quarter.bean.TuijianAdBean;
import com.mo.quarter.presenter.TuijianPresenter;
import com.mo.quarter.utils.MyIntercepter;
import com.mo.quarter.utils.TablayoutUtils;
import com.mo.quarter.view.TuijianView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 莫迎华 on 2017/11/27.15:30.
 */

public class TuijianFragment extends Fragment implements TuijianView, TuijianAdapter.Iclick {

    @BindView(R.id.tuijian_tab)
    TabLayout tuijianTab;

    Unbinder unbinder;
    @BindView(R.id.tuijian_rv)
    XRecyclerView tuijianRv;
    private XBanner xbanner;
    private TuijianPresenter presenter;
    private int page1;
    private int page2;
    private View view;
    private final String TYPE1 = "1";
    private final String TYPE2 = "2";
    private String type;
    private TuijianAdapter myadapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.tuijian_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        tuijianTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tag = (int) tab.getTag();
                if (tag == 1) {
                    presenter.getVideos(MyIntercepter.uid, TYPE1, String.valueOf(page1));
                    type = TYPE1;
                } else {
                    presenter.getVideos(MyIntercepter.uid, TYPE2, String.valueOf(page2));
                    type = TYPE2;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void initView() {
        tuijianTab.addTab(tuijianTab.newTab().setText("热门").setTag(1));
        tuijianTab.addTab(tuijianTab.newTab().setText("关注").setTag(2));
        TablayoutUtils.setIndicator(tuijianTab, 40, 40);
        view = View.inflate(getActivity(), R.layout.xbanner, null);
        xbanner = view.findViewById(R.id.xbanner);
        tuijianRv.addHeaderView(view);
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        tuijianRv.setLayoutManager(lm);
        tuijianRv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                if (type.equals(TYPE1)) {
                    page1 = 1;
                    presenter.getVideos(MyIntercepter.uid, TYPE1, String.valueOf(page1));
                } else {
                    page2 = 1;
                    presenter.getVideos(MyIntercepter.uid, TYPE2, String.valueOf(page2));
                }
            }

            @Override
            public void onLoadMore() {
                if (type.equals(TYPE1)) {
                    page1++;
                    presenter.getVideos(MyIntercepter.uid, TYPE1, String.valueOf(page1));
                } else {
                    page2++;
                    presenter.getVideos(MyIntercepter.uid, TYPE2, String.valueOf(page2));
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter = new TuijianPresenter(this, getContext());
        presenter.getVideos(MyIntercepter.uid, "1", String.valueOf(page1));
        type = TYPE1;
        presenter.getAd();
    }

    @Override
    public void onStop() {
        super.onStop();
        xbanner.stopAutoPlay();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getAdSuccess(TuijianAdBean tuijianAdBean) {
        final List<String> imgs = new ArrayList<>();
        List<TuijianAdBean.DataBean> data = tuijianAdBean.data;
        for (TuijianAdBean.DataBean datum : data) {
            imgs.add(datum.icon);
        }
        xbanner.setData(imgs, null);
        xbanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(imgs.get(position)).into((ImageView) view);
            }
        });
    }

    @Override
    public void getAdFailure(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT);
    }

    @Override
    public void getVideosSuccess(GetVideosBean getVideosBean) {
        if (myadapter == null) {
            myadapter = new TuijianAdapter(getActivity(), getVideosBean.data);
            myadapter.setIclick(this);
            tuijianRv.setAdapter(myadapter);
        } else {
            if (type.equals(TYPE1)) {
                isrefresh(getVideosBean.data);
            } else {
                isrefresh(getVideosBean.data);
            }
        }
    }

    private void isrefresh(List<GetVideosBean.DataBean> data) {
        if (page1 == 1) {
            myadapter.refresh(data);
            tuijianRv.refreshComplete();
        } else {
            myadapter.loadmore(data);
            tuijianRv.loadMoreComplete();
        }
    }

    @Override
    public void getVideosFailure(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT);
    }

    @Override
    public void dianzanSuccess(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dianzanFailure(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void shoucangSuccess(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void shoucangFailure(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void pinglunSuccess(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void pinglungFailure(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Error(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT);
    }

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
        final EditText et = new EditText(getActivity());
        AlertDialog.Builder ab = new AlertDialog.Builder(getActivity())
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
}
