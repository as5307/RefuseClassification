package com.umeng.soexample.bean;





public class Guide  {
    /*
    指南
    */
    private String type;
    private String definition;
    private String goods;

    private String request;

    private int icon;
    private int color;


    public String getType() {
        return type;
    }


    public int getIcon() {
        return icon;
    }


    public Guide(String type, String definition, String goods, String request, int color,int icon) {
        this.type = type;
        this.definition = definition;
        this.goods = goods;
        this.request = request;
        this.color = color;
        this.icon=icon;
    }

    public String getTitle() {
        return type;
    }


    public String getDefinition() {
        return definition;
    }

    public String getGoods() {
        return goods;
    }

    public String getRequest() {
        return request;
    }


    public int getColor() {
        return color;
    }


    @Override
    public String toString() {
        return "Guide{" +
                "type='" +type + '\'' +
                ", definition=" +definition+
                ", goods=" +goods +
                ", request=" +request +
                ", color=" + color +
                "' icon="+icon+'}';
    }
}
