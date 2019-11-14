package com.umeng.soexample.bean;

import java.io.Serializable;

public class Result implements Serializable {
    private String keyword;
    private String score;
    private String type;

    public Result(String keyword, String score, String type) {
        this.keyword = keyword;
        this.score = score;
        this.type = type;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getScore() {
        return score;
    }


    public String getType() {
        return type;
    }
}
