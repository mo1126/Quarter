package com.mo.quarter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.mo.quarter.adapter.ItemAdapter;
import com.mo.quarter.bean.CreatDuanziBean;
import com.mo.quarter.presenter.CreateDuanziPresenter;
import com.mo.quarter.utils.GlideLoader;
import com.mo.quarter.utils.MyIntercepter;
import com.mo.quarter.view.CreateDuanziView;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DuanziActivity extends BaseActivity<CreateDuanziPresenter> implements CreateDuanziView {

    @BindView(R.id.createduanzi_content)
    EditText createduanziContent;
    @BindView(R.id.duanzi_iv_rv)
    RecyclerView duanziIvRv;
    private ArrayList<String> listImg;
    private List<String> pathList;

    @Override
    public int getLayoutid() {
        return R.layout.activity_duanzi;
    }

    @Override
    public CreateDuanziPresenter initPresenter() {
        return new CreateDuanziPresenter(this, this);
    }

    @Override
    public void Creat() {
        ButterKnife.bind(this);
    }

    @OnClick({R.id.creatduanzi_back, R.id.creatduanzi_share, R.id.creatduanzi_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.creatduanzi_back:
                finish();
                break;
            case R.id.creatduanzi_share:
                if(pathList!=null){
                    presenter.createDuanzi(String.valueOf(MyIntercepter.uid), createduanziContent.getText().toString(),pathList);
                }else{
                    presenter.createDuanzi(String.valueOf(MyIntercepter.uid), createduanziContent.getText().toString(),null);
                }
                break;
            case R.id.creatduanzi_add:
                ShowToast("添加图片");
                listImg = new ArrayList<>();
                ImageConfig imageConfig
                        = new ImageConfig.Builder(new GlideLoader())
                        .steepToolBarColor(getResources().getColor(R.color.colorBlue))
                        .titleBgColor(getResources().getColor(R.color.colorBlue))
                        .titleSubmitTextColor(getResources().getColor(R.color.white))
                        .titleTextColor(getResources().getColor(R.color.white))
                        // 开启多选   （默认为多选）
                        .mutiSelect()
                        // 多选时的最大数量   （默认 9 张）
                        .mutiSelectMaxSize(9)
                        // 开启拍照功能 （默认关闭）
                        .showCamera()
                        // 已选择的图片路径
                        .pathList(listImg)
                        // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                        .filePath("/ImageSelector/Pictures")
                        .build();
                ImageSelector.open(DuanziActivity.this, imageConfig);   // 开启图片选择器
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImageSelector.IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // Get Image Path List
            pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            String[] split = new String[pathList.size()];
            String[] imgs = new String[9];
            for (int i = 0; i < pathList.size(); i++) {
                split[i] = pathList.get(i);
            }

            System.out.println(split.length);
            if (split.length > 9) {
                for (int i = 0; i < 9; i++) {
                    imgs[i] = split[i];
                }
            }
                if (split.length == 1) {
                    GridLayoutManager gm = new GridLayoutManager(this, 1);
                    duanziIvRv.setLayoutManager(gm);
                    ItemAdapter myadapter = new ItemAdapter(split);
                    duanziIvRv.setAdapter(myadapter);
                } else if (split.length == 2||split.length == 4) {
                    GridLayoutManager gm = new GridLayoutManager(this, 2);
                    duanziIvRv.setLayoutManager(gm);
                    ItemAdapter myadapter = new ItemAdapter(split);
                    duanziIvRv.setAdapter(myadapter);
                } else if (split.length < 9&&split.length>3&&split.length!=4) {
                    GridLayoutManager gm = new GridLayoutManager(this, 3);
                    duanziIvRv.setLayoutManager(gm);
                    ItemAdapter myadapter = new ItemAdapter(split);
                    duanziIvRv.setAdapter(myadapter);
                } else {
                    GridLayoutManager gm = new GridLayoutManager(this, 3);
                    duanziIvRv.setLayoutManager(gm);
                    ItemAdapter myadapter = new ItemAdapter(imgs);
                    duanziIvRv.setAdapter(myadapter);
                }

            }
        }

    @Override
    public void shareSuccess(CreatDuanziBean creatDuanziBean) {
        ShowToast(creatDuanziBean.msg);
        startActivity(new Intent(this,ShareSuccActivity.class));
        finish();
    }

    @Override
    public void shareFailure(String msg) {
        ShowToast(msg);
    }

    @Override
    public void shareFailureToken(String msg) {
        ShowToast(msg);
    }

    @Override
    public void shareError(String msg) {
        ShowToast(msg);
    }


}
