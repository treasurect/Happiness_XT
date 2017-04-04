package com.treasure_ct.happiness_xt.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by treasure on 2017.04.04.
 */

public class HomeNewsNeihanListBean implements Serializable{



    private String message;
    private DataBeanX data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * has_more : true
         * tip : 20条新内容
         * has_new_message : false
         * max_time : 1.491309271E9
         * min_time : 1491309290
         * data : [{"group":{"360p_video":{"width":320,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=360p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=360p&line=1&is_gif=0&device_platform="}],"uri":"360p/8e5b163a506a491093a3608bc9caf5db","height":568},"mp4_url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=480p&line=0&is_gif=0&device_platform=.mp4","text":"青岛猫展","720p_video":{"width":320,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=720p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=720p&line=1&is_gif=0&device_platform="}],"uri":"720p/8e5b163a506a491093a3608bc9caf5db","height":568},"digg_count":6014,"duration":7.8,"480p_video":{"width":320,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=480p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=480p&line=1&is_gif=0&device_platform="}],"uri":"480p/8e5b163a506a491093a3608bc9caf5db","height":568},"create_time":1491137058,"keywords":"","id":58786982722,"favorite_count":28,"go_detail_count":18378,"m3u8_url":"","large_cover":{"url_list":[{"url":"http://p3.pstatp.com/large/1aa00008298c7cbc00a8"},{"url":"http://pb2.pstatp.com/large/1aa00008298c7cbc00a8"},{"url":"http://pb3.pstatp.com/large/1aa00008298c7cbc00a8"}],"uri":"large/1aa00008298c7cbc00a8"},"video_wh_ratio":1,"user_favorite":0,"share_type":1,"title":"","user":{"user_id":56829500242,"name":"ACat喵","followings":0,"user_verified":false,"ugc_count":47,"avatar_url":"http://wx.qlogo.cn/mmopen/gTiaWLzArw211kSqNdmsiatKG3p6hTljmgeBf8TSXEwLiawA4gr6krfYYhmww8WhDHP22KJcibsGAMn5boutIlXaC7mrJOdG7HLX/0","followers":82,"is_following":false,"is_pro_user":false},"is_can_share":1,"category_type":1,"share_url":"http://m.neihanshequ.com/share/group/58786982722/?iid=0&app=joke_essay","label":4,"content":"青岛猫展","video_height":568,"comment_count":122,"cover_image_uri":"1aa00008298c7cbc00a8","id_str":"58786982722","media_type":3,"share_count":198,"type":2,"category_id":65,"status":103,"has_comments":0,"publish_time":"","user_bury":0,"status_desc":"已发表","dislike_reason":[{"type":1,"id":577,"title":"UGC原创"},{"type":2,"id":65,"title":"吧:搞笑视频"},{"type":4,"id":0,"title":"内容重复"},{"type":3,"id":56829500242,"title":"作者:ACat喵"}],"play_count":315856,"user_repin":0,"medium_cover":{"url_list":[{"url":"http://p3.pstatp.com/w202/1aa00008298c7cbc00a8"},{"url":"http://pb2.pstatp.com/w202/1aa00008298c7cbc00a8"},{"url":"http://pb3.pstatp.com/w202/1aa00008298c7cbc00a8"}],"uri":"medium/1aa00008298c7cbc00a8"},"user_digg":0,"video_width":320,"online_time":1491137058,"category_name":"搞笑视频","flash_url":"","category_visible":true,"bury_count":255,"is_anonymous":false,"repin_count":28,"video_id":"8e5b163a506a491093a3608bc9caf5db","uri":"8e5b163a506a491093a3608bc9caf5db","is_public_url":1,"has_hot_comments":1,"allow_dislike":true,"origin_video":{"width":320,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=origin&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=origin&line=1&is_gif=0&device_platform="}],"uri":"origin/8e5b163a506a491093a3608bc9caf5db","height":568},"cover_image_url":"","activity":{},"group_id":58786982722,"is_video":1,"display_type":0},"comments":[],"type":1,"display_time":1491309290,"online_time":1491309290},{"group":{"360p_video":{"width":360,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=f59557149b0b43ecb8725563361c3ea5&quality=360p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=f59557149b0b43ecb8725563361c3ea5&quality=360p&line=1&is_gif=0&device_platform="}],"uri":"360p/f59557149b0b43ecb8725563361c3ea5","height":634},"mp4_url":"http://ic.snssdk.com/neihan/video/playback/?video_id=f59557149b0b43ecb8725563361c3ea5&quality=480p&line=0&is_gif=0&device_platform=.mp4","text":"😂😂😂😂我就笑笑","720p_video":{"width":360,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=f59557149b0b43ecb8725563361c3ea5&quality=720p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=f59557149b0b43ecb8725563361c3ea5&quality=720p&line=1&is_gif=0&device_platform="}],"uri":"720p/f59557149b0b43ecb8725563361c3ea5","height":634},"digg_count":4205,"duration":7.6,"480p_video":{"width":360,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=f59557149b0b43ecb8725563361c3ea5&quality=480p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=f59557149b0b43ecb8725563361c3ea5&quality=480p&line=1&is_gif=0&device_platform="}],"uri":"480p/f59557149b0b43ecb8725563361c3ea5","height":634},"create_time":1489827304,"keywords":"","id":58176742164,"favorite_count":1168,"go_detail_count":103034,"m3u8_url":"","large_cover":{"url_list":[{"url":"http://p3.pstatp.com/large/19360007e2f2ee36cdcf"},{"url":"http://pb2.pstatp.com/large/19360007e2f2ee36cdcf"},{"url":"http://pb3.pstatp.com/large/19360007e2f2ee36cdcf"}],"uri":"large/19360007e2f2ee36cdcf"},"video_wh_ratio":1,"user_favorite":0,"share_type":1,"title":"","user":{"user_id":-1,"name":"匿名用户","followings":0,"user_verified":false,"ugc_count":0,"avatar_url":"http://mat1.gtimg.com/www/mb/images/head_50.jpg","followers":0,"is_following":false,"is_pro_user":false},"is_can_share":1,"category_type":1,"share_url":"http://m.neihanshequ.com/share/group/58176742164/?iid=0&app=joke_essay","label":4,"content":"😂😂😂😂我就笑笑","video_height":634,"comment_count":554,"cover_image_uri":"19360007e2f2ee36cdcf","id_str":"58176742164","media_type":3,"share_count":2317,"type":2,"category_id":123,"status":103,"has_comments":0,"publish_time":"","user_bury":0,"status_desc":"已发表","dislike_reason":[{"type":1,"id":384,"title":"随手拍"},{"type":2,"id":123,"title":"吧:晒手机截屏"},{"type":4,"id":0,"title":"内容重复"},{"type":5,"id":0,"title":"作者:匿名"}],"play_count":757165,"user_repin":0,"medium_cover":{"url_list":[{"url":"http://p3.pstatp.com/w202/19360007e2f2ee36cdcf"},{"url":"http://pb2.pstatp.com/w202/19360007e2f2ee36cdcf"},{"url":"http://pb3.pstatp.com/w202/19360007e2f2ee36cdcf"}],"uri":"medium/19360007e2f2ee36cdcf"},"user_digg":0,"video_width":360,"online_time":1489827304,"category_name":"晒手机截屏","flash_url":"","category_visible":true,"bury_count":356,"is_anonymous":true,"repin_count":1168,"video_id":"f59557149b0b43ecb8725563361c3ea5","uri":"f59557149b0b43ecb8725563361c3ea5","is_public_url":1,"has_hot_comments":1,"allow_dislike":true,"origin_video":{"width":360,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=f59557149b0b43ecb8725563361c3ea5&quality=origin&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=f59557149b0b43ecb8725563361c3ea5&quality=origin&line=1&is_gif=0&device_platform="}],"uri":"origin/f59557149b0b43ecb8725563361c3ea5","height":634},"cover_image_url":"","activity":{},"group_id":58176742164,"is_video":1,"display_type":0},"comments":[],"type":1,"display_time":1.491309289E9,"online_time":1.491309289E9},{"group":{"360p_video":{"width":640,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=3185f8ad3e8d4b8ba88309b9688eec88&quality=360p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=3185f8ad3e8d4b8ba88309b9688eec88&quality=360p&line=1&is_gif=0&device_platform="}],"uri":"360p/3185f8ad3e8d4b8ba88309b9688eec88","height":360},"mp4_url":"http://ic.snssdk.com/neihan/video/playback/?video_id=3185f8ad3e8d4b8ba88309b9688eec88&quality=480p&line=0&is_gif=0&device_platform=.mp4","text":"快速提臀瘦腿，只要这几组简单的动作","720p_video":{"width":640,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=3185f8ad3e8d4b8ba88309b9688eec88&quality=720p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=3185f8ad3e8d4b8ba88309b9688eec88&quality=720p&line=1&is_gif=0&device_platform="}],"uri":"720p/3185f8ad3e8d4b8ba88309b9688eec88","height":360},"digg_count":1270,"duration":95,"480p_video":{"width":640,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=3185f8ad3e8d4b8ba88309b9688eec88&quality=480p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=3185f8ad3e8d4b8ba88309b9688eec88&quality=480p&line=1&is_gif=0&device_platform="}],"uri":"480p/3185f8ad3e8d4b8ba88309b9688eec88","height":360},"create_time":1489820777,"keywords":"","id":6398726567833911553,"favorite_count":20998,"go_detail_count":10453,"m3u8_url":"","large_cover":{"url_list":[{"url":"http://p3.pstatp.com/large/191e0003a59be1ca55cb"},{"url":"http://pb2.pstatp.com/large/191e0003a59be1ca55cb"},{"url":"http://pb3.pstatp.com/large/191e0003a59be1ca55cb"}],"uri":"large/191e0003a59be1ca55cb"},"user_favorite":0,"share_type":1,"title":"","user":{"user_id":3351485093,"name":"本人阅女无术","followings":0,"user_verified":false,"ugc_count":2199,"avatar_url":"http://tp3.sinaimg.cn/3048552294/50/5651381013/0","followers":5258,"is_following":false,"is_pro_user":false},"is_can_share":1,"category_type":2,"share_url":"http://m.neihanshequ.com/share/group/6398726567833911553/?iid=0&app=joke_essay","label":4,"content":"快速提臀瘦腿，只要这几组简单的动作","video_height":360,"comment_count":228,"cover_image_uri":"191e0003a59be1ca55cb","id_str":"6398726567833911553","media_type":3,"share_count":8825,"type":2,"category_id":34,"status":103,"has_comments":0,"publish_time":"","user_bury":0,"status_desc":"已发表","dislike_reason":[{"type":1,"id":418,"title":"美妆"},{"type":2,"id":34,"title":"吧:美妆达人"},{"type":4,"id":0,"title":"内容重复"},{"type":3,"id":3351485093,"title":"作者:本人阅女无术"}],"play_count":215373,"user_repin":0,"medium_cover":{"url_list":[{"url":"http://p3.pstatp.com/w202/191e0003a59be1ca55cb"},{"url":"http://pb2.pstatp.com/w202/191e0003a59be1ca55cb"},{"url":"http://pb3.pstatp.com/w202/191e0003a59be1ca55cb"}],"uri":"medium/191e0003a59be1ca55cb"},"user_digg":0,"video_width":640,"online_time":1489820777,"category_name":"美妆达人","flash_url":"","category_visible":true,"bury_count":176,"is_anonymous":false,"repin_count":20998,"video_id":"3185f8ad3e8d4b8ba88309b9688eec88","uri":"3185f8ad3e8d4b8ba88309b9688eec88","is_public_url":1,"has_hot_comments":0,"allow_dislike":true,"origin_video":{"width":640,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=3185f8ad3e8d4b8ba88309b9688eec88&quality=origin&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=3185f8ad3e8d4b8ba88309b9688eec88&quality=origin&line=1&is_gif=0&device_platform="}],"uri":"origin/3185f8ad3e8d4b8ba88309b9688eec88","height":360},"cover_image_url":"","activity":{},"group_id":6398726567833911553,"is_video":1,"display_type":0},"comments":[],"type":1,"display_time":1.491309288E9,"online_time":1.491309288E9},{"group":{"user":{"user_id":6275816262,"name":"忘记了84224116","followings":0,"user_verified":false,"ugc_count":4,"avatar_url":"http://p1.pstatp.com/medium/1a6e0002bc116f51985f","followers":11,"is_following":false,"is_pro_user":false},"text":"如果用你10斤的肉换你母亲5年寿命，你愿意吗:","dislike_reason":[{"type":2,"id":59,"title":"吧:突然觉得哪里不对"},{"type":4,"id":0,"title":"内容重复"},{"type":3,"id":6275816262,"title":"作者:忘记了84224116"}],"create_time":1491231734,"id":58810923734,"favorite_count":133,"go_detail_count":365400,"user_favorite":0,"share_type":1,"max_screen_width_percent":0.6,"is_can_share":1,"category_type":1,"share_url":"http://m.neihanshequ.com/share/group/58810923734/?iid=0&app=joke_essay","label":4,"content":"如果用你10斤的肉换你母亲5年寿命，你愿意吗:","comment_count":10571,"id_str":"58810923734","media_type":1,"share_count":11804,"type":3,"status":103,"has_comments":0,"large_image":{"width":200,"r_height":205,"r_width":200,"url_list":[{"url":"http://p3.pstatp.com/large/194500032787449ba7d2"},{"url":"http://pb2.pstatp.com/large/194500032787449ba7d2"},{"url":"http://pb3.pstatp.com/large/194500032787449ba7d2"}],"uri":"large/194500032787449ba7d2","height":205},"user_bury":0,"status_desc":"已发表","display_type":0,"user_digg":0,"online_time":1491231734,"category_name":"突然觉得哪里不对","category_visible":true,"bury_count":1303,"is_anonymous":false,"repin_count":133,"min_screen_width_percent":0.167,"digg_count":67213,"has_hot_comments":1,"allow_dislike":true,"image_status":1,"user_repin":0,"activity":{},"group_id":58810923734,"middle_image":{"width":202,"r_height":207,"r_width":202,"url_list":[{"url":"http://p3.pstatp.com/w202/194500032787449ba7d2"},{"url":"http://pb2.pstatp.com/w202/194500032787449ba7d2"},{"url":"http://pb3.pstatp.com/w202/194500032787449ba7d2"}],"uri":"w202/194500032787449ba7d2","height":207},"category_id":59},"comments":[],"type":1,"display_time":1.491309287E9,"online_time":1.491309287E9},{"group":{"user":{"user_id":4058085022,"name":"远离喧嚣还我初心","followings":0,"user_verified":false,"ugc_count":4922,"avatar_url":"http://p1.pstatp.com/medium/18a40018169289a7e2c0","followers":59420,"is_following":false,"is_pro_user":false},"text":"","dislike_reason":[{"type":2,"id":2,"title":"吧:搞笑囧图"},{"type":4,"id":0,"title":"内容重复"},{"type":3,"id":4058085022,"title":"作者:远离喧嚣还我初心"}],"create_time":1491289641,"id":58831730592,"favorite_count":472,"go_detail_count":248632,"user_favorite":0,"share_type":1,"max_screen_width_percent":0.6,"is_can_share":1,"category_type":1,"share_url":"http://m.neihanshequ.com/share/group/58831730592/?iid=0&app=joke_essay","label":4,"content":"","comment_count":2379,"id_str":"58831730592","media_type":1,"share_count":2934,"type":3,"status":110,"has_comments":0,"large_image":{"width":148,"r_height":2000,"r_width":148,"url_list":[{"url":"http://p3.pstatp.com/large/1b1100013b3f122ec29b"},{"url":"http://pb2.pstatp.com/large/1b1100013b3f122ec29b"},{"url":"http://pb3.pstatp.com/large/1b1100013b3f122ec29b"}],"uri":"large/1b1100013b3f122ec29b","height":2000},"user_bury":0,"status_desc":"已发表","display_type":0,"user_digg":0,"online_time":1491289641,"category_name":"搞笑囧图","category_visible":true,"bury_count":545,"is_anonymous":false,"repin_count":472,"min_screen_width_percent":0.167,"digg_count":33888,"has_hot_comments":1,"allow_dislike":true,"image_status":1,"user_repin":0,"activity":{},"group_id":58831730592,"middle_image":{"width":202,"r_height":2729,"r_width":202,"url_list":[{"url":"http://p3.pstatp.com/w202/1b1100013b3f122ec29b"},{"url":"http://pb2.pstatp.com/w202/1b1100013b3f122ec29b"},{"url":"http://pb3.pstatp.com/w202/1b1100013b3f122ec29b"}],"uri":"w202/1b1100013b3f122ec29b","height":2729},"category_id":2},"comments":[],"type":1,"display_time":1.491309286E9,"online_time":1.491309286E9},{"group":{"360p_video":{"width":320,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=07b6f2c7108548aab9daa09e83678ec1&quality=360p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=07b6f2c7108548aab9daa09e83678ec1&quality=360p&line=1&is_gif=0&device_platform="}],"uri":"360p/07b6f2c7108548aab9daa09e83678ec1","height":320},"mp4_url":"http://ic.snssdk.com/neihan/video/playback/?video_id=07b6f2c7108548aab9daa09e83678ec1&quality=480p&line=0&is_gif=0&device_platform=.mp4","text":"哥就是这样上去的","720p_video":{"width":320,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=07b6f2c7108548aab9daa09e83678ec1&quality=720p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=07b6f2c7108548aab9daa09e83678ec1&quality=720p&line=1&is_gif=0&device_platform="}],"uri":"720p/07b6f2c7108548aab9daa09e83678ec1","height":320},"digg_count":52856,"duration":8.08,"480p_video":{"width":320,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=07b6f2c7108548aab9daa09e83678ec1&quality=480p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=07b6f2c7108548aab9daa09e83678ec1&quality=480p&line=1&is_gif=0&device_platform="}],"uri":"480p/07b6f2c7108548aab9daa09e83678ec1","height":320},"create_time":1489143408,"keywords":"","id":57583462975,"favorite_count":9497,"go_detail_count":366215,"m3u8_url":"","large_cover":{"url_list":[{"url":"http://p3.pstatp.com/large/18b3000a60534f002915"},{"url":"http://pb2.pstatp.com/large/18b3000a60534f002915"},{"url":"http://pb3.pstatp.com/large/18b3000a60534f002915"}],"uri":"large/18b3000a60534f002915"},"user_favorite":0,"share_type":1,"title":"","user":{"user_id":3197541539,"name":"bab8486253","followings":0,"user_verified":false,"ugc_count":6,"avatar_url":"http://p1.pstatp.com/medium/674/8496468502","followers":271,"is_following":false,"is_pro_user":false},"is_can_share":1,"category_type":1,"share_url":"http://m.neihanshequ.com/share/group/57583462975/?iid=0&app=joke_essay","label":1,"content":"哥就是这样上去的","video_height":320,"comment_count":3650,"cover_image_uri":"18b3000a60534f002915","id_str":"57583462975","media_type":3,"share_count":44511,"type":2,"category_id":65,"status":112,"has_comments":1,"publish_time":"","user_bury":0,"status_desc":"热门投稿","dislike_reason":[{"type":1,"id":302,"title":"糗人糗事"},{"type":1,"id":340,"title":"猎奇"},{"type":2,"id":65,"title":"吧:搞笑视频"},{"type":4,"id":0,"title":"内容重复"},{"type":3,"id":3197541539,"title":"作者:bab8486253"}],"play_count":2361954,"user_repin":0,"medium_cover":{"url_list":[{"url":"http://p3.pstatp.com/w202/18b3000a60534f002915"},{"url":"http://pb2.pstatp.com/w202/18b3000a60534f002915"},{"url":"http://pb3.pstatp.com/w202/18b3000a60534f002915"}],"uri":"medium/18b3000a60534f002915"},"user_digg":0,"video_width":320,"online_time":1489143408,"category_name":"搞笑视频","flash_url":"","category_visible":true,"bury_count":902,"is_anonymous":false,"repin_count":9497,"video_id":"07b6f2c7108548aab9daa09e83678ec1","uri":"07b6f2c7108548aab9daa09e83678ec1","is_public_url":1,"has_hot_comments":1,"allow_dislike":true,"origin_video":{"width":320,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=07b6f2c7108548aab9daa09e83678ec1&quality=origin&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=07b6f2c7108548aab9daa09e83678ec1&quality=origin&line=1&is_gif=0&device_platform="}],"uri":"origin/07b6f2c7108548aab9daa09e83678ec1","height":320},"cover_image_url":"","activity":{},"group_id":57583462975,"is_video":1,"display_type":0},"comments":[{"text":"卧槽，你既然有教程","create_time":1489153730,"user_verified":false,"user_bury":0,"user_id":6130850998,"bury_count":0,"share_url":"http://m.neihanshequ.com/share/group/57583462975/?comment_id=57596182998","id":57596182998,"platform":"feifei","is_digg":0,"user_name":"冷暖浅深","user_profile_image_url":"http://p3.pstatp.com/thumb/78f0006e0ba8ebb9e11","status":5,"description":"","comment_id":57596182998,"user_digg":0,"user_profile_url":"","share_type":1,"digg_count":55473,"is_pro_user":false,"platform_id":"feifei","avatar_url":"http://p3.pstatp.com/thumb/78f0006e0ba8ebb9e11","group_id":57583462975},{"text":"那个说给十万的人呢","create_time":1489154533,"user_verified":false,"user_bury":0,"user_id":4476650599,"bury_count":0,"share_url":"http://m.neihanshequ.com/share/group/57583462975/?comment_id=57597041581","id":57597041581,"platform":"feifei","is_digg":0,"user_name":"Darker4...","user_profile_image_url":"http://p1.pstatp.com/thumb/4114/7604433156","status":5,"description":"","comment_id":57597041581,"user_digg":0,"user_profile_url":"","share_type":1,"digg_count":41119,"is_pro_user":false,"platform_id":"feifei","avatar_url":"http://p1.pstatp.com/thumb/4114/7604433156","group_id":57583462975}],"type":1,"display_time":1.491309285E9,"online_time":1.491309285E9},{"group":{"user":{"user_id":4014662013,"name":"束槑","followings":0,"user_verified":false,"ugc_count":89,"avatar_url":"http://p3.pstatp.com/medium/ef4001108e73c0b3065","followers":34,"is_following":false,"is_pro_user":false},"text":"蛮好玩的样子","dislike_reason":[{"type":1,"id":340,"title":"猎奇"},{"type":2,"id":10,"title":"吧:爆笑GIF"},{"type":4,"id":0,"title":"内容重复"},{"type":3,"id":4014662013,"title":"作者:束槑"}],"create_time":1490977394,"id":58743528319,"favorite_count":561,"go_detail_count":90718,"user_favorite":0,"share_type":1,"max_screen_width_percent":0.6,"is_can_share":1,"category_type":1,"share_url":"http://m.neihanshequ.com/share/group/58743528319/?iid=0&app=joke_essay","label":1,"content":"蛮好玩的样子","comment_count":699,"id_str":"58743528319","media_type":2,"share_count":1007,"type":3,"status":112,"has_comments":1,"large_image":{"width":360,"r_height":202,"r_width":360,"url_list":[{"url":"http://p2.pstatp.com/large/19400004420102f897f6"},{"url":"http://pb3.pstatp.com/large/19400004420102f897f6"},{"url":"http://pb3.pstatp.com/large/19400004420102f897f6"}],"uri":"large/19400004420102f897f6","height":202},"user_bury":0,"status_desc":"热门投稿","display_type":0,"is_gif":1,"user_digg":0,"online_time":1490977394,"category_name":"爆笑GIF","category_visible":true,"bury_count":280,"is_anonymous":false,"repin_count":561,"min_screen_width_percent":0.167,"digg_count":19363,"gifvideo":{"360p_video":{"width":360,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=564bc3398d9c44888fad69fe34c97009&quality=360p&line=0&is_gif=1&device_platform=None"},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=564bc3398d9c44888fad69fe34c97009&quality=360p&line=1&is_gif=1&device_platform=None"}],"uri":"360p/564bc3398d9c44888fad69fe34c97009","height":202},"origin_video":{"width":360,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=564bc3398d9c44888fad69fe34c97009&quality=origin&line=0&is_gif=1&device_platform=None"},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=564bc3398d9c44888fad69fe34c97009&quality=origin&line=1&is_gif=1&device_platform=None"}],"uri":"origin/564bc3398d9c44888fad69fe34c97009","height":202},"video_id":"564bc3398d9c44888fad69fe34c97009","720p_video":{"width":360,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=564bc3398d9c44888fad69fe34c97009&quality=720p&line=0&is_gif=1&device_platform=None"},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=564bc3398d9c44888fad69fe34c97009&quality=720p&line=1&is_gif=1&device_platform=None"}],"uri":"720p/564bc3398d9c44888fad69fe34c97009","height":202},"mp4_url":"http://ic.snssdk.com/neihan/video/playback/?video_id=564bc3398d9c44888fad69fe34c97009&quality=480p&line=0&is_gif=1&device_platform=None.mp4","video_height":202,"480p_video":{"width":360,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=564bc3398d9c44888fad69fe34c97009&quality=480p&line=0&is_gif=1&device_platform=None"},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=564bc3398d9c44888fad69fe34c97009&quality=480p&line=1&is_gif=1&device_platform=None"}],"uri":"480p/564bc3398d9c44888fad69fe34c97009","height":202},"cover_image_uri":"1a8f00083bf70f88d4bd","duration":50,"video_width":360},"has_hot_comments":1,"allow_dislike":true,"image_status":1,"user_repin":0,"activity":{},"group_id":58743528319,"middle_image":{"width":202,"r_height":113,"r_width":202,"url_list":[{"url":"http://p2.pstatp.com/w202/19400004420102f897f6"},{"url":"http://pb3.pstatp.com/w202/19400004420102f897f6"},{"url":"http://pb3.pstatp.com/w202/19400004420102f897f6"}],"uri":"w202/19400004420102f897f6","height":113},"category_id":10},"comments":[{"text":"这不是我们能玩的","create_time":1491208556,"user_verified":false,"user_bury":0,"user_id":52570441759,"bury_count":0,"share_url":"http://m.neihanshequ.com/share/group/58743528319/?comment_id=58803372222","id":58803372222,"platform":"feifei","is_digg":0,"user_name":"调先生","user_profile_image_url":"http://q.qlogo.cn/qqapp/100290348/A5185146A183B78E568FDDEC29A52363/100","status":5,"description":"","comment_id":58803372222,"user_digg":0,"user_profile_url":"","share_type":1,"digg_count":25424,"is_pro_user":false,"platform_id":"feifei","avatar_url":"http://q.qlogo.cn/qqapp/100290348/A5185146A183B78E568FDDEC29A52363/100","group_id":58743528319}],"type":1,"display_time":1.491309284E9,"online_time":1.491309284E9},{"group":{"user":{"user_id":6189923900,"name":"张家长李家短你的选择不会错","followings":0,"user_verified":false,"ugc_count":23,"avatar_url":"http://p3.pstatp.com/medium/17820013644bc2749eba","followers":12,"is_following":false,"is_pro_user":false},"text":"有贵阳长友可以进来","dislike_reason":[{"type":2,"id":2,"title":"吧:搞笑囧图"},{"type":4,"id":0,"title":"内容重复"},{"type":3,"id":6189923900,"title":"作者:张家长李家短你的选择不会错"}],"create_time":1491298748,"id":58834419512,"favorite_count":0,"go_detail_count":924,"user_favorite":0,"share_type":1,"max_screen_width_percent":0.6,"is_can_share":1,"category_type":1,"share_url":"http://m.neihanshequ.com/share/group/58834419512/?iid=0&app=joke_essay","label":4,"content":"有贵阳长友可以进来","comment_count":123,"id_str":"58834419512","media_type":1,"share_count":151,"type":3,"status":102,"has_comments":0,"large_image":{"width":1440,"r_height":1138,"r_width":640,"url_list":[{"url":"http://p3.pstatp.com/large/1b10000146ff5d3f43ef"},{"url":"http://pb2.pstatp.com/large/1b10000146ff5d3f43ef"},{"url":"http://pb3.pstatp.com/large/1b10000146ff5d3f43ef"}],"uri":"large/1b10000146ff5d3f43ef","height":2560},"user_bury":0,"status_desc":"已发表","display_type":0,"user_digg":0,"online_time":1491298748,"category_name":"搞笑囧图","category_visible":true,"bury_count":10,"is_anonymous":false,"repin_count":0,"min_screen_width_percent":0.167,"digg_count":100,"has_hot_comments":0,"allow_dislike":true,"image_status":1,"user_repin":0,"activity":{},"group_id":58834419512,"middle_image":{"width":202,"r_height":359,"r_width":202,"url_list":[{"url":"http://p3.pstatp.com/w202/1b10000146ff5d3f43ef"},{"url":"http://pb2.pstatp.com/w202/1b10000146ff5d3f43ef"},{"url":"http://pb3.pstatp.com/w202/1b10000146ff5d3f43ef"}],"uri":"w202/1b10000146ff5d3f43ef","height":359},"category_id":2},"comments":[],"type":1,"display_time":1.491309283E9,"online_time":1.491309283E9},{"group":{"user":{"user_id":50363120687,"name":"谁S","followings":0,"user_verified":false,"ugc_count":16,"avatar_url":"http://p3.pstatp.com/medium/1353000f32b6401d4b6b","followers":267,"is_following":false,"is_pro_user":false},"text":"留下你的眼\u2022ᴗ\u2022","dislike_reason":[{"type":2,"id":104,"title":"吧:聊电影"},{"type":4,"id":0,"title":"内容重复"},{"type":3,"id":50363120687,"title":"作者:谁S"}],"create_time":1491099252,"id":58776036354,"favorite_count":23806,"go_detail_count":64293,"user_favorite":0,"share_type":1,"max_screen_width_percent":0.6,"is_can_share":1,"category_type":1,"share_url":"http://m.neihanshequ.com/share/group/58776036354/?iid=0&app=joke_essay","label":4,"content":"留下你的眼\u2022ᴗ\u2022","comment_count":7839,"id_str":"58776036354","media_type":1,"share_count":14913,"type":3,"status":103,"has_comments":0,"large_image":{"width":440,"r_height":1806,"r_width":440,"url_list":[{"url":"http://p3.pstatp.com/large/1b10000039436580ff81"},{"url":"http://pb2.pstatp.com/large/1b10000039436580ff81"},{"url":"http://pb3.pstatp.com/large/1b10000039436580ff81"}],"uri":"large/1b10000039436580ff81","height":1806},"user_bury":0,"status_desc":"已发表","display_type":0,"user_digg":0,"online_time":1491099252,"category_name":"聊电影","category_visible":true,"bury_count":254,"is_anonymous":false,"repin_count":23806,"min_screen_width_percent":0.167,"digg_count":4913,"has_hot_comments":1,"allow_dislike":true,"image_status":1,"user_repin":0,"activity":{},"group_id":58776036354,"middle_image":{"width":202,"r_height":829,"r_width":202,"url_list":[{"url":"http://p3.pstatp.com/w202/1b10000039436580ff81"},{"url":"http://pb2.pstatp.com/w202/1b10000039436580ff81"},{"url":"http://pb3.pstatp.com/w202/1b10000039436580ff81"}],"uri":"w202/1b10000039436580ff81","height":829},"category_id":104},"comments":[],"type":1,"display_time":1.491309282E9,"online_time":1.491309282E9},{"group":{"text":"大保健点到了我大学时疯狂追三年的女神，花了2000狠狠的发泄一夜！\u2026第二天早上，我愤然问道：当年对你是万般讨好，关心倍至，每月举债请你吃饭游玩娱乐，你始终却对我爱搭不理的，还2000包夜，现在后悔了吧？\u2026她笑道：你在这花2000包夜是因为我的职业，只要出了这个房门，你就花再多钱，我还是像以前一样不会搭理你\u2026\u2026\u2026","dislike_reason":[{"type":1,"id":301,"title":"成人"},{"type":1,"id":302,"title":"糗人糗事"},{"type":2,"id":1,"title":"吧:内涵段子"},{"type":4,"id":0,"title":"内容重复"},{"type":3,"id":2767940456,"title":"作者:百里媳妇"}],"create_time":1475051478,"id":51159139141,"favorite_count":5161,"go_detail_count":1037544,"user_favorite":0,"share_type":1,"user":{"user_id":2767940456,"name":"百里媳妇","followings":0,"user_verified":false,"ugc_count":1437,"avatar_url":"http://p3.pstatp.com/medium/d2a000c63e05781cd2a","followers":37191,"is_following":false,"is_pro_user":true},"is_can_share":1,"category_type":1,"share_url":"http://m.neihanshequ.com/share/group/51159139141/?iid=0&app=joke_essay","label":1,"content":"大保健点到了我大学时疯狂追三年的女神，花了2000狠狠的发泄一夜！\u2026第二天早上，我愤然问道：当年对你是万般讨好，关心倍至，每月举债请你吃饭游玩娱乐，你始终却对我爱搭不理的，还2000包夜，现在后悔了吧？\u2026她笑道：你在这花2000包夜是因为我的职业，只要出了这个房门，你就花再多钱，我还是像以前一样不会搭理你\u2026\u2026\u2026","comment_count":8487,"id_str":"51159139141","media_type":0,"share_count":12421,"type":3,"status":112,"has_comments":1,"user_bury":0,"status_desc":"热门投稿","display_type":0,"user_digg":0,"online_time":1475051478,"category_name":"内涵段子","category_visible":true,"bury_count":8997,"is_anonymous":false,"repin_count":5161,"digg_count":189565,"has_hot_comments":1,"allow_dislike":true,"user_repin":0,"activity":{},"group_id":51159139141,"category_id":1},"comments":[{"text":"市场经济让你把不可能成为现实了","create_time":1475053050,"user_verified":false,"user_bury":0,"user_id":50019817000,"bury_count":0,"share_url":"http://m.neihanshequ.com/share/group/51159139141/?comment_id=51159609682","id":51159609682,"platform":"feifei","is_digg":0,"user_name":"英昂百魅","user_profile_image_url":"http://p1.pstatp.com/thumb/ef500131f578024285b","status":5,"description":"拿出自信，做回自己，在最美的季节绽放。每个人都可以做一个写手，欢迎关注","comment_id":51159609682,"user_digg":0,"user_profile_url":"","share_type":2,"digg_count":109119,"is_pro_user":true,"platform_id":"feifei","avatar_url":"http://p1.pstatp.com/thumb/ef500131f578024285b","group_id":51159139141}],"type":1,"display_time":1.491309281E9,"online_time":1.491309281E9},{"group":{"user":{"user_id":1375845416,"name":"否极泰来","followings":0,"user_verified":false,"ugc_count":24,"avatar_url":"http://p3.pstatp.com/medium/16ab000126cc6bd6a355","followers":15,"is_following":false,"is_pro_user":false},"text":"","dislike_reason":[{"type":1,"id":319,"title":"恶搞"},{"type":2,"id":2,"title":"吧:搞笑囧图"},{"type":4,"id":0,"title":"内容重复"},{"type":3,"id":1375845416,"title":"作者:否极泰来"}],"create_time":1488796178,"id":57229386602,"favorite_count":475,"go_detail_count":77578,"user_favorite":0,"share_type":1,"max_screen_width_percent":0.6,"is_can_share":1,"category_type":1,"share_url":"http://m.neihanshequ.com/share/group/57229386602/?iid=0&app=joke_essay","label":1,"content":"","comment_count":372,"id_str":"57229386602","media_type":1,"share_count":775,"type":3,"status":112,"has_comments":1,"large_image":{"width":353,"r_height":1600,"r_width":353,"url_list":[{"url":"http://p3.pstatp.com/large/17fc00043bfbb0f24de8"},{"url":"http://pb2.pstatp.com/large/17fc00043bfbb0f24de8"},{"url":"http://pb3.pstatp.com/large/17fc00043bfbb0f24de8"}],"uri":"large/17fc00043bfbb0f24de8","height":1600},"user_bury":0,"status_desc":"热门投稿","display_type":0,"user_digg":0,"online_time":1488796178,"category_name":"搞笑囧图","category_visible":true,"bury_count":261,"is_anonymous":false,"repin_count":475,"min_screen_width_percent":0.167,"digg_count":26493,"has_hot_comments":0,"allow_dislike":true,"image_status":1,"user_repin":0,"activity":{},"group_id":57229386602,"middle_image":{"width":202,"r_height":915,"r_width":202,"url_list":[{"url":"http://p3.pstatp.com/w202/17fc00043bfbb0f24de8"},{"url":"http://pb2.pstatp.com/w202/17fc00043bfbb0f24de8"},{"url":"http://pb3.pstatp.com/w202/17fc00043bfbb0f24de8"}],"uri":"w202/17fc00043bfbb0f24de8","height":915},"category_id":2},"comments":[{"text":"那你是看不起我赵四了？","create_time":1488864417,"user_verified":false,"user_bury":0,"user_id":3238540909,"bury_count":0,"share_url":"http://m.neihanshequ.com/share/group/57229386602/?comment_id=57299431314","id":57299431314,"platform":"feifei","is_digg":0,"user_name":"日理萬機","user_profile_image_url":"http://p1.pstatp.com/thumb/17820007af6c42ea54d2","status":5,"description":"","comment_id":57299431314,"user_digg":0,"user_profile_url":"","share_type":2,"digg_count":57388,"is_pro_user":false,"platform_id":"feifei","avatar_url":"http://p1.pstatp.com/thumb/17820007af6c42ea54d2","group_id":57229386602},{"text":"听说刘关张桃园结义拜的是关二爷？？","create_time":1488865053,"user_verified":false,"user_bury":0,"user_id":55143701239,"bury_count":0,"share_url":"http://m.neihanshequ.com/share/group/57229386602/?comment_id=57300312488","id":57300312488,"platform":"feifei","is_digg":0,"user_name":"Cc果冻146...","user_profile_image_url":"http://q.qlogo.cn/qqapp/100290348/0A490812C1666BDABF085D7B023449A3/100","status":5,"description":"","comment_id":57300312488,"user_digg":0,"user_profile_url":"","share_type":2,"digg_count":41876,"is_pro_user":false,"platform_id":"feifei","avatar_url":"http://q.qlogo.cn/qqapp/100290348/0A490812C1666BDABF085D7B023449A3/100","group_id":57229386602}],"type":1,"display_time":1.49130928E9,"online_time":1.49130928E9},{"group":{"user":{"user_id":50066656753,"name":"一尾狐有内涵","followings":0,"user_verified":false,"ugc_count":2,"avatar_url":"http://p1.pstatp.com/medium/18a30005ae479bb67e19","followers":108,"is_following":false,"is_pro_user":false},"text":"看懂你就没救了","dislike_reason":[{"type":2,"id":8,"title":"吧:内涵漫画"},{"type":4,"id":0,"title":"内容重复"},{"type":3,"id":50066656753,"title":"作者:一尾狐有内涵"}],"create_time":1489120298,"id":57561872025,"favorite_count":145,"go_detail_count":171851,"user_favorite":0,"share_type":1,"max_screen_width_percent":0.6,"is_can_share":1,"category_type":2,"share_url":"http://m.neihanshequ.com/share/group/57561872025/?iid=0&app=joke_essay","label":4,"content":"看懂你就没救了","comment_count":56,"id_str":"57561872025","media_type":1,"share_count":264,"type":3,"status":103,"has_comments":0,"large_image":{"width":550,"r_height":1539,"r_width":550,"url_list":[{"url":"http://p3.pstatp.com/large/18a700029304a27e66e9"},{"url":"http://pb2.pstatp.com/large/18a700029304a27e66e9"},{"url":"http://pb3.pstatp.com/large/18a700029304a27e66e9"}],"uri":"large/18a700029304a27e66e9","height":1539},"user_bury":0,"status_desc":"已发表","display_type":0,"user_digg":0,"online_time":1489120298,"category_name":"内涵漫画","category_visible":true,"bury_count":426,"is_anonymous":false,"repin_count":145,"min_screen_width_percent":0.167,"digg_count":12299,"has_hot_comments":1,"allow_dislike":true,"image_status":1,"user_repin":0,"activity":{},"group_id":57561872025,"middle_image":{"width":202,"r_height":565,"r_width":202,"url_list":[{"url":"http://p3.pstatp.com/w202/18a700029304a27e66e9"},{"url":"http://pb2.pstatp.com/w202/18a700029304a27e66e9"},{"url":"http://pb3.pstatp.com/w202/18a700029304a27e66e9"}],"uri":"w202/18a700029304a27e66e9","height":565},"category_id":8},"comments":[],"type":1,"display_time":1.491309279E9,"online_time":1.491309279E9},{"group":{"user":{"user_id":3323806282,"name":"小师弟爱小师妹","followings":0,"user_verified":false,"ugc_count":67,"avatar_url":"http://p2.pstatp.com/medium/178000044a103bc3d4fb","followers":3299,"is_following":false,"is_pro_user":false},"text":"猫：老公，要亲亲ლ(°◕\u2035ƹ\u2032◕ლ)么么么么哒人家要醉了","dislike_reason":[{"type":1,"id":349,"title":"猫"},{"type":1,"id":350,"title":"狗"},{"type":2,"id":10,"title":"吧:爆笑GIF"},{"type":4,"id":0,"title":"内容重复"},{"type":3,"id":3323806282,"title":"作者:小师弟爱小师妹"}],"create_time":1486547556,"id":55378573369,"favorite_count":198211,"go_detail_count":82741,"user_favorite":0,"share_type":1,"max_screen_width_percent":0.6,"is_can_share":1,"category_type":1,"share_url":"http://m.neihanshequ.com/share/group/55378573369/?iid=0&app=joke_essay","label":1,"content":"猫：老公，要亲亲ლ(°◕\u2035ƹ\u2032◕ლ)么么么么哒人家要醉了","comment_count":929,"id_str":"55378573369","media_type":2,"share_count":10136,"type":3,"status":112,"has_comments":1,"large_image":{"width":267,"r_height":267,"r_width":267,"url_list":[{"url":"http://p3.pstatp.com/large/16620003c52e0c044d4b"},{"url":"http://pb2.pstatp.com/large/16620003c52e0c044d4b"},{"url":"http://pb3.pstatp.com/large/16620003c52e0c044d4b"}],"uri":"large/16620003c52e0c044d4b","height":267},"user_bury":0,"status_desc":"热门投稿","display_type":0,"is_gif":1,"user_digg":0,"online_time":1486547556,"category_name":"爆笑GIF","category_visible":true,"bury_count":693,"is_anonymous":false,"repin_count":198211,"min_screen_width_percent":0.167,"digg_count":30443,"gifvideo":{"360p_video":{"width":268,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=5ac62fa40129404b9de00c84d47bad38&quality=360p&line=0&is_gif=1&device_platform=None"},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=5ac62fa40129404b9de00c84d47bad38&quality=360p&line=1&is_gif=1&device_platform=None"}],"uri":"360p/5ac62fa40129404b9de00c84d47bad38","height":268},"origin_video":{"width":268,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=5ac62fa40129404b9de00c84d47bad38&quality=origin&line=0&is_gif=1&device_platform=None"},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=5ac62fa40129404b9de00c84d47bad38&quality=origin&line=1&is_gif=1&device_platform=None"}],"uri":"origin/5ac62fa40129404b9de00c84d47bad38","height":268},"video_id":"5ac62fa40129404b9de00c84d47bad38","720p_video":{"width":268,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=5ac62fa40129404b9de00c84d47bad38&quality=720p&line=0&is_gif=1&device_platform=None"},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=5ac62fa40129404b9de00c84d47bad38&quality=720p&line=1&is_gif=1&device_platform=None"}],"uri":"720p/5ac62fa40129404b9de00c84d47bad38","height":268},"mp4_url":"http://ic.snssdk.com/neihan/video/playback/?video_id=5ac62fa40129404b9de00c84d47bad38&quality=480p&line=0&is_gif=1&device_platform=None.mp4","video_height":268,"480p_video":{"width":268,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=5ac62fa40129404b9de00c84d47bad38&quality=480p&line=0&is_gif=1&device_platform=None"},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=5ac62fa40129404b9de00c84d47bad38&quality=480p&line=1&is_gif=1&device_platform=None"}],"uri":"480p/5ac62fa40129404b9de00c84d47bad38","height":268},"cover_image_uri":"16600001d82bed7a0bdc","duration":13.05,"video_width":268},"has_hot_comments":1,"allow_dislike":true,"image_status":1,"user_repin":0,"activity":{},"group_id":55378573369,"middle_image":{"width":202,"r_height":202,"r_width":202,"url_list":[{"url":"http://p3.pstatp.com/w202/16620003c52e0c044d4b"},{"url":"http://pb2.pstatp.com/w202/16620003c52e0c044d4b"},{"url":"http://pb3.pstatp.com/w202/16620003c52e0c044d4b"}],"uri":"w202/16620003c52e0c044d4b","height":202},"category_id":10},"comments":[{"text":"猫：玛德你有口臭","create_time":1486548345,"user_verified":false,"user_bury":0,"user_id":4318136173,"bury_count":0,"share_url":"http://m.neihanshequ.com/share/group/55378573369/?comment_id=55378937465","id":55378937465,"platform":"feifei","is_digg":0,"user_name":"孤傲男神鹏羽","user_profile_image_url":"http://p9.pstatp.com/thumb/1233000354d3ce6ce8dd","status":5,"description":"呵呵呵呵，霍哈哈哈哈，","comment_id":55378937465,"user_digg":0,"user_profile_url":"","share_type":1,"digg_count":43736,"is_pro_user":false,"platform_id":"feifei","avatar_url":"http://p9.pstatp.com/thumb/1233000354d3ce6ce8dd","group_id":55378573369},{"text":"本来今天高高兴兴。。","create_time":1486548517,"user_verified":false,"user_bury":0,"user_id":6051634321,"bury_count":0,"share_url":"http://m.neihanshequ.com/share/group/55378573369/?comment_id=55379135823","id":55379135823,"platform":"feifei","is_digg":0,"user_name":"等150人觉得很赞","user_profile_image_url":"http://p3.pstatp.com/thumb/150d0011339bd5dba9bf","status":5,"description":"","comment_id":55379135823,"user_digg":0,"user_profile_url":"","share_type":1,"digg_count":36411,"is_pro_user":false,"platform_id":"feifei","avatar_url":"http://p3.pstatp.com/thumb/150d0011339bd5dba9bf","group_id":55378573369}],"type":1,"display_time":1.491309278E9,"online_time":1.491309278E9},{"group":{"user":{"user_id":3239851167,"name":"蔑视奥可马的普亨大帝","followings":0,"user_verified":false,"ugc_count":135,"avatar_url":"http://p9.pstatp.com/medium/1471/1727334666","followers":13286,"is_following":false,"is_pro_user":false},"text":"湖南广电封杀韩媒明星:广电封杀韩星、近日有关广电禁韩令、封杀韩国艺人在华活动的消息甚为流传。据了解：总局确有意向针对韩国艺人在华演艺活动进行限制，准确来说应为\u201c调控\u201d，而非封杀，但对近年来来华发展得风生水起的韩国娱乐事业，恐怕将是一记重创。对此，你支持吗？","dislike_reason":[{"type":1,"id":408,"title":"八卦新闻"},{"type":2,"id":79,"title":"吧:热点818"},{"type":4,"id":0,"title":"内容重复"},{"type":3,"id":3239851167,"title":"作者:蔑视奥可马的普亨大帝"}],"create_time":1470201449,"id":50033920818,"favorite_count":32,"go_detail_count":57559,"user_favorite":0,"share_type":1,"max_screen_width_percent":0.6,"is_can_share":1,"category_type":1,"share_url":"http://m.neihanshequ.com/share/group/50033920818/?iid=0&app=joke_essay","label":1,"content":"湖南广电封杀韩媒明星:广电封杀韩星、近日有关广电禁韩令、封杀韩国艺人在华活动的消息甚为流传。据了解：总局确有意向针对韩国艺人在华演艺活动进行限制，准确来说应为\u201c调控\u201d，而非封杀，但对近年来来华发展得风生水起的韩国娱乐事业，恐怕将是一记重创。对此，你支持吗？","comment_count":2391,"id_str":"50033920818","media_type":1,"share_count":3020,"type":3,"status":112,"has_comments":0,"large_image":{"width":333,"r_height":500,"r_width":333,"url_list":[{"url":"http://p3.pstatp.com/large/b3a00031b023ea58ed6"},{"url":"http://pb2.pstatp.com/large/b3a00031b023ea58ed6"},{"url":"http://pb3.pstatp.com/large/b3a00031b023ea58ed6"}],"uri":"large/b3a00031b023ea58ed6","height":500},"user_bury":0,"status_desc":"热门投稿","display_type":0,"user_digg":0,"online_time":1470201449,"category_name":"热点818","category_visible":true,"bury_count":1123,"is_anonymous":false,"repin_count":32,"min_screen_width_percent":0.167,"digg_count":65256,"has_hot_comments":1,"allow_dislike":true,"image_status":1,"user_repin":0,"activity":{},"group_id":50033920818,"middle_image":{"width":202,"r_height":303,"r_width":202,"url_list":[{"url":"http://p3.pstatp.com/w202/b3a00031b023ea58ed6"},{"url":"http://pb2.pstatp.com/w202/b3a00031b023ea58ed6"},{"url":"http://pb3.pstatp.com/w202/b3a00031b023ea58ed6"}],"uri":"w202/b3a00031b023ea58ed6","height":303},"category_id":79},"comments":[],"type":1,"display_time":1.491309277E9,"online_time":1.491309277E9},{"group":{"text":"和大家聊天，我说到：\u201c到什么年龄做什么事，这是社会的公理。比如：22岁要谈恋爱，25岁要结婚，60岁应该退休养老，70岁差不多该死了。。。。\u201d然后我就被他们打了。尤其是那个69岁的大爷，真是老当益壮，下手真狠啊！","dislike_reason":[{"type":2,"id":1,"title":"吧:内涵段子"},{"type":4,"id":0,"title":"内容重复"},{"type":3,"id":52070270436,"title":"作者:隐姓埋名丶宝宝"}],"create_time":1491303693,"id":58836025216,"favorite_count":17,"go_detail_count":1972,"user_favorite":0,"share_type":1,"user":{"user_id":52070270436,"name":"隐姓埋名丶宝宝","followings":0,"user_verified":false,"ugc_count":39,"avatar_url":"http://p3.pstatp.com/medium/16ab00139b43b2018876","followers":213,"is_following":false,"is_pro_user":false},"is_can_share":1,"category_type":1,"share_url":"http://m.neihanshequ.com/share/group/58836025216/?iid=0&app=joke_essay","label":4,"content":"和大家聊天，我说到：\u201c到什么年龄做什么事，这是社会的公理。比如：22岁要谈恋爱，25岁要结婚，60岁应该退休养老，70岁差不多该死了。。。。\u201d然后我就被他们打了。尤其是那个69岁的大爷，真是老当益壮，下手真狠啊！","comment_count":25,"id_str":"58836025216","media_type":0,"share_count":82,"type":3,"status":110,"has_comments":0,"user_bury":0,"status_desc":"已发表","display_type":0,"user_digg":0,"online_time":1491303693,"category_name":"内涵段子","category_visible":true,"bury_count":21,"is_anonymous":false,"repin_count":17,"digg_count":733,"has_hot_comments":1,"allow_dislike":true,"user_repin":0,"activity":{},"group_id":58836025216,"category_id":1},"comments":[],"type":1,"display_time":1.491309276E9,"online_time":1.491309276E9},{"group":{"360p_video":{"width":640,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=301da4e7b50240249e75bfaf881c6519&quality=360p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=301da4e7b50240249e75bfaf881c6519&quality=360p&line=1&is_gif=0&device_platform="}],"uri":"360p/301da4e7b50240249e75bfaf881c6519","height":360},"mp4_url":"http://ic.snssdk.com/neihan/video/playback/?video_id=301da4e7b50240249e75bfaf881c6519&quality=480p&line=0&is_gif=0&device_platform=.mp4","text":"看完想认岳父的有多少？","720p_video":{"width":640,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=301da4e7b50240249e75bfaf881c6519&quality=720p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=301da4e7b50240249e75bfaf881c6519&quality=720p&line=1&is_gif=0&device_platform="}],"uri":"720p/301da4e7b50240249e75bfaf881c6519","height":360},"digg_count":8292,"duration":188.32,"480p_video":{"width":640,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=301da4e7b50240249e75bfaf881c6519&quality=480p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=301da4e7b50240249e75bfaf881c6519&quality=480p&line=1&is_gif=0&device_platform="}],"uri":"480p/301da4e7b50240249e75bfaf881c6519","height":360},"create_time":1491300181,"keywords":"","id":58835051490,"favorite_count":1775,"go_detail_count":106969,"m3u8_url":"","large_cover":{"url_list":[{"url":"http://p1.pstatp.com/large/1b03000648d197a0c25a"},{"url":"http://pb3.pstatp.com/large/1b03000648d197a0c25a"},{"url":"http://pb3.pstatp.com/large/1b03000648d197a0c25a"}],"uri":"large/1b03000648d197a0c25a"},"user_favorite":0,"share_type":1,"title":"","user":{"user_id":6966301802,"name":"-消逝的温柔-","followings":0,"user_verified":false,"ugc_count":2417,"avatar_url":"http://p1.pstatp.com/medium/16ab0005ce585ca36c06","followers":101158,"is_following":false,"is_pro_user":false},"is_can_share":1,"category_type":1,"share_url":"http://m.neihanshequ.com/share/group/58835051490/?iid=0&app=joke_essay","label":4,"content":"看完想认岳父的有多少？","video_height":360,"comment_count":713,"cover_image_uri":"1b03000648d197a0c25a","id_str":"58835051490","media_type":3,"share_count":962,"type":2,"category_id":104,"status":110,"has_comments":0,"publish_time":"","user_bury":0,"status_desc":"已发表","dislike_reason":[{"type":1,"id":459,"title":"电影"},{"type":2,"id":104,"title":"吧:聊电影"},{"type":4,"id":0,"title":"内容重复"},{"type":3,"id":6966301802,"title":"作者:-消逝的温柔-"}],"play_count":343801,"user_repin":0,"medium_cover":{"url_list":[{"url":"http://p1.pstatp.com/w202/1b03000648d197a0c25a"},{"url":"http://pb3.pstatp.com/w202/1b03000648d197a0c25a"},{"url":"http://pb3.pstatp.com/w202/1b03000648d197a0c25a"}],"uri":"medium/1b03000648d197a0c25a"},"user_digg":0,"video_width":640,"online_time":1491300181,"category_name":"聊电影","flash_url":"","category_visible":true,"bury_count":387,"is_anonymous":false,"repin_count":1775,"video_id":"301da4e7b50240249e75bfaf881c6519","uri":"301da4e7b50240249e75bfaf881c6519","is_public_url":1,"has_hot_comments":1,"allow_dislike":true,"origin_video":{"width":640,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=301da4e7b50240249e75bfaf881c6519&quality=origin&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=301da4e7b50240249e75bfaf881c6519&quality=origin&line=1&is_gif=0&device_platform="}],"uri":"origin/301da4e7b50240249e75bfaf881c6519","height":360},"cover_image_url":"","activity":{},"group_id":58835051490,"is_video":1,"display_type":0},"comments":[],"type":1,"display_time":1.491309275E9,"online_time":1.491309275E9},{"group":{"360p_video":{"width":360,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=e92a7495fa194a7c85c88198f7d1bca2&quality=360p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=e92a7495fa194a7c85c88198f7d1bca2&quality=360p&line=1&is_gif=0&device_platform="}],"uri":"360p/e92a7495fa194a7c85c88198f7d1bca2","height":268},"mp4_url":"http://ic.snssdk.com/neihan/video/playback/?video_id=e92a7495fa194a7c85c88198f7d1bca2&quality=480p&line=0&is_gif=0&device_platform=.mp4","text":"","720p_video":{"width":360,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=e92a7495fa194a7c85c88198f7d1bca2&quality=720p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=e92a7495fa194a7c85c88198f7d1bca2&quality=720p&line=1&is_gif=0&device_platform="}],"uri":"720p/e92a7495fa194a7c85c88198f7d1bca2","height":268},"digg_count":54008,"duration":296.44,"480p_video":{"width":360,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=e92a7495fa194a7c85c88198f7d1bca2&quality=480p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=e92a7495fa194a7c85c88198f7d1bca2&quality=480p&line=1&is_gif=0&device_platform="}],"uri":"480p/e92a7495fa194a7c85c88198f7d1bca2","height":268},"create_time":1490274296,"keywords":"","id":58523753775,"favorite_count":11033,"go_detail_count":218274,"m3u8_url":"","large_cover":{"url_list":[{"url":"http://p3.pstatp.com/large/19cd0004e32333280356"},{"url":"http://pb2.pstatp.com/large/19cd0004e32333280356"},{"url":"http://pb3.pstatp.com/large/19cd0004e32333280356"}],"uri":"large/19cd0004e32333280356"},"user_favorite":0,"share_type":1,"title":"","user":{"user_id":4024156268,"name":"萌萝莉莎","followings":0,"user_verified":false,"ugc_count":445,"avatar_url":"http://p9.pstatp.com/medium/2460/3896551156","followers":15890,"is_following":false,"is_pro_user":false},"is_can_share":1,"category_type":1,"share_url":"http://m.neihanshequ.com/share/group/58523753775/?iid=0&app=joke_essay","label":1,"content":"","video_height":268,"comment_count":880,"cover_image_uri":"19cd0004e32333280356","id_str":"58523753775","media_type":3,"share_count":3802,"type":2,"category_id":65,"status":112,"has_comments":1,"publish_time":"","user_bury":0,"status_desc":"热门投稿","dislike_reason":[{"type":1,"id":339,"title":"搞笑"},{"type":2,"id":65,"title":"吧:搞笑视频"},{"type":4,"id":0,"title":"内容重复"},{"type":3,"id":4024156268,"title":"作者:萌萝莉莎"}],"play_count":823009,"user_repin":0,"medium_cover":{"url_list":[{"url":"http://p3.pstatp.com/w202/19cd0004e32333280356"},{"url":"http://pb2.pstatp.com/w202/19cd0004e32333280356"},{"url":"http://pb3.pstatp.com/w202/19cd0004e32333280356"}],"uri":"medium/19cd0004e32333280356"},"user_digg":0,"video_width":360,"online_time":1490274296,"category_name":"搞笑视频","flash_url":"","category_visible":true,"bury_count":793,"is_anonymous":false,"repin_count":11033,"video_id":"e92a7495fa194a7c85c88198f7d1bca2","uri":"e92a7495fa194a7c85c88198f7d1bca2","is_public_url":1,"has_hot_comments":1,"allow_dislike":true,"origin_video":{"width":360,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=e92a7495fa194a7c85c88198f7d1bca2&quality=origin&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=e92a7495fa194a7c85c88198f7d1bca2&quality=origin&line=1&is_gif=0&device_platform="}],"uri":"origin/e92a7495fa194a7c85c88198f7d1bca2","height":268},"cover_image_url":"","activity":{},"group_id":58523753775,"is_video":1,"display_type":0},"comments":[{"text":"终于，，最好的演员和最好的主持人要结合了吗！   咦，不对，，我为什么要用结合这个词啊？","create_time":1490282334,"user_verified":false,"user_bury":0,"user_id":3580784127,"bury_count":0,"share_url":"http://m.neihanshequ.com/share/group/58523753775/?comment_id=58528613928","id":58528613928,"platform":"feifei","is_digg":0,"user_name":"丶羞涩小男人1","user_profile_image_url":"http://p2.pstatp.com/thumb/6816/3308270881","status":5,"description":"2016还是简单的一个人，，简单的一个人，，一个人，，，个人，，人！！！","comment_id":58528613928,"user_digg":0,"user_profile_url":"","share_type":1,"digg_count":36162,"is_pro_user":false,"platform_id":"feifei","avatar_url":"http://p2.pstatp.com/thumb/6816/3308270881","group_id":58523753775}],"type":1,"display_time":1.491309274E9,"online_time":1.491309274E9},{"group":{"user":{"user_id":3232360555,"name":"可爱的傻妞是你曾经的爱-","followings":0,"user_verified":false,"ugc_count":4910,"avatar_url":"http://p1.pstatp.com/medium/150c0013f67c16355034","followers":167443,"is_following":false,"is_pro_user":false},"text":"","dislike_reason":[{"type":1,"id":339,"title":"搞笑"},{"type":2,"id":10,"title":"吧:爆笑GIF"},{"type":4,"id":0,"title":"内容重复"},{"type":3,"id":3232360555,"title":"作者:可爱的傻妞是你曾经的爱-"}],"create_time":1490270861,"id":58522228033,"favorite_count":2498,"go_detail_count":249962,"user_favorite":0,"share_type":1,"max_screen_width_percent":0.6,"is_can_share":1,"category_type":1,"share_url":"http://m.neihanshequ.com/share/group/58522228033/?iid=0&app=joke_essay","label":1,"content":"","comment_count":580,"id_str":"58522228033","media_type":2,"share_count":2616,"type":3,"status":112,"has_comments":1,"large_image":{"width":360,"r_height":259,"r_width":360,"url_list":[{"url":"http://p3.pstatp.com/large/193d0003b1e9c58bf2e9"},{"url":"http://pb2.pstatp.com/large/193d0003b1e9c58bf2e9"},{"url":"http://pb3.pstatp.com/large/193d0003b1e9c58bf2e9"}],"uri":"large/193d0003b1e9c58bf2e9","height":259},"user_bury":0,"status_desc":"热门投稿","display_type":0,"is_gif":1,"user_digg":0,"online_time":1490270861,"category_name":"爆笑GIF","category_visible":true,"bury_count":590,"is_anonymous":false,"repin_count":2498,"min_screen_width_percent":0.167,"digg_count":45585,"gifvideo":{"360p_video":{"width":360,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=3975147381e94f5ab7937ff599a1b1a6&quality=360p&line=0&is_gif=1&device_platform=None"},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=3975147381e94f5ab7937ff599a1b1a6&quality=360p&line=1&is_gif=1&device_platform=None"}],"uri":"360p/3975147381e94f5ab7937ff599a1b1a6","height":260},"origin_video":{"width":360,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=3975147381e94f5ab7937ff599a1b1a6&quality=origin&line=0&is_gif=1&device_platform=None"},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=3975147381e94f5ab7937ff599a1b1a6&quality=origin&line=1&is_gif=1&device_platform=None"}],"uri":"origin/3975147381e94f5ab7937ff599a1b1a6","height":260},"video_id":"3975147381e94f5ab7937ff599a1b1a6","720p_video":{"width":360,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=3975147381e94f5ab7937ff599a1b1a6&quality=720p&line=0&is_gif=1&device_platform=None"},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=3975147381e94f5ab7937ff599a1b1a6&quality=720p&line=1&is_gif=1&device_platform=None"}],"uri":"720p/3975147381e94f5ab7937ff599a1b1a6","height":260},"mp4_url":"http://ic.snssdk.com/neihan/video/playback/?video_id=3975147381e94f5ab7937ff599a1b1a6&quality=480p&line=0&is_gif=1&device_platform=None.mp4","video_height":260,"480p_video":{"width":360,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=3975147381e94f5ab7937ff599a1b1a6&quality=480p&line=0&is_gif=1&device_platform=None"},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=3975147381e94f5ab7937ff599a1b1a6&quality=480p&line=1&is_gif=1&device_platform=None"}],"uri":"480p/3975147381e94f5ab7937ff599a1b1a6","height":260},"cover_image_uri":"19d0000261a2d976ffe5","duration":12.1,"video_width":360},"has_hot_comments":1,"allow_dislike":true,"image_status":1,"user_repin":0,"activity":{},"group_id":58522228033,"middle_image":{"width":202,"r_height":145,"r_width":202,"url_list":[{"url":"http://p3.pstatp.com/w202/193d0003b1e9c58bf2e9"},{"url":"http://pb2.pstatp.com/w202/193d0003b1e9c58bf2e9"},{"url":"http://pb3.pstatp.com/w202/193d0003b1e9c58bf2e9"}],"uri":"w202/193d0003b1e9c58bf2e9","height":145},"category_id":10},"comments":[{"text":"别抓我，我小...肉少...还有毛...","create_time":1490274380,"user_verified":false,"user_bury":0,"user_id":3232360555,"bury_count":0,"share_url":"http://m.neihanshequ.com/share/group/58522228033/?comment_id=58523637685","id":58523637685,"platform":"feifei","is_digg":0,"user_name":"可爱的傻妞是你...","user_profile_image_url":"http://p1.pstatp.com/thumb/150c0013f67c16355034","status":5,"description":"湘里妹子进城来，玩转段子把心开，各玩各的勿干涉，谢绝喷子的厚爱！（欢迎爱好聊天的可爱段友到167112053笑谈人生）","comment_id":58523637685,"user_digg":0,"user_profile_url":"","share_type":1,"digg_count":53344,"is_pro_user":false,"platform_id":"feifei","avatar_url":"http://p1.pstatp.com/thumb/150c0013f67c16355034","group_id":58522228033}],"type":1,"display_time":1.491309273E9,"online_time":1.491309273E9},{"group":{"user":{"user_id":4058085022,"name":"远离喧嚣还我初心","followings":0,"user_verified":false,"ugc_count":4922,"avatar_url":"http://p1.pstatp.com/medium/18a40018169289a7e2c0","followers":59420,"is_following":false,"is_pro_user":false},"text":"","dislike_reason":[{"type":1,"id":319,"title":"恶搞"},{"type":2,"id":2,"title":"吧:搞笑囧图"},{"type":4,"id":0,"title":"内容重复"},{"type":3,"id":4058085022,"title":"作者:远离喧嚣还我初心"}],"create_time":1490931208,"id":58723761002,"favorite_count":346,"go_detail_count":253151,"user_favorite":0,"share_type":1,"max_screen_width_percent":0.6,"is_can_share":1,"category_type":1,"share_url":"http://m.neihanshequ.com/share/group/58723761002/?iid=0&app=joke_essay","label":1,"content":"","comment_count":1332,"id_str":"58723761002","media_type":1,"share_count":1570,"type":3,"status":112,"has_comments":1,"large_image":{"width":500,"r_height":583,"r_width":500,"url_list":[{"url":"http://p1.pstatp.com/large/194400014924d6d10725"},{"url":"http://pb3.pstatp.com/large/194400014924d6d10725"},{"url":"http://pb3.pstatp.com/large/194400014924d6d10725"}],"uri":"large/194400014924d6d10725","height":583},"user_bury":0,"status_desc":"热门投稿","display_type":0,"user_digg":0,"online_time":1490931208,"category_name":"搞笑囧图","category_visible":true,"bury_count":506,"is_anonymous":false,"repin_count":346,"min_screen_width_percent":0.167,"digg_count":45604,"has_hot_comments":1,"allow_dislike":true,"image_status":1,"user_repin":0,"activity":{},"group_id":58723761002,"middle_image":{"width":202,"r_height":235,"r_width":202,"url_list":[{"url":"http://p1.pstatp.com/w202/194400014924d6d10725"},{"url":"http://pb3.pstatp.com/w202/194400014924d6d10725"},{"url":"http://pb3.pstatp.com/w202/194400014924d6d10725"}],"uri":"w202/194400014924d6d10725","height":235},"category_id":2},"comments":[{"text":"你会后悔的，知道美国一男子误入女子监狱是什么后果了吗？","create_time":1490935174,"user_verified":false,"user_bury":0,"user_id":3048208431,"bury_count":0,"share_url":"http://m.neihanshequ.com/share/group/58723761002/?comment_id=58725377509","id":58725377509,"platform":"feifei","is_digg":0,"user_name":"笑看红尘512...","user_profile_image_url":"http://p3.pstatp.com/thumb/2024/4520714988","status":5,"description":"","comment_id":58725377509,"user_digg":0,"user_profile_url":"","share_type":2,"digg_count":70289,"is_pro_user":false,"platform_id":"feifei","avatar_url":"http://p3.pstatp.com/thumb/2024/4520714988","group_id":58723761002}],"type":1,"display_time":1.491309272E9,"online_time":1.491309272E9},{"group":{"text":"下班回到家，看家里没人。正准备先找点吃的，突然麒麟臂不受控制，把我拉进自己的卧室，反锁上门，打开电脑\u2026\u2026点开了XXXX.avi。5分钟后麒麟臂回复了正常\u2026\u2026突然身后传来表妹的声音：\u201c表哥，刚\u2026\u2026看你很忙\u2026\u2026没敢出声\u2026\u2026舅妈看我不舒服让我在你床上休息下\u2026\u2026现在没事了\u2026\u2026我该回去了\u201d","dislike_reason":[{"type":2,"id":1,"title":"吧:内涵段子"},{"type":4,"id":0,"title":"内容重复"},{"type":3,"id":6334473957,"title":"作者:段联会射长"}],"create_time":1491218257,"id":58806495630,"favorite_count":11,"go_detail_count":7521,"user_favorite":0,"share_type":1,"user":{"user_id":6334473957,"name":"段联会射长","followings":0,"user_verified":false,"ugc_count":1531,"avatar_url":"http://p1.pstatp.com/medium/2b60025febbef5bba07","followers":2976,"is_following":false,"is_pro_user":false},"is_can_share":1,"category_type":1,"share_url":"http://m.neihanshequ.com/share/group/58806495630/?iid=0&app=joke_essay","label":4,"content":"下班回到家，看家里没人。正准备先找点吃的，突然麒麟臂不受控制，把我拉进自己的卧室，反锁上门，打开电脑\u2026\u2026点开了XXXX.avi。5分钟后麒麟臂回复了正常\u2026\u2026突然身后传来表妹的声音：\u201c表哥，刚\u2026\u2026看你很忙\u2026\u2026没敢出声\u2026\u2026舅妈看我不舒服让我在你床上休息下\u2026\u2026现在没事了\u2026\u2026我该回去了\u201d","comment_count":30,"id_str":"58806495630","media_type":0,"share_count":38,"type":3,"status":103,"has_comments":0,"user_bury":0,"status_desc":"已发表","display_type":0,"user_digg":0,"online_time":1491218257,"category_name":"内涵段子","category_visible":true,"bury_count":16,"is_anonymous":false,"repin_count":11,"digg_count":493,"has_hot_comments":0,"allow_dislike":true,"user_repin":0,"activity":{},"group_id":58806495630,"category_id":1},"comments":[],"type":1,"display_time":1.491309271E9,"online_time":1.491309271E9}]
         */

        private boolean has_more;
        private String tip;
        private boolean has_new_message;
        private double max_time;
        private int min_time;
        private List<DataBean> data;

        public boolean isHas_more() {
            return has_more;
        }

        public void setHas_more(boolean has_more) {
            this.has_more = has_more;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }

        public boolean isHas_new_message() {
            return has_new_message;
        }

        public void setHas_new_message(boolean has_new_message) {
            this.has_new_message = has_new_message;
        }

        public double getMax_time() {
            return max_time;
        }

        public void setMax_time(double max_time) {
            this.max_time = max_time;
        }

        public int getMin_time() {
            return min_time;
        }

        public void setMin_time(int min_time) {
            this.min_time = min_time;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * group : {"360p_video":{"width":320,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=360p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=360p&line=1&is_gif=0&device_platform="}],"uri":"360p/8e5b163a506a491093a3608bc9caf5db","height":568},"mp4_url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=480p&line=0&is_gif=0&device_platform=.mp4","text":"青岛猫展","720p_video":{"width":320,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=720p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=720p&line=1&is_gif=0&device_platform="}],"uri":"720p/8e5b163a506a491093a3608bc9caf5db","height":568},"digg_count":6014,"duration":7.8,"480p_video":{"width":320,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=480p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=480p&line=1&is_gif=0&device_platform="}],"uri":"480p/8e5b163a506a491093a3608bc9caf5db","height":568},"create_time":1491137058,"keywords":"","id":58786982722,"favorite_count":28,"go_detail_count":18378,"m3u8_url":"","large_cover":{"url_list":[{"url":"http://p3.pstatp.com/large/1aa00008298c7cbc00a8"},{"url":"http://pb2.pstatp.com/large/1aa00008298c7cbc00a8"},{"url":"http://pb3.pstatp.com/large/1aa00008298c7cbc00a8"}],"uri":"large/1aa00008298c7cbc00a8"},"video_wh_ratio":1,"user_favorite":0,"share_type":1,"title":"","user":{"user_id":56829500242,"name":"ACat喵","followings":0,"user_verified":false,"ugc_count":47,"avatar_url":"http://wx.qlogo.cn/mmopen/gTiaWLzArw211kSqNdmsiatKG3p6hTljmgeBf8TSXEwLiawA4gr6krfYYhmww8WhDHP22KJcibsGAMn5boutIlXaC7mrJOdG7HLX/0","followers":82,"is_following":false,"is_pro_user":false},"is_can_share":1,"category_type":1,"share_url":"http://m.neihanshequ.com/share/group/58786982722/?iid=0&app=joke_essay","label":4,"content":"青岛猫展","video_height":568,"comment_count":122,"cover_image_uri":"1aa00008298c7cbc00a8","id_str":"58786982722","media_type":3,"share_count":198,"type":2,"category_id":65,"status":103,"has_comments":0,"publish_time":"","user_bury":0,"status_desc":"已发表","dislike_reason":[{"type":1,"id":577,"title":"UGC原创"},{"type":2,"id":65,"title":"吧:搞笑视频"},{"type":4,"id":0,"title":"内容重复"},{"type":3,"id":56829500242,"title":"作者:ACat喵"}],"play_count":315856,"user_repin":0,"medium_cover":{"url_list":[{"url":"http://p3.pstatp.com/w202/1aa00008298c7cbc00a8"},{"url":"http://pb2.pstatp.com/w202/1aa00008298c7cbc00a8"},{"url":"http://pb3.pstatp.com/w202/1aa00008298c7cbc00a8"}],"uri":"medium/1aa00008298c7cbc00a8"},"user_digg":0,"video_width":320,"online_time":1491137058,"category_name":"搞笑视频","flash_url":"","category_visible":true,"bury_count":255,"is_anonymous":false,"repin_count":28,"video_id":"8e5b163a506a491093a3608bc9caf5db","uri":"8e5b163a506a491093a3608bc9caf5db","is_public_url":1,"has_hot_comments":1,"allow_dislike":true,"origin_video":{"width":320,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=origin&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=origin&line=1&is_gif=0&device_platform="}],"uri":"origin/8e5b163a506a491093a3608bc9caf5db","height":568},"cover_image_url":"","activity":{},"group_id":58786982722,"is_video":1,"display_type":0}
             * comments : []
             * type : 1
             * display_time : 1491309290
             * online_time : 1491309290
             */

            private GroupBean group;
            private int type;
            private int display_time;
            private int online_time;
            private List<?> comments;

            public GroupBean getGroup() {
                return group;
            }

            public void setGroup(GroupBean group) {
                this.group = group;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getDisplay_time() {
                return display_time;
            }

            public void setDisplay_time(int display_time) {
                this.display_time = display_time;
            }

            public int getOnline_time() {
                return online_time;
            }

            public void setOnline_time(int online_time) {
                this.online_time = online_time;
            }

            public List<?> getComments() {
                return comments;
            }

            public void setComments(List<?> comments) {
                this.comments = comments;
            }

            public static class GroupBean {
                /**
                 * 360p_video : {"width":320,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=360p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=360p&line=1&is_gif=0&device_platform="}],"uri":"360p/8e5b163a506a491093a3608bc9caf5db","height":568}
                 * mp4_url : http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=480p&line=0&is_gif=0&device_platform=.mp4
                 * text : 青岛猫展
                 * 720p_video : {"width":320,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=720p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=720p&line=1&is_gif=0&device_platform="}],"uri":"720p/8e5b163a506a491093a3608bc9caf5db","height":568}
                 * digg_count : 6014
                 * duration : 7.8
                 * 480p_video : {"width":320,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=480p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=480p&line=1&is_gif=0&device_platform="}],"uri":"480p/8e5b163a506a491093a3608bc9caf5db","height":568}
                 * create_time : 1491137058
                 * keywords :
                 * id : 58786982722
                 * favorite_count : 28
                 * go_detail_count : 18378
                 * m3u8_url :
                 * large_cover : {"url_list":[{"url":"http://p3.pstatp.com/large/1aa00008298c7cbc00a8"},{"url":"http://pb2.pstatp.com/large/1aa00008298c7cbc00a8"},{"url":"http://pb3.pstatp.com/large/1aa00008298c7cbc00a8"}],"uri":"large/1aa00008298c7cbc00a8"}
                 * video_wh_ratio : 1
                 * user_favorite : 0
                 * share_type : 1
                 * title :
                 * user : {"user_id":56829500242,"name":"ACat喵","followings":0,"user_verified":false,"ugc_count":47,"avatar_url":"http://wx.qlogo.cn/mmopen/gTiaWLzArw211kSqNdmsiatKG3p6hTljmgeBf8TSXEwLiawA4gr6krfYYhmww8WhDHP22KJcibsGAMn5boutIlXaC7mrJOdG7HLX/0","followers":82,"is_following":false,"is_pro_user":false}
                 * is_can_share : 1
                 * category_type : 1
                 * share_url : http://m.neihanshequ.com/share/group/58786982722/?iid=0&app=joke_essay
                 * label : 4
                 * content : 青岛猫展
                 * video_height : 568
                 * comment_count : 122
                 * cover_image_uri : 1aa00008298c7cbc00a8
                 * id_str : 58786982722
                 * media_type : 3
                 * share_count : 198
                 * type : 2
                 * category_id : 65
                 * status : 103
                 * has_comments : 0
                 * publish_time :
                 * user_bury : 0
                 * status_desc : 已发表
                 * dislike_reason : [{"type":1,"id":577,"title":"UGC原创"},{"type":2,"id":65,"title":"吧:搞笑视频"},{"type":4,"id":0,"title":"内容重复"},{"type":3,"id":56829500242,"title":"作者:ACat喵"}]
                 * play_count : 315856
                 * user_repin : 0
                 * medium_cover : {"url_list":[{"url":"http://p3.pstatp.com/w202/1aa00008298c7cbc00a8"},{"url":"http://pb2.pstatp.com/w202/1aa00008298c7cbc00a8"},{"url":"http://pb3.pstatp.com/w202/1aa00008298c7cbc00a8"}],"uri":"medium/1aa00008298c7cbc00a8"}
                 * user_digg : 0
                 * video_width : 320
                 * online_time : 1491137058
                 * category_name : 搞笑视频
                 * flash_url :
                 * category_visible : true
                 * bury_count : 255
                 * is_anonymous : false
                 * repin_count : 28
                 * video_id : 8e5b163a506a491093a3608bc9caf5db
                 * uri : 8e5b163a506a491093a3608bc9caf5db
                 * is_public_url : 1
                 * has_hot_comments : 1
                 * allow_dislike : true
                 * origin_video : {"width":320,"url_list":[{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=origin&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=origin&line=1&is_gif=0&device_platform="}],"uri":"origin/8e5b163a506a491093a3608bc9caf5db","height":568}
                 * cover_image_url :
                 * activity : {}
                 * group_id : 58786982722
                 * is_video : 1
                 * display_type : 0
                 */

                @SerializedName("360p_video")
                private _$360pVideoBean _$360p_video;
                private String mp4_url;
                private String text;
                @SerializedName("720p_video")
                private _$720pVideoBean _$720p_video;
                private int digg_count;
                private double duration;
                @SerializedName("480p_video")
                private _$480pVideoBean _$480p_video;
                private int create_time;
                private String keywords;
                private long id;
                private int favorite_count;
                private int go_detail_count;
                private String m3u8_url;
                private LargeCoverBean large_cover;
                private int video_wh_ratio;
                private int user_favorite;
                private int share_type;
                private String title;
                private UserBean user;
                private int is_can_share;
                private int category_type;
                private String share_url;
                private int label;
                private String content;
                private int video_height;
                private int comment_count;
                private String cover_image_uri;
                private String id_str;
                private int media_type;
                private int share_count;
                private int type;
                private int category_id;
                private int status;
                private int has_comments;
                private String publish_time;
                private int user_bury;
                private String status_desc;
                private int play_count;
                private int user_repin;
                private MediumCoverBean medium_cover;
                private int user_digg;
                private int video_width;
                private int online_time;
                private String category_name;
                private String flash_url;
                private boolean category_visible;
                private int bury_count;
                private boolean is_anonymous;
                private int repin_count;
                private String video_id;
                private String uri;
                private int is_public_url;
                private int has_hot_comments;
                private boolean allow_dislike;
                private OriginVideoBean origin_video;
                private String cover_image_url;
                private ActivityBean activity;
                private long group_id;
                private int is_video;
                private int display_type;
                private List<DislikeReasonBean> dislike_reason;

                public _$360pVideoBean get_$360p_video() {
                    return _$360p_video;
                }

                public void set_$360p_video(_$360pVideoBean _$360p_video) {
                    this._$360p_video = _$360p_video;
                }

                public String getMp4_url() {
                    return mp4_url;
                }

                public void setMp4_url(String mp4_url) {
                    this.mp4_url = mp4_url;
                }

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public _$720pVideoBean get_$720p_video() {
                    return _$720p_video;
                }

                public void set_$720p_video(_$720pVideoBean _$720p_video) {
                    this._$720p_video = _$720p_video;
                }

                public int getDigg_count() {
                    return digg_count;
                }

                public void setDigg_count(int digg_count) {
                    this.digg_count = digg_count;
                }

                public double getDuration() {
                    return duration;
                }

                public void setDuration(double duration) {
                    this.duration = duration;
                }

                public _$480pVideoBean get_$480p_video() {
                    return _$480p_video;
                }

                public void set_$480p_video(_$480pVideoBean _$480p_video) {
                    this._$480p_video = _$480p_video;
                }

                public int getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(int create_time) {
                    this.create_time = create_time;
                }

                public String getKeywords() {
                    return keywords;
                }

                public void setKeywords(String keywords) {
                    this.keywords = keywords;
                }

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public int getFavorite_count() {
                    return favorite_count;
                }

                public void setFavorite_count(int favorite_count) {
                    this.favorite_count = favorite_count;
                }

                public int getGo_detail_count() {
                    return go_detail_count;
                }

                public void setGo_detail_count(int go_detail_count) {
                    this.go_detail_count = go_detail_count;
                }

                public String getM3u8_url() {
                    return m3u8_url;
                }

                public void setM3u8_url(String m3u8_url) {
                    this.m3u8_url = m3u8_url;
                }

                public LargeCoverBean getLarge_cover() {
                    return large_cover;
                }

                public void setLarge_cover(LargeCoverBean large_cover) {
                    this.large_cover = large_cover;
                }

                public int getVideo_wh_ratio() {
                    return video_wh_ratio;
                }

                public void setVideo_wh_ratio(int video_wh_ratio) {
                    this.video_wh_ratio = video_wh_ratio;
                }

                public int getUser_favorite() {
                    return user_favorite;
                }

                public void setUser_favorite(int user_favorite) {
                    this.user_favorite = user_favorite;
                }

                public int getShare_type() {
                    return share_type;
                }

                public void setShare_type(int share_type) {
                    this.share_type = share_type;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public UserBean getUser() {
                    return user;
                }

                public void setUser(UserBean user) {
                    this.user = user;
                }

                public int getIs_can_share() {
                    return is_can_share;
                }

                public void setIs_can_share(int is_can_share) {
                    this.is_can_share = is_can_share;
                }

                public int getCategory_type() {
                    return category_type;
                }

                public void setCategory_type(int category_type) {
                    this.category_type = category_type;
                }

                public String getShare_url() {
                    return share_url;
                }

                public void setShare_url(String share_url) {
                    this.share_url = share_url;
                }

                public int getLabel() {
                    return label;
                }

                public void setLabel(int label) {
                    this.label = label;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public int getVideo_height() {
                    return video_height;
                }

                public void setVideo_height(int video_height) {
                    this.video_height = video_height;
                }

                public int getComment_count() {
                    return comment_count;
                }

                public void setComment_count(int comment_count) {
                    this.comment_count = comment_count;
                }

                public String getCover_image_uri() {
                    return cover_image_uri;
                }

                public void setCover_image_uri(String cover_image_uri) {
                    this.cover_image_uri = cover_image_uri;
                }

                public String getId_str() {
                    return id_str;
                }

                public void setId_str(String id_str) {
                    this.id_str = id_str;
                }

                public int getMedia_type() {
                    return media_type;
                }

                public void setMedia_type(int media_type) {
                    this.media_type = media_type;
                }

                public int getShare_count() {
                    return share_count;
                }

                public void setShare_count(int share_count) {
                    this.share_count = share_count;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getCategory_id() {
                    return category_id;
                }

                public void setCategory_id(int category_id) {
                    this.category_id = category_id;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public int getHas_comments() {
                    return has_comments;
                }

                public void setHas_comments(int has_comments) {
                    this.has_comments = has_comments;
                }

                public String getPublish_time() {
                    return publish_time;
                }

                public void setPublish_time(String publish_time) {
                    this.publish_time = publish_time;
                }

                public int getUser_bury() {
                    return user_bury;
                }

                public void setUser_bury(int user_bury) {
                    this.user_bury = user_bury;
                }

                public String getStatus_desc() {
                    return status_desc;
                }

                public void setStatus_desc(String status_desc) {
                    this.status_desc = status_desc;
                }

                public int getPlay_count() {
                    return play_count;
                }

                public void setPlay_count(int play_count) {
                    this.play_count = play_count;
                }

                public int getUser_repin() {
                    return user_repin;
                }

                public void setUser_repin(int user_repin) {
                    this.user_repin = user_repin;
                }

                public MediumCoverBean getMedium_cover() {
                    return medium_cover;
                }

                public void setMedium_cover(MediumCoverBean medium_cover) {
                    this.medium_cover = medium_cover;
                }

                public int getUser_digg() {
                    return user_digg;
                }

                public void setUser_digg(int user_digg) {
                    this.user_digg = user_digg;
                }

                public int getVideo_width() {
                    return video_width;
                }

                public void setVideo_width(int video_width) {
                    this.video_width = video_width;
                }

                public int getOnline_time() {
                    return online_time;
                }

                public void setOnline_time(int online_time) {
                    this.online_time = online_time;
                }

                public String getCategory_name() {
                    return category_name;
                }

                public void setCategory_name(String category_name) {
                    this.category_name = category_name;
                }

                public String getFlash_url() {
                    return flash_url;
                }

                public void setFlash_url(String flash_url) {
                    this.flash_url = flash_url;
                }

                public boolean isCategory_visible() {
                    return category_visible;
                }

                public void setCategory_visible(boolean category_visible) {
                    this.category_visible = category_visible;
                }

                public int getBury_count() {
                    return bury_count;
                }

                public void setBury_count(int bury_count) {
                    this.bury_count = bury_count;
                }

                public boolean isIs_anonymous() {
                    return is_anonymous;
                }

                public void setIs_anonymous(boolean is_anonymous) {
                    this.is_anonymous = is_anonymous;
                }

                public int getRepin_count() {
                    return repin_count;
                }

                public void setRepin_count(int repin_count) {
                    this.repin_count = repin_count;
                }

                public String getVideo_id() {
                    return video_id;
                }

                public void setVideo_id(String video_id) {
                    this.video_id = video_id;
                }

                public String getUri() {
                    return uri;
                }

                public void setUri(String uri) {
                    this.uri = uri;
                }

                public int getIs_public_url() {
                    return is_public_url;
                }

                public void setIs_public_url(int is_public_url) {
                    this.is_public_url = is_public_url;
                }

                public int getHas_hot_comments() {
                    return has_hot_comments;
                }

                public void setHas_hot_comments(int has_hot_comments) {
                    this.has_hot_comments = has_hot_comments;
                }

                public boolean isAllow_dislike() {
                    return allow_dislike;
                }

                public void setAllow_dislike(boolean allow_dislike) {
                    this.allow_dislike = allow_dislike;
                }

                public OriginVideoBean getOrigin_video() {
                    return origin_video;
                }

                public void setOrigin_video(OriginVideoBean origin_video) {
                    this.origin_video = origin_video;
                }

                public String getCover_image_url() {
                    return cover_image_url;
                }

                public void setCover_image_url(String cover_image_url) {
                    this.cover_image_url = cover_image_url;
                }

                public ActivityBean getActivity() {
                    return activity;
                }

                public void setActivity(ActivityBean activity) {
                    this.activity = activity;
                }

                public long getGroup_id() {
                    return group_id;
                }

                public void setGroup_id(long group_id) {
                    this.group_id = group_id;
                }

                public int getIs_video() {
                    return is_video;
                }

                public void setIs_video(int is_video) {
                    this.is_video = is_video;
                }

                public int getDisplay_type() {
                    return display_type;
                }

                public void setDisplay_type(int display_type) {
                    this.display_type = display_type;
                }

                public List<DislikeReasonBean> getDislike_reason() {
                    return dislike_reason;
                }

                public void setDislike_reason(List<DislikeReasonBean> dislike_reason) {
                    this.dislike_reason = dislike_reason;
                }

                public static class _$360pVideoBean {
                    /**
                     * width : 320
                     * url_list : [{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=360p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=360p&line=1&is_gif=0&device_platform="}]
                     * uri : 360p/8e5b163a506a491093a3608bc9caf5db
                     * height : 568
                     */

                    private int width;
                    private String uri;
                    private int height;
                    private List<UrlListBean> url_list;

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public String getUri() {
                        return uri;
                    }

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public List<UrlListBean> getUrl_list() {
                        return url_list;
                    }

                    public void setUrl_list(List<UrlListBean> url_list) {
                        this.url_list = url_list;
                    }

                    public static class UrlListBean {
                        /**
                         * url : http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=360p&line=0&is_gif=0&device_platform=
                         */

                        private String url;

                        public String getUrl() {
                            return url;
                        }

                        public void setUrl(String url) {
                            this.url = url;
                        }
                    }
                }

                public static class _$720pVideoBean {
                    /**
                     * width : 320
                     * url_list : [{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=720p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=720p&line=1&is_gif=0&device_platform="}]
                     * uri : 720p/8e5b163a506a491093a3608bc9caf5db
                     * height : 568
                     */

                    private int width;
                    private String uri;
                    private int height;
                    private List<UrlListBeanX> url_list;

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public String getUri() {
                        return uri;
                    }

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public List<UrlListBeanX> getUrl_list() {
                        return url_list;
                    }

                    public void setUrl_list(List<UrlListBeanX> url_list) {
                        this.url_list = url_list;
                    }

                    public static class UrlListBeanX {
                        /**
                         * url : http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=720p&line=0&is_gif=0&device_platform=
                         */

                        private String url;

                        public String getUrl() {
                            return url;
                        }

                        public void setUrl(String url) {
                            this.url = url;
                        }
                    }
                }

                public static class _$480pVideoBean {
                    /**
                     * width : 320
                     * url_list : [{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=480p&line=0&is_gif=0&device_platform="},{"url":"http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=480p&line=1&is_gif=0&device_platform="}]
                     * uri : 480p/8e5b163a506a491093a3608bc9caf5db
                     * height : 568
                     */

                    private int width;
                    private String uri;
                    private int height;
                    private List<UrlListBeanXX> url_list;

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public String getUri() {
                        return uri;
                    }

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public List<UrlListBeanXX> getUrl_list() {
                        return url_list;
                    }

                    public void setUrl_list(List<UrlListBeanXX> url_list) {
                        this.url_list = url_list;
                    }

                    public static class UrlListBeanXX {
                        /**
                         * url : http://ic.snssdk.com/neihan/video/playback/?video_id=8e5b163a506a491093a3608bc9caf5db&quality=480p&line=0&is_gif=0&device_platform=
                         */

                        private String url;

                        public String getUrl() {
                            return url;
                        }

                        public void setUrl(String url) {
                            this.url = url;
                        }
                    }
                }

                public static class LargeCoverBean {
                    /**
                     * url_list : [{"url":"http://p3.pstatp.com/large/1aa00008298c7cbc00a8"},{"url":"http://pb2.pstatp.com/large/1aa00008298c7cbc00a8"},{"url":"http://pb3.pstatp.com/large/1aa00008298c7cbc00a8"}]
                     * uri : large/1aa00008298c7cbc00a8
                     */

                    private String uri;
                    private List<UrlListBeanXXX> url_list;

                    public String getUri() {
                        return uri;
                    }

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public List<UrlListBeanXXX> getUrl_list() {
                        return url_list;
                    }

                    public void setUrl_list(List<UrlListBeanXXX> url_list) {
                        this.url_list = url_list;
                    }

                    public static class UrlListBeanXXX {
                        /**
                         * url : http://p3.pstatp.com/large/1aa00008298c7cbc00a8
                         */

                        private String url;

                        public String getUrl() {
                            return url;
                        }

                        public void setUrl(String url) {
                            this.url = url;
                        }
                    }
                }

                public static class UserBean {
                    /**
                     * user_id : 56829500242
                     * name : ACat喵
                     * followings : 0
                     * user_verified : false
                     * ugc_count : 47
                     * avatar_url : http://wx.qlogo.cn/mmopen/gTiaWLzArw211kSqNdmsiatKG3p6hTljmgeBf8TSXEwLiawA4gr6krfYYhmww8WhDHP22KJcibsGAMn5boutIlXaC7mrJOdG7HLX/0
                     * followers : 82
                     * is_following : false
                     * is_pro_user : false
                     */

                    private long user_id;
                    private String name;
                    private int followings;
                    private boolean user_verified;
                    private int ugc_count;
                    private String avatar_url;
                    private int followers;
                    private boolean is_following;
                    private boolean is_pro_user;

                    public long getUser_id() {
                        return user_id;
                    }

                    public void setUser_id(long user_id) {
                        this.user_id = user_id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public int getFollowings() {
                        return followings;
                    }

                    public void setFollowings(int followings) {
                        this.followings = followings;
                    }

                    public boolean isUser_verified() {
                        return user_verified;
                    }

                    public void setUser_verified(boolean user_verified) {
                        this.user_verified = user_verified;
                    }

                    public int getUgc_count() {
                        return ugc_count;
                    }

                    public void setUgc_count(int ugc_count) {
                        this.ugc_count = ugc_count;
                    }

                    public String getAvatar_url() {
                        return avatar_url;
                    }

                    public void setAvatar_url(String avatar_url) {
                        this.avatar_url = avatar_url;
                    }

                    public int getFollowers() {
                        return followers;
                    }

                    public void setFollowers(int followers) {
                        this.followers = followers;
                    }

                    public boolean isIs_following() {
                        return is_following;
                    }

                    public void setIs_following(boolean is_following) {
                        this.is_following = is_following;
                    }

                    public boolean isIs_pro_user() {
                        return is_pro_user;
                    }

                    public void setIs_pro_user(boolean is_pro_user) {
                        this.is_pro_user = is_pro_user;
                    }
                }

                public static class MediumCoverBean {
                    /**
                     * url_list : [{"url":"http://p3.pstatp.com/w202/1aa00008298c7cbc00a8"},{"url":"http://pb2.pstatp.com/w202/1aa00008298c7cbc00a8"},{"url":"http://pb3.pstatp.com/w202/1aa00008298c7cbc00a8"}]
                     * uri : medium/1aa00008298c7cbc00a8
                     */

                    private String uri;
                    private List<UrlListBeanXXXX> url_list;

                    public String getUri() {
                        return uri;
                    }

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public List<UrlListBeanXXXX> getUrl_list() {
                        return url_list;
                    }

                    public void setUrl_list(List<UrlListBeanXXXX> url_list) {
                        this.url_list = url_list;
                    }

                    public static class UrlListBeanXXXX {
                        /**
                         * url : http://p3.pstatp.com/w202/1aa00008298c7cbc00a8
                         */

                        private String url;

                        public String getUrl() {
                            return url;
                        }

                        public void setUrl(String url) {
                            this.url = url;
                        }
                    }
                }

                public static class OriginVideoBean {
                    public static class UrlListBeanXXXXX {
                    }
                }

                public static class ActivityBean {
                }

                public static class DislikeReasonBean {
                    /**
                     * type : 1
                     * id : 577
                     * title : UGC原创
                     */

                    private int type;
                    private int id;
                    private String title;

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }
                }
            }
        }
    }
}
