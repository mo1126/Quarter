package com.mo.quarter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mo.quarter.R;
import com.mo.quarter.bean.HotShipin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 莫迎华 on 2017/12/1.19:29.
 */

public class ShipinAdapter extends XRecyclerView.Adapter<ShipinAdapter.shipinViewHolder> {

    private Context context;
    private List<HotShipin.DataBean>  list;
    private List<Integer> heightList;
    public ShipinAdapter(Context context, List<HotShipin.DataBean>  list) {
        this.context = context;
        this.list = list;
        heightList=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            heightList.add(new Random().nextInt(200)+300);
        }
    }
    public void refresh(List<HotShipin.DataBean>  data){
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }
    public void loadmore(List<HotShipin.DataBean>  data){
        list.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public shipinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.shipin_item, null);
        shipinViewHolder shipinViewHolder=new shipinViewHolder(inflate);
        return shipinViewHolder;
    }

    @Override
    public void onBindViewHolder(shipinViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.iv.getLayoutParams();
        try {
            layoutParams.height=heightList.get(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.iv.setLayoutParams(layoutParams);
        Glide.with(context).load(list.get(position).cover).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class shipinViewHolder extends XRecyclerView.ViewHolder{
        private ImageView iv;
        public shipinViewHolder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.shipin_item_iv);
        }
    }
}
