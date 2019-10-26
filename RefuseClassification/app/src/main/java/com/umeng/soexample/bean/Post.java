package com.umeng.soexample.bean;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

/*
帖子
*/

public class Post extends BmobObject {
    private String userIcon; //头像
    private String userName;  // 名字
    private String content;   // 说说内容

    private String headImgUrl; //图片的UR
    private boolean haveIcon;  //判断是否有图片

    private BmobRelation vote;


    public BmobRelation getVote() {
        return vote;
    }

    public void setVote(BmobRelation vote) {
        this.vote = vote;
    }



    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }



    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public boolean isHaveIcon() {
        return haveIcon;
    }

    public void setHaveIcon(boolean haveIcon) {
        this.haveIcon = haveIcon;
    }


}
