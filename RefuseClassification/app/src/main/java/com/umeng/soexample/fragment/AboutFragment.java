package com.umeng.soexample.fragment;

import android.view.View;
import android.widget.TextView;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.utils.Utils;
import com.xuexiang.xui.widget.grouplist.XUIGroupListView;
import com.xuexiang.xutil.app.AppUtils;

import butterknife.BindView;

public class AboutFragment extends BaseFragment {
    @BindView(R.id.version)
    TextView version;
    @BindView(R.id.about_list)
    XUIGroupListView aboutList;
    @BindView(R.id.copyright)
    TextView copyright;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    protected void initView() {
        version.setText(String.format("版本号：%s", AppUtils.getAppVersionName()));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListeners() {
        XUIGroupListView.newSection(getActivity())
                .addItemView(aboutList.createItemView("版本更新")
                        , new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Utils.getInstance().checkUpdate(getActivity(),true);
                            }
                        }).addTo(aboutList);

    }
}
