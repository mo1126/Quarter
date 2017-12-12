package com.mo.quarter.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mo.quarter.LoginActivity;
import com.mo.quarter.R;
import com.mo.quarter.adapter.ShipinAdapter;
import com.mo.quarter.bean.HotShipin;
import com.mo.quarter.presenter.HotShipinPresenter;
import com.mo.quarter.utils.TablayoutUtils;
import com.mo.quarter.view.HotShipinView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 莫迎华 on 2017/11/27.15:30.
 */

public class ShiPinFragment extends Fragment implements HotShipinView {


    Unbinder unbinder;
    @BindView(R.id.shipin_tab)
    TabLayout shipinTab;
    @BindView(R.id.shipin_rv)
    XRecyclerView shipinRv;
    private View inflate;
    private HotShipinPresenter hotShipinPresenter;
    private int page;
    private ShipinAdapter myadapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = View.inflate(getContext(), R.layout.shipin_fragment, null);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onResume() {
        super.onResume();
        hotShipinPresenter = new HotShipinPresenter(this,getActivity());
        hotShipinPresenter.gethotvideo(String.valueOf(page));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        shipinTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tag = (int) tab.getTag();
                if (tag == 1) {
                    // TODO: 2017/12/1 获取热门视频
                    hotShipinPresenter.gethotvideo(String.valueOf(page));
                } else {
                    // TODO: 2017/12/1  获取附近视频

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
        shipinTab.addTab(shipinTab.newTab().setText("热门").setTag(1));
        shipinTab.addTab(shipinTab.newTab().setText("附近").setTag(2));
        TablayoutUtils.setIndicator(shipinTab, 40, 40);
        StaggeredGridLayoutManager sm=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        shipinRv.setLayoutManager(sm);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getVideosSuccess(HotShipin getVideosBean) {
        StaggeredGridLayoutManager sm=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        shipinRv.setLayoutManager(sm);
        if(myadapter==null){
            myadapter = new ShipinAdapter(getContext(),getVideosBean.data);
            shipinRv.setAdapter(myadapter);
        }else{
            if(page==1){
                myadapter.refresh(getVideosBean.data);
            }else{
                myadapter.loadmore(getVideosBean.data);
            }
        }
    }

    @Override
    public void getVideosFailure(String msg, String code) {
        if(code.equals("1")){
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        }else{
            getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    }

    @Override
    public void Error(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
