package com.treasure_ct.happiness_xt.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by treasure on 2017.04.12.
 */

public class PushBean extends BmobObject{
    private String label;
    private String title;
    private String message;
    private String top_title;
    private String url;
    private String image_url;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTop_title() {
        return top_title;
    }

    public void setTop_title(String top_title) {
        this.top_title = top_title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
