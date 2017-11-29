package com.mo.quarter.myapp;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.igexin.sdk.PushManager;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by 莫迎华 on 2017/11/14.8:44.
 */

public class MyApp extends Application {


    public static Context context;
    private RefWatcher install;
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        initBugLy();
        initLeackcanary();

    }

    private void initLeackcanary() {
        install = LeakCanary.install(this);
    }

    private void initBugLy() {
        Context context = getApplicationContext();
// 获取当前包名
        String packageName = context.getPackageName();
// 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
// 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
// 初始化Bugly
        //CrashReport.initCrashReport(getApplicationContext(), "02dbbd19b5", true);
        CrashReport.initCrashReport(context, "02dbbd19b5", true, strategy);
    }


    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
