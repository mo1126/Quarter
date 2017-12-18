package com.mo.quarter;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.meg7.widget.CustomShapeImageView;
import com.mo.quarter.presenter.MyCenterPresenter;
import com.mo.quarter.utils.GlideLoader;
import com.mo.quarter.view.MyCenterView;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCenterActivity extends BaseActivity<MyCenterPresenter> implements MyCenterView {

    @BindView(R.id.my_head)
    CustomShapeImageView myHead;
    @BindView(R.id.my_name)
    TextView myName;
    private List<String> pathList;
    private String name;
    @Override
    public int getLayoutid() {
        return R.layout.activity_my_center;
    }

    @Override
    public MyCenterPresenter initPresenter() {
        return new MyCenterPresenter(this, this);
    }

    @Override
    public void Creat() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String head = intent.getStringExtra("head");
        Glide.with(this).load(head).into(myHead);
        myName.setText(name);
    }

    @Override
    public void setHeadSuccess(String msg) {
        ShowToast(msg+":setHeadSuccess");
        myHead.setImageURI(Uri.parse(pathList.get(0)));
    }

    @Override
    public void setHeadFailure(String msg) {
        ShowToast(msg+"setHeadFailure");
    }

    @Override
    public void setNameSuccess(String msg) {
        myName.setText(name);
        ShowToast(msg);
    }

    @Override
    public void setNameFailure(String msg) {
        ShowToast(msg);
    }

    @Override
    public void mycenterError(String msg) {
        ShowToast(msg+"mycenterError");
        System.out.println(msg);
    }

    @OnClick({R.id.my_head, R.id.my_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_head:
                ImageConfig imageConfig
                        = new ImageConfig.Builder(new GlideLoader())
                        .steepToolBarColor(getResources().getColor(R.color.colorBlue))
                        .titleBgColor(getResources().getColor(R.color.colorBlue))
                        .titleSubmitTextColor(getResources().getColor(R.color.white))
                        .titleTextColor(getResources().getColor(R.color.white))
                        // 开启单选   （默认为多选）
                        .singleSelect()
                        // 开启拍照功能 （默认关闭）
                        .showCamera()
                        // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                        .filePath("/ImageSelector/Pictures")
                        .build();
                ImageSelector.open(MyCenterActivity.this, imageConfig);   // 开启图片选择器
                break;
            case R.id.my_name:
                View inflate = View.inflate(this, R.layout.updataname, null);
                final EditText et_name = inflate.findViewById(R.id.updata_name);

                AlertDialog.Builder al=new AlertDialog.Builder(this).setTitle("更换昵称")
                        .setMessage("请输入要更换的昵称")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {



                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                name = et_name.getText().toString();
                                if(name !=null){
                                    presenter.setName(name);
                                }else{
                                    ShowToast("请输入正确的格式");
                                }

                            }
                        });
                al.setView(inflate).create().show();
                break;
        }
    }

    @Override
    public   void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImageSelector.IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // Get Image Path List
            pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            presenter.sethead(pathList.get(0));
        }
    }
}
