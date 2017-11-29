package com.mo.quarter.adapter;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 莫迎华 on 2017/11/27.9:39.
 */

public class HomeVpAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    String[] strings={"推荐","段子","视频"};
    public HomeVpAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strings[position];
    }
}
