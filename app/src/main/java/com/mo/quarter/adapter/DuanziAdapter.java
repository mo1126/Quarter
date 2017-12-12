package com.mo.quarter.adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.meg7.widget.CustomShapeImageView;
import com.mo.quarter.R;
import com.mo.quarter.bean.JokesBean;

import java.util.List;

/**
 * Created by 莫迎华 on 2017/11/28.18:34.
 */

public class DuanziAdapter extends RecyclerView.Adapter<DuanziAdapter.Holder> {

    private List<JokesBean.DataBean> list;
    private Context context;
    private String[] split;
    private GridLayoutManager gm;

    public DuanziAdapter(List<JokesBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public void refresh(List<JokesBean.DataBean> data){
        list.clear();
        list=data;
        notifyDataSetChanged();
    }
    public void loadmore(List<JokesBean.DataBean> data){
        list.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public DuanziAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.dzitem1, null);
        Holder holder=new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        Glide.with(context).load(list.get(position).user.icon).into(holder.head);
        holder.name.setText(list.get(position).user.nickname);
        holder.time.setText(list.get(position).createTime);
        holder.content.setText(list.get(position).content);
        if(list.get(position).isopen){
            open(holder,position);
        }else{
            holder.shutdown.setVisibility(View.GONE);
            holder.open.setVisibility(View.VISIBLE);
            holder.guanzhu.setVisibility(View.GONE);
            holder.fenxiang.setVisibility(View.GONE);
            holder.pinglun.setVisibility(View.GONE);
            shutdown(holder);
        }
        holder.open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(holder,position);
                list.get(position).isopen=true;
            }
        });
        holder.shutdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.shutdown.setVisibility(View.GONE);
                holder.open.setVisibility(View.VISIBLE);
                shutdown(holder);
                list.get(position).isopen=false;
            }
        });

        if(list.get(position).imgUrls!=null){
            holder.item_rc.setVisibility(View.VISIBLE);
            getlist(position);
            if(split.length==1){
                gm = new GridLayoutManager(context,1);
            }else if(split.length==2){
                gm=new GridLayoutManager(context,2);
            } else{
                gm=new GridLayoutManager(context,3);
            }
            holder.item_rc.setLayoutManager(gm);
            ItemAdapter myadapter=new ItemAdapter(split);
            holder.item_rc.setAdapter(myadapter);
        }else{
            holder.item_rc.setVisibility(View.GONE);
        }
    }

    private void getlist(int position) {
        String imgUrls = list.get(position).imgUrls;
        split = imgUrls.split("\\|");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    private void open(Holder holder, int position){
        holder.shutdown.setVisibility(View.VISIBLE);
        holder.open.setVisibility(View.GONE);
        holder.guanzhu.setVisibility(View.VISIBLE);
        holder.fenxiang.setVisibility(View.VISIBLE);
        holder.pinglun.setVisibility(View.VISIBLE);
        ObjectAnimator animator1=ObjectAnimator.ofFloat(holder.shutdown,"rotation",0,-180);
        ObjectAnimator animator2=ObjectAnimator.ofFloat(holder.guanzhu_iv,"rotation",90,0);
        ObjectAnimator animator3=ObjectAnimator.ofFloat(holder.guanzhu,"translationX",0,-90);
        ObjectAnimator animator4=ObjectAnimator.ofFloat(holder.guanzhu_iv,"alpha",0,1);
        ObjectAnimator animator41=ObjectAnimator.ofFloat(holder.guanzhu_tv,"alpha",0,1);

        ObjectAnimator animator5=ObjectAnimator.ofFloat(holder.fenxiang_iv,"rotation",90,0);
        ObjectAnimator animator6=ObjectAnimator.ofFloat(holder.fenxiang,"translationX",0,-180);
        ObjectAnimator animator7=ObjectAnimator.ofFloat(holder.fenxiang_iv,"alpha",0,1);
        ObjectAnimator animator71=ObjectAnimator.ofFloat(holder.fenxiang_tv,"alpha",0,1);

        ObjectAnimator animator8=ObjectAnimator.ofFloat(holder.pinglun_iv,"rotation",90,0);
        ObjectAnimator animator9=ObjectAnimator.ofFloat(holder.pinglun,"translationX",0,-260);
        ObjectAnimator animator10=ObjectAnimator.ofFloat(holder.pinglun_iv,"alpha",0,1);
        ObjectAnimator animator101=ObjectAnimator.ofFloat(holder.pinglun_tv,"alpha",0,1);

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
    private void shutdown(final Holder holder){
        ObjectAnimator animator1=ObjectAnimator.ofFloat(holder.open,"rotation",0,180);
        ObjectAnimator animator2=ObjectAnimator.ofFloat(holder.guanzhu_iv,"rotation",0,90);
        ObjectAnimator animator3=ObjectAnimator.ofFloat(holder.guanzhu,"translationX",-90,0);
        ObjectAnimator animator4=ObjectAnimator.ofFloat(holder.guanzhu_iv,"alpha",1,0);
        ObjectAnimator animator41=ObjectAnimator.ofFloat(holder.guanzhu_tv,"alpha",1,0);

        ObjectAnimator animator5=ObjectAnimator.ofFloat(holder.fenxiang_iv,"rotation",0,90);
        ObjectAnimator animator6=ObjectAnimator.ofFloat(holder.fenxiang,"translationX",-180,0);
        ObjectAnimator animator7=ObjectAnimator.ofFloat(holder.fenxiang_iv,"alpha",1,0);
        ObjectAnimator animator71=ObjectAnimator.ofFloat(holder.fenxiang_tv,"alpha",1,0);

        ObjectAnimator animator8=ObjectAnimator.ofFloat(holder.pinglun_iv,"rotation",0,90);
        ObjectAnimator animator9=ObjectAnimator.ofFloat(holder.pinglun,"translationX",-260,0);
        ObjectAnimator animator10=ObjectAnimator.ofFloat(holder.pinglun_iv,"alpha",1,0);
        ObjectAnimator animator101=ObjectAnimator.ofFloat(holder.pinglun_tv,"alpha",1,0);

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
                holder.guanzhu.setVisibility(View.GONE);
                holder.fenxiang.setVisibility(View.GONE);
                holder.pinglun.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public class  Holder extends RecyclerView.ViewHolder{
        private   CustomShapeImageView head;
        private TextView time;
        private TextView name;
        private TextView content;
        private ImageView open;
        private ImageView shutdown;
        private LinearLayout pinglun;
        private CustomShapeImageView pinglun_iv;
        private TextView pinglun_tv;
        private XRecyclerView item_rc;
        private LinearLayout fenxiang;
        private CustomShapeImageView fenxiang_iv;
        private TextView fenxiang_tv;

        private LinearLayout guanzhu;
        private CustomShapeImageView guanzhu_iv;
        private TextView guanzhu_tv;

        public Holder(View itemView) {
            super(itemView);
        head=itemView.findViewById(R.id.duanzi_head);
        time=itemView.findViewById(R.id.duanzi_time);
        name=itemView.findViewById(R.id.duanzi_name);
        content=itemView.findViewById(R.id.duanzi_content);
            item_rc = itemView.findViewById(R.id.item_rc);
            open=itemView.findViewById(R.id.shipin_open);
        shutdown=itemView.findViewById(R.id.shipin_shutdown);

        pinglun=itemView.findViewById(R.id.shipin_pinglun);
        pinglun_iv=itemView.findViewById(R.id.shipin_pinglun_iv);
        pinglun_tv=itemView.findViewById(R.id.shipin_pinglun_tv);

            fenxiang=itemView.findViewById(R.id.shipin_fenxiang);
            fenxiang_iv=itemView.findViewById(R.id.shipin_fenxiang_iv);
            fenxiang_tv=itemView.findViewById(R.id.shipin_fenxiang_tv);

            guanzhu=itemView.findViewById(R.id.shipin_guanzhu);
            guanzhu_iv=itemView.findViewById(R.id.shipin_guanzhu_iv);
            guanzhu_tv=itemView.findViewById(R.id.shipin_guanzhu_tv);
        }
    }
}
