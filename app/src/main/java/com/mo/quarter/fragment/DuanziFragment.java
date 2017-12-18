package com.mo.quarter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mo.quarter.R;
import com.mo.quarter.adapter.DuanziAdapter;
import com.mo.quarter.bean.JokesBean;
import com.mo.quarter.presenter.DuanziPresenter;
import com.mo.quarter.view.DuanziView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 莫迎华 on 2017/11/27.15:30.
 */

public class DuanziFragment extends Fragment implements DuanziView {

    @BindView(R.id.duanzi_rv)
    XRecyclerView duanziRv;
    Unbinder unbinder;
    private DuanziPresenter duanziPresenter;
    private DuanziAdapter myadapter;
    private int page=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getContext(), R.layout.duanzi_fragment, null);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager lm=new LinearLayoutManager(getContext());
        duanziRv.setLayoutManager(lm);
        duanziRv.setLoadingMoreEnabled(true);
        duanziRv.setPullRefreshEnabled(true);
        duanziRv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=0;
                duanziPresenter.getJokes(String.valueOf(page));
            }
            @Override
            public void onLoadMore() {
                page++;
                System.out.println("page"+page);
                duanziPresenter.getJokes(String.valueOf(page));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        duanziPresenter = new DuanziPresenter(this, getContext());
        duanziPresenter.getJokes(String.valueOf(page));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getJokesSuccess(JokesBean jokesBean) {
        Toast.makeText(getContext(), jokesBean.msg, Toast.LENGTH_SHORT).show();
        if(myadapter==null){
            myadapter = new DuanziAdapter(jokesBean.data,getContext());
            duanziRv.setAdapter(myadapter);
        }else{
            if(page==0){
                myadapter.refresh(jokesBean.data);
                duanziRv.refreshComplete();
            }else{
                myadapter.loadmore(jokesBean.data);
                duanziRv.loadMoreComplete();
            }

        }
    }

    @Override
    public void getJokesFailure(String msg) {
        System.out.println(msg);
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getError(String msg) {
        System.out.println(msg);
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
