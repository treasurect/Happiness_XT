package com.treasure_ct.happiness_xt.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by treasure on 2016.11.12.
 */

public class LifeDeliciousListBean implements Serializable{

    private String code;
    private String msg;
    private String version;
    private long timestamp;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * page : 1
         * size : 20
         * total : 469
         * count : 20
         * data : [{"image":"http://img.szzhangchu.com/1467129959730_2586575900.JPG","material_video":"http://video.szzhangchu.com/nanguaroudingdunfanA.mp4","process_video":"http://video.szzhangchu.com/nanguaroudingdunfanB.mp4","dishes_id":15539,"dishes_name":"南瓜肉丁炖饭","dishes_desc":"米饭和南瓜都炖得很软，老人小孩也很适合食用哦！","share_url":"http://m.izhangchu.com/web/dishes_view/index.html?&dishes_id=15539","like":0,"tags_info":[{"id":289,"text":"增强免疫力","type":2},{"id":181,"text":"大米","type":2},{"id":322,"text":"淡","type":2}],"comoditys":[]},{"image":"http://img.szzhangchu.com/1466225308214_2588952340.jpg","material_video":"http://video.szzhangchu.com/shuiguoxuegaotaA.mp4","process_video":"http://video.szzhangchu.com/shuiguoxuegaotaB.mp4","dishes_id":15490,"dishes_name":"水果雪糕塔","dishes_desc":"炎炎夏日，宅在家里煲剧，再来一个水果冰激凌Party，还有什么比这种生活更休闲惬意了呢？","share_url":"http://m.izhangchu.com/web/dishes_view/index.html?&dishes_id=15490","like":0,"tags_info":[{"id":293,"text":"养心润肺","type":2},{"id":280,"text":"美白","type":2},{"id":233,"text":"下午茶","type":2},{"id":163,"text":"瓜果类","type":2},{"id":317,"text":"甜","type":2}],"comoditys":[]},{"image":"http://img.szzhangchu.com/1439973038774_4103438493.JPG","material_video":"http://video.szzhangchu.com/huotuijuanA.mp4","process_video":"http://video.szzhangchu.com/huotuijuanB.mp4","dishes_id":8382,"dishes_name":"火腿卷","dishes_desc":"梦想是一根长长线，在苍白的时空中，它是那一抹红，它是那耀眼夺目。","share_url":"http://m.izhangchu.com/web/dishes_view/index.html?&dishes_id=8382","like":0,"tags_info":[{"id":289,"text":"增强免疫力","type":2},{"id":224,"text":"包子馒头花卷","type":2},{"id":321,"text":"鲜","type":2},{"id":328,"text":"蒸","type":2}],"comoditys":[]},{"image":"http://img.szzhangchu.com/1439432606864_7838194455.JPG","material_video":"http://video.szzhangchu.com/haidainiuroutangA.mp4","process_video":"http://video.szzhangchu.com/haidainiuroutangB.mp4","dishes_id":8534,"dishes_name":"海带牛肉汤","dishes_desc":"还记得小时候，早上的早餐，永远都是一碗热腾腾的海带牛肉汤加上白白胖胖的馒头，满满都是幸福感。","share_url":"http://m.izhangchu.com/web/dishes_view/index.html?&dishes_id=8534","like":0,"tags_info":[{"id":321,"text":"鲜","type":2},{"id":231,"text":"简易午餐","type":2},{"id":332,"text":"煮","type":2},{"id":399,"text":"荤菜汤","type":2},{"id":296,"text":"益气补血","type":2}],"comoditys":[]},{"image":"http://img.szzhangchu.com/1446804013434_2899887751.jpg","material_video":"http://video.szzhangchu.com/fashishalayugaliganbeiA.mp4","process_video":"http://video.szzhangchu.com/fashishalayugaliganbeiB.mp4","dishes_id":14614,"dishes_name":"法式沙拉与咖喱干贝","dishes_desc":"不用一清早就在厨房热得汗流浃背，让沙拉王子和干贝公主告诉你属于它们的法式浪漫，给你不一样的清晨。","share_url":"http://m.izhangchu.com/web/dishes_view/index.html?&dishes_id=14614","like":0,"tags_info":[{"id":346,"text":"小鲜肉","type":2},{"id":169,"text":"贝","type":2},{"id":321,"text":"鲜","type":2},{"id":230,"text":"营养早餐","type":2},{"id":378,"text":"早餐","type":2}],"comoditys":[]},{"image":"http://img.szzhangchu.com/1450773158962_6465352948.jpg","material_video":"http://video.szzhangchu.com/lakaomalingshuA.mp4","process_video":"http://video.szzhangchu.com/lakaomalingshuB.mp4","dishes_id":14735,"dishes_name":"辣烤马铃薯","dishes_desc":"爱吃土豆的，可变换无数的花样来捯饬土豆，只要你有新意，就能整出一道不一样的风味土豆。","share_url":"http://m.izhangchu.com/web/dishes_view/index.html?&dishes_id=14735","like":0,"tags_info":[{"id":407,"text":"午餐","type":2},{"id":159,"text":"根茎类","type":2},{"id":333,"text":"烤","type":2},{"id":295,"text":"开胃消食","type":2},{"id":326,"text":"炒","type":2}],"comoditys":[]},{"image":"http://img.szzhangchu.com/1464442983908_9476150023.JPG","material_video":"http://video.szzhangchu.com/boluoshijinshuiguotianpinA.mp4","process_video":"http://video.szzhangchu.com/boluoshijinshuiguotianpinB.mp4","dishes_id":15133,"dishes_name":"菠萝什锦水果甜品","dishes_desc":"休闲的周末，最是喜欢自己在厨房里小忙活，享受创造美食的乐趣。","share_url":"http://m.izhangchu.com/web/dishes_view/index.html?&dishes_id=15133","like":0,"tags_info":[{"id":317,"text":"甜","type":2},{"id":302,"text":"补铁","type":2},{"id":233,"text":"下午茶","type":2},{"id":295,"text":"开胃消食","type":2}],"comoditys":[]},{"image":"http://img.szzhangchu.com/1445250977070_9277864229.JPG","material_video":"http://video.szzhangchu.com/gailanchaochashaoA.mp4","process_video":"http://video.szzhangchu.com/gailanchaochashaoB.mp4","dishes_id":14593,"dishes_name":"芥蓝炒叉烧","dishes_desc":"芥蓝碧绿爽脆，叉烧肥瘦相间，快手小菜，色香味俱全。","share_url":"http://m.izhangchu.com/web/dishes_view/index.html?&dishes_id=14593","like":0,"tags_info":[{"id":295,"text":"开胃消食","type":2},{"id":232,"text":"晚餐","type":2},{"id":209,"text":"广东小吃","type":2},{"id":320,"text":"咸","type":2},{"id":261,"text":"粤菜","type":2}],"comoditys":[]},{"image":"http://img.szzhangchu.com/1466227444858_3208702878.jpg","material_video":"http://video.szzhangchu.com/qiezhikaoqieziA.mp4","process_video":"http://video.szzhangchu.com/qiezhikaoqieziB.mp4","dishes_id":15497,"dishes_name":"茄汁烤茄子","dishes_desc":"带上茄子去厨房捣鼓几道好菜，今天就弄一道烤茄子吧~","share_url":"http://m.izhangchu.com/web/dishes_view/index.html?&dishes_id=15497","like":0,"tags_info":[{"id":289,"text":"增强免疫力","type":2},{"id":315,"text":"调节三高","type":2},{"id":333,"text":"烤","type":2},{"id":321,"text":"鲜","type":2},{"id":163,"text":"瓜果类","type":2}],"comoditys":[]},{"image":"http://img.szzhangchu.com/1440656394160_8896836158.jpg","material_video":"http://video.szzhangchu.com/paojiaoluobodunniunanA.mp4","process_video":"http://video.szzhangchu.com/paojiaoluobodunniunanB.mp4","dishes_id":13753,"dishes_name":"泡椒萝卜炖牛腩","dishes_desc":"牛腩的鲜香，萝卜的爽脆，加上酸辣的泡椒，让人吃得微微冒汗。一顿吃下来从里暖到外，适合冬天吃的首选暖身菜。","share_url":"http://m.izhangchu.com/web/dishes_view/index.html?&dishes_id=13753","like":0,"tags_info":[{"id":289,"text":"增强免疫力","type":2},{"id":331,"text":"炖","type":2},{"id":398,"text":"肉类","type":2},{"id":152,"text":"牛肉","type":2},{"id":321,"text":"鲜","type":2}],"comoditys":[]},{"image":"http://img.szzhangchu.com/1464425841743_9631591160.jpg","material_video":"http://video.szzhangchu.com/juanxincaibiandoushalaA.mp4","process_video":"http://video.szzhangchu.com/juanxincaibiandoushalaB.mp4","dishes_id":15089,"dishes_name":"卷心菜扁豆沙拉","dishes_desc":"将蔬菜园放进小小的玻璃杯中，随身携带，随时品尝。","share_url":"http://m.izhangchu.com/web/dishes_view/index.html?&dishes_id=15089","like":0,"tags_info":[{"id":316,"text":"酸","type":2},{"id":295,"text":"开胃消食","type":2},{"id":160,"text":"叶菜类","type":2},{"id":323,"text":"拌","type":2}],"comoditys":[]},{"image":"http://img.szzhangchu.com/1439435519154_9018501275.jpg","material_video":"http://video.szzhangchu.com/hongzaoshanyaopaigutangA.mp4","process_video":"http://video.szzhangchu.com/hongzaoshanyaopaigutangB.mp4","dishes_id":13081,"dishes_name":"红枣山药排骨汤","dishes_desc":"绵绵的山药化在浓骨汤的醇香中，口口香甜清新，无油腻感，真是怎么爱你都不嫌多。","share_url":"http://m.izhangchu.com/web/dishes_view/index.html?&dishes_id=13081","like":0,"tags_info":[{"id":314,"text":"健胃消食","type":2},{"id":399,"text":"荤菜汤","type":2},{"id":151,"text":"猪肉","type":2},{"id":380,"text":"靓汤","type":2}],"comoditys":[]},{"image":"http://img.szzhangchu.com/1464446288690_1142112937.JPG","material_video":"http://video.szzhangchu.com/pidongbanqingguaA.mp4","process_video":"http://video.szzhangchu.com/pidongbanqingguaB.mp4","dishes_id":15146,"dishes_name":"皮冻拌青瓜","dishes_desc":"要来点透心凉的，皮冻青瓜怎样，荤素相搭，既能填饱肚子，又能吃得美美哒。","share_url":"http://m.izhangchu.com/web/dishes_view/index.html?&dishes_id=15146","like":0,"tags_info":[{"id":320,"text":"咸","type":2},{"id":292,"text":"美容养颜","type":2},{"id":323,"text":"拌","type":2}],"comoditys":[]},{"image":"http://img.szzhangchu.com/1440989545044_2323782644.jpg","material_video":"http://video.szzhangchu.com/wuxiangjichiA.mp4","process_video":"http://video.szzhangchu.com/wuxiangjichiB.mp4","dishes_id":11560,"dishes_name":"五香鸡翅","dishes_desc":"全新的美味五香鸡翅，滑嫩的鸡肉中浸透着香料的精华，简单易学，绝对令你爱不释\u201c口\u201d。","share_url":"http://m.izhangchu.com/web/dishes_view/index.html?&dishes_id=11560","like":0,"tags_info":[{"id":289,"text":"增强免疫力","type":2},{"id":328,"text":"蒸","type":2},{"id":154,"text":"鸡肉","type":2},{"id":321,"text":"鲜","type":2}],"comoditys":[]},{"image":"http://img.szzhangchu.com/1439368573346_7449437258.JPG","material_video":"http://video.szzhangchu.com/yangrouhuoguoA.mp4","process_video":"http://video.szzhangchu.com/yangrouhuoguoB.mp4","dishes_id":5804,"dishes_name":"羊肉火锅","dishes_desc":"麻辣鲜香的羊肉火锅牢牢抓住你的胃，让你这个季节不再寒冷。三五好友，热气腾腾地吃得满嘴汤汁、浑身出汗，不亦乐乎？","share_url":"http://m.izhangchu.com/web/dishes_view/index.html?&dishes_id=5804","like":0,"tags_info":[{"id":319,"text":"辣","type":2},{"id":341,"text":"大叔","type":2},{"id":153,"text":"羊肉","type":2},{"id":294,"text":"保肝护肾","type":2}],"comoditys":[]},{"image":"http://img.szzhangchu.com/1466350007418_8240658599.JPG","material_video":"http://video.szzhangchu.com/jiangyoujiA.mp4","process_video":"http://video.szzhangchu.com/jiangyoujiB.mp4","dishes_id":15510,"dishes_name":"酱油鸡","dishes_desc":"鸡肉和姜葱向来是经典的搭配，在姜葱的基础上加入红椒粒，制成的酱汁更加惹味，让鸡肉的鲜美进一步升华。","share_url":"http://m.izhangchu.com/web/dishes_view/index.html?&dishes_id=15510","like":0,"tags_info":[{"id":154,"text":"鸡肉","type":2},{"id":320,"text":"咸","type":2},{"id":328,"text":"蒸","type":2}],"comoditys":[]},{"image":"http://img.szzhangchu.com/1464437906397_8479038377.JPG","material_video":"http://video.szzhangchu.com/xuelihongtangdunanchundanA.mp4","process_video":"http://video.szzhangchu.com/xuelihongtangdunanchundanB.mp4","dishes_id":15118,"dishes_name":"雪梨红糖炖鹌鹑蛋","dishes_desc":"西方有红酒炖雪梨，东方则有红糖炖雪梨，两者谁更能打动你呢，赶紧试一试吧。","share_url":"http://m.izhangchu.com/web/dishes_view/index.html?&dishes_id=15118","like":0,"tags_info":[{"id":317,"text":"甜","type":2},{"id":296,"text":"益气补血","type":2},{"id":332,"text":"煮","type":2}],"comoditys":[]},{"image":"http://img.szzhangchu.com/1465636934781_3573902476.JPG","material_video":"http://video.szzhangchu.com/youlaozaoA.mp4","process_video":"http://video.szzhangchu.com/youlaozaoB.mp4","dishes_id":15362,"dishes_name":"油醪糟","dishes_desc":"营养又美味，休闲好滋味，补充能量的绝佳选择。","share_url":"http://m.izhangchu.com/web/dishes_view/index.html?&dishes_id=15362","like":0,"tags_info":[{"id":332,"text":"煮","type":2},{"id":317,"text":"甜","type":2},{"id":273,"text":"产后坐月子","type":2}],"comoditys":[]},{"image":"http://img.szzhangchu.com/1439364261877_6776588454.jpg","material_video":"http://video.szzhangchu.com/guangshilaweibaozaifanA.mp4","process_video":"http://video.szzhangchu.com/guangshilaweibaozaifanB.mp4","dishes_id":11527,"dishes_name":"广式腊味煲仔饭","dishes_desc":"风靡广州大街小巷的传统腊味饭，香味独特，而且米饭也带了肉香，没有丝毫油腻感，令人食欲大增\u2026","share_url":"http://m.izhangchu.com/web/dishes_view/index.html?&dishes_id=11527","like":0,"tags_info":[{"id":295,"text":"开胃消食","type":2},{"id":222,"text":"米饭","type":2},{"id":332,"text":"煮","type":2},{"id":344,"text":"厨师","type":2},{"id":321,"text":"鲜","type":2}],"comoditys":[]},{"image":"http://img.szzhangchu.com/1467124919532_8245212079.JPG","material_video":"http://video.szzhangchu.com/jitangwawacaiA.mp4","process_video":"http://video.szzhangchu.com/jitangwawacaiB.mp4","dishes_id":15535,"dishes_name":"鸡汤娃娃菜杯","dishes_desc":"清爽软嫩的娃娃菜，吸收了鸡汤的鲜美，太好吃了！","share_url":"http://m.izhangchu.com/web/dishes_view/index.html?&dishes_id=15535","like":0,"tags_info":[{"id":380,"text":"靓汤","type":2},{"id":321,"text":"鲜","type":2},{"id":328,"text":"蒸","type":2},{"id":291,"text":"瘦身排毒","type":2}],"comoditys":[]}]
         */

