package com.treasure_ct.happiness_xt.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by treasure on 2017.04.13.
 */

public class FeedBackBean extends BmobObject{
    private String user_name;
    private String user_nick;
    private String content;
    private String contacts;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_nick() {
        return user_nick;
    }

    public void setUser_nick(String user_nick) {
        this.user_nick = user_nick;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
}
