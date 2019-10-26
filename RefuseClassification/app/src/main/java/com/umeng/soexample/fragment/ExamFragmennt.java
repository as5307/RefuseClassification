/*
package com.umeng.soexample.fragment;


import  android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.umeng.soexample.Callback.BeanCallback;
import com.umeng.soexample.Constans;
import com.umeng.soexample.R;
import com.umeng.soexample.activity.AnswerActivity;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.bean.Popular;
import com.umeng.soexample.bean.Table;
import com.umeng.soexample.model.GarbageAPIModeImpl;

import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ExamFragmennt extends BaseFragment implements  BeanCallback.OnPopularGarbageListeners{

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_conunt)
    TextView tvConunt;
    @BindView(R.id.btn_recycling)
    TextView btnRecycling; 
    @BindView(R.id.btn_harmfulwaste)
    TextView btnHarmfulwaste;
    @BindView(R.id.btn_wetgarbage)
    TextView btnWetgarbage;
    @BindView(R.id.btn_drygarbage)
    TextView btnDrygarbage;
    private Unbinder unbind;
    private String TAG = this.getClass().getCanonicalName();

    private Popular popular;

    private Random random;

    private String name;

    private String result;

    private int potions;

    private List<Table> list_table = new ArrayList<>();

    private int count = 0;

    private GarbageAPIModeImpl garbageAPIMode;

    private int[] i_type = new int[]{0, 1, 2, 3};



    private int temp;

    private String sl_text;

    @Override
    protected String getTitle() {
        return "测试";
    }

    @Override
    protected void initView() {
        garbageAPIMode = new GarbageAPIModeImpl();
        random = new Random();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_exam;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListeners() {
        btnRecycling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sl_text = btnRecycling.getText().toString();
                setTableData(sl_text, name);
            }
        });
        btnHarmfulwaste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sl_text = btnRecycling.getText().toString();
                setTableData(sl_text, name);
            }
        });
        btnWetgarbage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sl_text = btnRecycling.getText().toString();
                setTableData(sl_text, name);
            }
        });
        btnDrygarbage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sl_text = btnRecycling.getText().toString();
                setTableData(sl_text, name);
            }
        });
    }

    //获取垃圾分类的数据
    private void getExamData() {
        temp = random.nextInt(i_type.length);
        garbageAPIMode.reuqestExamData(getActivity(),temp,this);
    }

    private String getCorrect(String name) {
        if (name.equals(Constans.type[temp])) {
            return "正确";
        } else {
            return "错误";
        }
    }

    private void setTableData(String type, String name) {
        Table table = new Table(name, type, getCorrect(sl_text));
        list_table.add(table);
        if ((list_table.size()) == 10) {
            Intent intent = new Intent(getActivity(), AnswerActivity.class);
            intent.putExtra("list _table", (Serializable) list_table);
            startActivity(intent);
            list_table.clear();
        }
        getExamData();
    }

    @Override
    public void onStart() {
        super.onStart();
        count = 0;
        temp = random.nextInt(i_type.length);
        Log.d(TAG, "onStart: "+temp);
        getExamData();
    }
    
    @Override
    public void onPopularSuccess(List<Popular> list) throws JSONException {
        Log.d(TAG, "s uccess");
        count = count + 1;
        int potion=random.nextInt(list.size());
        tvConunt.setText(count + "/10");
        name=list.get(potion).getName();
        tvName.setText(name);
    }

    @Override
    public void onPopularError(Throwable throwable, String errorMag) {
        Log.e(TAG, "onFailure: " + errorMag);
    }
}
*/
