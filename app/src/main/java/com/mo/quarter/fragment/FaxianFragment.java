package com.mo.quarter.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mo.quarter.LiaotianActivity;
import com.mo.quarter.R;
import com.mo.quarter.adapter.MyAttentionAdapter;
import com.mo.quarter.bean.MyFollowUserBean;
import com.mo.quarter.presenter.MyAttentionPresenter;
import com.mo.quarter.view.MyAttentionView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 莫迎华 on 2017/11/27.15:30.
 */

public class FaxianFragment extends Fragment implements MyAttentionView {

    @BindView(R.id.faxian_lv)
    ListView faxianLv;
    Unbinder unbinder;
    private MyAttentionPresenter presenter;
    private MyAttentionAdapter myadapter;
    private View inflate;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = View.inflate(getActivity(), R.layout.faxian_fragment, null);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new MyAttentionPresenter(this, getActivity());

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getFollowUsers();
    }

    @Override
    public void getFollowUserSuccess(MyFollowUserBean myFollowUserBean) {
        final List<MyFollowUserBean.DataBean> data = myFollowUserBean.data;
        if (myadapter == null) {
            myadapter = new MyAttentionAdapter(getActivity(), data);
            faxianLv.setAdapter(myadapter);
        } else {
            myadapter.notifyDataSetChanged();
        }
        faxianLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), LiaotianActivity.class);
                intent.putExtra("mobile",data.get(position).mobile);
                intent.putExtra("name",data.get(position).nickname);
                intent.putExtra("head",data.get(position).icon);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getFollowUserFailure(String msg) {
        System.out.println(msg);
    }

    @Override
    public void getError(String msg) {
        System.out.println(msg);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
