package com.umeng.soexample.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.umeng.soexample.R;
import com.xuexiang.xui.widget.flowlayout.BaseTagAdapter;

public class FlowTagAdapter extends BaseTagAdapter<String, TextView>{

    public FlowTagAdapter(Context context) {
        super(context);
    }

    @Override
    protected TextView newViewHolder(View convertView) {
        TextView textView=convertView.findViewById(R.id.tv_tag);
        return textView;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.adapter_item_tag;
    }

    @Override
    protected void convert(TextView holder, String item, int position) {
        holder.setText(item);
    }
}