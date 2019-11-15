package com.umeng.soexample.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.umeng.soexample.Callback.SqlCallback;
import com.umeng.soexample.Constans;
import com.umeng.soexample.R;
import com.umeng.soexample.activity.SeachActivity;
import com.umeng.soexample.adapter.SearchAdapter;
import com.umeng.soexample.adapter.menu.RecyclerViewBannerAdapter;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.bean.Banner;
import com.umeng.soexample.bean.Definition;
import com.umeng.soexample.bean.Guide;
import com.umeng.soexample.bean.Popular;
import com.umeng.soexample.model.SqlModeImpl;
import com.xuexiang.xui.widget.banner.recycler.BannerLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bmob.v3.exception.BmobException;
import io.reactivex.annotations.Nullable;

public class HomeFragment extends BaseFragment implements SqlCallback.OnBannerListeners {
    @BindView(R.id.bl_horizontal)
    BannerLayout blHorizontal;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;


    @BindView(R.id.search_view)
    EditText searchView;

    private Unbinder unbind;
    private TextToSpeech textToSpeech;
    private String TAG = this.getClass().getCanonicalName();


    private String name;
    private Definition definition;
    private Popular popular;


    private List<Definition> lsit_definition;


    public int[] title_image;
    public int[] title_color;


    private List<String> list_name;
    private List<String> mItems = new ArrayList<>();


    private SearchAdapter searchAdapter;

    private View containerView;

    private static final int REQUEST_CODE_GALLERY = 0;

    private String filePath;

    private SqlModeImpl mobMode;

    private List<String> list_banner = new ArrayList<>();


    private ItemView itemView;

    private int type = 0;

    private List<Guide> list_guide;

    private TabLayout.Tab tab_one;
    private TabLayout.Tab tab_two;
    private TabLayout.Tab tab_three;
    private TabLayout.Tab tab_four;


    @Override
    protected void initView() {
        mobMode = new SqlModeImpl();
        tabLayout.addTab(tabLayout.newTab().setText( Constans.type[0]));
        tabLayout.addTab(tabLayout.newTab().setText( Constans.type[1]));
        tabLayout.addTab(tabLayout.newTab().setText( Constans.type[2]));
        tabLayout.addTab(tabLayout.newTab().setText( Constans.type[3]));
        tab_one=tabLayout.getTabAt(3);
        tab_two=tabLayout.getTabAt(2);
        tab_three=tabLayout.getTabAt(1);
        tab_four=tabLayout.getTabAt(0);
        list_guide=Constans.getGuideData();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initData() {
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tab_one.setIcon(R.drawable.ic_recyclable);
        tab_two.setIcon(R.drawable.ic_harmful);
        tab_three.setIcon(R.drawable.ic_dry);
        tab_four.setIcon(R.drawable.ic_wet);
        getBannerData();
    }

    @Override
    protected void initListeners() {
        searchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Log.d(TAG, "onFocusChange: " + b);
                if (b) {
                    openPage(SeachActivity.class);
                }
            }
        });
    }

    //隐藏虚拟键盘
    protected void hideSoftInput() {
        View v = getActivity().getCurrentFocus();
        if (v != null && v.getWindowToken() != null) {
            InputMethodManager manager = (InputMethodManager) getActivity()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            boolean isOpen = manager.isActive();
            if (isOpen) {
                manager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return Constans.type.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return Constans.type[position];
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            itemView = new ItemView(getActivity());
            Log.d(TAG, "instantiateItem: "+position);
            switch (position) {
                case 0:
                    getGuideData(0, itemView);
                    break;
                case 1:
                    getGuideData(1, itemView);
                    break;
                case 2:
                    getGuideData(2, itemView);
                    break;
                case 3:
                    getGuideData(3, itemView);
                    break;
            }
            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String result = "";
        InputStream inputStream = null;
        if (requestCode == 100) {
            try {
                List<ImageItem> imageItems = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                File file = new File(imageItems.get(0).path);
                inputStream = new FileInputStream(file);
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes);
                result = Base64.encodeToString(bytes, Base64.DEFAULT);
                Log.d(TAG, "onActivityResult: " + result);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }



    /*获取数据库数据*/

    private void getGuideData(int type, ItemView itemView) {
        Guide guide = list_guide.get(type);
        Log.d(TAG, "onSuccess: " + guide.toString());
        itemView.ivTitle.setImageResource(guide.getIcon());
        itemView.tvTitle.setText(guide.getType());
        itemView.btnGoods.setBackgroundResource(guide.getColor());
        itemView.btnRequest.setBackgroundResource(guide.getColor());
        itemView.tvGoods.setText(guide.getGoods());
        itemView.tvRequest.setText(guide.getRequest());
        itemView.tvDefine.setText(guide.getDefinition());
        itemView.btnGoods.setText("常见物品");
        itemView.btnRequest.setText("投放要求");

    }

    private void getBannerData() {
        mobMode.queryBannerInfo(getActivity(), this);
    }

    @Override
    public void onStart() {
        super.onStart();
        searchView.clearFocus();
    }


    @Override
    public void onSuccess(List<Banner> t, BmobException e) {
        blHorizontal.setAdapter(new RecyclerViewBannerAdapter(t));
    }

    public class ItemView extends FrameLayout {
        @BindView(R.id.iv_title)
        ImageView ivTitle;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_define)
        TextView tvDefine;
        @BindView(R.id.btn_request)
        AppCompatButton btnRequest;
        @BindView(R.id.tv_request)
        TextView tvRequest;
        @BindView(R.id.btn_goods)
        AppCompatButton btnGoods;
        @BindView(R.id.tv_goods)
        TextView tvGoods;

        public ItemView(Context context) {
            super(context);
            View view = LayoutInflater.from(context).inflate(R.layout.activity_guide_content, null);
            ButterKnife.bind(this, view);
            addView(view);
        }
    }
}

