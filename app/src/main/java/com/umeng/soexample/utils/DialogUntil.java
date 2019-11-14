package com.umeng.soexample.utils;

import android.content.Context;
import android.view.View;


import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.dialog.LoadingDialog;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.dialog.materialdialog.GravityEnum;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;

public class DialogUntil {

    private LoadingDialog loadingDialog;

    private MaterialDialog  materialDialog;

    private static volatile DialogUntil qmuiTipDialogUntil = null;

    private BottomSheet bottomSheet;

    //单例模式
    public static DialogUntil getInstance() {
        if (qmuiTipDialogUntil == null) {
            synchronized (DialogUntil.class) {
                    qmuiTipDialogUntil = new DialogUntil();
            }
        }
        return qmuiTipDialogUntil;
    }

    public void  showLoadBox(Context context,String name){
        loadingDialog = WidgetUtils.getLoadingDialog(context);
        loadingDialog.setLoadingSpeed(8);
        loadingDialog.updateMessage(name);
        loadingDialog.setIconScale(0.4f);
        loadingDialog.show();
    }

    public void  showHintBox(Context context, String content){
        materialDialog=new MaterialDialog.Builder(context)
                .title("提示")
                .content(content)
                .titleGravity(GravityEnum.CENTER)
                .show();
    }

    public void showInputComment(Context context, String content, MaterialDialog.InputCallback inputCallback,MaterialDialog.SingleButtonCallback singleButtonCallback){
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

    public void  showBottomDialog(Context context,View view){
        bottomSheet=new BottomSheet.BottomListSheetBuilder(context)
                .addHeaderView(view).addItem("关闭").setOnSheetItemClickListener(new BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                    }
                })
                .setIsCenter(true)
                .build();
        bottomSheet.show();
    }

    public void hideLoad(){
        loadingDialog.dismiss();
    }

    public void hideHint(){
        materialDialog.hide();
    }

    public void  hideBottomListSheet(){
        bottomSheet.hide();
    }
}
