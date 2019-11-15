package com.umeng.soexample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.orhanobut.hawk.Hawk;
import com.umeng.soexample.Callback.BeanCallback;
import com.umeng.soexample.Callback.LoginCallback;
import com.umeng.soexample.Callback.SqlCallback;
import com.umeng.soexample.Constans;
import com.umeng.soexample.R;
import com.umeng.soexample.bean.Definition;
import com.umeng.soexample.bean.Result;
import com.umeng.soexample.bean.User;
import com.umeng.soexample.model.GarbageAPIModeImpl;
import com.umeng.soexample.model.LoginMode;
import com.umeng.soexample.model.LoginNodeImpl;
import com.umeng.soexample.model.SqlMode;
import com.umeng.soexample.model.SqlModeImpl;
import com.umeng.soexample.utils.DialogUntil;
import com.umeng.soexample.utils.FileUtil;
import com.umeng.soexample.utils.HttpUtil;
import com.umeng.soexample.utils.Utils;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.imageview.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.exception.BmobException;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import id.zelory.compressor.Compressor;
import io.reactivex.annotations.Nullable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements BeanCallback.OnBasicGarbageListeners, LoginCallback, SqlCallback.OnAddListeners, SqlCallback.OnIdDataListeners {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private AppBarConfiguration appBarConfiguration;
    private List<LocalMedia> mediaList;

    private static final int RESULT = 1;

    private ImageView imageView;

    private TextView textView;

    private RecyclerView list;

    private RelativeLayout relativeLayout;

    private SqlMode sqlMode;

    private String userid;

    private String imageUrl;

    private String imagePath;

    private String name;

    private SqlModeImpl mobMode;


    private static final String TAG = "MainActivity";


    private String access_token;

    private String result;

    private String userId;

    private GarbageAPIModeImpl garbageAPIMode;

    private ArrayList<Result> list_result;

    private JSONObject jsonObject2;

    private String[] tpye = new String[]{"可回收物", "有害垃圾", "湿垃圾", "干垃圾"};

    private JSONArray jsonArray;

    private Result result2;

    private LoginMode loginMode;

    private BottomSheet.BottomListSheetBuilder bottomListSheetBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        list_result = new ArrayList<>();
        garbageAPIMode = new GarbageAPIModeImpl();
        imageView = navView.getHeaderView(0).findViewById(R.id.iv_head);
        textView = navView.getHeaderView(0).findViewById(R.id.tv_head);
        relativeLayout = navView.getHeaderView(0).findViewById(R.id.rl_head);
        mediaList = new ArrayList<>();
        loginMode = new LoginNodeImpl();
        sqlMode = new SqlModeImpl();
        setSupportActionBar(toolbar);
        initListeners();
        initView();

    }

    private void initView() {
        Utils.getInstance().checkUpdate(this, false);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_found,
                R.id.nav_news,
                R.id.nav_help,
                R.id.nav_about)
                .setDrawerLayout(drawerLayout)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (Hawk.get("userId") != null) {
            ImageLoader.get().loadImage(imageView, Hawk.get("imageUrl"));
            textView.setText(Hawk.get("name"));
        } else {
            ImageLoader.get().loadImage(imageView, R.drawable.ic_head);
            textView.setText("登录");
        }
    }

    private void initListeners() {
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Hawk.get("userId") != null) {
                    Intent intent = new Intent(MainActivity.this, UserActivity.class);
                    intent.putExtra("user_head", imageUrl);
                    intent.putExtra("user_name", name);
                    startActivity(intent);
                } else {
                    View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_bottom_dialog, null);
                    DialogUntil.getInstance().showBottomDialog(MainActivity.this, view);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration) ||
                super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_camera:
                Utils.getInstance().getPictureSelector(MainActivity.this).selectionMedia(mediaList).forResult(RESULT);
                break;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == RESULT) {
                try {
                    DialogUntil.getInstance().showMinLoadBox(MainActivity.this,"正在识别中......");
                    mediaList = PictureSelector.obtainMultipleResult(data);
                    File file = new File(mediaList.get(0).getPath());
                    File compress = Compressor.getDefault(MainActivity.this).compressToFile(file);
                    imagePath = compress.getPath();
                    Log.d(TAG, "onActivityResult: " + compress);
                    byte[] bytes = FileUtil.readFileByBytes(imagePath);
                    String imgStr = Base64.encodeToString(bytes, Base64.DEFAULT);
                    String imgParam = URLEncoder.encode(imgStr, "UTF-8");
                    getAuth(imgParam);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //图像识别
    public void getAuth(String imgParam) {
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

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                result = response.body().string();
                Log.d(TAG, "onResponse" + result);
                try {
                    Log.d(TAG, "onResponse: 1111111111111111111111");
                    JSONObject jsonObject = new JSONObject(result);
                    access_token = jsonObject.getString("access_token");
                    String param = "image=" + imgParam;
                    result = HttpUtil.post(Constans.URL, access_token, param);
                    JSONObject jsonObject1 = new JSONObject(result);
                    jsonArray = jsonObject1.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject2 = jsonArray.getJSONObject(i);
                        getGarbagType(jsonObject2.getString("keyword"), jsonObject2.getString("score"));
                    }
                    Log.d(TAG, "onResponse: 2222222222222222222222");
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getGarbagType(String keyword, String score) {
        garbageAPIMode.reuqestBasicGarbage(this, keyword, score, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public void onBasicSuccess(List<Definition> list, String keyword, String score) throws JSONException {
        openPage(keyword, score, tpye[list.get(0).getType()]);

    }

    @Override
    public void onBasicError(Throwable throwable, String errorMag, String keyword, String score) {
        Log.e(TAG, "onBasicError: " + errorMag);
        openPage(keyword, score, null);
    }

    private void openPage(String keyword, String score, String type) {
        result2 = new Result(keyword, score, type);
        list_result.add(result2);
        if (list_result.size() == 5) {
            DialogUntil.getInstance().hideMainLoad();
            Bundle bundle = new Bundle();
            bundle.putSerializable("result", list_result);
            bundle.putString("image_path", imagePath);
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            list_result.clear();
            Log.d("aaa", "1111111111111111111");
        }
    }

    /*
    微信登录
    */

    public void weixinLogin(View view) {
        loginMode.login(this, Wechat.NAME, this);
    }


    /*
    QQ微信登录
    */
    public void qqLogin(View view) {
        loginMode.login(this, QQ.NAME, this);
    }

    /*
    微博登录
    */
    public void sinaLogin(View view) {
        loginMode.login(this, SinaWeibo.NAME, this);
    }

    //获取QQ。微信.微博的数据
    private void getUsetInfo(Platform platform) {
        //输出所有授权信息
        DialogUntil.getInstance().hideLoad();
        final String data = platform.getDb().exportData();
        Log.d(TAG, "完成授权");
        Log.d(TAG, "完成授权" + data);
        PlatformDb platDB = platform.getDb();//获取数平台数据DB
        imageUrl = platDB.getUserIcon();
        name = platDB.getUserName();
        userId = platDB.getUserId();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DialogUntil.getInstance().hideBottomListSheet();
                ImageLoader.get().loadImage(imageView, imageUrl);
                textView.setText(name);
            }
        });
        Hawk.put("userId", userId);
        Hawk.put("name", name);
        Hawk.put("imageUrl", imageUrl);

        sqlMode.addDataSql(this, name, imageUrl, userId, this);
    }


    /*
    登录回调接口
    */

    @Override
    public void onError(String mag, Throwable arg2) {
        DialogUntil.getInstance().hideLoad();

    }

    @Override
    public void onSuccess(Platform platform) {
        getUsetInfo(platform);
    }

    @Override
    public void onCancel() {
        // 取消所有授权信息
        DialogUntil.getInstance().hideLoad();
    }

    @Override
    public void onAddSuccees(String s, BmobException e) {
        if (e == null) {
            Hawk.put("objectId", s);
        } else {
            sqlMode.queryObjectId(this, userId, this);
        }
    }

    @Override
    public void onIdDataSuccess(List<User> t, BmobException e) {
        Hawk.put("objectId", t.get(0).getObjectId());
    }
}
