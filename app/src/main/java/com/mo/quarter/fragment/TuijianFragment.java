package com.mo.quarter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.mo.quarter.R;
import com.mo.quarter.utils.TablayoutUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 莫迎华 on 2017/11/27.15:30.
 */

public class TuijianFragment extends Fragment {

    @BindView(R.id.tuijian_tab)
    TabLayout tuijianTab;
    @BindView(R.id.tuijian_fl)
    FrameLayout tuijianFl;
    Unbinder unbinder;
    private TjRemenFragment tjRemenFragment;
    private TjGuanzhuFragment tjGuanzhuFragment;

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
        tuijianTab.addTab(tuijianTab.newTab().setText("热门").setTag(1));
        tuijianTab.addTab(tuijianTab.newTab().setText("关注").setTag(2));
        TablayoutUtils.setIndicator(tuijianTab,40,40);
        tjRemenFragment = new TjRemenFragment();
        tjGuanzhuFragment = new TjGuanzhuFragment();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.tuijian_fl, tjRemenFragment).show(tjRemenFragment).commit();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.tuijian_fl, tjGuanzhuFragment).hide(tjGuanzhuFragment).commit();
        tuijianTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tag = (int) tab.getTag();
                if(tag==1){
                    getActivity().getSupportFragmentManager().beginTransaction().show(tjRemenFragment).commit();
                    getActivity().getSupportFragmentManager().beginTransaction().hide(tjGuanzhuFragment).commit();
                }else{
                    getActivity().getSupportFragmentManager().beginTransaction().hide(tjRemenFragment).commit();
                    getActivity().getSupportFragmentManager().beginTransaction() .show(tjGuanzhuFragment).commit();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
