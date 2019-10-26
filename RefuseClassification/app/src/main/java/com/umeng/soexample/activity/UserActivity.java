package com.umeng.soexample.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import com.orhanobut.hawk.Hawk;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseActivity;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.imageview.ImageLoader;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends BaseActivity {


    @BindView(R.id.tv_user_toolbar)
    TitleBar tvUserToollbaar;
    @BindView(R.id.iv_user_head)
    RadiusImageView ivUserHead;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.btn_btn_quit)
    AppCompatButton btnBtnQuit;

    private String user_name;
    private String user_head;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    protected void initView() {
        user_name = Hawk.get("name");
        user_head = Hawk.get("imageUrl");
        ImageLoader.get().loadImage(ivUserHead,user_head);
        tvUserName.setText(user_name);

    }

    @Override
    protected void initListeners() {
        tvUserToollbaar.setTitle("当前账号信息");
        btnBtnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hawk.put("isLogin", false);
                Hawk.put("name", null);
                Hawk.put("imageUrl", null);
                finish();
            }
        });

        tvUserToollbaar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

}
