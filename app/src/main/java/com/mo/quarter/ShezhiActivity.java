package com.mo.quarter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.mo.quarter.bean.GetVersionBean;
import com.mo.quarter.myapp.MyApp;
import com.mo.quarter.presenter.BasePresenter;
import com.mo.quarter.presenter.ShezhiPresenter;
import com.mo.quarter.utils.APKVersionCodeUtils;
import com.mo.quarter.utils.CleanMessageUtil;
import com.mo.quarter.view.ShezhiView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShezhiActivity extends BaseActivity<ShezhiPresenter> implements ShezhiView {

    @BindView(R.id.shezhi_versoncode)
    TextView shezhiVersoncode;
    @BindView(R.id.shezhi_shezhi_clear_size)
    TextView shezhiShezhiClearSize;
    private int verCode;
    private String verName;
    private String versionCode;
    private String versionName;
    private ProgressDialog m_progressDlg;
    private String apkUrl;
    private Handler m_mainHandler;
    @Override
    public int getLayoutid() {
        return R.layout.activity_shezhi;
    }

    @Override
    public ShezhiPresenter initPresenter() {
        return new ShezhiPresenter(this,this);
    }

    @Override
    public void Creat() {
        ButterKnife.bind(this);
        try {
            shezhiShezhiClearSize.setText(CleanMessageUtil.getTotalCacheSize(MyApp.context));
        } catch (Exception e) {
            e.printStackTrace();
        }
        m_mainHandler = new Handler();


    }
    @OnClick({R.id.shezhi_back, R.id.shezhi_updata, R.id.shezhi_yijian, R.id.shezhi_about, R.id.shezhi_clear, R.id.shezhi_tuichu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shezhi_back:
                finish();
                break;
            case R.id.shezhi_updata:
                ShowToast("版本升级");
                presenter.getVersion();
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
                EMClient.getInstance().logout(true);
                startActivity(new Intent(this,LoginActivity.class));
                finish();
                finishActivity(1);
                break;
        }
    }

    @Override
    public void getSuccess(GetVersionBean getVersionBean) {
        System.out.println(getVersionBean.msg);
        versionCode = getVersionBean.data.versionCode;
        versionName = getVersionBean.data.versionName;
        apkUrl = getVersionBean.data.apkUrl;
        verCode = APKVersionCodeUtils.getVersionCode(this);
        verName = APKVersionCodeUtils.getVerName(this);
        if(versionCode.equals(verCode +"")){
            System.out.println("已经是最新版本了");
        }else{
            System.out.println("版本不一致");
            doNewVersionUpdate();
        }
    }
    /**
     * 提示更新新版本
     */
    private void doNewVersionUpdate() {
        m_progressDlg =  new ProgressDialog(this);
        m_progressDlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 设置ProgressDialog 的进度条是否不明确 false 就是不设置为不明确
        m_progressDlg.setIndeterminate(false);
        String str= "当前版本："+verName+" Code:"+verCode+" ,发现新版本："+versionName+
                " Code:"+versionCode+" ,是否更新？";
        Dialog dialog = new AlertDialog.Builder(this).setTitle("软件更新").setMessage(str)
                // 设置内容
                .setPositiveButton("更新",// 设置确定按钮
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                m_progressDlg.setTitle("正在下载");
                                m_progressDlg.setMessage("请稍候...");
                                downFile(apkUrl);  //开始下载
                            }
                        })
                .setNegativeButton("暂不更新",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                // 点击"取消"按钮之后退出程序
                                finish();
                            }
                        }).create();// 创建
        // 显示对话框
        dialog.show();
    }
    private void downFile(final String url)
    {
        m_progressDlg.show();
        new Thread() {
            public void run() {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(url);
                HttpResponse response;
                try {
                    response = client.execute(get);
                    HttpEntity entity = response.getEntity();
                    long length = entity.getContentLength();

                    m_progressDlg.setMax((int)length);//设置进度条的最大值

                    InputStream is = entity.getContent();
                    FileOutputStream fileOutputStream = null;
                    if (is != null) {
                        File file = new File(
                                Environment.getExternalStorageDirectory(),
                                "Quter.apk");
                        fileOutputStream = new FileOutputStream(file);
                        byte[] buf = new byte[1024];
                        int ch = -1;
                        int count = 0;
                        while ((ch = is.read(buf)) != -1) {
                            fileOutputStream.write(buf, 0, ch);
                            count += ch;
                            if (length > 0) {
                                m_progressDlg.setProgress(count);
                            }
                        }
                    }
                    fileOutputStream.flush();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    down();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    private void down() {
        m_mainHandler.post(new Runnable() {
            public void run() {
                m_progressDlg.cancel();
                update();
            }
        });
    }
    void update() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(Environment
                        .getExternalStorageDirectory(), "Quter.apk")),
                "application/vnd.android.package-archive");
        startActivity(intent);
        shezhiVersoncode.setText(versionCode);
    }
        @Override
    public void getFailure(String msg) {
        ShowToast(msg);
        System.out.println(msg+"getFailure");
    }

    @Override
    public void Error(String msg) {
        ShowToast(msg);
        System.out.println(msg+"Error");
    }
}
