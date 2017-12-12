package com.mo.quarter.presenter;

import android.content.Context;
import android.content.Intent;

import com.mo.quarter.LoginActivity;
import com.mo.quarter.model.ShareVideoModel;
import com.mo.quarter.view.ShareVideoView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by 莫迎华 on 2017/12/6.16:39.
 */

public class ShareVideoPresenter extends BasePresenter<ShareVideoView> implements ShareVideoModel.shareVideoInterface {

    private  Context context;
    private ShareVideoModel model;
    public ShareVideoPresenter(ShareVideoView mview, Context context) {
        super(mview);
        this.context = context;
        model=new ShareVideoModel();
        model.setShareVideoInterface(this);
    }

    public void publish(String uid, String videoFile,String coverFile,String videoDesc,String longitude,String latitude){

        MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("uid",uid)
                .addFormDataPart("longitude",longitude)
                .addFormDataPart("latitude",latitude);
        File video=new File(videoFile);
        RequestBody videobody=RequestBody.create(MediaType.parse("multipart/form-data"),video);
        builder.addFormDataPart("videoFile",video.getName(),videobody);

        File cover=new File(coverFile);
        RequestBody coverbody=RequestBody.create(MediaType.parse("multipart/form-data"),cover);
        builder.addFormDataPart("coverFile",cover.getName(),coverbody);
        if(videoDesc!=null){
            builder.addFormDataPart("videoDesc",videoDesc);
        }
        model.publish(builder.build().parts());
    }
    @Override
    public void shareVideoSuccess(String msg) {
        mview.shareVideoSuccess(msg);
    }

    @Override
    public void shareVideoFailure(String msg,String code) {
        if(code.equals("1")){
            mview.shareVideoFailure(msg);
        }else{
            mview.shareVideoFailure(msg);
            context.startActivity(new Intent(context, LoginActivity.class));
        }

    }
    @Override
    public void shareError(String msg) {
        mview.shareError(msg);
    }
}
