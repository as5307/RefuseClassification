package com.umeng.soexample.adapter;

import android.text.TextUtils;
import android.view.View;

import com.scwang.smartrefresh.layout.adapter.SmartViewHolder;
import com.umeng.soexample.Constans;
import com.umeng.soexample.R;
import com.umeng.soexample.adapter.base.BroccoliRecyclerAdapter;
import com.umeng.soexample.bean.Information;
import com.umeng.soexample.utils.PlaceholderHelper;
import com.xuexiang.xui.widget.imageview.ImageLoader;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import me.samlss.broccoli.Broccoli;

public class NewsAdapter extends BroccoliRecyclerAdapter<Information> {

    private boolean mIsAnim;

    public NewsAdapter(boolean anim) {
        super(Constans.getEmptyNewInfo(), R.layout.adapter_news_list_item);
        mIsAnim = anim;
    }

    @Override
    protected void onBindData(SmartViewHolder holder, Information model, int position) {
        holder.text(R.id.tv_content, model.getTitle());
        holder.text(R.id.tv_time, model.getCtime());
        holder.text(R.id.tv_type,model.getDescription());
        RadiusImageView imageView = holder.findViewById(R.id.iv_image);
        if (!TextUtils.isEmpty(model.getPicUrl())){
            imageView.setVisibility(View.VISIBLE);
            ImageLoader.get().loadImage(imageView, model.getPicUrl());
        }else {
            imageView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onBindBroccoli(SmartViewHolder holder, Broccoli broccoli) {
        if (mIsAnim) {
            broccoli.addPlaceholder(PlaceholderHelper.getParameter(holder.findView(R.id.tv_content)))
                    .addPlaceholder(PlaceholderHelper.getParameter(holder.findView(R.id.tv_time)))
                    .addPlaceholder(PlaceholderHelper.getParameter(holder.findView(R.id.iv_image)))
                    .addPlaceholder(PlaceholderHelper.getParameter(holder.findView(R.id.tv_type)));
        } else {
            broccoli.addPlaceholders(
                    holder.findView(R.id.tv_time),
                    holder.findView(R.id.tv_title),
                    holder.findView(R.id.iv_image),
                    holder.findView(R.id.tv_type));
        }
    }
}
