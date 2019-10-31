package com.umeng.soexample.utils.update;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.umeng.soexample.utils.Utils;
import com.xuexiang.xui.widget.dialog.DialogLoader;


import io.reactivex.annotations.Nullable;


public class UpdateTipDialog extends AppCompatActivity implements DialogInterface.OnDismissListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        finish();
    }
}
