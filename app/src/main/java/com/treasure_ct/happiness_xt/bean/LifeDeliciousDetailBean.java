package com.treasure_ct.happiness_xt.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by treasure on 2016.11.13.
 */

public class LifeDeliciousDetailBean implements Serializable{

    private String code;
    private String msg;
    private String version;
    private long timestamp;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * dashes_id : 8382
         * dashes_name : 火腿卷
         * material_video : http://video.szzhangchu.com/huotuijuanA.mp4
         * process_video : http://video.szzhangchu.com/huotuijuanB.mp4
         * hard_level : 较难
         * taste : 鲜
         * cooke_time : 55分钟
         * image : http://img.szzhangchu.com/1439973038774_4103438493.JPG
         * material_desc : 梦想是一根长长线，在苍白的时空中，它是那一抹红，它是那耀眼夺目。
         * share_amount : 0
         * dishes_name : 火腿卷
         * dishes_title : 裹住的火腿裹不住的美味
         * dishes_id : 8382
         * cooking_time : 55分钟
         * collect_count : 5896
         * click_count : 13009
         * create_date : 2010-01-18
         * last_update : 2017-04-25
         * comment_count : 3
         * agreement_amount : 297
         * tags_info : [{"id":321,"text":"鲜","type":2},{"id":289,"text":"增强免疫力","type":2},{"id":328,"text":"蒸","type":2},{"id":224,"text":"包子馒头花卷","type":2}]
         * share_url : http://m.izhangchu.com/web/dishes_view/index.html?&dishes_id=8382
         * step : [{"dishes_step_id":"175189","dishes_id":"8382","dishes_step_order":"1","dishes_step_image":"http://img.szzhangchu.com/20140828143919_0.jpg","dishes_step_desc":"将低筋面粉倒在操作台上，用刮板开窝。"},{"dishes_step_id":"175190","dishes_id":"8382","dishes_step_order":"2","dishes_step_image":"http://img.szzhangchu.com/20140828143919_1.jpg","dishes_step_desc":"把细砂糖、酵母倒入水中。"},{"dishes_step_id":"175191","dishes_id":"8382","dishes_step_order":"3","dishes_step_image":"http://img.szzhangchu.com/20140828143919_2.jpg","dishes_step_desc":"将泡打粉倒入低筋面粉中。"},{"dishes_step_id":"175192","dishes_id":"8382","dishes_step_order":"4","dishes_step_image":"http://img.szzhangchu.com/20140828143919_3.jpg","dishes_step_desc":"拌匀，用刮板开窝。"},{"dishes_step_id":"175193","dishes_id":"8382","dishes_step_order":"5","dishes_step_image":"http://img.szzhangchu.com/20140828143919_4.jpg","dishes_step_desc":"把酵母、细砂糖搅拌均匀。"},{"dishes_step_id":"175194","dishes_id":"8382","dishes_step_order":"6","dishes_step_image":"http://img.szzhangchu.com/20140828143919_5.jpg","dishes_step_desc":"分三次倒入低筋面粉中，将材料搅拌匀。"},{"dishes_step_id":"175195","dishes_id":"8382","dishes_step_order":"7","dishes_step_image":"http://img.szzhangchu.com/20140828143919_6.jpg","dishes_step_desc":"把材料揉搓成面团。"},{"dishes_step_id":"175196","dishes_id":"8382","dishes_step_order":"8","dishes_step_image":"http://img.szzhangchu.com/20140828143919_7.jpg","dishes_step_desc":"把猪油放到面团中间。"},{"dishes_step_id":"175197","dishes_id":"8382","dishes_step_order":"9","dishes_step_image":"http://img.szzhangchu.com/20140828143919_8.jpg","dishes_step_desc":"将其揉搓成纯滑的面团。"},{"dishes_step_id":"175198","dishes_id":"8382","dishes_step_order":"10","dishes_step_image":"http://img.szzhangchu.com/20140828143919_9.jpg","dishes_step_desc":"用刮板取下一块面团。"},{"dishes_step_id":"175199","dishes_id":"8382","dishes_step_order":"11","dishes_step_image":"http://img.szzhangchu.com/20140828143919_10.jpg","dishes_step_desc":"用擀面杖将面团擀成长片"},{"dishes_step_id":"175200","dishes_id":"8382","dishes_step_order":"12","dishes_step_image":"http://img.szzhangchu.com/20140828143919_11.jpg","dishes_step_desc":"将火腿对半切开。"},{"dishes_step_id":"175201","dishes_id":"8382","dishes_step_order":"13","dishes_step_image":"http://img.szzhangchu.com/20140828143919_12.jpg","dishes_step_desc":"将面皮横放，用手揉搓成长条状。"},{"dishes_step_id":"175202","dishes_id":"8382","dishes_step_order":"14","dishes_step_image":"http://img.szzhangchu.com/20140828143919_13.jpg","dishes_step_desc":"用手摘成几个适量大小的剂子。"},{"dishes_step_id":"175203","dishes_id":"8382","dishes_step_order":"15","dishes_step_image":"http://img.szzhangchu.com/20140828143919_14.jpg","dishes_step_desc":"把剂子揉搓成细长条。"},{"dishes_step_id":"175204","dishes_id":"8382","dishes_step_order":"16","dishes_step_image":"http://img.szzhangchu.com/20140828143919_15.jpg","dishes_step_desc":"用揉好的面团将火腿缠绕成形。"},{"dishes_step_id":"175205","dishes_id":"8382","dishes_step_order":"17","dishes_step_image":"http://img.szzhangchu.com/20140828143919_16.jpg","dishes_step_desc":"切去多余的火腿，制成火腿卷生坯。"},{"dishes_step_id":"175206","dishes_id":"8382","dishes_step_order":"18","dishes_step_image":"http://img.szzhangchu.com/20140828143919_17.jpg","dishes_step_desc":"用包底纸包好火腿卷生坯，放入蒸笼，使其自然发酵40分钟。"},{"dishes_step_id":"175207","dishes_id":"8382","dishes_step_order":"19","dishes_step_image":"http://img.szzhangchu.com/20140828143919_18.jpg","dishes_step_desc":"蒸锅注入适量清水，用大火烧热。"},{"dishes_step_id":"175208","dishes_id":"8382","dishes_step_order":"20","dishes_step_image":"http://img.szzhangchu.com/20140828143919_19.jpg","dishes_step_desc":"将火腿卷生坯放入蒸锅中。"},{"dishes_step_id":"175209","dishes_id":"8382","dishes_step_order":"21","dishes_step_image":"http://img.szzhangchu.com/20140828143919_20.jpg","dishes_step_desc":"盖上盖，用大火蒸4分钟至熟。"},{"dishes_step_id":"175210","dishes_id":"8382","dishes_step_order":"22","dishes_step_image":"http://img.szzhangchu.com/20140828143919_21.jpg","dishes_step_desc":"取出蒸好的火腿卷即可。"}]
         * like : 0
         */

