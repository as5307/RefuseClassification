/*
 * Tencent is pleased to support the open source community by making QMUI_Android available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the MIT License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://opensource.org/licenses/MIT
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.umeng.soexample.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;

import com.orhanobut.hawk.Hawk;
import com.umeng.soexample.R;
import com.umeng.soexample.activity.CommentActivity;
import com.umeng.soexample.bean.Post;
import com.umeng.soexample.bean.User;
import com.xuexiang.xui.widget.imageview.ImageLoader;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.sharesdk.onekeyshare.OnekeyShare;


public class FoundAdapter extends BaseAdapter {

    private Context context;
    private List<Post> mItems = new ArrayList<>();

    private String TAG = "FoundAdapter";
    private Post post;

    private int number;


    private User user;


    private String userid;


    public FoundAdapter(Context context) {
        this.context = context;
        userid = Hawk.get("userId");
    }

    public void addItem(List<Post> items) {
        if (items != null) {
            mItems.clear();
            mItems.addAll(items);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView: " + position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_found_list_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        post = mItems.get(position);

        if (mItems.get(position).isHaveIcon()) {//判断是否有图片
            ImageLoader.get().loadImage(viewHolder.postNineGrid, post.getHeadImgUrl());
        }

    /*    queryVote(viewHolder.recyclableVote, "rvote");
        queryVote(viewHolder.harmfulVote, "hvote");
        queryVote(viewHolder.wetVote, "wvote");
        queryVote(viewHolder.dryVote, "dvote");*/

        Log.d(TAG, "getView: " + post.toString());
        viewHolder.postUsername.setText(post.getUserName());
        viewHolder.tvQuestion.setText("你认为" + "<" + post.getContent() + ">" + "属于?");

        number = mItems.size() - position;
        viewHolder.tvCount.setText("第" + number + "个求助");

        ImageLoader.get().loadImage(viewHolder.userIcon, post.getUserIcon());

        viewHolder.postShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post = mItems.get(position);
                showShare(post, "你认为" + "<" + post.getContent() + ">" + "属于?");
            }
        });

        viewHolder.postRepy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post = mItems.get(position);
                Intent intent = new Intent(context, CommentActivity.class);
                intent.putExtra("post_objectId", post.getObjectId());
                intent.putExtra("post_title", post.getContent());
                if (post.isHaveIcon()) {
                    intent.putExtra("post_image", post.getHeadImgUrl());
                }
                context.startActivity(intent);
            }
        });

        viewHolder.rgVote.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d(TAG, "onCheckedChanged: " + checkedId);
                switch (checkedId) {
                    case R.id.rb_recyclable:
                        Log.d(TAG, "recyclable: ");
                        break;
                    case R.id.rb_harmful:
                        Log.d(TAG, "harmful: ");
                        break;
                    case R.id.rb_dry:
                        Log.d(TAG, "dry: ");
                        break;
                    case R.id.rb_wet:
                        Log.d(TAG, "wet: ");
                        break;
                }
            }
        });
        return convertView;
    }

    /*
 一键分享
 */
    private void showShare(Post post, String tilte) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(post.getUserName() + "分享了内容");
        oks.setText(tilte);
        oks.show(context);
    }

    /*
  投票
  */
    public void setVote(String objectid, TextView textView) {
        user = new User();
        user.setObjectId(objectid);
        BmobRelation relation = new BmobRelation();
        relation.add(user);
        post.setVote(relation);
        post.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.i("bmob", "投票成功");
                } else {
                    Log.i("bmob", "失败：" + e.getMessage());
                }
            }
        });
    }
    private void setVote(TextView textView) {
        user=new User();
        user.setName(Hawk.get("name"));
        user.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                setVote(s, textView);
            }
        });
    }
    /*
     * 查询投票
     * */

    public void queryVote(TextView textView, String vote) {
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

    static
    class ViewHolder {
        @BindView(R.id.post_repy)
        ImageView postRepy;
        @BindView(R.id.tv_question)
        TextView tvQuestion;
        @BindView(R.id.post_share)
        AppCompatImageView postShare;
        @BindView(R.id.post_username)
        TextView postUsername;
        @BindView(R.id.userIcon)
        RadiusImageView userIcon;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.tv_recyclable)
        TextView tvRecyclable;
        @BindView(R.id.tv_harmful)
        TextView tvHarmful;
        @BindView(R.id.tv_dry)
        TextView tvDry;
        @BindView(R.id.tv_wet)
        TextView tvWet;
        @BindView(R.id.rb_recyclable)
        RadioButton rbRecyclable;
        @BindView(R.id.rb_harmful)
        RadioButton rbHarmful;
        @BindView(R.id.rb_dry)
        RadioButton rbDry;
        @BindView(R.id.rb_wet)
        RadioButton rbWet;
        @BindView(R.id.rg_vote)
        RadioGroup rgVote;
        @BindView(R.id.post_nineGrid)
        AppCompatImageView postNineGrid;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}







