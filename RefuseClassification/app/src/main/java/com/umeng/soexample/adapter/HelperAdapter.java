package com.umeng.soexample.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.umeng.soexample.R;
import com.umeng.soexample.activity.CommentActivity;
import com.umeng.soexample.bean.Post;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;

import java.util.List;

public class HelperAdapter extends BaseRecyclerAdapter<Post> {

    private Context context;

    public HelperAdapter(Context context,List<Post> list) {
        super(list);
        this.context=context;
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adapter_recycler_help;
    }

    @Override
    protected void bindData(@NonNull RecyclerViewHolder recyclerViewHolder, int i, Post post) {
        TextView textView= recyclerViewHolder.findViewById(R.id.tv_goods_name);
        LinearLayout linearLayout=recyclerViewHolder.findViewById(R.id.ll_history);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("aaa", "onClick: ");
                Intent intent = new Intent(context, CommentActivity.class);
                intent.putExtra("post_objectId", post.getObjectId());
                intent.putExtra("post_title", post.getContent());
                if (post.isHaveIcon()) {
                    intent.putExtra("post_image", post.getHeadImgUrl());
                }
                context.startActivity(intent);
            }
        });
        textView.setText(post.getContent());
    }
}
