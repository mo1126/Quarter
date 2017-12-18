package com.mo.quarter.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.meg7.widget.CustomShapeImageView;
import com.mo.quarter.R;
import com.mo.quarter.bean.MyFollowUserBean;

import java.util.List;

/**
 * Created by 莫迎华 on 2017/12/16.10:33.
 */

public class MyAttentionAdapter extends BaseAdapter {
    private Context context;
    private List<MyFollowUserBean.DataBean> list;

    public MyAttentionAdapter(Context context, List<MyFollowUserBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyAttentionHoler holer=null;
        if(convertView==null){
            holer=new MyAttentionHoler();
            convertView = View.inflate(context, R.layout.attention_lvitem, null);
            holer.head=convertView.findViewById(R.id.attention_head);
            holer.name=convertView.findViewById(R.id.attention_name);
            holer.time=convertView.findViewById(R.id.attention_time);
            convertView.setTag(holer);
        }else{
            holer= (MyAttentionHoler) convertView.getTag();
        }
        holer.name.setText(list.get(position).nickname);
        holer.time.setText(list.get(position).createtime);
        Glide.with(context).load(list.get(position).icon).into(holer.head);
        return convertView;
    }

    public class MyAttentionHoler {
        private CustomShapeImageView head;
        private TextView name;
        private TextView time;
    }
}
