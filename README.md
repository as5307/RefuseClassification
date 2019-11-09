# 垃圾分类助手

## 一、项目简介部分

### 项目简介
#### 垃圾分类助手是一款精心设计帮助大家更好的进行垃圾分类工具，通过多种查询垃圾分类方式，轻松应对垃圾分类的难题。


### 项目功能结构
<img src="http://tianping.hellgirl.top/2019/11/09/c5772a65405dd9f880abe59b79b69351.png" width="600" height="400" >



### 项目截图
<img src="http://tianping.hellgirl.top/2019/11/09/16a5e82c4061280980631d093858d5ae.jpg  height="200" > ![](http://tianping.hellgirl.top/2019/10/31/72877fbb400d24ae805d0e81d6e5ea64.jpg) [](http://tianping.hellgirl.top/2019/10/31/723e5c7040222d6c803c199c52ac8e1a.jpg)   ![](http://tianping.hellgirl.top/2019/10/31/8c6eab0c40d06b9080220ea48faec56e.jpg) ![](http://tianping.hellgirl.top/2019/10/31/a2c9258a40aa2778801230c2395f4e05.jpg) ![](http://tianping.hellgirl.top/2019/11/01/5195bca740f15801802510b725a96265.jpg)
 

## 二、安装使用注意事项

### 项目安装
#### clone GitHub链接https://github.com/as5307/RefuseClassification 的项目，用 Android Studio 导入项目
 ### app安装
#### 在手机百度网址上复制http://tianping.hellgirl.top/2019/10/31/5134a7b04088f1e180ab9fecd938a8dd.apk 的链接打开安装app

### 注意事项
#### Android Studio  gradle版本至少为3.2.0以上，以及compileSdkVersion为28以上。



## 三、源码说明部分
### 后台部分
> app的后台采用的bmob后端云，通过调用接口直接存储数据和图片


#### 首先建立实体类继承BmobObject
```
public class Person extends BmobObject {
    private String name;
    private String address;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}

```
#### 通过set方法直接存储数据
```
Person p2 = new Person();
p2.setName("lucky");
p2.setAddress("北京海淀");
p2.save(new SaveListener<String>() {
    @Override
    public void done(String objectId,BmobException e) {
        if(e==null){
            toast("添加数据成功，返回objectId为："+objectId);
        }else{
            toast("创建数据失败：" + e.getMessage());
        }
    }
});
```

### 源码结构
> 我采用的是MVC结构：M是指逻辑模型，V是指视图模型，C则是控制器。

![](http://tianping.hellgirl.top/2019/11/01/b9e5963e4047ec68801c756ab654b3a2.png)


### 登录部分
> MobTech全球领先的移动智能科技平台 的 ShareSDK完成第三方登录和分享

#### 首先，要去你要去各个社交平台里申请应用，拿到key信息，然后，在Gradle集成方式可以在Mob产品的module下面的build.gradle文件里面配置ShareSDK各个社交平台的key信息。最后，调用接口
```
Platform plat = ShareSDK.getPlatform(Facebook.NAME);
plat.authorize();
plat.setPlatformActionListener(this)
```

### 数据获取
> 我采用的是天行数据的垃圾分类和新闻api，使用retrofit2+rxjava2的方式请求数据。

定义数据请求接口
```
public interface ApiUrl {
@GET(Constans.Retrofit)
Observable<BaseReponse<Definition>> getAllData(@Query("key") String key, @Query("word") String word);
```


