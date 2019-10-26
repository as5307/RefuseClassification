package com.umeng.soexample.activity;


import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import com.umeng.soexample.Callback.BeanCallback;
import com.umeng.soexample.Constans;
import com.umeng.soexample.R;
import com.umeng.soexample.adapter.FlowTagAdapter;
import com.umeng.soexample.adapter.SearchAdapter;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.bean.Definition;
import com.umeng.soexample.bean.Popular;
import com.umeng.soexample.model.GarbageAPIModeImpl;
import com.umeng.soexample.utils.DialogUntil;
import com.umeng.soexample.utils.TTSUtils;
import com.xuexiang.xui.widget.flowlayout.FlowTagLayout;


import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

public class SeachActivity extends BaseActivity implements BeanCallback.OnBasicGarbageListeners,BeanCallback.OnPopularGarbageListeners{

    @BindView(R.id.tb_title)
    Toolbar tbTitle;
    @BindView(R.id.edit_search)
    SearchView editSearch;
    @BindView(R.id.lv_goods)
    ListView lvGoods;

    @BindView(R.id.ftl_hot)
    FlowTagLayout ftlHot;
    @BindView(R.id.ll_content)
    LinearLayout llContent;

    private GarbageAPIModeImpl garbageAPIMode;
    private FlowTagAdapter flowTagAdapter;

    private String TAG = "SeachActivity";

    private SearchAdapter searchAdapter;

    private List<String> list_name;
    private List<String> list_tab;

    private List<String> list_type;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_seach;
    }

    @Override
    protected void initView() {
        garbageAPIMode = new GarbageAPIModeImpl();
        searchAdapter = new SearchAdapter(this);
        list_name = new ArrayList<>();
        list_tab = new ArrayList<>();
        list_type=new ArrayList<>();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tbTitle.inflateMenu(R.menu.menu_search);
        editSearch.requestFocus();
        lvGoods.setAdapter(searchAdapter);
        flowTagAdapter = new FlowTagAdapter(this);
        ftlHot.setAdapter(flowTagAdapter);
        getPopularGarbageData();

    }

    @Override
    protected void initListeners() {
        //返回圖標
        tbTitle.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //搜索圖標
        tbTitle.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });

        //搜索框監聽
        editSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "onQueryTextSubmit: " + query);
                if (list_name.contains(query)){
                    String name=list_name.get(0);
                    String type=list_type.get(0);
                    String hint=name + "是" + type;
                    DialogUntil.getInstance().initHintBox(SeachActivity.this, hint);
                    TTSUtils.getInstance().speak(hint,SeachActivity.this);
                }else {
                    DialogUntil.getInstance().initHintBox(SeachActivity.this, "查不到相关信息");
                    TTSUtils.getInstance().speak("查不到相关信息",SeachActivity.this);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() == 0) {
                    lvGoods.setVisibility(View.GONE);
                    llContent.setVisibility(View.VISIBLE);
                } else {
                    lvGoods.setVisibility(View.VISIBLE);
                    llContent.setVisibility(View.GONE);
                    getBasicGarbageData(newText);
                }
                return false;
            }
        });

        //listview监听
        lvGoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editSearch.setQuery(list_name.get(i), false);
            }
        });

        ftlHot.setOnTagClickListener(new FlowTagLayout.OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
                editSearch.requestFocus();
                editSearch.setQuery((CharSequence) parent.getAdapter().getItem(position), false);

            }
        });
    }
    //获取搜索垃圾分类的数据
    private void getBasicGarbageData(final String word) {
        list_name.clear();
        list_type.clear();
        garbageAPIMode.reuqestBasicGarbage(this,word, null,this);
    }

    //获取的热门数据
    private void getPopularGarbageData() {
        garbageAPIMode.reuqestPopularGarbage(this, this);
    }


    @Override
    public void onPopularSuccess(List<Popular> list) throws JSONException {
        Log.d(TAG, "onPopularSuccess: "+list);
        for (int i = 0; i < 10; i++) {
            int temp = new Random().nextInt(list.size());
            list_tab.add(list.get(temp).getName());
        }
        flowTagAdapter.addTags(list_tab);
    }

    @Override
    public void onPopularError(Throwable throwable, String errorMag) {
        Log.e(TAG, "onFailure: " + errorMag);
    }

    @Override
    public void onBasicSuccess(List<Definition> list, String keyword, String score) throws JSONException {
        Log.d(TAG, "onBasicSuccess"+list);
        for (Definition definition : list) {
            list_name.add(definition.getName());
            list_type.add(Constans.type[definition.getType()]);
        }
        searchAdapter.addItem(list_name);
    }

    @Override
    public void onBasicError(Throwable throwable, String errorMag, String keyword, String score) {
        Log.e(TAG, "onError: " + errorMag);
    }
}
