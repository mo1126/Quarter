package com.mo.quarter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mo.quarter.myapp.MyApp;
import com.mo.quarter.presenter.BasePresenter;
import com.mo.quarter.utils.CleanMessageUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShezhiActivity extends BaseActivity {

    @BindView(R.id.shezhi_versoncode)
    TextView shezhiVersoncode;
    @BindView(R.id.shezhi_shezhi_clear_size)
    TextView shezhiShezhiClearSize;

    @Override
    public int getLayoutid() {
        return R.layout.activity_shezhi;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void Creat() {
        ButterKnife.bind(this);
        try {
            shezhiShezhiClearSize.setText(CleanMessageUtil.getTotalCacheSize(MyApp.context));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @OnClick({R.id.shezhi_back, R.id.shezhi_updata, R.id.shezhi_yijian, R.id.shezhi_about, R.id.shezhi_clear, R.id.shezhi_tuichu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shezhi_back:
                finish();
                break;
            case R.id.shezhi_updata:
                ShowToast("版本升级");
                break;
            case R.id.shezhi_yijian:
                ShowToast("意见");
                break;
            case R.id.shezhi_about:
                ShowToast("关于");
                break;
            case R.id.shezhi_clear:
                CleanMessageUtil.clearAllCache(MyApp.context);
                try {
                    shezhiShezhiClearSize.setText(CleanMessageUtil.getTotalCacheSize(MyApp.context));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.shezhi_tuichu:
                SharedPreferences token = getSharedPreferences("token", MODE_PRIVATE);
                SharedPreferences.Editor edit = token.edit();
                edit.clear().commit();
                startActivity(new Intent(this,LoginActivity.class));
                finish();
                finishActivity(1);
                break;
        }
    }
}
