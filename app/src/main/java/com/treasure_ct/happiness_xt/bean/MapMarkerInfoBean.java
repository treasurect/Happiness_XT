package com.treasure_ct.happiness_xt.bean;

import com.baidu.mapapi.model.LatLng;

import java.io.Serializable;

/**
 * Created by treasure on 2017.05.19.
 */

public class MapMarkerInfoBean implements Serializable{
    private LatLng latLng;
    private String name;
    private String address;

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
