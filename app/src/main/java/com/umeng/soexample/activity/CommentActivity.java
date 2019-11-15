package com.umeng.soexample.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.orhanobut.hawk.Hawk;
import com.umeng.soexample.R;
import com.umeng.soexample.adapter.CommentAdapter;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.bean.Comment;
import com.umeng.soexample.bean.Post;
import com.umeng.soexample.bean.User;
import com.umeng.soexample.model.SqlModeImpl;
import com.umeng.soexample.utils.DialogUntil;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.dialog.materialdialog.DialogAction;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.imageview.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class CommentActivity extends BaseActivity {


    @BindView(R.id.tb_title)
    TitleBar tbTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_recycling)
    TextView tvRecycling;
    @BindView(R.id.tv_harmfulwaste)
    TextView tvHarmfulwaste;
    @BindView(R.id.tv_wetgarbage)
    TextView tvWetgarbage;
    @BindView(R.id.tv_drygarbage)
    TextView tvDrygarbage;
    @BindView(R.id.ll_vote)
    LinearLayout llVote;
    @BindView(R.id.image_layout)
    LinearLayout imageLayout;
    @BindView(R.id.comment_lv)
    ListView commentLv;
    @BindView(R.id.btn_write)
    AppCompatButton btnWrite;
    private String TAG = this.getClass().getCanonicalName();

    private String inputext;
    private String posttitle;
    private String postimage;
    private String time;
    private String post_objectId;
    private String user_objectId;

    private List<Comment> list = new ArrayList<>();

    private SqlModeImpl sqlMode;


    private String username;
    private String userIcon;

    private Post post;

    private CommentAdapter commentAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.main_item;
    }

    @Override
    protected void initView() {
        sqlMode = new SqlModeImpl();
        username = Hawk.get("name");
        userIcon = Hawk.get("imageUrl");
    }

    @Override
    protected void initListeners() {
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Bundle bundle = this.getIntent().getExtras();
        posttitle = bundle.getString("post_title");
        post_objectId = bundle.getString("post_objectId");
        post = new Post();
        post.setObjectId(post_objectId);
        postimage = bundle.getString("post_image");
        tvTitle.setText("<" + posttitle + ">" + "之垃圾分类？");
        queryVote(tvRecycling, "rvote");
        queryVote(tvHarmfulwaste, "hvote");
        queryVote(tvWetgarbage, "wvote");
        queryVote(tvDrygarbage, "dvote");

        if (postimage != null) {
            imageLayout.removeAllViews();
            ImageView imageView = new ImageView(this);
            ImageLoader.get().loadImage(imageView, postimage);
            imageLayout.addView(imageView);
            imageLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            imageLayout.setVisibility(View.VISIBLE);
        }

        findComments();
        commentAdapter = new CommentAdapter(CommentActivity.this, list);
        commentLv.setAdapter(commentAdapter);

        initToobar();
    }

    private void initToobar() {
        tbTitle.setTitle("垃圾分类讨论");
        tbTitle.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    //发表评论
    private void publishComment(String content) {
        final Comment comment = new Comment();
        comment.setContent(content);
        comment.setPost(post);
        comment.setName(username);
        comment.setUserHead(userIcon);
        comment.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Log.d(TAG, "评论成功");
                    findComments();
                    commentAdapter.notifyDataSetInvalidated();
                } else {
                    Log.d(TAG, e.getMessage());
                }
            }
        });
    }


    /*
 查询评论
  */
    private void findComments() {
        list.clear();
        BmobQuery<Comment> query = new BmobQuery<Comment>();
        query.order("-createdAt");
        query.addWhereEqualTo("post", new BmobPointer(post));
        query.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> arg0, BmobException e) {
                if (e == null) {
                    list.addAll(arg0);
                    commentAdapter.notifyDataSetInvalidated();
                } else {
                    Log.e(TAG, "查询评论失败" + e.toString());
                }
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

    @OnClick(R.id.btn_write)
    public void onViewClicked() {
        if (Hawk.get("userId")!=null) {
            DialogUntil.getInstance().showInputBox(this, "关于<" + posttitle + ">" + "之垃圾分类看法", new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(@NonNull MaterialDialog materialDialog, CharSequence charSequence) {
                            Log.d(TAG, "onInput: " + charSequence.toString());
                            inputext = charSequence.toString();
                        }
                    },
                    new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                            Log.d(TAG, "onClick: " + materialDialog.getInputEditText().getText().toString());
                            publishComment(materialDialog.getInputEditText().getText().toString());
///
                        }
                    });
        } else {
            DialogUntil.getInstance().showHintBox(CommentActivity.this, "请先登录");
        }
    }
}

