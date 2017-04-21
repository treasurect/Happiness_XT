package com.treasure_ct.happiness_xt.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/1.
 */

public class LifeBaseLocationQueryBean implements Serializable{

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
         * addr : 云南省西双版纳傣族自治州景洪市嘎洒镇傣家印象西,026乡道西南约348米
         * cell : 62041
         * googleLat : 22.01428
         * googleLng : 100.752143
         * id : 0_34860_62041
         * lac : 34860
         * lat : 22.0171
         * lng : 100.751
         * mcc : 460
         * mnc : 0
         * precision : 1500
         */

        private String addr;
        private int cell;
        private double googleLat;
        private double googleLng;
        private String id;
        private int lac;
        private double lat;
        private double lng;
        private int mcc;
        private int mnc;
        private int precision;

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public int getCell() {
            return cell;
        }

        public void setCell(int cell) {
            this.cell = cell;
        }

        public double getGoogleLat() {
            return googleLat;
        }

        public void setGoogleLat(double googleLat) {
            this.googleLat = googleLat;
        }

        public double getGoogleLng() {
            return googleLng;
        }

        public void setGoogleLng(double googleLng) {
            this.googleLng = googleLng;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getLac() {
            return lac;
        }

        public void setLac(int lac) {
            this.lac = lac;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public int getMcc() {
            return mcc;
        }

        public void setMcc(int mcc) {
            this.mcc = mcc;
        }

        public int getMnc() {
            return mnc;
        }

        public void setMnc(int mnc) {
            this.mnc = mnc;
        }

        public int getPrecision() {
            return precision;
        }

        public void setPrecision(int precision) {
            this.precision = precision;
        }
    }
}
