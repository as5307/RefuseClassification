package com.umeng.soexample.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.bean.Result;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.imageview.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;

public class ResultActivity extends BaseActivity {


    @BindView(R.id.tb_title)
    TitleBar tbTitle;
    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.table_layout)
    TableLayout tableLayout;

    private ArrayList<Result> list_result;

    private String image_path;
    private String TAG = "ResultActivity";

    private TableRow tableRow;

    private TextView textView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_result;
    }

    @Override
    protected void initView() {
        Bundle bundle = this.getIntent().getExtras();
        list_result = (ArrayList<Result>) bundle.getSerializable("result");
        image_path = bundle.getString("image_path");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tbTitle.setTitle("图像识别");
        Log.d(TAG, "initData: " + list_result + " " + " " + image_path);
        ImageLoader.get().loadImage(ivTitle, image_path);
        addView();
    }

    @Override
    protected void initListeners() {
        tbTitle.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /*
      动态设置布局
      */
    @SuppressLint("SetTextI18n")
    private void addView() {
        if (list_result != null) {
            for (int i = 0; i < list_result.size()+1; i++) {
                tableRow = new TableRow(this);
                tableRow.setBackgroundColor(Color.parseColor("#ffffff"));
                tableLayout.addView(tableRow);
                if (i==0){
                    for (int a = 0; a < 3; a++) {
                        textView = new TextView(this);
                        if (a == 0) {
                            textView.setText("物品名称");
                        } else if (a == 1) {
                            textView.setText("置信度");
                        } else {
                            textView.setText("类别");
                        }
                        textView.setGravity(Gravity.CENTER);
                        textView.setTextSize(15);
                        tableRow.addView(textView);
                    }
                }else {
                    Result result = list_result.get(i-1);

                    for (int a = 0; a < 3; a++) {
                        textView = new TextView(this);
                        if (a == 0) {
                            textView.setText(result.getKeyword());
                        } else if (a == 1) {
                            textView.setText(result.getScore());
                        } else {
                            textView.setText(result.getType());
                        }
                        textView.setGravity(Gravity.CENTER);
                        textView.setTextSize(15);
                        tableRow.addView(textView);
                    }
                }
            }
        } else {
            Log.d(TAG, "addView: list为空");
        }
    }
}
