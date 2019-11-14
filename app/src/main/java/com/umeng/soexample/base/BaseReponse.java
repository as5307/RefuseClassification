package com.umeng.soexample.base;

import java.util.List;

public class BaseReponse<T> {
    /**
     * code : 200
     * msg : success
     * newslist : [{"name":"隐形眼镜包装盒","type":0,"explain":"可回收垃圾是指适宜回收、可循环利用的生活废弃物。","contain":"常见包括各类废金属、玻璃瓶、易拉罐、饮料瓶、塑料玩具、书本、报纸、广告单、纸板箱、衣服、床上用品、电子产品等","tip":"轻投轻放；清洁干燥，避免污染，费纸尽量平整；立体包装物请清空内容物，清洁后压扁投放；有尖锐边角的、应包裹后投放"},{"name":"智能眼镜","type":0,"explain":"可回收垃圾是指适宜回收、可循环利用的生活废弃物。","contain":"常见包括各类废金属、玻璃瓶、易拉罐、饮料瓶、塑料玩具、书本、报纸、广告单、纸板箱、衣服、床上用品、电子产品等","tip":"轻投轻放；清洁干燥，避免污染，费纸尽量平整；立体包装物请清空内容物，清洁后压扁投放；有尖锐边角的、应包裹后投放"},{"name":"眼镜","type":3,"explain":"干垃圾即其它垃圾，指除可回收物、有害垃圾、厨余垃圾（湿垃圾）以外的其它生活废弃物。","contain":"常见包括砖瓦陶瓷、渣土、卫生间废纸、猫砂、污损塑料、毛发、硬壳、一次性制品、灰土、瓷器碎片等难以回收的废弃物","tip":"尽量沥干水分；难以辨识类别的生活垃圾都可以投入干垃圾容器内"},{"name":"隐形眼镜","type":3,"explain":"干垃圾即其它垃圾，指除可回收物、有害垃圾、厨余垃圾（湿垃圾）以外的其它生活废弃物。","contain":"常见包括砖瓦陶瓷、渣土、卫生间废纸、猫砂、污损塑料、毛发、硬壳、一次性制品、灰土、瓷器碎片等难以回收的废弃物","tip":"尽量沥干水分；难以辨识类别的生活垃圾都可以投入干垃圾容器内"}]
     */
    private int code;
    private String msg;
    private List<T> newslist;

    public List<T> getNewslist()  {
        return newslist;
    }

    public void setNewslist(List<T> newslist) {
        this.newslist = newslist;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
