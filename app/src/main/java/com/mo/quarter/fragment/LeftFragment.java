package com.mo.quarter.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.meg7.widget.CustomShapeImageView;
import com.mo.quarter.LoginActivity;
import com.mo.quarter.MyAttentionActivity;
import com.mo.quarter.MyCenterActivity;
import com.mo.quarter.MyCollectActivity;
import com.mo.quarter.R;
import com.mo.quarter.ShezhiActivity;
import com.mo.quarter.bean.UserInfoBean;
import com.mo.quarter.utils.MyIntercepter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 莫迎华 on 2017/11/27.16:29.
 */

public class LeftFragment extends Fragment {
    @BindView(R.id.left_head)
    CustomShapeImageView leftHead;
    @BindView(R.id.left_top)
    LinearLayout leftTop;
    @BindView(R.id.iv_day_night)
    ImageView ivDayNight;
    @BindView(R.id.tv_day_night)
    TextView tvDayNight;
    @BindView(R.id.left_name)
    TextView leftname;
    @BindView(R.id.iv_left_select)
    ImageView ivLeftSelect;
    @BindView(R.id.iv_left_ye)
    ImageView ivLeftYe;
    Unbinder unbinder;
    private View inflate;
    private Toast toast;
    private UserInfoBean info;
    private String head;
    private String nickname;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = View.inflate(getContext(), R.layout.left_fragment, null);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getinfo(UserInfoBean u){
        head = u.data.icon;
        nickname = u.data.nickname;
        info=u;
        Glide.with(getContext()).load(u.data.icon).into(leftHead);
        leftname.setText(u.data.nickname);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);//订阅
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);//解除订阅
    }

    @OnClick({R.id.left_top, R.id.rl_left1, R.id.rl_left2, R.id.rl_left3, R.id.rl_left4, R.id.iv_left_select, R.id.iv_left_ye, R.id.left_zuopin, R.id.left_shezhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_top:
                if(MyIntercepter.uid==null){
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }else{
                    Showtoast("进入个人页面");
                    Intent intent = new Intent(getContext(), MyCenterActivity.class);
                    intent.putExtra("head",head);
                    intent.putExtra("name",nickname);
                    startActivity(intent);
                }
                break;
            case R.id.rl_left1:
                 startActivity(new Intent(getActivity(), MyAttentionActivity.class));
                break;
            case R.id.rl_left2:
                startActivity(new Intent(getActivity(), MyCollectActivity.class));
                break;
            case R.id.rl_left3:
                Showtoast("搜索好友Activity");
                break;
            case R.id.rl_left4:
                Showtoast("消息通知Activity");
                break;
            case R.id.iv_left_select:
                Showtoast("夜间模式");
                tvDayNight.setText("夜间模式");
                ivDayNight.setImageResource(R.mipmap.yueliang2);
                ivLeftYe.setVisibility(View.VISIBLE);
                ivLeftSelect.setVisibility(View.GONE);
                break;
            case R.id.iv_left_ye:
                Showtoast("日间模式");
                tvDayNight.setText("日间模式");
                ivDayNight.setImageResource(R.mipmap.yueliang1);
                ivLeftYe.setVisibility(View.GONE);
                ivLeftSelect.setVisibility(View.VISIBLE);
                break;
            case R.id.left_zuopin:
                Showtoast("作品Activity");
                break;
            case R.id.left_shezhi:
                Showtoast("设置Activity");
                startActivity(new Intent(getContext(), ShezhiActivity.class));
                break;
        }
    }

    public void Showtoast(String msg){
        if(toast==null){
            toast=Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT);
        }else{
            toast.setText(msg);
        }
        toast.show();
    }
}
