package com.mo.quarter.bean;

import java.util.List;

/**
 * Created by 莫迎华 on 2017/12/16.10:08.
 */

public class MyFollowUserBean {

    /**
     * msg : 获取关注用户列表成功
     * code : 0
     * data : [{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-12-15T16:44:28","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/88.jpg","latitude":null,"longitude":null,"mobile":"13716792264","money":0,"nickname":"解杰\n","password":"111111","praiseNum":null,"token":"80228EFBFC03F309AF88913FDD074A2B","uid":88,"userId":null,"username":"13716792264"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-12-15T21:54:22","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/1513246264287cropped_1513246262263.jpg","latitude":null,"longitude":null,"mobile":"15810672623","money":0,"nickname":"小狼","password":"123456","praiseNum":null,"token":"28C3793F9AB8E555D523C46D8D867998","uid":150,"userId":null,"username":"15810672623"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-12-16T10:07:49","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/1512470827115mo.jpg","latitude":null,"longitude":null,"mobile":"18410261121","money":0,"nickname":"nnnnnn","password":"111111","praiseNum":null,"token":"C5F1896E305FA85630AA9BB54A621BC3","uid":195,"userId":null,"username":"18410261121"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-12-16T10:06:05","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/15131486893501.jpg","latitude":null,"longitude":null,"mobile":"15297526557","money":0,"nickname":"beautiful","password":"123456","praiseNum":null,"token":"D9C75CAADF0EE4C106A4C4D9B4DE1B19","uid":170,"userId":null,"username":"15297526557"}]
     */

    public String msg;
    public String code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * age : null
         * appkey : null
         * appsecret : null
         * createtime : 2017-12-15T16:44:28
         * email : null
         * fans : null
         * follow : null
         * gender : 0
         * icon : https://www.zhaoapi.cn/images/88.jpg
         * latitude : null
         * longitude : null
         * mobile : 13716792264
         * money : 0
         * nickname : 解杰

         * password : 111111
         * praiseNum : null
         * token : 80228EFBFC03F309AF88913FDD074A2B
         * uid : 88
         * userId : null
         * username : 13716792264
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
        public Object userId;
        public String username;
    }
}
