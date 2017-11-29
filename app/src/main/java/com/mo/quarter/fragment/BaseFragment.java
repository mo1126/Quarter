package com.mo.quarter.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mo.quarter.myapp.MyApp;
import com.mo.quarter.presenter.BasePresenter;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by 莫迎华 on 2017/11/27.19:12.
 */

public abstract class BaseFragment<P extends BasePresenter>  extends Fragment {

    private View inflate;

    public P presenter;
    public Toast toast;
    private Context getcontext;

    public  abstract int getLayoutid();
    public abstract P initPresenter();

    public abstract Context getcontext();
    public abstract void onActivityCreated();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = View.inflate(getcontext(), getLayoutid(), null);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onActivityCreated();
        presenter=initPresenter();
        getcontext = getcontext();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }
    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(getcontext);
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getcontext);
    }

    public void ShowToast(String s){
        if(toast==null){
            toast=Toast.makeText(getcontext,s,Toast.LENGTH_SHORT);
        }else{
            toast.setText(s);
        }
        toast.show();
    }
}
