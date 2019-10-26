package com.umeng.soexample.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.just.agentweb.core.AgentWeb;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseActivity;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import butterknife.BindView;

public class AgentWebActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;
    @BindView(R.id.ll_content)
    LinearLayout llContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_agent_web;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Bundle bundle = this.getIntent().getExtras();
        String title = bundle.getString("title");
        String url = bundle.getString("url");
        gotWeb(url);
        tbTitle.setTitle(title);

        tbTitle.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void gotWeb(String url) {
        AgentWeb agentWeb = AgentWeb.with(this).
                setAgentWebParent(llContent, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(url);
    }
}
