package com.mo.quarter.adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.meg7.widget.CustomShapeImageView;
import com.mo.quarter.R;
import com.mo.quarter.UserVideosActivity;
import com.mo.quarter.bean.GetVideosBean;
import com.mo.quarter.bean.UserVideosBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 莫迎华 on 2017/12/3.20:42.
 */

public class UserVideoAdapter extends XRecyclerView.Adapter<UserVideoAdapter.TjViewHolder> {
    private Activity context;
    private  UserVideosBean.DataBean dataBean;
    private View rootView;

    public UserVideoAdapter(Activity context, UserVideosBean.DataBean  dataBean) {
        this.context = context;
        this.dataBean = dataBean;
    }

    public void refresh(UserVideosBean.DataBean data){
        dataBean.worksEntities.clear();
        dataBean.worksEntities.addAll(data.worksEntities);
        notifyDataSetChanged();
    }
    public void loadmore(UserVideosBean.DataBean data){
        dataBean.worksEntities.addAll(data.worksEntities);
        notifyDataSetChanged();
    }

    @Override
    public TjViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.tj_rv_item, null);
        TjViewHolder holder = new TjViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(final TjViewHolder holder, final int position) {
        holder.setIsRecyclable(false);
        holder.content.setText(dataBean.worksEntities.get(position).workDesc);
        holder.name.setText(dataBean.user.nickname);
        holder.time.setText(dataBean.worksEntities.get(position).createTime);
        Glide.with(context).load(dataBean.user .icon).into(holder.head);
        if(dataBean.worksEntities.get(position).isopen){
            open(holder);
        }else{
            shutdown(holder);
        }
        setPlayer(holder,position);
        holder.shipinOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(holder);
                dataBean.worksEntities.get(position).isopen=true;
            }
        });
        holder.shipinShutdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.shipinShutdown.setVisibility(View.GONE);
                holder.shipinOpen.setVisibility(View.VISIBLE);
                shutdown(holder);
                dataBean.worksEntities.get(position).isopen=false;
            }
        });
    }

    private void setPlayer(TjViewHolder holder, final int position) {
        String videoUrl = dataBean.worksEntities.get(position).videoUrl;
        String replace = videoUrl.replace("https://www.zhaoapi.cn", "http://120.27.23.105");
        /**播放器*/
        View rootView = View.inflate(context, R.layout.simple_player_view_player, holder.player);
        final PlayerView player = new PlayerView(context, rootView)
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .forbidTouch(true)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        /**加载前显示的缩略图*/
                        Glide.with(context)
                                .load(dataBean.worksEntities.get(position).cover)
                                .into(ivThumbnail);
                    }
                })
                .setPlaySource(replace);
        player.hideBack(true);
        player.hideMenu(true);
        player.hideSteam(true);
        player.hideRotation(true);
        player.hideFullscreen(true);
        player.hideCenterPlayer(false);
        player.hideFullscreen(true);
        player.setForbidDoulbeUp(true);
        player.hideFullscreen(true);
    }

    private void open(TjViewHolder holder) {
        holder.shipinShutdown.setVisibility(View.VISIBLE);
        holder.shipinOpen.setVisibility(View.GONE);
        holder.shipinGuanzhu.setVisibility(View.VISIBLE);
        holder.shipinFenxiang.setVisibility(View.VISIBLE);
        holder.shipinPinglun.setVisibility(View.VISIBLE);
        ObjectAnimator animator1=ObjectAnimator.ofFloat(holder.shipinShutdown,"rotation",0,-180);
        ObjectAnimator animator2=ObjectAnimator.ofFloat(holder.shipinGuanzhuIv,"rotation",90,0);
        ObjectAnimator animator3=ObjectAnimator.ofFloat(holder.shipinGuanzhu,"translationX",0,-90);
        ObjectAnimator animator4=ObjectAnimator.ofFloat(holder.shipinGuanzhuIv,"alpha",0,1);
        ObjectAnimator animator41=ObjectAnimator.ofFloat(holder.shipinGuanzhuTv,"alpha",0,1);

        ObjectAnimator animator5=ObjectAnimator.ofFloat(holder.shipinFenxiangIv,"rotation",90,0);
        ObjectAnimator animator6=ObjectAnimator.ofFloat(holder.shipinFenxiang,"translationX",0,-180);
        ObjectAnimator animator7=ObjectAnimator.ofFloat(holder.shipinFenxiangIv,"alpha",0,1);
        ObjectAnimator animator71=ObjectAnimator.ofFloat(holder.shipinFenxiangTv,"alpha",0,1);

        ObjectAnimator animator8=ObjectAnimator.ofFloat(holder.shipinPinglunIv,"rotation",90,0);
        ObjectAnimator animator9=ObjectAnimator.ofFloat(holder.shipinPinglun,"translationX",0,-270);
        ObjectAnimator animator10=ObjectAnimator.ofFloat(holder.shipinPinglunIv,"alpha",0,1);
        ObjectAnimator animator101=ObjectAnimator.ofFloat(holder.shipinPinglunTv,"alpha",0,1);

        AnimatorSet animationSet=new AnimatorSet();
        animationSet.playTogether(animator10); animationSet.playTogether(animator4);animationSet.playTogether(animator7);
        animationSet.playTogether(animator101); animationSet.playTogether(animator41);animationSet.playTogether(animator71);
        animationSet.playTogether(animator1);
        animationSet.playTogether(animator2);
        animationSet.playTogether(animator5);
        animationSet.playTogether(animator8);
        animationSet.playTogether(animator3);
        animationSet.playTogether(animator6);
        animationSet.playTogether(animator9);
        animationSet.setDuration(500);
        animationSet.start();
    }

    private void shutdown(final TjViewHolder holder) {
        ObjectAnimator animator1=ObjectAnimator.ofFloat(holder.shipinOpen,"rotation",0,180);
        ObjectAnimator animator2=ObjectAnimator.ofFloat(holder.shipinGuanzhuIv,"rotation",0,90);
        ObjectAnimator animator3=ObjectAnimator.ofFloat(holder.shipinGuanzhu,"translationX",-90,0);
        ObjectAnimator animator4=ObjectAnimator.ofFloat(holder.shipinGuanzhuIv,"alpha",1,0);
        ObjectAnimator animator41=ObjectAnimator.ofFloat(holder.shipinGuanzhuTv,"alpha",1,0);

        ObjectAnimator animator5=ObjectAnimator.ofFloat(holder.shipinFenxiangIv,"rotation",0,90);
        ObjectAnimator animator6=ObjectAnimator.ofFloat(holder.shipinFenxiang,"translationX",-180,0);
        ObjectAnimator animator7=ObjectAnimator.ofFloat(holder.shipinFenxiangIv,"alpha",1,0);
        ObjectAnimator animator71=ObjectAnimator.ofFloat(holder.shipinFenxiangTv,"alpha",1,0);

        ObjectAnimator animator8=ObjectAnimator.ofFloat(holder.shipinPinglunIv,"rotation",0,90);
        ObjectAnimator animator9=ObjectAnimator.ofFloat(holder.shipinPinglun,"translationX",-260,0);
        ObjectAnimator animator10=ObjectAnimator.ofFloat(holder.shipinPinglunIv,"alpha",1,0);
        ObjectAnimator animator101=ObjectAnimator.ofFloat(holder.shipinPinglunTv,"alpha",1,0);

        AnimatorSet animationSet=new AnimatorSet();
        animationSet.playTogether(animator10);  animationSet.playTogether(animator7);  animationSet.playTogether(animator4);
        animationSet.playTogether(animator101);  animationSet.playTogether(animator71);  animationSet.playTogether(animator41);
        animationSet.playTogether(animator1);
        animationSet.playTogether(animator2);
        animationSet.playTogether(animator5);
        animationSet.playTogether(animator8);
        animationSet.playTogether(animator3);
        animationSet.playTogether(animator6);
        animationSet.playTogether(animator9);
        animationSet.setDuration(500);
        animationSet.start();
        animationSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                holder.shipinGuanzhu.setVisibility(View.GONE);
                holder.shipinFenxiang.setVisibility(View.GONE);
                holder.shipinPinglun.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBean.worksEntities.size();
    }

    public class TjViewHolder extends XRecyclerView.ViewHolder {
        private CustomShapeImageView head;
        private TextView time;
        private TextView name;
        private TextView content;
        private RelativeLayout player;

        @BindView(R.id.shipin_open)
        ImageView shipinOpen;
        @BindView(R.id.shipin_shutdown)
        ImageView shipinShutdown;
        @BindView(R.id.shipin_pinglun_iv)
        CustomShapeImageView shipinPinglunIv;
        @BindView(R.id.shipin_pinglun_tv)
        TextView shipinPinglunTv;
        @BindView(R.id.shipin_pinglun)
        LinearLayout shipinPinglun;
        @BindView(R.id.shipin_fenxiang_iv)
        CustomShapeImageView shipinFenxiangIv;
        @BindView(R.id.shipin_fenxiang_tv)
        TextView shipinFenxiangTv;
        @BindView(R.id.shipin_fenxiang)
        LinearLayout shipinFenxiang;
        @BindView(R.id.shipin_guanzhu_iv)
        CustomShapeImageView shipinGuanzhuIv;
        @BindView(R.id.shipin_guanzhu_tv)
        TextView shipinGuanzhuTv;
        @BindView(R.id.shipin_guanzhu)
        LinearLayout shipinGuanzhu;

        public TjViewHolder(View itemView) {
            super(itemView);
            head = itemView.findViewById(R.id.tuijian_head);
            time = itemView.findViewById(R.id.tuijian_time);
            name = itemView.findViewById(R.id.tuijian_name);
            content = itemView.findViewById(R.id.tuijian_content);
            player = itemView.findViewById(R.id.player);
            ButterKnife.bind(this,itemView);
        }
    }

}
