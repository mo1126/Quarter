package com.mo.quarter.bean;

/**
 * Created by 莫迎华 on 2017/11/28.8:39.
 */

public class LoginOtherBean {

    /**
     * msg : 登录成功
     * code : 0
     * data : {"age":null,"appkey":null,"appsecret":null,"createtime":"2017-11-28T08:51:49","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/98.jpg","latitude":null,"longitude":null,"mobile":"15011217423","money":0,"nickname":"Mo","password":"971121","praiseNum":null,"token":"328E60FC7E24996E96A80D99F6311F01","uid":98,"username":"15011217423"}
     */

    public String msg;
    public String code;
    public DataBean data;

    public static class DataBean {
        /**
         * age : null
         * appkey : null
         * appsecret : null
         * createtime : 2017-11-28T08:51:49
         * email : null
         * fans : null
         * follow : null
         * gender : 0
         * icon : https://www.zhaoapi.cn/images/98.jpg
         * latitude : null
         * longitude : null
         * mobile : 15011217423
         * money : 0
         * nickname : Mo
         * password : 971121
         * praiseNum : null
         * token : 328E60FC7E24996E96A80D99F6311F01
         * uid : 98
         * username : 15011217423
         */

        public Object age;
        public Object appkey;
        public Object appsecret;
        public String createtime;
        public Object email;
        public Object fans;
        public Object follow;
        public int gender;
        public String icon;
        public Object latitude;
        public Object longitude;
        public String mobile;
        public int money;
        public String nickname;
        public String password;
        public Object praiseNum;
        public String token;
        public int uid;
        public String username;
    }
}
