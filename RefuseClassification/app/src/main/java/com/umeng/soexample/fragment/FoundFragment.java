package com.umeng.soexample.fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;

import androidx.annotation.NonNull;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orhanobut.hawk.Hawk;
import com.umeng.soexample.R;
import com.umeng.soexample.activity.PublushActivity;
import com.umeng.soexample.activity.SeachActivity;
import com.umeng.soexample.adapter.FoundAdapter;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.bean.Post;
import com.umeng.soexample.utils.DialogUntil;
import com.xuexiang.xui.widget.statelayout.MultipleStatusView;
import com.xuexiang.xutil.tip.ToastUtils;

import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.sharesdk.onekeyshare.OnekeyShare;
import link.fls.swipestack.SwipeStack;

public class FoundFragment extends BaseFragment implements SwipeStack.SwipeStackListener {
    @BindView(R.id.fab_scrolling)
    FloatingActionButton fabScrolling;
    @BindView(R.id.swipeStack)
    SwipeStack swipeStack;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;

    private FoundAdapter foundAdapter;

    private String TAG = "FoundFragment";

    private List<Post> mlist;


    @Override
    protected void initView() {
        multipleStatusView.showLoading();
        foundAdapter = new FoundAdapter(getActivity());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_seekhelp;
    }

    @Override
    protected void initData() {
        swipeStack.setAdapter(foundAdapter);
    }

    @Override
    protected void initListeners() {
        swipeStack.setListener(this);

        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "点击重试");
                loading();
            }
        });

        fabScrolling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Hawk.get("name")!=null&& Hawk.get("imageUrl")!=null){
                    openPage(PublushActivity.class);
                }else {
                    DialogUntil.getInstance().showHintBox(getActivity(), "请先去登录");
                }
            }
        });
    }

    private void loading(){
        multipleStatusView.showLoading();
        handler.sendEmptyMessageDelayed(0,1000);
    }

    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (multipleStatusView.getViewStatus() == MultipleStatusView.STATUS_LOADING){
                Log.d(TAG, "handleMessage: ");
                swipeStack.resetStack();
                multipleStatusView.showContent();
            }
            return true;
        }
    });

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        queryCount();
    }

   /*
   查询发表的条目
   */

    private void queryCount() {
        BmobQuery<Post> query = new BmobQuery<Post>();
        query.order("-createdAt");
        query.findObjects(new FindListener<Post>() {
            @Override
            public void done(List<Post> list, BmobException e) {
                if (e == null) {
                    Log.d(TAG, "成功");
                    foundAdapter.addItem(list);
                    swipeStack.resetStack();
                    multipleStatusView.showContent();

                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }

    @Override
    public void onViewSwipedToLeft(int position) {
        Log.d(TAG, "onViewSwipedToLeft: "+position);
    }

    @Override
    public void onViewSwipedToRight(int position) {
        Log.d(TAG, "onViewSwipedToRight: "+position);
    }

    @Override
    public void onStackEmpty() {
        Log.d(TAG, "onStackEmpty: ");
        multipleStatusView.showEmpty();
    }
}
