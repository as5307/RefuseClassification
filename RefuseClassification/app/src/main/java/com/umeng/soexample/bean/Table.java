package com.umeng.soexample.bean;


import java.io.Serializable;

public class Table implements Serializable {
    private String name;
    private String type;
    private String answer;

    public Table(String name, String type, String answer) {
        this.name = name;
        this.type = type;
        this.answer = answer;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }


    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
