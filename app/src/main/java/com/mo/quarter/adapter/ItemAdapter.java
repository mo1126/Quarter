package com.mo.quarter.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.renderscript.Sampler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mo.quarter.R;
import com.mo.quarter.myapp.MyApp;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.mo.quarter.myapp.MyApp.context;

/**
 * Created by 莫迎华 on 2017/11/30.9:27.
 */

public class ItemAdapter extends XRecyclerView.Adapter<ItemAdapter.Holder> {

    private String[] imgs;
    public ItemAdapter(String[] imgs) {
        this.imgs = imgs;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.item_item_iv, null);
        Holder holder=new Holder(inflate);
        return holder;
    }
    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        if (imgs.length == 1) {
            holder.itemIv1.setVisibility(View.VISIBLE);
            Glide.with(context).load(imgs[position]).into(holder.itemIv1);
        }else if(imgs.length==2||imgs.length==4){
            holder.itemIv1.setVisibility(View.GONE );
            holder.itemIv2.setVisibility(View.GONE );
            Glide.with(context).load( imgs[position]).into(holder.itemIv);
        }else{
            holder.itemIv1.setVisibility(View.GONE );
            holder.itemIv.setVisibility(View.GONE );
            holder.itemIv2.setVisibility(View.VISIBLE );
            Glide.with(context).load(imgs[position]).into(holder.itemIv2);
        }
    }

    @Override
    public int getItemCount() {
        return imgs.length;
    }

    public class Holder extends XRecyclerView.ViewHolder{
        private ImageView itemIv;
        private ImageView itemIv1;
        private ImageView itemIv2;
        public Holder(View itemView) {
            super(itemView);
            itemIv=itemView.findViewById(R.id.item_rc_item);
            itemIv1=itemView.findViewById(R.id.item_rc_item1);
            itemIv2=itemView.findViewById(R.id.item_rc_item2);
        }
    }



}
