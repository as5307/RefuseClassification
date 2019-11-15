package com.umeng.soexample.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.widget.AppCompatImageView;

import com.umeng.soexample.R;
import com.umeng.soexample.activity.PublushActivity;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.dialog.LoadingDialog;
import com.xuexiang.xui.widget.dialog.MiniLoadingDialog;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.dialog.materialdialog.GravityEnum;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.imageview.ImageLoader;
import com.xuexiang.xui.widget.progress.loading.ARCLoadingView;

public class DialogUntil {

    private LoadingDialog loadingDialog;

    private MaterialDialog  materialDialog;

    private ProgressDialog progressDialog;

    private static volatile DialogUntil qmuiTipDialogUntil = null;

    private ARCLoadingView arcLoadingView;

    private BottomSheet bottomSheet;

    private MiniLoadingDialog miniLoadingDialog;

    //单例模式
    public static DialogUntil getInstance() {
        if (qmuiTipDialogUntil == null) {
            synchronized (DialogUntil.class) {
                    qmuiTipDialogUntil = new DialogUntil();
            }
        }
        return qmuiTipDialogUntil;
    }

    //加载对话框
    public void  showLoadBox(Context context,String name){
        loadingDialog = WidgetUtils.getLoadingDialog(context);
        loadingDialog.setLoadingSpeed(8);
        loadingDialog.updateMessage(name);
        loadingDialog.setIconScale(0.4f);
        loadingDialog.show();
    }

    public void showMinLoadBox(Context context,String name){
        miniLoadingDialog =  WidgetUtils.getMiniLoadingDialog(context);
        miniLoadingDialog.updateMessage(name);
        miniLoadingDialog.show();
    }

    //提示性弹框
    public void showCustomBox(Context context, String name,String type){
        View view= LayoutInflater.from(context).inflate(R.layout.layout_hintbox,null);
        TextView textGoods=view.findViewById(R.id.tv_hint_goods);
        TextView textType=view.findViewById(R.id.tv_hint_type);
        AppCompatImageView imageView=view.findViewById(R.id.iv_hint_type);
        textGoods.setText(name);
        textType.setText(type);

        switch (type){
            case "可回收物":
                ImageLoader.get().loadImage(imageView,R.drawable.ic_recyclable);
                break;
            case "有害垃圾":
                ImageLoader.get().loadImage(imageView,R.drawable.ic_harmful);
                break;
            case "湿垃圾":
                ImageLoader.get().loadImage(imageView,R.drawable.ic_wet);
                break;
            case "干垃圾":
                ImageLoader.get().loadImage(imageView,R.drawable.ic_dry);
                break;
        }
        materialDialog=new MaterialDialog.Builder(context)
                .title("提示")
                .customView(view,false)
                .titleGravity(GravityEnum.CENTER)
                .show();
    }

    //提示性弹框
    public void  showHintBox(Context context, String content){
        materialDialog=new MaterialDialog.Builder(context)
                .title("提示")
                .content(content)
                .titleGravity(GravityEnum.CENTER)
                .show();
    }



    //输入弹框
    public void showInputBox(Context context, String content, MaterialDialog.InputCallback inputCallback,MaterialDialog.SingleButtonCallback singleButtonCallback){
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

    //底部弹框
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

    //进度弹框
    public void  showProgressDialog(Context context){
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("上传图片中...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }


    public void setProgress(int value){
        progressDialog.setProgress(value);
    }

    public void hideLoad(){
        loadingDialog.dismiss();
    }

    public void hideMainLoad(){
        miniLoadingDialog.dismiss();
    }

    public void hideHint(){
        materialDialog.hide();
    }

    public void hideProgress(){
        progressDialog.hide();
    }

    public void  hideBottomListSheet(){
        bottomSheet.dismiss();
    }
}
