package com.mo.quarter.bean;

/**
 * Created by 莫迎华 on 2017/12/14.19:14.
 */

public class GetVersionBean {

    /**
     * msg : 获取版本信息成功
     * code : 0
     * data : {"apkUrl":"https://www.zhaoapi.cn/version/app.apk","type":2,"vId":3,"versionCode":"300","versionName":"3.0"}
     */

    public String msg;
    public String code;
    public DataBean data;

    public static class DataBean {
        /**
         * apkUrl : https://www.zhaoapi.cn/version/app.apk
         * type : 2
         * vId : 3
         * versionCode : 300
         * versionName : 3.0
         */

        public String apkUrl;
        public int type;
        public int vId;
        public String versionCode;
        public String versionName;
    }
}
