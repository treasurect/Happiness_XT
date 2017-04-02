package com.treasure_ct.happiness_xt.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */

public class AssistantPostCode2Bean implements Serializable{

    private String msg;
    private String retCode;
    private List<ResultBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * address : ["葡萄街","沟西路","北塔街","警民路","南屏街","中枢路","站前街","沙雅路","西园街","东林街","安康路","中枢北路","铁三小路","西站七街","西站五街","西站八街","西站六街","机务段路","车辆段路","北站公路","小地窝村","西站十二街","西站十四街","西站十一街","西站十五街","三十五户路","8街8街住宅小区","5街5街住宅小区","6街6街住宅小区","11街11街住宅小区","头屯河农场王家沟6队","12街12街住宅小区","15街15街住宅小区","14街14街住宅小区","乌昌路(10号，18号#)"]
         * cId : 4001
         * city : 乌鲁木齐市
         * dId : 40011
         * district : 头屯河区
         * pId : 40
         * postNumber : 830023
         * province : 新疆维吾尔自治区
         * size : 35
         */

        private String cId;
        private String city;
        private String dId;
        private String district;
        private String pId;
        private String postNumber;
        private String province;
        private String size;
        private List<String> address;

        public String getCId() {
            return cId;
        }

        public void setCId(String cId) {
            this.cId = cId;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDId() {
            return dId;
        }

        public void setDId(String dId) {
            this.dId = dId;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getPId() {
            return pId;
        }

        public void setPId(String pId) {
            this.pId = pId;
        }

        public String getPostNumber() {
            return postNumber;
        }

        public void setPostNumber(String postNumber) {
            this.postNumber = postNumber;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public List<String> getAddress() {
            return address;
        }

        public void setAddress(List<String> address) {
            this.address = address;
        }
    }
}
