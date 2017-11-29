package com.mo.quarter.bean;

/**
 * Created by 莫迎华 on 2017/11/28.13:58.
 */

public class UserInfoBean {

    /**
     * msg : 获取用户信息成功
     * code : 0
     * data : {"age":null,"appkey":null,"appsecret":null,"createtime":"2017-11-28T11:10:06","email":null,"fans":0,"follow":0,"gender":0,"icon":"https://www.zhaoapi.cn/images/98.jpg","latitude":null,"longitude":null,"mobile":"15011217423","money":0,"nickname":"Mo","password":"971121","praiseNum":null,"token":"328E60FC7E24996E96A80D99F6311F01","uid":98,"userId":null,"username":"15011217423"}
     */

    public String msg;
    public String code;
    public DataBean data;

    public static class DataBean {
        /**
         * age : null
         * appkey : null
         * appsecret : null
         * createtime : 2017-11-28T11:10:06
         * email : null
         * fans : 0
         * follow : 0
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
         * userId : null
         * username : 15011217423
         */

        public Object age;
        public Object appkey;
        public Object appsecret;
        public String createtime;
        public Object email;
        public int fans;
        public int follow;
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
        public Object userId;
        public String username;
    }
}
