package com.umeng.soexample.bean;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

/*
帖子
*/

public class Post extends BmobObject {
    private String title;   // 标题
    private User user; //用户
    private String headImgUrl; //图片的UR
    private boolean haveIcon;  //判断是否有图片

    private BmobRelation rvote;
    private BmobRelation hvote;
    private BmobRelation wvote;
    private BmobRelation dvote;


    public BmobRelation getHvote() {
        return hvote;
    }

    public void setHvote(BmobRelation hvote) {
        this.hvote = hvote;
    }

    public BmobRelation getWvote() {
        return wvote;
    }

    public void setWvote(BmobRelation wvote) {
        this.wvote = wvote;
    }

    public BmobRelation getDvote() {
        return dvote;
    }

    public void setDvote(BmobRelation dvote) {
        this.dvote = dvote;
    }

    public BmobRelation getRvote() {
        return rvote;
    }

    public void setRvote(BmobRelation rvote) {
        this.rvote = rvote;
    }


    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public boolean isHaveIcon() {
        return haveIcon;
    }

    public void setHaveIcon(boolean haveIcon) {
        this.haveIcon = haveIcon;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", user=" + user +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", haveIcon=" + haveIcon +
                '}';
    }
}
