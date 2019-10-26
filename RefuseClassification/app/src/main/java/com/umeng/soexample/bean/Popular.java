package com.umeng.soexample.bean;

public class Popular {
    /**
     * name : 水果
     * type : 2
     * index : 11144
     */
    private String name;

    private int type;
    private int index;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Popular{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", index=" + index +
                '}';
    }
}

