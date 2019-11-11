# 垃圾分类助手

## 一、项目简介部分

### 项目简介
#### 垃圾分类助手是一款精心设计帮助大家更好的进行垃圾分类工具，通过多种查询垃圾分类方式，轻松应对垃圾分类的难题。


### 项目功能结构
<img src="http://tianping.hellgirl.top/2019/11/09/c5772a65405dd9f880abe59b79b69351.png" width="600" height="400" >



### 项目截图
![](http://tianping.hellgirl.top/2019/11/09/80e8645a4019b2a98044b5895f1e4d7d.jpg) ![](http://tianping.hellgirl.top/2019/11/09/cf7d05d740261bc980c8cd1488cd86d2.jpg) ![](http://tianping.hellgirl.top/2019/11/09/0d84c0ec4077157a808f22f4efaa2520.jpg)   ![](http://tianping.hellgirl.top/2019/11/09/9683364b40ee2e418030670131c78857.jpg) ![](http://tianping.hellgirl.top/2019/11/09/a494f89a40774550808fad90e859d860.jpg) ![](http://tianping.hellgirl.top/2019/11/09/6c7f34324041ef1b8022dd7c27a0ea94.jpg)  ![](http://tianping.hellgirl.top/2019/11/09/6b13f5914030f0d580e83dfa63940b2a.jpg)
 

## 二、安装使用注意事项

### 项目安装
#### clone GitHub链接 https://github.com/as5307/RefuseClassification 的项目，用 Android Studio 导入项目
### app安装
#### 打开链接 
https://www.pgyer.com/3c9U 的链接打开安装app

#### 扫描二维码 
![](http://tianping.hellgirl.top/2019/11/09/086a1f6840fa640c807e38a5e3c21710.png)
### 注意事项
#### Android Studio  gradle版本至少为3.2.0以上，以及compileSdkVersion为28以上。

## 三、源码说明部分
### 后台部分
> app的后台采用的bmob后端云，通过调用接口直接存储数据.删除数据、查询数据、上传图片

#### 云数据库结构

![](http://tianping.hellgirl.top/2019/11/09/30626eb74069815180fb75a45a3f33c4.png)
####  首先建立实体类继承BmobObject
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

#### 通过Uploadblock上传文件
```
String picPath = "sdcard/temp.jpg";
BmobFile bmobFile = new BmobFile(new File(picPath));
bmobFile.uploadblock(new UploadFileListener() {

    @Override
    public void done(BmobException e) {
        if(e==null){
            //bmobFile.getFileUrl()--返回的上传文件的完整地址
            toast("上传文件成功:" + bmobFile.getFileUrl());
        }else{
            toast("上传文件失败：" + e.getMessage());
        }

    }

    @Override
    public void onProgress(Integer value) {
        // 返回的上传进度（百分比）
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

### Api数据获取部分
> 我采用的是天行数据的垃圾分类和新闻api，使用retrofit2+rxjava2的方式请求网络数据。

#### 定义数据请求接口
```
public interface ApiUrl {
@GET(Constans.Retrofit)
Observable<BaseReponse<Definition>> getAllData(@Query("key") String key, @Query("word") String word);
```
#### 实现Retrofit2+Rxjava2请求
```
 Retrofit retrofit = new Retrofit.Builder().
                client(okHttpClient)
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
                
  ApiUrl apiUrl=retrofit.create(ApiUrl.class)
  apiUrl.getAllData(XXXX,XXXX)
  .compose(RxHelper.observableIO2Main(context))
  .subscribe(new Observer<BaseReponse<Definition>>() {
  @Override
  public void onSubscribe(Disposable d) {   
  }  
  @Override
  public void onNext(BaseReponse<Definition> definitionBaseReponse) 
  }
  @Override
  public void onError(Throwable e) {
  }
  @Override
  public void onComplete() {
  }
  });
  
```
  ### 百度图片识别部分
  >调用百度API通用物体识别
  
  #### 通过Okhttp发送Post请求获取access_token
  ```
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        // 官网获取的 API Key 更新为你注册的
        String clientId = "64EbWZMY653g1H4N8zLiPGE1";
        // 官网获取的 Secret Key 更新为你注册的
        String clientSecret = "Q90BipsuoycjqUGeMxA1enakpaLlYTf9";

        FormBody formBody = new FormBody.Builder()
                .add("grant_type", "client_credentials")
                .add("client_id", clientId)
                .add("client_secret", clientSecret)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(authHost).post(formBody).build();
  ```
 #### 在URL中带上access_token参数,向API服务地址使用POST发送请求获取,输出图片中的多个物体及场景标签
  ```
  HttpUtil.post(Constans.URL, access_token, param)
  ```
  
  ### 框架的使用
  #### 1、![Hawk](https://github.com/orhanobut/hawk) 数据库库
  #### 2、![XUpdate](https://github.com/xuexiangjys/XUpdate/wiki) 版本更新库
  ####  3、![AgentWeb](https://github.com/Justson/AgentWeb) 加载网页库
  #### 4、![Swipestack](https://github.com/flschweiger/SwipeStack) 视图堆栈框架
  #### 5、![Broccoli](https://github.com/samlss/Broccoli) 预加载占位控件
  #### 6、![Xui](https://github.com/xuexiangjys/XUI/wiki) UI框架
  #### 7、![PictureSelector](https://github.com/LuckSiege/PictureSelector) 图片选择库
  #### 8、Retrofit2+Rxjava2+Okhttp 网络请求框架
  #### 9、![Compressor](https://github.com/zetbaitsu/Compressor) 图片压缩库
  #### 10、![SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout) 下拉和上拉刷新库
  #### 11、![Butterknife](https://github.com/JakeWharton/butterknife) View注入框架
