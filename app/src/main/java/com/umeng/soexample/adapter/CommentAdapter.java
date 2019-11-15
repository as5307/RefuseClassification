package com.umeng.soexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;



import com.umeng.soexample.R;
import com.umeng.soexample.bean.Comment;
import com.xuexiang.xui.widget.imageview.ImageLoader;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentAdapter extends BaseAdapter {
    private List<Comment> list ;
    private Context context;

    public CommentAdapter(Context context, List<Comment> list) {
        this.context = context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_comment_item, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final Comment comment = list.get(i);
        String mycontent = list.get(i).getContent();
        if (mycontent == null || mycontent.length() <= 0) {
            holder.tvCommContent.setVisibility(View.GONE);
        } else {
            holder.tvCommContent.setVisibility(View.VISIBLE);
            holder.tvCommContent.setText(mycontent);
        }
        holder.commTime.setText(comment.getCreatedAt());
        holder.tvCommAuthor.setText(comment.getName());
        ImageLoader.get().loadImage(holder.commentItemIcon,comment.getUserHead());
        return view;
    }

    class ViewHolder {
        @BindView(R.id.tv_comm_author)
        TextView tvCommAuthor;
        @BindView(R.id.comment_item_icon)
        RadiusImageView commentItemIcon;
        @BindView(R.id.tv_comm_content)
        TextView tvCommContent;
        @BindView(R.id.comm_time)
        TextView commTime;
        @BindView(R.id.rl_post)
        RelativeLayout rlPost;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
