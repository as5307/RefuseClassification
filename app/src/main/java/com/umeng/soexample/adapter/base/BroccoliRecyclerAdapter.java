package com.umeng.soexample.adapter.base;

import android.view.View;

import com.scwang.smartrefresh.layout.adapter.SmartRecyclerAdapter;
import com.scwang.smartrefresh.layout.adapter.SmartViewHolder;
import com.umeng.soexample.bean.Information;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import me.samlss.broccoli.Broccoli;

public abstract class BroccoliRecyclerAdapter<T> extends SmartRecyclerAdapter<T>{

    private boolean isLoad=false;

    private Map<View,Broccoli> map=new HashMap<>();

    public BroccoliRecyclerAdapter(Collection<T> collection, int layoutId) {
        super(collection, layoutId);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, T model, int position) {
        Broccoli broccoli=map.get(holder.itemView);
        if (broccoli==null){
            broccoli=new Broccoli();
            map.put(holder.itemView,broccoli);
        }
        if (isLoad){
            broccoli.removeAllPlaceholders();
            onBindData(holder,model,position);
        }else {
            onBindBroccoli(holder,broccoli);
            broccoli.show();
        }
    }

    protected abstract void onBindData(SmartViewHolder holder, T model, int position);

    protected abstract void  onBindBroccoli(SmartViewHolder holder, Broccoli broccoli);

    public  SmartRecyclerAdapter<T> refresh (Collection<T> collection) {
        isLoad=true;
        return super.refresh(collection);
    }

    public void recycle() {
        for (Broccoli broccoli : map.values()) {
            broccoli.removeAllPlaceholders();
        }
        map.clear();
        clear();
    }
}