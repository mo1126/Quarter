package com.mo.quarter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mo.quarter.R;
import com.mo.quarter.adapter.ShipinAdapter;
import com.mo.quarter.utils.TablayoutUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 莫迎华 on 2017/11/27.15:30.
 */

public class ShiPinFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.shipin_tab)
    TabLayout shipinTab;
    @BindView(R.id.shipin_rv)
    XRecyclerView shipinRv;
    private View inflate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = View.inflate(getContext(), R.layout.shipin_fragment, null);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        shipinTab.addTab(shipinTab.newTab().setText("热门").setTag(1));
        shipinTab.addTab(shipinTab.newTab().setText("附近").setTag(2));
        TablayoutUtils.setIndicator(shipinTab, 40, 40);

        StaggeredGridLayoutManager sm=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        shipinRv.setLayoutManager(sm);

        ShipinAdapter myadapter=new ShipinAdapter(getContext(),null);
        shipinRv.setAdapter(myadapter);

        shipinTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                StaggeredGridLayoutManager sm=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
                shipinRv.setLayoutManager(sm);
                int tag = (int) tab.getTag();
                if (tag == 1) {
                    // TODO: 2017/12/1 获取热门视频

                } else {
                    // TODO: 2017/12/1  获取附近视频
                }
                ShipinAdapter myadapter=new ShipinAdapter(getContext(),null);
                shipinRv.setAdapter(myadapter);
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
