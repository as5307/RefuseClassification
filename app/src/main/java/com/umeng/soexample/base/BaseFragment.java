package com.umeng.soexample.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.umeng.soexample.R;

import com.xuexiang.xui.XUI;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.actionbar.TitleUtils;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.annotations.Nullable;

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        XUI.initTheme(getActivity());
        Log.d("aaa", "onCreateView: ");
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder= ButterKnife.bind(this,view);
        initView();
        initData();
        initListeners();
        return view;
    }


    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListeners();


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("aaa", "onActivityCreated: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void openPage(Class<?> c){
        Intent intent=new Intent(getActivity(),c);
        startActivity(intent);
    }
    public void openPage(Class<?> c,Bundle bundle){
        Intent intent=new Intent(getActivity(),c);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void openPage(Class<?> c, ArrayList<String> arrayList){
        Intent intent=new Intent(getActivity(),c);
        intent.putStringArrayListExtra("data",arrayList);
        startActivity(intent);
    }
}