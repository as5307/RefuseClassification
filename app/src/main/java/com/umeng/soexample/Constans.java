package com.umeng.soexample;


import com.umeng.soexample.bean.Guide;
import com.umeng.soexample.bean.Information;

import java.util.ArrayList;
import java.util.List;
/*
展示的数据
*/
public class Constans {

    // 获取token地址
    public final static String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
    // 官网获取的 API Key 更新为你注册的
    public final static String clientId = "64EbWZMY653g1H4N8zLiPGE1";
    // 官网获取的 Secret Key 更新为你注册的
    public final static String clientSecret = "Q90BipsuoycjqUGeMxA1enakpaLlYTf9";

    /*
    垃圾分类api
    */

    public final static String BaseUrl="http://api.tianapi.com/";

    public final static String URL="https://aip.baidubce.com/rest/2.0/image-classify/v2/advanced_general";
    public final static int DEFAULT_TIME=10;

    public final static String Retrofit="txapi/lajifenlei";

    public final static String Retrofit2="txapi/hotlajifenlei";

    public final static String Retrofit3="generalnews";

    public final static String Retrofit4="rest/2.0/image-classify/v2/advanced_general";

    public final static String Key="afb31dd79a1cc8209076065e610d4ca0";


    /**
     * 用于占位的空信息
     *
     * @return
     */

    public static List<Information> getEmptyNewInfo() {
        List<Information> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new Information());
        }
        return list;
    }

    /*
    垃圾分类图标
    */
    public static int[] icon=new int[]{R.drawable.bg1,R.drawable.bg2,R.drawable.bg3,R.drawable.bg4};

    public static int[] color=new int[]{R.color.app_color_recycling_title,R.color.app_color_harmfulwaste_title,R.color.app_color_drygarbage_title,R.color.app_color_wetgarbage_title};

    public static  String[] type = new String[]{"可回收物", "有害垃圾", "干垃圾", "湿垃圾"};

    public static String[] goods=new String[]{"报纸、纸箱、书本、广告单、塑料瓶、塑料玩具、油桶、酒瓶、玻璃杯、易拉罐、旧铁锅、旧衣服、包、旧玩偶、旧数码产品、旧家电"
    ,"废电池（充电电池、铅酸电池、镍镉电池、纽扣电池等）、废油漆、消毒剂、荧光灯管、含贡温度计、废药品及其包装物等"
    ,"餐盒、餐巾纸、湿纸巾、卫生间用纸、塑料袋、食品包装袋、污染严重的纸、烟蒂、纸尿裤、一次性杯子、大骨头、贝壳、花盆等"
    ,"食材废料、剩饭剩菜、过期食品、蔬菜水果、瓜皮果核、花卉绿植、中药残渣等"};


    public static String[] definition=new String[]{"可回收物是指，适宜回收利用和资源化利用的生活废弃物，如废纸张、废塑料、废玻璃制品、废金属、废织物等"
    ,"有害垃圾是指，对人体健康或者自然环境造成直接或潜在危害的废弃物"
    ,"干垃圾是指，除可回收物、有害垃圾、湿垃圾以外的其它生活废弃物"
    ,"湿垃圾是指，日常生活垃圾产生的容易腐烂的生物质废弃物"};


    public static String[] reqquest=new String[]{"1. 轻投轻放\n" +
            "2. 清洁干燥、避免污染，废纸尽量平整\n" +
            "3. 立体包装请清空内容物，清洁后压扁投放\n" +
            "4. 有尖锐边角的，应包裹后投放","1. 投放时请注意轻放\n" +
            "2. 易破损的请连带包装或包裹后轻放\n" +
            "3. 如易挥发，请密封后投放","1. 尽量沥干水分\n" +
            "2. 难以辨识类别的生活垃圾投入干垃圾容器内","1. 纯流质的食物垃圾，如牛奶等，应直接倒进下水口\n" +
            "2. 有包装物的湿垃圾应将包装物去除后分类投放，包装物请投放到对应的可回收物或干垃圾容器"};

    public static final  List<Guide> list_guide=new ArrayList<>();


    public static List<Guide> getGuideData(){
        for (int i=0;i<4;i++){
            Guide guide=new Guide(type[i],definition[i],goods[i],reqquest[i],color[i],icon[i]);
            list_guide.add(guide);
        }
        return list_guide;
    }
}
