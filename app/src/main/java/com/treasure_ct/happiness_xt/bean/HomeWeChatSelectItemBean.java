package com.treasure_ct.happiness_xt.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by treasure on 2017.04.21.
 */

public class HomeWeChatSelectItemBean implements Serializable{

    private String msg;
    private ResultBean result;
    private String retCode;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public static class ResultBean {
        /**
         * curPage : 1
         * list : [{"cid":"9","id":"4393605","pubTime":"2016-12-15 01:03","sourceUrl":"http://www.yinews.cn/article/4393605.shtm","subTitle":"欧洲游，令国人倍觉新鲜的方式（组图）","title":"欧洲游，令国人倍觉新鲜的方式（组图）"},{"cid":"9","id":"4393061","pubTime":"2016-12-14 21:29","sourceUrl":"http://www.yinews.cn/article/4393061.shtm","subTitle":"丽江古城内客栈那么多，我为什么选择这一家","title":"丽江古城内客栈那么多，我为什么选择这一家"},{"cid":"9","id":"4394480","pubTime":"2016-12-14 20:08","sourceUrl":"http://www.yinews.cn/article/4394480.shtm","subTitle":"冬天最温暖的八大旅行地，让你重温明媚的阳光！","title":"冬天最温暖的八大旅行地，让你重温明媚的阳光！"},{"cid":"9","id":"4393998","pubTime":"2016-12-14 19:52","sourceUrl":"http://www.yinews.cn/article/4393998.shtm","subTitle":"西安的回民街到底值不值得去？","title":"西安的回民街到底值不值得去？"},{"cid":"9","id":"4392981","pubTime":"2016-12-14 19:45","sourceUrl":"http://www.yinews.cn/article/4392981.shtm","subTitle":"福建最难发展的两个城市","title":"福建最难发展的两个城市"},{"cid":"9","id":"4392571","pubTime":"2016-12-14 19:44","sourceUrl":"http://www.yinews.cn/article/4392571.shtm","subTitle":"日本牛人用硬币颠覆地心引力，就算牛顿看了也发懵！","title":"日本牛人用硬币颠覆地心引力，就算牛顿看了也发懵！"},{"cid":"9","id":"4392569","pubTime":"2016-12-14 19:44","sourceUrl":"http://www.yinews.cn/article/4392569.shtm","subTitle":"日本那个容易被忽视的长野县，使用正确方式打开后居然这么棒？！","title":"日本那个容易被忽视的长野县，使用正确方式打开后居然这么棒？！"},{"cid":"9","id":"4392568","pubTime":"2016-12-14 19:44","sourceUrl":"http://www.yinews.cn/article/4392568.shtm","subTitle":"日本主妇都爱用的一款除臭剂，8倍除臭能力99%消除异味！","title":"日本主妇都爱用的一款除臭剂，8倍除臭能力99%消除异味！"},{"cid":"9","id":"4392567","pubTime":"2016-12-14 19:44","sourceUrl":"http://www.yinews.cn/article/4392567.shtm","subTitle":"日本东京表参道绘本风梦幻糖果屋展，简直是\u201c甜死人不偿命\u201d！","title":"日本东京表参道绘本风梦幻糖果屋展，简直是\u201c甜死人不偿命\u201d！"},{"cid":"9","id":"4392566","pubTime":"2016-12-14 19:44","sourceUrl":"http://www.yinews.cn/article/4392566.shtm","subTitle":"今年冬天日本妹子们都在优衣库买了什么好物？温暖过冬就靠它们啦！","title":"今年冬天日本妹子们都在优衣库买了什么好物？温暖过冬就靠它们啦！"},{"cid":"9","id":"4392902","pubTime":"2016-12-14 18:43","sourceUrl":"http://www.yinews.cn/article/4392902.shtm","subTitle":"安徽省的五座\u201c国家历史文化名城\u201d！","title":"安徽省的五座\u201c国家历史文化名城\u201d！"},{"cid":"9","id":"4393453","pubTime":"2016-12-14 18:25","sourceUrl":"http://www.yinews.cn/article/4393453.shtm","subTitle":"安徽这11个地方入选国家全域旅游示范区！有没有你家乡？","title":"安徽这11个地方入选国家全域旅游示范区！有没有你家乡？"},{"cid":"9","id":"4392333","pubTime":"2016-12-14 18:23","sourceUrl":"http://www.yinews.cn/article/4392333.shtm","subTitle":"山西省最有发展潜力的4个县","title":"山西省最有发展潜力的4个县"},{"cid":"9","id":"4394371","pubTime":"2016-12-14 18:19","sourceUrl":"http://www.yinews.cn/article/4394371.shtm","subTitle":"半价！16日起，常州、镇江人都去这里旅行！","title":"半价！16日起，常州、镇江人都去这里旅行！"},{"cid":"9","id":"4392714","pubTime":"2016-12-14 18:19","sourceUrl":"http://www.yinews.cn/article/4392714.shtm","subTitle":"走进历史与美食的眷顾者\u2014西安 三人行 6天5夜欢乐之旅","title":"走进历史与美食的眷顾者\u2014西安 三人行 6天5夜欢乐之旅"},{"cid":"9","id":"4393287","pubTime":"2016-12-14 18:12","sourceUrl":"http://www.yinews.cn/article/4393287.shtm","subTitle":"不容错过的考山路","title":"不容错过的考山路"},{"cid":"9","id":"4392242","pubTime":"2016-12-14 18:03","sourceUrl":"http://www.yinews.cn/article/4392242.shtm","subTitle":"广东野温泉独家探路地图，这才叫泡温泉，你那叫洗热水澡！","title":"广东野温泉独家探路地图，这才叫泡温泉，你那叫洗热水澡！"},{"cid":"9","id":"4393542","pubTime":"2016-12-14 17:52","sourceUrl":"http://www.yinews.cn/article/4393542.shtm","subTitle":"金堂故事｜创业，在云顶山","title":"金堂故事｜创业，在云顶山"},{"cid":"9","id":"4392335","pubTime":"2016-12-14 17:51","sourceUrl":"http://www.yinews.cn/article/4392335.shtm","subTitle":"看完印度最干净的城市 再看中国的，差距还是挺大的","title":"看完印度最干净的城市 再看中国的，差距还是挺大的"},{"cid":"9","id":"4392505","pubTime":"2016-12-14 17:50","sourceUrl":"http://www.yinews.cn/article/4392505.shtm","subTitle":"新疆的9个\u201c直管市\u201d","title":"新疆的9个\u201c直管市\u201d"}]
         * total : 14842
         */

        private int curPage;
        private int total;
        private List<ListBean> list;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * cid : 9
             * id : 4393605
             * pubTime : 2016-12-15 01:03
             * sourceUrl : http://www.yinews.cn/article/4393605.shtm
             * subTitle : 欧洲游，令国人倍觉新鲜的方式（组图）
             * title : 欧洲游，令国人倍觉新鲜的方式（组图）
             */

            private String cid;
            private String id;
            private String pubTime;
            private String sourceUrl;
            private String subTitle;
            private String title;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPubTime() {
                return pubTime;
            }

            public void setPubTime(String pubTime) {
                this.pubTime = pubTime;
            }

            public String getSourceUrl() {
                return sourceUrl;
            }

            public void setSourceUrl(String sourceUrl) {
                this.sourceUrl = sourceUrl;
            }

            public String getSubTitle() {
                return subTitle;
            }

            public void setSubTitle(String subTitle) {
                this.subTitle = subTitle;
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