        private int page;
        private int size;
        private int total;
        private int count;
        private List<DataBean> data;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * image : http://img.szzhangchu.com/1467129959730_2586575900.JPG
             * material_video : http://video.szzhangchu.com/nanguaroudingdunfanA.mp4
             * process_video : http://video.szzhangchu.com/nanguaroudingdunfanB.mp4
             * dishes_id : 15539
             * dishes_name : 南瓜肉丁炖饭
             * dishes_desc : 米饭和南瓜都炖得很软，老人小孩也很适合食用哦！
             * share_url : http://m.izhangchu.com/web/dishes_view/index.html?&dishes_id=15539
             * like : 0
             * tags_info : [{"id":289,"text":"增强免疫力","type":2},{"id":181,"text":"大米","type":2},{"id":322,"text":"淡","type":2}]
             * comoditys : []
             */

            private String image;
            private String material_video;
            private String process_video;
            private int dishes_id;
            private String dishes_name;
            private String dishes_desc;
            private String share_url;
            private int like;
            private List<TagsInfoBean> tags_info;
            private List<?> comoditys;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
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

            public int getDishes_id() {
                return dishes_id;
            }

            public void setDishes_id(int dishes_id) {
                this.dishes_id = dishes_id;
            }

            public String getDishes_name() {
                return dishes_name;
            }

            public void setDishes_name(String dishes_name) {
                this.dishes_name = dishes_name;
            }

            public String getDishes_desc() {
                return dishes_desc;
            }

            public void setDishes_desc(String dishes_desc) {
                this.dishes_desc = dishes_desc;
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

            public List<?> getComoditys() {
                return comoditys;
            }

            public void setComoditys(List<?> comoditys) {
                this.comoditys = comoditys;
            }

            public static class TagsInfoBean {
                /**
                 * id : 289
                 * text : 增强免疫力
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
        }
    }
}
