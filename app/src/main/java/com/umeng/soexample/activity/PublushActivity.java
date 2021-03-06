package com.umeng.soexample.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

 import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;

import com.luck.picture.lib.PictureSelectionModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.orhanobut.hawk.Hawk;
import com.umeng.soexample.Callback.SqlCallback;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.bean.Post;
import com.umeng.soexample.bean.User;
import com.umeng.soexample.model.SqlMode;
import com.umeng.soexample.model.SqlModeImpl;
import com.umeng.soexample.utils.DialogUntil;
import com.umeng.soexample.utils.Utils;
import com.xuexiang.xui.widget.imageview.ImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import id.zelory.compressor.Compressor;
import io.reactivex.annotations.Nullable;


public class PublushActivity extends BaseActivity implements SqlCallback.OnuploadFileListeners {

    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tb_title)
    Toolbar tbTitle;
    @BindView(R.id.iv_select_pic)
    AppCompatImageView ivSelectPic;


    private String title_image;
    private int size = 0;
    private String time;
    private String objectKey;
    private String picPath;
    private String picNmae;
    private String imageUrl;

    private List<String> list;

    private String TAG = this.getClass().getCanonicalName();

    private SqlMode mobMode;

    private List<LocalMedia> mediaList;
    private static final int RESULT = 1;

    private Post post;

    private User user;


    @Override
    protected int getLayoutId() {
        return R.layout.layout_help_publush;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        mediaList = new ArrayList<>();
        mobMode = new SqlModeImpl();
        imageUrl = Hawk.get("imageUtl");

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tbTitle.inflateMenu(R.menu.menu_publish);
        tbTitle.setTitle("求助");
    }


    @Override
    protected void initListeners() {
        //返回监听
        tbTitle.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tbTitle.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                tv_upload_database();
                return true;
            }
        });
    }

    /**
     * 上传发表内容
     */
    private void tv_upload_database() {
        //隐藏软硬盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        post = new Post();
        user=new User();
        user.setObjectId(Hawk.get("objectId"));
        post.setTitle(etContent.getText().toString());
        post.setUser(user);
        if (size == 0) {
            post.setHaveIcon(false);
            post.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        etContent.setText("");
                        Log.d(TAG, "内容发表成功");
                        finish();
                    } else {
                        Log.d(TAG, "内容发表失败：" + e.getMessage());
                    }
                }
            });
            return;
        }
        size = 0;
        final String filePaths = mediaList.get(0).getPath();
        File file = new File(filePaths);
        File compress = Compressor.getDefault(PublushActivity.this).compressToFile(file);
        DialogUntil.getInstance().showProgressDialog(this);
        mobMode.uploadFileDataSql(compress.getPath(),this );
    }

    /*
   图片选取的回调
   */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("aaa", String.valueOf(requestCode));
        if (resultCode == RESULT_OK) {
            if (requestCode == RESULT) {
                mediaList = PictureSelector.obtainMultipleResult(data);
                Log.d(TAG, "onActivityResult: " + mediaList.get(0).getPath());
                ImageLoader.get().loadImage(ivSelectPic,mediaList.get(0).getPath());
                size = mediaList.size();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void changImage(View view) {
        Utils.getInstance().getPictureSelector(this).selectionMedia(mediaList).forResult(RESULT);
    }

    @Override
    public void onUploadSuccess(String imagurl, BmobException e) {
        if (e == null) {
            post.setHeadImgUrl(imagurl);
            post.setHaveIcon(true);
            post.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        Log.d(TAG, "done: 发表成功");
                        finish();
                    }else {
                        Log.e(TAG, "done: "+e.getMessage() );
                    }
                }
            });
        }
    }
    @Override
    public void onUploadProgress(Integer value) {
        DialogUntil.getInstance().setProgress(value);
    }

    @Override
    public void onUploadError(int code, String msg) {
        DialogUntil.getInstance().hideProgress();
    }
}