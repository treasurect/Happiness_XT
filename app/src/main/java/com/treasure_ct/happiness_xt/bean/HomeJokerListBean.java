package com.treasure_ct.happiness_xt.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by treasure on 2017.04.06.
 */

public class HomeJokerListBean implements Serializable{

    private MetaBean meta;
    private List<BodyBean> body;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class MetaBean {
        /**
         * id : http://api.3g.ifeng.com/clientShortNews?type=joke
         * type : list
         * expiredTime : 180000
         * pageSize : 3501
         */

        private String id;
        private String type;
        private int expiredTime;
        private int pageSize;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getExpiredTime() {
            return expiredTime;
        }

        public void setExpiredTime(int expiredTime) {
            this.expiredTime = expiredTime;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
    }

    public static class BodyBean {
        /**
         * id : shortNews_302535
         * type : shortNews
         * content : 朋友家是内陆的，不常见海鲜。这次来烟台我带她去超市买虾。这货指着一盘死虾问售货员：大姐这虾是活的么？售货员看了看她道：可能是活的吧。这不，天冷了，冬眠呢！这货就在那眨眼一个劲儿看。售货员问：给你称多少？这货瘪瘪嘴挤出一句：那算了，不打扰它休息了。售货员当时就凌乱了。笑抽了。。。
         * title : 凤凰新闻搞笑段子
         * shareTitle : 凤凰新闻搞笑段子:朋友家是内陆的，不常见海鲜。这次来烟台我
         * cid : 2
         * thumbnail :
         * cThumbnail :
         * source :
         * status : 1
         * shareUrl : http://share.iclient.ifeng.com/news/sharenews.f?&fromType=spider&aid=302535
         * commentsUrl : http://share.iclient.ifeng.com/news/sharenews.f?&fromType=spider&aid=302535
         * ctime : 2017-04-06 21:10:08
         * utime : 2017-04-06 21:10:08
         * img : []
         * staticImg :
         * link : {"type":"shortNews","url":"http://api.iclient.ifeng.com/clientShortNewsDetail?id=302535"}
         * praise : 209
         * tread : 12
         * comments : 0
         * commentsall : 0
         * likes : 2
         */

        private String id;
        private String type;
        private String content;
        private String title;
        private String shareTitle;
        private int cid;
        private String thumbnail;
        private String cThumbnail;
        private String source;
        private int status;
        private String shareUrl;
        private String commentsUrl;
        private String ctime;
        private String utime;
        private String staticImg;
        private LinkBean link;
        private String praise;
        private String tread;
        private int comments;
        private int commentsall;
        private int likes;
        private List<?> img;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getShareTitle() {
            return shareTitle;
        }

        public void setShareTitle(String shareTitle) {
            this.shareTitle = shareTitle;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getCThumbnail() {
            return cThumbnail;
        }

        public void setCThumbnail(String cThumbnail) {
            this.cThumbnail = cThumbnail;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getShareUrl() {
            return shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }

        public String getCommentsUrl() {
            return commentsUrl;
        }

        public void setCommentsUrl(String commentsUrl) {
            this.commentsUrl = commentsUrl;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getUtime() {
            return utime;
        }

        public void setUtime(String utime) {
            this.utime = utime;
        }

        public String getStaticImg() {
            return staticImg;
        }

        public void setStaticImg(String staticImg) {
            this.staticImg = staticImg;
        }

        public LinkBean getLink() {
            return link;
        }

        public void setLink(LinkBean link) {
            this.link = link;
        }

        public String getPraise() {
            return praise;
        }

        public void setPraise(String praise) {
            this.praise = praise;
        }

        public String getTread() {
            return tread;
        }

        public void setTread(String tread) {
            this.tread = tread;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public int getCommentsall() {
            return commentsall;
        }

        public void setCommentsall(int commentsall) {
            this.commentsall = commentsall;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public List<?> getImg() {
            return img;
        }

        public void setImg(List<?> img) {
            this.img = img;
        }

        public static class LinkBean {
            /**
             * type : shortNews
             * url : http://api.iclient.ifeng.com/clientShortNewsDetail?id=302535
             */

            private String type;
            private String url;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
