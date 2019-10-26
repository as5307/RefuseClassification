package com.umeng.soexample.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.bean.Table;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AnswerActivity extends BaseActivity {

    @BindView(R.id.table_layout)
    TableLayout tableLayout;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.anew_text)
    Button anewText;

    private TableRow tableRow;
    private TextView textView;

    private List<Table> list;

    private int count = 0;

    private String TAG = "ResultActivity";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_answer;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        list = (List<Table>) getIntent().getSerializableExtra("list _table");
        addView();
    }


    /*
        动态设置布局
        */
    @SuppressLint("SetTextI18n")
    private void addView() {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Table table = list.get(i);
                tableRow = new TableRow(AnswerActivity.this);
                if ((i + 1) % 2 == 0) {
                    tableRow.setBackgroundColor(Color.parseColor("#eeeeee"));
                }
                tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
                tableLayout.addView(tableRow);
                for (int a = 0; a < 3; a++) {
                    textView = new TextView(AnswerActivity.this);
                    if (a == 0) {
                        textView.setText(table.getName());
                    } else if (a == 1) {
                        textView.setText(table.getType());
                    } else {
                        textView.setText(table.getAnswer());
                        if (table.getAnswer().equals("正确")) {
                            count = count + 10;
                        }
                        tvCount.setText(count + "分");
                    }
                    textView.setTextSize(20);
                    tableRow.addView(textView);
                    textView.setPadding(30, 30, 30, 30);
                }
            }
        } else {
            Log.d(TAG, "addView: list为空");
        }
    }

    @OnClick(R.id.anew_text)
    public void onViewClicked() {
        finish();
    }
}
