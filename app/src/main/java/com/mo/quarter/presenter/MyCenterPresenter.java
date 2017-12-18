package com.mo.quarter.presenter;

import android.content.Context;

import com.mo.quarter.model.MyCenterModel;
import com.mo.quarter.utils.MyIntercepter;
import com.mo.quarter.view.MyCenterView;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by 莫迎华 on 2017/12/14.14:02.
 */

public class MyCenterPresenter extends BasePresenter<MyCenterView> implements MyCenterModel.setHeadInterface {
    private Context context;
    private MyCenterModel model;
    public MyCenterPresenter(MyCenterView mview, Context context) {
        super(mview);
        this.context = context;
        model=new MyCenterModel();
        model.setSetHeadInterface(this);
    }

    public void sethead(String filepath){
        File file=new File(filepath);
        MultipartBody.Builder builder=new MultipartBody.Builder()  .setType(MultipartBody.FORM);
        RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);
        builder.addFormDataPart("file",file.getName(),requestBody);
        builder.addFormDataPart("uid", MyIntercepter.uid);
        model.setHead(builder.build().parts());
    }
    public void setName(String name){
        model.setName(name);
    }
    @Override
    public void setHeadSuccess(String msg) {
        mview.setHeadSuccess(msg);
    }

    @Override
    public void setHeadFailure(String msg) {
        mview.setHeadFailure(msg);
    }

    @Override
    public void setNameSuccess(String msg) {
        mview.setNameSuccess(msg);
    }

    @Override
    public void setNameFailure(String msg) {
        mview.setNameFailure(msg);
    }

    @Override
    public void mycenterError(String msg) {
        mview.mycenterError(msg);
    }
}
