package com.umeng.soexample.bean;



public class Definition {
    private String name;

    /**
     * name : 隐形眼镜包装盒
     * type : 0
     * explain : 可回收垃圾是指适宜回收、可循环利用的生活废弃物。
     * contain : 常见包括各类废金属、玻璃瓶、易拉罐、饮料瓶、塑料玩具、书本、报纸、广告单、纸板箱、衣服、床上用品、电子产品等
     * tip : 轻投轻放；清洁干燥，避免污染，费纸尽量平整；立体包装物请清空内容物，清洁后压扁投放；有尖锐边角的、应包裹后投放
     */

    private int type;
    private String explain;
    private String contain;
    private String tip;

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

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getContain() {
        return contain;
    }

    public void setContain(String contain) {
        this.contain = contain;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return "Definition{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", explain='" + explain + '\'' +
                ", contain='" + contain + '\'' +
                ", tip='" + tip + '\'' +
                '}';
    }
}
