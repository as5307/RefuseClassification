package com.umeng.soexample.bean;

public class Information {

    /**
     * ctime : 2019-07-08 05:58:28
     * title : 女子独自一人深夜约见男网友 却被困民房五小时 结果被伤害四次
     * description : 女子独自一人深夜约见男网友 却被困民房五小时 结果被伤害四次
     * picUrl : http://p6-tt.byteimg.com/list/tos-cn-i-0000/9aa800e29be911e9b2977cd30a55d46c
     * url : http://toutiao.com/group/6708637225535406606/
     */

    private String ctime;
    private String title;
    private String description;
    private String picUrl;
    private String url;

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Information{" +
                "ctime='" + ctime + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}

