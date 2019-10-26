package com.umeng.soexample.bean;

import cn.bmob.v3.BmobObject;

public class User extends BmobObject {
    private String name;
    private String imageUrl;
    private String userID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }
}