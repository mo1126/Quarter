package com.mo.quarter.adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

    public DuanziAdapter(List<JokesBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public DuanziAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.dzitem1, null);
        Holder holder=new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        Glide.with(context).load(list.get(position).user.icon).into(holder.head);
        holder.name.setText(list.get(position).user.nickname);
        holder.time.setText(list.get(position).createTime);
        holder.content.setText(list.get(position).content);
        holder.open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.shutdown.setVisibility(View.VISIBLE);
                holder.open.setVisibility(View.GONE);
                holder.guanzhu.setVisibility(View.VISIBLE);
                holder.fenxiang.setVisibility(View.VISIBLE);
                holder.pinglun.setVisibility(View.VISIBLE);
                ObjectAnimator animator1=ObjectAnimator.ofFloat(holder.shutdown,"rotation",0,-180);
                ObjectAnimator animator2=ObjectAnimator.ofFloat(holder.guanzhu_iv,"rotation",90,0);
                ObjectAnimator animator3=ObjectAnimator.ofFloat(holder.guanzhu,"translationX",0,-70);
                ObjectAnimator animator4=ObjectAnimator.ofFloat(holder.guanzhu,"alpha",0,1);

                ObjectAnimator animator5=ObjectAnimator.ofFloat(holder.fenxiang_iv,"rotation",90,0);
                ObjectAnimator animator6=ObjectAnimator.ofFloat(holder.fenxiang,"translationX",0,-140);
                ObjectAnimator animator7=ObjectAnimator.ofFloat(holder.fenxiang,"alpha",0,1);

                ObjectAnimator animator8=ObjectAnimator.ofFloat(holder.pinglun_iv,"rotation",90,0);
                ObjectAnimator animator9=ObjectAnimator.ofFloat(holder.pinglun,"translationX",0,-200);
                ObjectAnimator animator10=ObjectAnimator.ofFloat(holder.pinglun,"alpha",0,1);

                AnimatorSet animationSet=new AnimatorSet();
                animationSet.playTogether(animator1);
                animationSet.playTogether(animator2);
                animationSet.playTogether(animator3);
                animationSet.playTogether(animator4);
                animationSet.playTogether(animator5);
                animationSet.playTogether(animator6);
                animationSet.playTogether(animator7);
                animationSet.playTogether(animator8);
                animationSet.playTogether(animator9);
                animationSet.playTogether(animator10);
                animationSet.setDuration(500);
                animationSet.start();

            }
        });
        holder.shutdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.shutdown.setVisibility(View.GONE);
                holder.open.setVisibility(View.VISIBLE);

                ObjectAnimator animator1=ObjectAnimator.ofFloat(holder.open,"rotation",0,180);
                ObjectAnimator animator2=ObjectAnimator.ofFloat(holder.guanzhu_iv,"rotation",0,90);
                ObjectAnimator animator3=ObjectAnimator.ofFloat(holder.guanzhu,"translationX",-70,0);
                ObjectAnimator animator4=ObjectAnimator.ofFloat(holder.guanzhu,"alpha",1,0);

                ObjectAnimator animator5=ObjectAnimator.ofFloat(holder.fenxiang_iv,"rotation",0,90);
                ObjectAnimator animator6=ObjectAnimator.ofFloat(holder.fenxiang,"translationX",-140,0);
                ObjectAnimator animator7=ObjectAnimator.ofFloat(holder.fenxiang,"alpha",1,0);

                ObjectAnimator animator8=ObjectAnimator.ofFloat(holder.pinglun_iv,"rotation",0,90);
                ObjectAnimator animator9=ObjectAnimator.ofFloat(holder.pinglun,"translationX",-200,0);
                ObjectAnimator animator10=ObjectAnimator.ofFloat(holder.pinglun,"alpha",1,0);

                AnimatorSet animationSet=new AnimatorSet();
                animationSet.playTogether(animator1);
                animationSet.playTogether(animator2);
                animationSet.playTogether(animator3);
                animationSet.playTogether(animator4);
                animationSet.playTogether(animator5);
                animationSet.playTogether(animator6);
                animationSet.playTogether(animator7);
                animationSet.playTogether(animator8);
                animationSet.playTogether(animator9);
                animationSet.playTogether(animator10);
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
        });


        if(list.get(position).imgUrls!=null){
            holder.item_rc.setVisibility(View.VISIBLE);
            String imgUrls = list.get(position).imgUrls;
            String[] split = imgUrls.split("\\|");
            String[] imgs = new String[9];
            if(split.length>9){
                for (int i = 0; i <9; i++) {
                    imgs[i]=split[i];
                }
            }
            if(split.length==1){
                GridLayoutManager gm=new GridLayoutManager(context,1);
                holder.item_rc.setLayoutManager(gm);
                ItemAdapter myadapter=new ItemAdapter(split);
                holder.item_rc.setAdapter(myadapter);
            }else if(split.length==2){
                GridLayoutManager gm=new GridLayoutManager(context,2);
                holder.item_rc.setLayoutManager(gm);
                ItemAdapter myadapter=new ItemAdapter(split);
                holder.item_rc.setAdapter(myadapter);
            }else if(split.length<9) {
                GridLayoutManager gm=new GridLayoutManager(context,3);
                holder.item_rc.setLayoutManager(gm);
                ItemAdapter myadapter=new ItemAdapter(split);
                holder.item_rc.setAdapter(myadapter);
            }else{
                GridLayoutManager gm=new GridLayoutManager(context,3);
                holder.item_rc.setLayoutManager(gm);
                ItemAdapter myadapter=new ItemAdapter(imgs);
                holder.item_rc.setAdapter(myadapter);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
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
            open=itemView.findViewById(R.id.duanzi_open);
        shutdown=itemView.findViewById(R.id.duanzi_shutdown);

        pinglun=itemView.findViewById(R.id.dzitem1_pinglun);
        pinglun_iv=itemView.findViewById(R.id.dzitem1_pinglun_iv);
        pinglun_tv=itemView.findViewById(R.id.dzitem1_pinglun_tv);

            fenxiang=itemView.findViewById(R.id.dzitem1_fenxiang);
            fenxiang_iv=itemView.findViewById(R.id.dzitem1_fenxiang_iv);
            fenxiang_tv=itemView.findViewById(R.id.dzitem1_fenxiang_tv);

            guanzhu=itemView.findViewById(R.id.dzitem1_guanzhu);
            guanzhu_iv=itemView.findViewById(R.id.dzitem1_guanzhu_iv);
            guanzhu_tv=itemView.findViewById(R.id.dzitem1_guanzhu_tv);
        }
    }
}
