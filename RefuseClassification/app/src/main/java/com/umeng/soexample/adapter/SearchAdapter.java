package com.umeng.soexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;

import com.umeng.soexample.R;
import com.umeng.soexample.bean.Definition;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 搜索结果选项列表适配器
 */

public class SearchAdapter extends BaseAdapter {

    private List<String> mdata=new ArrayList<>();
    private LayoutInflater inflater;

    public SearchAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void addItem(List<String> data) {
        if (data != null) {
            mdata.clear();
            mdata.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public Object getItem(int position) {
        return mdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SuggestionsViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_search_item, parent, false);
            viewHolder = new SuggestionsViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SuggestionsViewHolder) convertView.getTag();
        }

        String name=mdata.get(position);
        viewHolder.tvSerch.setText(name);
        return convertView;
    }

     class SuggestionsViewHolder {
        @BindView(R.id.tv_serch)
        TextView tvSerch;
        @BindView(R.id.iv_select)
        AppCompatImageView ivSelect;

        public SuggestionsViewHolder(View convertView) {
            ButterKnife.bind(this, convertView);
        }
    }
}