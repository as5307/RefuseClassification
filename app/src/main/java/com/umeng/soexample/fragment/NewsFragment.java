package com.umeng.soexample.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.adapter.SmartViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.soexample.Callback.BeanCallback;
import com.umeng.soexample.R;
import com.umeng.soexample.activity.AgentWebActivity;
import com.umeng.soexample.adapter.NewsAdapter;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.bean.Information;
import com.umeng.soexample.model.GarbageAPIModeImpl;
import com.umeng.soexample.utils.Utils;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.utils.ThemeUtils;
import com.xuexiang.xui.utils.WidgetUtils;

import org.json.JSONException;

import java.util.List;

import butterknife.BindView;

/*
* 头条
* */
public class NewsFragment extends BaseFragment implements BeanCallback.OnNewsListeners {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private NewsAdapter newsAdapter;
    private GarbageAPIModeImpl garbageAPIMode;

    private static final String WORD = "垃圾分类";
    private static final int NUM = 10;
    private String TAG = this.getClass().getCanonicalName();

    @Override
    protected void initView() {
        garbageAPIMode = new GarbageAPIModeImpl();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initData() {
        WidgetUtils.initRecyclerView(recyclerView, DensityUtils.dp2px(5), ThemeUtils.resolveColor(getContext(), R.attr.xui_config_color_background));
        recyclerView.setAdapter(newsAdapter = new NewsAdapter(true));
        refreshLayout.setDisableContentWhenLoading(true);
        refreshLayout.setDisableContentWhenRefresh(true);
        getRefreshData();
    }

    @Override
    protected void initListeners() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getRefreshData();
                Log.d(TAG, "onRefresh: ");
            }
        });

        newsAdapter.setOnItemClickListener(new SmartViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("url", newsAdapter.getItem(position).getUrl());
                bundle.putString("title", "头条");
                Utils.getInstance().goWeb(getActivity(),bundle);
            }
        });
    }

    private void getRefreshData() {
        Log.d(TAG, "getRefreshData: ");
        garbageAPIMode.reuqestNewsData(getActivity(), WORD, NUM, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (newsAdapter != null)
            newsAdapter.recycle();
    }

    @Override
    public void onSuccess(List<Information> list) throws JSONException {
        newsAdapter.refresh(list);
        Log.d(TAG, "onSuccess: ");
        refreshLayout.finishRefresh();
    }

    @Override
    public void onError(Throwable throwable, String errorMag) {
        Log.e(TAG, "onError:" + errorMag);
    }
}

