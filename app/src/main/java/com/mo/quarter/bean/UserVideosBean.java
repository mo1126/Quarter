package com.mo.quarter.bean;

import java.util.List;

/**
 * Created by 莫迎华 on 2017/12/15.14:32.
 */

public class UserVideosBean {


    /**
     * msg : 获取作品列表成功
     * code : 0
     * data : {"user":{"age":null,"fans":"null","follow":true,"icon":"https://www.zhaoapi.cn/images/1512179089975avator_thump.jpg","nickname":"李灿灿","praiseNum":"null"},"worksEntities":[{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512475119412截屏_20170710_235930.jpg","createTime":"2017-12-05T19:58:39","favoriteNum":null,"latitude":"0.0","localUri":null,"longitude":"0.0","playNum":null,"praiseNum":null,"uid":148,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512475119412video_20171205_194037.mp4","wid":54,"workDesc":null},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512539663412B612_20170608_184223.jpg","createTime":"2017-12-06T13:54:23","favoriteNum":null,"latitude":"40.04045685778869","localUri":null,"longitude":"116.30005933226604","playNum":null,"praiseNum":null,"uid":148,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512539663412PictureSelector_20171206_115353.mp4","wid":69,"workDesc":"fyfff"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512539663771B612_20170608_184223.jpg","createTime":"2017-12-06T13:54:23","favoriteNum":null,"latitude":"40.04045685778869","localUri":null,"longitude":"116.30005933226604","playNum":null,"praiseNum":null,"uid":148,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512539663771PictureSelector_20171206_115353.mp4","wid":70,"workDesc":"fyfff"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512539671506B612_20170608_184223.jpg","createTime":"2017-12-06T13:54:31","favoriteNum":null,"latitude":"40.04045685778869","localUri":null,"longitude":"116.30005933226604","playNum":null,"praiseNum":null,"uid":148,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512539671506PictureSelector_20171206_115353.mp4","wid":71,"workDesc":"fyfff"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512540178850imagescrop_avatar.jpg","createTime":"2017-12-06T14:02:58","favoriteNum":null,"latitude":"40.04045687560077","localUri":null,"longitude":"116.30007135218668","playNum":null,"praiseNum":null,"uid":148,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512540178850PictureSelector_20171206_115326.mp4","wid":72,"workDesc":"李灿灿"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512540193350imagescrop_avatar.jpg","createTime":"2017-12-06T14:03:13","favoriteNum":null,"latitude":"40.04045687560077","localUri":null,"longitude":"116.30007135218668","playNum":null,"praiseNum":null,"uid":148,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512540193350PictureSelector_20171206_115326.mp4","wid":73,"workDesc":"李灿灿"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/15125403322409688184044fb644b01a1556afffe9e94.jpg","createTime":"2017-12-06T14:05:32","favoriteNum":null,"latitude":"40.04045502515057","localUri":null,"longitude":"116.30007300627379","playNum":null,"praiseNum":null,"uid":148,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512540332240PictureSelector_20171206_140440.mp4","wid":74,"workDesc":"ll"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512695479928215628ekxkvk5xnhx35ccm.jpg","createTime":"2017-12-08T09:11:19","favoriteNum":null,"latitude":"40.04045210096649","localUri":null,"longitude":"116.30003464079894","playNum":null,"praiseNum":null,"uid":148,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512695479928PictureSelector_20171208_091033.mp4","wid":193,"workDesc":"夜光"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512695492193215628ekxkvk5xnhx35ccm.jpg","createTime":"2017-12-08T09:11:32","favoriteNum":null,"latitude":"40.04045210099616","localUri":null,"longitude":"116.30003466083213","playNum":null,"praiseNum":null,"uid":148,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512695492193PictureSelector_20171208_091033.mp4","wid":194,"workDesc":"夜光"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1513320695418avator_thump.jpg","createTime":"2017-12-15T14:51:35","favoriteNum":null,"latitude":"40.040438843506415","localUri":null,"longitude":"116.30001389784546","playNum":null,"praiseNum":null,"uid":148,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1513320695418PictureSelector_20171206_115353.mp4","wid":219,"workDesc":"cc"}]}
     */

    public String msg;
    public String code;
    public DataBean data;

