package com.mo.quarter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
    public void onBindViewHolder(Holder holder, int position) {
        Glide.with(context).load(list.get(position).user.icon).into(holder.head);
        holder.name.setText(list.get(position).user.nickname);
        holder.time.setText(list.get(position).createTime);
        holder.content.setText(list.get(position).content);
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

        public Holder(View itemView) {
            super(itemView);
        head=itemView.findViewById(R.id.duanzi_head);
        time=itemView.findViewById(R.id.duanzi_time);
        name=itemView.findViewById(R.id.duanzi_name);
        content=itemView.findViewById(R.id.duanzi_content);

        }
    }
}
