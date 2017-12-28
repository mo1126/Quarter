package com.mo.quarter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.meg7.widget.CustomShapeImageView;
import com.mo.quarter.R;
import com.mo.quarter.bean.LiaotianBean;

import java.util.List;

/**
 * Created by 莫迎华 on 2017/12/27.20:04.
 */

public class LiaotianAdapter extends XRecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<LiaotianBean> list;
    private Context context;
    private int wo=1;
    private int ta=2;
    public LiaotianAdapter(Context context,List<LiaotianBean> list) {
        this.list = list;
        this.context=context;
    }
    public void refresh(List<LiaotianBean> list){
        this.list=list;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if(viewType==wo){
            View inflate = View.inflate(context, R.layout.liaotian_item1, null);
            holder = new LtViewHolder1(inflate);
        }else{
            View inflate = View.inflate(context, R.layout.liaotian_item, null);
             holder = new LtViewHolder(inflate);
        }
        return holder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType()==ta){
            if(list!=null&&list.size()>0){
                ((LtViewHolder)holder).content.setText(list.get(position).content);
                if(list.get(position).head!=null){
                    Glide.with(context).load(list.get(position).head).into(((LtViewHolder) holder).head);
                }
            }
        }else{
            if(list!=null&&list.size()>0){
                ((LtViewHolder1)holder).content.setText(list.get(position).content);
                if(list.get(position).head!=null){
                    Glide.with(context).load(list.get(position).head).into(((LtViewHolder1) holder).head);
                }
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(list.get(position).from){
            return wo;
        }else{
            return ta;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LtViewHolder extends XRecyclerView.ViewHolder{
        private TextView content;
        private CustomShapeImageView head;
        public LtViewHolder(View itemView) {
            super(itemView);
            content=itemView.findViewById(R.id.ltitem_content);
            head=itemView.findViewById(R.id.liaotian_head);
        }
    }
    public class LtViewHolder1 extends XRecyclerView.ViewHolder{
        private TextView content;
        private CustomShapeImageView head;
        public LtViewHolder1(View itemView) {
            super(itemView);
            content=itemView.findViewById(R.id.ltitem_content1);
            head=itemView.findViewById(R.id.liaotian_myhead);
        }
    }
}