    public static class DataBean {
        /**
         * user : {"age":null,"fans":"null","follow":true,"icon":"https://www.zhaoapi.cn/images/1512179089975avator_thump.jpg","nickname":"李灿灿","praiseNum":"null"}
         * worksEntities : [{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512475119412截屏_20170710_235930.jpg","createTime":"2017-12-05T19:58:39","favoriteNum":null,"latitude":"0.0","localUri":null,"longitude":"0.0","playNum":null,"praiseNum":null,"uid":148,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512475119412video_20171205_194037.mp4","wid":54,"workDesc":null},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512539663412B612_20170608_184223.jpg","createTime":"2017-12-06T13:54:23","favoriteNum":null,"latitude":"40.04045685778869","localUri":null,"longitude":"116.30005933226604","playNum":null,"praiseNum":null,"uid":148,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512539663412PictureSelector_20171206_115353.mp4","wid":69,"workDesc":"fyfff"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512539663771B612_20170608_184223.jpg","createTime":"2017-12-06T13:54:23","favoriteNum":null,"latitude":"40.04045685778869","localUri":null,"longitude":"116.30005933226604","playNum":null,"praiseNum":null,"uid":148,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512539663771PictureSelector_20171206_115353.mp4","wid":70,"workDesc":"fyfff"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512539671506B612_20170608_184223.jpg","createTime":"2017-12-06T13:54:31","favoriteNum":null,"latitude":"40.04045685778869","localUri":null,"longitude":"116.30005933226604","playNum":null,"praiseNum":null,"uid":148,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512539671506PictureSelector_20171206_115353.mp4","wid":71,"workDesc":"fyfff"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512540178850imagescrop_avatar.jpg","createTime":"2017-12-06T14:02:58","favoriteNum":null,"latitude":"40.04045687560077","localUri":null,"longitude":"116.30007135218668","playNum":null,"praiseNum":null,"uid":148,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512540178850PictureSelector_20171206_115326.mp4","wid":72,"workDesc":"李灿灿"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512540193350imagescrop_avatar.jpg","createTime":"2017-12-06T14:03:13","favoriteNum":null,"latitude":"40.04045687560077","localUri":null,"longitude":"116.30007135218668","playNum":null,"praiseNum":null,"uid":148,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512540193350PictureSelector_20171206_115326.mp4","wid":73,"workDesc":"李灿灿"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/15125403322409688184044fb644b01a1556afffe9e94.jpg","createTime":"2017-12-06T14:05:32","favoriteNum":null,"latitude":"40.04045502515057","localUri":null,"longitude":"116.30007300627379","playNum":null,"praiseNum":null,"uid":148,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512540332240PictureSelector_20171206_140440.mp4","wid":74,"workDesc":"ll"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512695479928215628ekxkvk5xnhx35ccm.jpg","createTime":"2017-12-08T09:11:19","favoriteNum":null,"latitude":"40.04045210096649","localUri":null,"longitude":"116.30003464079894","playNum":null,"praiseNum":null,"uid":148,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512695479928PictureSelector_20171208_091033.mp4","wid":193,"workDesc":"夜光"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1512695492193215628ekxkvk5xnhx35ccm.jpg","createTime":"2017-12-08T09:11:32","favoriteNum":null,"latitude":"40.04045210099616","localUri":null,"longitude":"116.30003466083213","playNum":null,"praiseNum":null,"uid":148,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512695492193PictureSelector_20171208_091033.mp4","wid":194,"workDesc":"夜光"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/1513320695418avator_thump.jpg","createTime":"2017-12-15T14:51:35","favoriteNum":null,"latitude":"40.040438843506415","localUri":null,"longitude":"116.30001389784546","playNum":null,"praiseNum":null,"uid":148,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1513320695418PictureSelector_20171206_115353.mp4","wid":219,"workDesc":"cc"}]
         */

        public UserBean user;
        public List<WorksEntitiesBean> worksEntities;

        public static class UserBean {
            /**
             * age : null
             * fans : null
             * follow : true
             * icon : https://www.zhaoapi.cn/images/1512179089975avator_thump.jpg
             * nickname : 李灿灿
             * praiseNum : null
             */

            public Object age;
            public String fans;
            public boolean follow;
            public String icon;
            public String nickname;
            public String praiseNum;
        }

        public static class WorksEntitiesBean {
            /**
             * commentNum : null
             * cover : https://www.zhaoapi.cn/images/quarter/1512475119412截屏_20170710_235930.jpg
             * createTime : 2017-12-05T19:58:39
             * favoriteNum : null
             * latitude : 0.0
             * localUri : null
             * longitude : 0.0
             * playNum : null
             * praiseNum : null
             * uid : 148
             * videoUrl : https://www.zhaoapi.cn/images/quarter/1512475119412video_20171205_194037.mp4
             * wid : 54
             * workDesc : null
             */

            public boolean isopen;
            public Object commentNum;
            public String cover;
            public String createTime;
            public Object favoriteNum;
            public String latitude;
            public Object localUri;
            public String longitude;
            public Object playNum;
            public Object praiseNum;
            public int uid;
            public String videoUrl;
            public int wid;
            public String workDesc;
        }
    }
}
