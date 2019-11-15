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
import com.umeng.soexample.bean.User;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class HelperAdapter extends BaseRecyclerAdapter<Post> {

    private Context context;


    public HelperAdapter(Context context,List<Post> list) {
        super(list);
        this.context=context;
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adapter_recycler_myhelp_histroy;
    }

    @Override
    protected void bindData(@NonNull RecyclerViewHolder recyclerViewHolder, int i, Post post) {
        TextView textView= recyclerViewHolder.findViewById(R.id.tv_goods_name);
        TextView tv_recyclable= recyclerViewHolder.findViewById(R.id.rb_recyclable);
        TextView tv_dry= recyclerViewHolder.findViewById(R.id.rb_dry);
        TextView tv_wet= recyclerViewHolder.findViewById(R.id.rb_wet);
        TextView tv_harmful= recyclerViewHolder.findViewById(R.id.rb_harmful);

        queryVote(tv_recyclable, "rvote",post);
        queryVote(tv_harmful, "hvote",post);
        queryVote(tv_wet, "wvote",post);
        queryVote(tv_dry, "dvote",post );
        LinearLayout linearLayout=recyclerViewHolder.findViewById(R.id.ll_history);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("aaa", "onClick: ");
                Intent intent = new Intent(context, CommentActivity.class);
                intent.putExtra("post_objectId", post.getObjectId());
                intent.putExtra("post_title", post.getTitle());
                if (post.isHaveIcon()) {
                    intent.putExtra("post_image", post.getHeadImgUrl());
                }
                context.startActivity(intent);
            }
        });
        textView.setText(post.getTitle());
    }

    /*
     * 查询投票
     * */
    public void queryVote(TextView textView, String vote,Post post) {
        BmobQuery<User> query = new BmobQuery<User>();
        query.addWhereRelatedTo(vote, new BmobPointer(post));
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> object, BmobException e) {
                if (e == null) {
                    Log.i("bmob", "查询个数：" + object.size());
                    textView.setText(object.size() + "票");
                } else {
                    Log.i("bmob", "失败：" + e.getMessage());
                }
            }
        });
    }
}
