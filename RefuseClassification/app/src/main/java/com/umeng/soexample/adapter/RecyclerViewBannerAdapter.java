package com.umeng.soexample.adapter.menu;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.umeng.soexample.R;
import com.umeng.soexample.bean.Banner;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.widget.imageview.ImageLoader;

import java.util.Arrays;
import java.util.List;

public class RecyclerViewBannerAdapter extends BaseRecyclerAdapter<Banner>{

    /**
     * 默认加载图片
     */
    private ColorDrawable mColorDrawable;

    public RecyclerViewBannerAdapter(List<Banner> list) {
        super(list);
        mColorDrawable = new ColorDrawable(Color.parseColor("#555555"));
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adapter_recycler_view_banner_image_item;
    }

    @Override
    protected void bindData(@NonNull RecyclerViewHolder recyclerViewHolder, int i, Banner banner) {
        ImageView imageView=recyclerViewHolder.findViewById(R.id.iv_item);
        if (!TextUtils.isEmpty(banner.getImage())){
            ImageLoader.get().loadImage(imageView,banner.getImage());
        }else {
            imageView.setImageDrawable(mColorDrawable);
        }
    }
}