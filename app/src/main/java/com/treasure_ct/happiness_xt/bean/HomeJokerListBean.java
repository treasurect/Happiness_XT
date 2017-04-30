package com.treasure_ct.happiness_xt.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by treasure on 2017.04.06.
 */

public class HomeJokerListBean implements Serializable{


    private String message;
    private DataBeanXX data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBeanXX getData() {
        return data;
    }

    public void setData(DataBeanXX data) {
        this.data = data;
    }

    public static class DataBeanXX {

        private boolean has_more;
        private String tip;
        private boolean has_new_message;
        private double max_time;
        private double min_time;
        private List<DataBeanX> data;

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

        public double getMin_time() {
            return min_time;
        }

        public void setMin_time(double min_time) {
            this.min_time = min_time;
        }

        public List<DataBeanX> getData() {
            return data;
        }

        public void setData(List<DataBeanX> data) {
            this.data = data;
        }

        public static class DataBeanX {

            private GroupBean group;
            private int type;
            private double display_time;
            private double online_time;
            private AdBean ad;
            private int show_type;
            private List<DataBean> data;

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

            public double getDisplay_time() {
                return display_time;
            }

            public void setDisplay_time(double display_time) {
                this.display_time = display_time;
            }

            public double getOnline_time() {
                return online_time;
            }

            public void setOnline_time(double online_time) {
                this.online_time = online_time;
            }

            public AdBean getAd() {
                return ad;
            }

            public void setAd(AdBean ad) {
                this.ad = ad;
            }

            public int getShow_type() {
                return show_type;
            }

