package com.umeng.soexample.fragment;


import android.util.Log;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.orhanobut.hawk.Hawk;
import com.umeng.soexample.Callback.SqlCallback;
import com.umeng.soexample.R;
import com.umeng.soexample.adapter.HelperAdapter;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.bean.Post;
import com.umeng.soexample.model.SqlMode;
import com.umeng.soexample.model.SqlModeImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.exception.BmobException;

public class MyHelperFragment extends BaseFragment implements SqlCallback.OnPostListeners {

    @BindView(R.id.rl_help)
    RecyclerView rlHelp;
    private String TAG = this.getClass().getCanonicalName();

    private SqlMode sqlMode;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_help;
    }

    @Override
    protected void initView() {
        sqlMode = new SqlModeImpl();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlHelp.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListeners() {
        sqlMode.queryPostInfo(getActivity(), Hawk.get("userId"),this);
    }

    @Override
    public void onSuccess(List<Post> t, BmobException e) {
        if (e==null){
            Log.d(TAG, "onSuccess: "+t);
            rlHelp.setAdapter(new HelperAdapter(getActivity(),t));
        }else {
            Log.e(TAG, "onSuccess: "+e.getMessage());
        }
    }
}