        private String dashes_id;
        private String dashes_name;
        private String material_video;
        private String process_video;
        private String hard_level;
        private String taste;
        private String cooke_time;
        private String image;
        private String material_desc;
        private String share_amount;
        private String dishes_name;
        private String dishes_title;
        private String dishes_id;
        private String cooking_time;
        private String collect_count;
        private String click_count;
        private String create_date;
        private String last_update;
        private String comment_count;
        private String agreement_amount;
        private String share_url;
        private int like;
        private List<TagsInfoBean> tags_info;
        private List<StepBean> step;

        public String getDashes_id() {
            return dashes_id;
        }

        public void setDashes_id(String dashes_id) {
            this.dashes_id = dashes_id;
        }

        public String getDashes_name() {
            return dashes_name;
        }

        public void setDashes_name(String dashes_name) {
            this.dashes_name = dashes_name;
        }

        public String getMaterial_video() {
            return material_video;
        }

        public void setMaterial_video(String material_video) {
            this.material_video = material_video;
        }

        public String getProcess_video() {
            return process_video;
        }

        public void setProcess_video(String process_video) {
            this.process_video = process_video;
        }

        public String getHard_level() {
            return hard_level;
        }

        public void setHard_level(String hard_level) {
            this.hard_level = hard_level;
        }

