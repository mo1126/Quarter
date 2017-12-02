package com.mo.quarter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.mo.quarter.presenter.BasePresenter;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_wx)
    LinearLayout loginWx;
    @BindView(R.id.login_qq)
    LinearLayout loginQq;
    @BindView(R.id.login_other)
    TextView loginOther;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener ;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;




    @Override
    public int getLayoutid() {
        return R.layout.activity_login;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void Creat() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
        String uid = token.getString("uid", null);
        if(uid!=null){
            startActivity(new Intent(this,HomeActivity.class));
            finish();
        }



        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationListener= new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        String country = aMapLocation.getCountry();//国家信息
                        String province = aMapLocation.getProvince();//省信息
                        String city = aMapLocation.getCity();//城市信息
                        String district = aMapLocation.getDistrict();//城区信息
                        String street = aMapLocation.getStreet();//街道信息
                        String streetNum = aMapLocation.getStreetNum();//街道门牌号信息
                        double latitude = aMapLocation.getLatitude();//获取纬度
                        double longitude = aMapLocation.getLongitude();//获取经度
                        System.out.println(country + "  "+province + "  " + city + "  " + district + "  " + street + "  " + streetNum + "  " + latitude + "  " + longitude);
//可在其中解析amapLocation获取相应内容。
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




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    @OnClick({R.id.login_back,R.id.login_wx, R.id.login_qq, R.id.login_other})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.login_wx:
                ShowToast("微信登录");
                //启动定位
                mLocationClient.startLocation();
                break;
            case R.id.login_qq:
                ShowToast("QQ登录");
                break;
            case R.id.login_other:
                ShowToast("其他登录");
                startActivity(new Intent(this,LoginOtherActivity.class));
                break;
        }
    }
}

