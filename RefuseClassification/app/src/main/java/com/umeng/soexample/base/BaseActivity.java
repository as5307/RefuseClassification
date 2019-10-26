package com.umeng.soexample.base;

import android.content.Intent;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.luck.picture.lib.tools.PictureFileUtils;
import com.xuexiang.xui.XUI;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.annotations.Nullable;


public abstract class BaseActivity extends FragmentActivity {
    private Unbinder unbinder;
    private  FragmentTransaction ft;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        XUI.initTheme(this);
        super.onCreate(savedInstanceState);
        setContentView();
        unbinder=ButterKnife.bind(this);
        initView();
        initListeners();
        initData(savedInstanceState);
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initListeners();

    protected abstract void initData(Bundle savedInstanceState);


    protected void setContentView(){
        int id=getLayoutId();
        if (id!=-1){
            setContentView(id);
        }
    }
    public void openPage(Fragment fragment, int containerViewId ){
        ft=getSupportFragmentManager().beginTransaction().replace(containerViewId,fragment);
        ft.commit();
    }

    public void removePage(Fragment fragment){
        ft.remove(fragment);
    }

    public void openActivity(Class<?> activity){
        Intent intent=new Intent(this,activity);
        startActivity(intent);
    }

    public void openActivity(Class<?> activity,Bundle bundle){
        Intent intent=new Intent(this,activity);
        if (bundle!=null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        PictureFileUtils.deleteCacheDirFile(this);
    }
}
