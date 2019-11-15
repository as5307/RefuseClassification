package com.umeng.soexample.fragment;


import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.umeng.soexample.Callback.SqlCallback;
import com.umeng.soexample.R;
import com.umeng.soexample.adapter.HelperAdapter;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.bean.Post;
import com.umeng.soexample.model.SqlMode;
import com.umeng.soexample.model.SqlModeImpl;
import com.xuexiang.xui.widget.statelayout.MultipleStatusView;

import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.exception.BmobException;

/*
 * 求助历史
 * */
public class MyHelperFragment extends BaseFragment implements SqlCallback.OnPostListeners {

    @BindView(R.id.rl_help)
    RecyclerView rlHelp;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;
    private String TAG = this.getClass().getCanonicalName();
    private SqlMode sqlMode;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_help;
    }

    @Override
    protected void initView() {
        sqlMode = new SqlModeImpl();
        multipleStatusView.showLoading();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlHelp.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initListeners() {
        sqlMode.queryPostInfo(getActivity(), "user", this);
    }

    @Override
    public void onSuccess(List<Post> t, BmobException e) {
        if (e == null) {
            Log.d(TAG, "onSuccess: " + t);
            rlHelp.setAdapter(new HelperAdapter(getActivity(), t));
            multipleStatusView.showContent();
        } else {
            Log.e(TAG, "onSuccess: " + e.getMessage());
        }
    }
}