            public void setShow_type(int show_type) {
                this.show_type = show_type;
            }


            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class GroupBean {
                /**
                 * text : 对于孙悟空打红孩儿的那一段 我一直有个疑问：孙悟空明明被三昧真火烧了七七四十九天都没事，为什么会怕红孩儿的三昧真火？
                 * neihan_hot_start_time : 00-00-00
                 * dislike_reason : []
                 * create_time : 1493281969
                 * id : 59501754486
                 * favorite_count : 0
                 * danmaku_attrs : {}
                 * user_favorite : 0
                 * share_type : 1
                 * user : {"user_id":52418954318,"name":"前面高能有妞子","followings":0,"user_verified":false,"ugc_count":12,"avatar_url":"http://p3.pstatp.com/medium/1bf6001bf72c09440d22","followers":49,"is_following":false,"is_pro_user":false}
                 * is_can_share : 1
                 * category_type : 1
                 * share_url : http://m.neihanshequ.com/share/group/59501754486/?iid=9871060169&app=joke_essay
                 * label : 4
                 * content : 对于孙悟空打红孩儿的那一段 我一直有个疑问：孙悟空明明被三昧真火烧了七七四十九天都没事，为什么会怕红孩儿的三昧真火？
                 * comment_count : 9
                 * id_str : 59501754486
                 * media_type : 0
                 * share_count : 10
                 * type : 3
                 * status : 102
                 * has_comments : 0
                 * go_detail_count : 330
                 * activity : {}
                 * status_desc : 已发表，粉丝第一时间可见
                 * quick_comment : false
                 * display_type : 0
                 * neihan_hot_end_time : 00-00-00
                 * user_digg : 0
                 * online_time : 1493281969
                 * category_name : 内涵段子
                 * category_visible : true
                 * bury_count : 12
                 * is_anonymous : false
                 * repin_count : 0
                 * is_neihan_hot : false
                 * digg_count : 85
                 * user_bury : 0
                 * has_hot_comments : 0
                 * allow_dislike : true
                 * user_repin : 0
                 * neihan_hot_link : {}
                 * group_id : 59501754486
                 * category_id : 1
                 */

                private String text;
                private String neihan_hot_start_time;
                private int create_time;
                private long id;
                private int favorite_count;
                private DanmakuAttrsBean danmaku_attrs;
                private int user_favorite;
                private int share_type;
                private UserBean user;
                private int is_can_share;
                private int category_type;
                private String share_url;
                private int label;
                private String content;
                private int comment_count;
                private String id_str;
                private int media_type;
                private int share_count;
                private int type;
                private int status;
                private int has_comments;
                private int go_detail_count;
                private ActivityBean activity;
                private String status_desc;
                private boolean quick_comment;
                private int display_type;
                private String neihan_hot_end_time;
                private int user_digg;
                private int online_time;
                private String category_name;
                private boolean category_visible;
                private int bury_count;
                private boolean is_anonymous;
                private int repin_count;
                private boolean is_neihan_hot;
                private int digg_count;
                private int user_bury;
                private int has_hot_comments;
                private boolean allow_dislike;
                private int user_repin;
                private NeihanHotLinkBean neihan_hot_link;
                private long group_id;
                private int category_id;
                private List<?> dislike_reason;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public String getNeihan_hot_start_time() {
                    return neihan_hot_start_time;
                }

                public void setNeihan_hot_start_time(String neihan_hot_start_time) {
                    this.neihan_hot_start_time = neihan_hot_start_time;
                }

                public int getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(int create_time) {
                    this.create_time = create_time;
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

                public DanmakuAttrsBean getDanmaku_attrs() {
                    return danmaku_attrs;
                }

                public void setDanmaku_attrs(DanmakuAttrsBean danmaku_attrs) {
                    this.danmaku_attrs = danmaku_attrs;
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

                public int getComment_count() {
                    return comment_count;
                }

                public void setComment_count(int comment_count) {
                    this.comment_count = comment_count;
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

                public int getGo_detail_count() {
                    return go_detail_count;
                }

                public void setGo_detail_count(int go_detail_count) {
                    this.go_detail_count = go_detail_count;
                }

                public ActivityBean getActivity() {
                    return activity;
                }

                public void setActivity(ActivityBean activity) {
                    this.activity = activity;
                }

                public String getStatus_desc() {
                    return status_desc;
                }

                public void setStatus_desc(String status_desc) {
                    this.status_desc = status_desc;
                }

                public boolean isQuick_comment() {
                    return quick_comment;
                }

                public void setQuick_comment(boolean quick_comment) {
                    this.quick_comment = quick_comment;
                }

                public int getDisplay_type() {
                    return display_type;
                }

                public void setDisplay_type(int display_type) {
                    this.display_type = display_type;
                }

                public String getNeihan_hot_end_time() {
                    return neihan_hot_end_time;
                }

                public void setNeihan_hot_end_time(String neihan_hot_end_time) {
                    this.neihan_hot_end_time = neihan_hot_end_time;
                }

                public int getUser_digg() {
                    return user_digg;
                }

                public void setUser_digg(int user_digg) {
                    this.user_digg = user_digg;
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

                public boolean isIs_neihan_hot() {
                    return is_neihan_hot;
                }

                public void setIs_neihan_hot(boolean is_neihan_hot) {
                    this.is_neihan_hot = is_neihan_hot;
                }

                public int getDigg_count() {
                    return digg_count;
                }

                public void setDigg_count(int digg_count) {
                    this.digg_count = digg_count;
                }

                public int getUser_bury() {
                    return user_bury;
                }

                public void setUser_bury(int user_bury) {
                    this.user_bury = user_bury;
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

                public int getUser_repin() {
                    return user_repin;
                }

                public void setUser_repin(int user_repin) {
                    this.user_repin = user_repin;
                }

                public NeihanHotLinkBean getNeihan_hot_link() {
                    return neihan_hot_link;
                }

                public void setNeihan_hot_link(NeihanHotLinkBean neihan_hot_link) {
                    this.neihan_hot_link = neihan_hot_link;
                }

                public long getGroup_id() {
                    return group_id;
                }

                public void setGroup_id(long group_id) {
                    this.group_id = group_id;
                }

                public int getCategory_id() {
                    return category_id;
                }

                public void setCategory_id(int category_id) {
                    this.category_id = category_id;
                }

                public List<?> getDislike_reason() {
                    return dislike_reason;
                }

                public void setDislike_reason(List<?> dislike_reason) {
                    this.dislike_reason = dislike_reason;
                }

                public static class DanmakuAttrsBean {
                }

                public static class UserBean {
                    private String name;
                    private String avatar_url;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getAvatar_url() {
                        return avatar_url;
                    }

                    public void setAvatar_url(String avatar_url) {
                        this.avatar_url = avatar_url;
                    }
                }

                public static class ActivityBean {
                }

                public static class NeihanHotLinkBean {
                }
            }

            public static class AdBean {
                /**
                 * log_extra : {"rit":11,"ad_price":"WQHkZgAEZJ5ZAeRmAARknvLzvAjd2xAE1NO3hA","req_id":"201704280908590100030231655643DF","convert_id":0}
                 * open_url :
                 * track_url :
                 * display_info : 买了一堆才花了300，阿迪全场1折，全家都在抢！
                 * web_url : http://ad.toutiao.com/tetris/page/52200882049/
                 * appleid :
                 * id : 58875184895
                 * display_image_height : 388
                 * display_image_width : 690
                 * title : 买了一堆才花了300，阿迪全场1折，全家都在抢！
                 * download_url : http://download.shandjj.com/apk/toutiao_wx18/sdjj.apk
                 * label : 广告
                 * source :
                 * track_url_list : []
                 * display_image : http://p9.pstatp.com/large/19470011e731022b6895
                 * filter_words : [{"id":"1:1","name":"应用下载","is_selected":false},{"id":"1:66","name":"移动应用","is_selected":false},{"id":"1:202","name":"网上购物","is_selected":false},{"id":"4:2","name":"看过了","is_selected":false}]
                 * avatar_name : 闪电降价-品牌运动鞋
                 * type : app
                 * is_ad : 1
                 * end_time : 1806983239
                 * gif_url :
                 * ad_id : 58875184895
                 * button_text : 立即下载
                 * display_type : 3
                 * click_delay : 0
                 * ab_show_style : 2
                 * package : com.app.shanjiang
                 * hide_if_exists : 1
                 * avatar_url : http://p1.pstatp.com/origin/9197/1375840585
                 * ipa_url :
                 */

                private LogExtraBean log_extra;
                private String open_url;
                private String track_url;
                private String display_info;
                private String web_url;
                private String appleid;
                private long id;
                private int display_image_height;
                private int display_image_width;
                private String title;
                private String download_url;
                private String label;
                private String source;
                private String display_image;
                private String avatar_name;
                private String type;
                private int is_ad;
                private int end_time;
                private String gif_url;
                private long ad_id;
                private String button_text;
                private int display_type;
                private int click_delay;
                private int ab_show_style;
                @SerializedName("package")
                private String packageX;
                private int hide_if_exists;
                private String avatar_url;
                private String ipa_url;
                private List<?> track_url_list;
                private List<FilterWordsBean> filter_words;

                public LogExtraBean getLog_extra() {
                    return log_extra;
                }

                public void setLog_extra(LogExtraBean log_extra) {
                    this.log_extra = log_extra;
                }

                public String getOpen_url() {
                    return open_url;
                }

                public void setOpen_url(String open_url) {
                    this.open_url = open_url;
                }

                public String getTrack_url() {
                    return track_url;
                }

                public void setTrack_url(String track_url) {
                    this.track_url = track_url;
                }

                public String getDisplay_info() {
                    return display_info;
                }

                public void setDisplay_info(String display_info) {
                    this.display_info = display_info;
                }

                public String getWeb_url() {
                    return web_url;
                }

                public void setWeb_url(String web_url) {
                    this.web_url = web_url;
                }

                public String getAppleid() {
                    return appleid;
                }

                public void setAppleid(String appleid) {
                    this.appleid = appleid;
                }

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public int getDisplay_image_height() {
                    return display_image_height;
                }

                public void setDisplay_image_height(int display_image_height) {
                    this.display_image_height = display_image_height;
                }

                public int getDisplay_image_width() {
                    return display_image_width;
                }

                public void setDisplay_image_width(int display_image_width) {
                    this.display_image_width = display_image_width;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDownload_url() {
                    return download_url;
                }

                public void setDownload_url(String download_url) {
                    this.download_url = download_url;
                }

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }

                public String getSource() {
                    return source;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                public String getDisplay_image() {
                    return display_image;
                }

                public void setDisplay_image(String display_image) {
                    this.display_image = display_image;
                }

                public String getAvatar_name() {
                    return avatar_name;
                }

                public void setAvatar_name(String avatar_name) {
                    this.avatar_name = avatar_name;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public int getIs_ad() {
                    return is_ad;
                }

                public void setIs_ad(int is_ad) {
                    this.is_ad = is_ad;
                }

                public int getEnd_time() {
                    return end_time;
                }

                public void setEnd_time(int end_time) {
                    this.end_time = end_time;
                }

                public String getGif_url() {
                    return gif_url;
                }

                public void setGif_url(String gif_url) {
                    this.gif_url = gif_url;
                }

                public long getAd_id() {
                    return ad_id;
                }

                public void setAd_id(long ad_id) {
                    this.ad_id = ad_id;
                }

                public String getButton_text() {
                    return button_text;
                }

                public void setButton_text(String button_text) {
                    this.button_text = button_text;
                }

                public int getDisplay_type() {
                    return display_type;
                }

                public void setDisplay_type(int display_type) {
                    this.display_type = display_type;
                }

                public int getClick_delay() {
                    return click_delay;
                }

                public void setClick_delay(int click_delay) {
                    this.click_delay = click_delay;
                }

                public int getAb_show_style() {
                    return ab_show_style;
                }

                public void setAb_show_style(int ab_show_style) {
                    this.ab_show_style = ab_show_style;
                }

                public String getPackageX() {
                    return packageX;
                }

                public void setPackageX(String packageX) {
                    this.packageX = packageX;
                }

                public int getHide_if_exists() {
                    return hide_if_exists;
                }

                public void setHide_if_exists(int hide_if_exists) {
                    this.hide_if_exists = hide_if_exists;
                }

                public String getAvatar_url() {
                    return avatar_url;
                }

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
                }

                public String getIpa_url() {
                    return ipa_url;
                }

                public void setIpa_url(String ipa_url) {
                    this.ipa_url = ipa_url;
                }

                public List<?> getTrack_url_list() {
                    return track_url_list;
                }

                public void setTrack_url_list(List<?> track_url_list) {
                    this.track_url_list = track_url_list;
                }

                public List<FilterWordsBean> getFilter_words() {
                    return filter_words;
                }

                public void setFilter_words(List<FilterWordsBean> filter_words) {
                    this.filter_words = filter_words;
                }

                public static class LogExtraBean {
                    /**
                     * rit : 11
                     * ad_price : WQHkZgAEZJ5ZAeRmAARknvLzvAjd2xAE1NO3hA
                     * req_id : 201704280908590100030231655643DF
                     * convert_id : 0
                     */

                    private int rit;
                    private String ad_price;
                    private String req_id;
                    private int convert_id;

                    public int getRit() {
                        return rit;
                    }

                    public void setRit(int rit) {
                        this.rit = rit;
                    }

                    public String getAd_price() {
                        return ad_price;
                    }

                    public void setAd_price(String ad_price) {
                        this.ad_price = ad_price;
                    }

                    public String getReq_id() {
                        return req_id;
                    }

                    public void setReq_id(String req_id) {
                        this.req_id = req_id;
                    }

                    public int getConvert_id() {
                        return convert_id;
                    }

                    public void setConvert_id(int convert_id) {
                        this.convert_id = convert_id;
                    }
                }

                public static class FilterWordsBean {
                    /**
                     * id : 1:1
                     * name : 应用下载
                     * is_selected : false
                     */

                    private String id;
                    private String name;
                    private boolean is_selected;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public boolean isIs_selected() {
                        return is_selected;
                    }

                    public void setIs_selected(boolean is_selected) {
                        this.is_selected = is_selected;
                    }
                }
            }

            public static class DataBean {
                /**
                 * reason : 1
                 * origin_reason : hot
                 * user : {"user_id":17895,"description":"快乐，其实就这么简单！","point":0,"gender":1,"followings":0,"followers":15085,"user_verified":false,"is_follower":false,"name":"鹅巨大","ugc_count":46,"avatar_url":"http://p3.pstatp.com/medium/624/3591842938","create_time":1333022332,"pro_user_desc":"","large_avatar_url":"http://p3.pstatp.com/large/624/3591842938","is_pro_user":false,"is_following":false,"id":17895,"screen_name":"鹅巨大"}
                 * reason_desc : 热门用户
                 */

                private int reason;
                private String origin_reason;
                private UserBeanX user;
                private String reason_desc;

                public int getReason() {
                    return reason;
                }

                public void setReason(int reason) {
                    this.reason = reason;
                }

                public String getOrigin_reason() {
                    return origin_reason;
                }

                public void setOrigin_reason(String origin_reason) {
                    this.origin_reason = origin_reason;
                }

                public UserBeanX getUser() {
                    return user;
                }

                public void setUser(UserBeanX user) {
                    this.user = user;
                }

                public String getReason_desc() {
                    return reason_desc;
                }

                public void setReason_desc(String reason_desc) {
                    this.reason_desc = reason_desc;
                }

                public static class UserBeanX {
                    /**
                     * user_id : 17895
                     * description : 快乐，其实就这么简单！
                     * point : 0
                     * gender : 1
                     * followings : 0
                     * followers : 15085
                     * user_verified : false
                     * is_follower : false
                     * name : 鹅巨大
                     * ugc_count : 46
                     * avatar_url : http://p3.pstatp.com/medium/624/3591842938
                     * create_time : 1333022332
                     * pro_user_desc :
                     * large_avatar_url : http://p3.pstatp.com/large/624/3591842938
                     * is_pro_user : false
                     * is_following : false
                     * id : 17895
                     * screen_name : 鹅巨大
                     */

                    private int user_id;
                    private String description;
                    private int point;
                    private int gender;
                    private int followings;
                    private int followers;
                    private boolean user_verified;
                    private boolean is_follower;
                    private String name;
                    private int ugc_count;
                    private String avatar_url;
                    private int create_time;
                    private String pro_user_desc;
                    private String large_avatar_url;
                    private boolean is_pro_user;
                    private boolean is_following;
                    private int id;
                    private String screen_name;

                    public int getUser_id() {
                        return user_id;
                    }

                    public void setUser_id(int user_id) {
                        this.user_id = user_id;
                    }

                    public String getDescription() {
                        return description;
                    }

                    public void setDescription(String description) {
                        this.description = description;
                    }

                    public int getPoint() {
                        return point;
                    }

                    public void setPoint(int point) {
                        this.point = point;
                    }

                    public int getGender() {
                        return gender;
                    }

                    public void setGender(int gender) {
                        this.gender = gender;
                    }

                    public int getFollowings() {
                        return followings;
                    }

                    public void setFollowings(int followings) {
                        this.followings = followings;
                    }

                    public int getFollowers() {
                        return followers;
                    }

                    public void setFollowers(int followers) {
                        this.followers = followers;
                    }

                    public boolean isUser_verified() {
                        return user_verified;
                    }

                    public void setUser_verified(boolean user_verified) {
                        this.user_verified = user_verified;
                    }

                    public boolean isIs_follower() {
                        return is_follower;
                    }

                    public void setIs_follower(boolean is_follower) {
                        this.is_follower = is_follower;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
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

                    public int getCreate_time() {
                        return create_time;
                    }

                    public void setCreate_time(int create_time) {
                        this.create_time = create_time;
                    }

                    public String getPro_user_desc() {
                        return pro_user_desc;
                    }

                    public void setPro_user_desc(String pro_user_desc) {
                        this.pro_user_desc = pro_user_desc;
                    }

                    public String getLarge_avatar_url() {
                        return large_avatar_url;
                    }

                    public void setLarge_avatar_url(String large_avatar_url) {
                        this.large_avatar_url = large_avatar_url;
                    }

                    public boolean isIs_pro_user() {
                        return is_pro_user;
                    }

                    public void setIs_pro_user(boolean is_pro_user) {
                        this.is_pro_user = is_pro_user;
                    }

                    public boolean isIs_following() {
                        return is_following;
                    }

                    public void setIs_following(boolean is_following) {
                        this.is_following = is_following;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getScreen_name() {
                        return screen_name;
                    }

                    public void setScreen_name(String screen_name) {
                        this.screen_name = screen_name;
                    }
                }
            }
        }
    }
}
