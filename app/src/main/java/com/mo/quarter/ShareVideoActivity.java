package com.mo.quarter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.mo.quarter.presenter.BasePresenter;
import com.mo.quarter.presenter.ShareVideoPresenter;
import com.mo.quarter.utils.GlideLoader;
import com.mo.quarter.utils.MyIntercepter;
import com.mo.quarter.view.ShareVideoView;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.io.ByteArrayOutputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareVideoActivity extends BaseActivity<ShareVideoPresenter> implements ShareVideoView {

    @BindView(R.id.sharevideo_topback)
    ImageView sharevideoTopback;
    @BindView(R.id.sharevideo_cover)
    ImageView sharevideoCover;
    @BindView(R.id.sharevideo_desc)
    EditText sharevideoDesc;
    @BindView(R.id.sharevideo_back)
    TextView sharevideoBack;
    private String video;
    private String cover;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener ;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private double latitude;
    private double longitude;


    @Override
    public int getLayoutid() {
        return R.layout.activity_share_video;
    }

    @Override
    public ShareVideoPresenter initPresenter() {
        return new ShareVideoPresenter(this,this);
    }

    @Override
    public void Creat() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        video = intent.getStringExtra("video");
    }



    @OnClick({R.id.sharevideo_btcover, R.id.sharevideo_publish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sharevideo_btcover:
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
                ImageSelector.open(ShareVideoActivity.this, imageConfig);   // 开启图片选择器
                break;
            case R.id.sharevideo_publish:
                getlocation();
                if(cover==null||MyIntercepter.uid==null||video==null||longitude==0||latitude==0){
                   ShowToast("请传入正确参数");
                }else{
                    presenter.publish(MyIntercepter.uid,video, cover,sharevideoDesc.getText().toString(),longitude+"",latitude+"");
                }
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mLocationClient!=null){
            mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImageSelector.IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // Get Image Path List
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            for (String path : pathList) {
              sharevideoCover.setImageURI(Uri.parse(path));
                cover=path;
            }
        }
    }

    @Override
    public void shareVideoSuccess(String msg) {
        ShowToast(msg);
        System.out.println(msg);
        Intent intent = new Intent(this, ShareSuccActivity.class);
        intent.putExtra("msg","视频");
        startActivity(intent);
        finish();
    }

    @Override
    public void shareVideoFailure(String msg) {
        ShowToast(msg);
        System.out.println(msg);
    }

    @Override
    public void shareError(String msg) {
        ShowToast(msg);
        System.out.println(msg);
    }

    public void  getlocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationListener= new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        //获取纬度
                        latitude = aMapLocation.getLatitude();
                        //获取经度
                        longitude = aMapLocation.getLongitude();
                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                        System.out.println("location Error, ErrCode:"+aMapLocation.getErrorCode());
                    }
                }
                mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
            }
        }   ;

        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
//该方法默认为false。
        mLocationOption.setOnceLocation(true);

//获取最近3s内精度最高的一次定位结果：
//设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }
}
