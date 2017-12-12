package com.mo.quarter.utils;

import android.content.Context;

import okhttp3.Cache;

/**
 * Created by 莫迎华 on 2017/12/10.21:04.
 */

public class CacheProvide {
    Context mContext;

    public CacheProvide(Context context) {
        mContext = context;
    }

    public Cache provideCache() {//使用应用缓存文件路径，缓存大小为10MB
        return new Cache(mContext.getCacheDir(), 10240 * 1024);
    }
}
