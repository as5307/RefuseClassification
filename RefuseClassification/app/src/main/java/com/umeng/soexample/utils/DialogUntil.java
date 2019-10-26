package com.umeng.soexample.utils;

import android.content.Context;

import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.dialog.LoadingDialog;
import com.xuexiang.xui.widget.dialog.materialdialog.GravityEnum;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;

public class DialogUntil {

    private LoadingDialog loadingDialog;

    private MaterialDialog materialDialog;

    private static volatile DialogUntil qmuiTipDialogUntil = null;

    //单例模式
    public static DialogUntil getInstance() {
        if (qmuiTipDialogUntil == null) {
            synchronized (TTSUtils.class) {
                    qmuiTipDialogUntil = new DialogUntil();
            }
        }
        return qmuiTipDialogUntil;
    }

    public void initLoadBox(Context context,String name){
        loadingDialog = WidgetUtils.getLoadingDialog(context);
        loadingDialog.setLoadingSpeed(8);
        loadingDialog.updateMessage(name);
        loadingDialog.setIconScale(0.4f);
        loadingDialog.show();
    }

    public void  initHintBox(Context context, String content){
        materialDialog=new MaterialDialog.Builder(context)
                .title("提示")
                .content(content)
                .titleGravity(GravityEnum.CENTER)
                .show();
    }

    public void initInputComment(Context context, String content, MaterialDialog.InputCallback inputCallback,MaterialDialog.SingleButtonCallback singleButtonCallback){

        materialDialog=new MaterialDialog.Builder(context)
                .title("评论")
                .content(content)
                .input("说点什么","", false, inputCallback)
                .inputRange(2,20)
                .positiveText("发表")
                .onPositive(singleButtonCallback)
                .cancelable(true)

                .show();

    }

    public void hideLoad(){
        loadingDialog.dismiss();
    }

    public void hideHint(){
        materialDialog.hide();
    }
}