        public String getTaste() {
            return taste;
        }

        public void setTaste(String taste) {
            this.taste = taste;
        }

        public String getCooke_time() {
            return cooke_time;
        }

        public void setCooke_time(String cooke_time) {
            this.cooke_time = cooke_time;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getMaterial_desc() {
            return material_desc;
        }

        public void setMaterial_desc(String material_desc) {
            this.material_desc = material_desc;
        }

        public String getShare_amount() {
            return share_amount;
        }

        public void setShare_amount(String share_amount) {
            this.share_amount = share_amount;
        }

        public String getDishes_name() {
            return dishes_name;
        }

        public void setDishes_name(String dishes_name) {
            this.dishes_name = dishes_name;
        }

        public String getDishes_title() {
            return dishes_title;
        }

        public void setDishes_title(String dishes_title) {
            this.dishes_title = dishes_title;
        }

        public String getDishes_id() {
            return dishes_id;
        }

        public void setDishes_id(String dishes_id) {
            this.dishes_id = dishes_id;
        }

        public String getCooking_time() {
            return cooking_time;
        }

        public void setCooking_time(String cooking_time) {
            this.cooking_time = cooking_time;
        }

        public String getCollect_count() {
            return collect_count;
        }

        public void setCollect_count(String collect_count) {
            this.collect_count = collect_count;
        }

        public String getClick_count() {
            return click_count;
        }

        public void setClick_count(String click_count) {
            this.click_count = click_count;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public String getLast_update() {
            return last_update;
        }

        public void setLast_update(String last_update) {
            this.last_update = last_update;
        }

        public String getComment_count() {
            return comment_count;
        }

        public void setComment_count(String comment_count) {
            this.comment_count = comment_count;
        }

        public String getAgreement_amount() {
            return agreement_amount;
        }

        public void setAgreement_amount(String agreement_amount) {
            this.agreement_amount = agreement_amount;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public int getLike() {
            return like;
        }

        public void setLike(int like) {
            this.like = like;
        }

        public List<TagsInfoBean> getTags_info() {
            return tags_info;
        }

        public void setTags_info(List<TagsInfoBean> tags_info) {
            this.tags_info = tags_info;
        }

        public List<StepBean> getStep() {
            return step;
        }

        public void setStep(List<StepBean> step) {
            this.step = step;
        }

        public static class TagsInfoBean {
            /**
             * id : 321
             * text : 鲜
             * type : 2
             */

            private int id;
            private String text;
            private int type;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

        public static class StepBean {
            /**
             * dishes_step_id : 175189
             * dishes_id : 8382
             * dishes_step_order : 1
             * dishes_step_image : http://img.szzhangchu.com/20140828143919_0.jpg
             * dishes_step_desc : 将低筋面粉倒在操作台上，用刮板开窝。
             */

            private String dishes_step_id;
            private String dishes_id;
            private String dishes_step_order;
            private String dishes_step_image;
            private String dishes_step_desc;

            public String getDishes_step_id() {
                return dishes_step_id;
            }

            public void setDishes_step_id(String dishes_step_id) {
                this.dishes_step_id = dishes_step_id;
            }

            public String getDishes_id() {
                return dishes_id;
            }

            public void setDishes_id(String dishes_id) {
                this.dishes_id = dishes_id;
            }

            public String getDishes_step_order() {
                return dishes_step_order;
            }

            public void setDishes_step_order(String dishes_step_order) {
                this.dishes_step_order = dishes_step_order;
            }

            public String getDishes_step_image() {
                return dishes_step_image;
            }

            public void setDishes_step_image(String dishes_step_image) {
                this.dishes_step_image = dishes_step_image;
            }

            public String getDishes_step_desc() {
                return dishes_step_desc;
            }

            public void setDishes_step_desc(String dishes_step_desc) {
                this.dishes_step_desc = dishes_step_desc;
            }
        }
    }
}
