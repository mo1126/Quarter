package com.mo.quarter.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.bean.VideoijkBean;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.meg7.widget.CustomShapeImageView;
import com.mo.quarter.MainActivity;
import com.mo.quarter.R;
import com.mo.quarter.bean.GetVideosBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 莫迎华 on 2017/12/3.20:42.
 */

public class TuijianAdapter extends XRecyclerView.Adapter<TuijianAdapter.TjViewHolder>{


    private Activity context;
    private List<GetVideosBean.DataBean> list;

    public TuijianAdapter(Activity context, List<GetVideosBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public TjViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.tj_rv_item, null);
        TjViewHolder holder=new TjViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(TjViewHolder holder, int position) {
        holder.content.setText(list.get(position).workDesc);
        holder.name.setText(list.get(position).user.nickname);
        holder.time.setText(list.get(position).createTime);
        Glide.with(context).load(list.get(position).user.icon).into(holder.head);

        String videoUrl = list.get(position).videoUrl;
        String replace = videoUrl.replace("https://www.zhaoapi.cn", "http://120.27.23.105");
        /**播放器*/
       // String url = "http://9890.vod.myqcloud.com/9890_9c1fa3e2aea011e59fc841df10c92278.f20.mp4";
        View rootView = View.inflate(context, R.layout.simple_player_view_player, holder.player);
        //context.setContentView(rootView);
        final PlayerView player = new PlayerView(context,rootView)
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        /**加载前显示的缩略图*/
                        Glide.with(context)
                                .load("http://pic2.nipic.com/20090413/406638_125424003_2.jpg")
                                .into(ivThumbnail);
                    }
                })
                .setPlaySource(replace);
        //隐藏返回键，true 隐藏，false 为显示
        player.hideBack(true);
//隐藏菜单键，true 隐藏，false 为显示
        player.hideMenu(true);
//隐藏分辨率按钮，true 隐藏，false 为显示
        player.hideSteam(true);
//隐藏旋转按钮，true 隐藏，false 为显示
        player.hideRotation(true);
//隐藏全屏按钮，true 隐藏，false 为显示
        player.hideFullscreen(true);
//隐藏中间播放按钮,ture 为隐藏，false 为不做隐藏处理，但不是显示
        player.hideCenterPlayer(false);
        //隐藏全屏按钮，true 隐藏，false 为显示
        player.hideFullscreen(true);
        //设置是否禁止双击
        player.setForbidDoulbeUp(true);
        //隐藏全屏按钮，true 隐藏，false 为显示
        player.hideFullscreen(true);
       /* holder.player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.startPlay();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TjViewHolder extends  XRecyclerView.ViewHolder{
        private CustomShapeImageView head;
        private TextView time;
        private TextView name;
        private TextView content;
        private RelativeLayout player;
        public TjViewHolder(View itemView) {
            super(itemView);
            head=itemView.findViewById(R.id.tuijian_head);
            time=itemView.findViewById(R.id.tuijian_time);
            name=itemView.findViewById(R.id.tuijian_name);
            content=itemView.findViewById(R.id.tuijian_content);
           player=itemView.findViewById(R.id.player);
        }
    }

}
