package com.umeng.soexample.utils.update;

import com.xuexiang.xupdate.entity.UpdateError;
import com.xuexiang.xupdate.listener.OnUpdateFailureListener;
import com.xuexiang.xutil.tip.ToastUtils;

public class CustomUpdateFailureListener implements OnUpdateFailureListener {

    /**
     * 是否需要错误提示
     */
    private boolean mNeedErrorTip;

    public CustomUpdateFailureListener(boolean needErrorTip) {
        mNeedErrorTip = needErrorTip;
    }

    /**
     * 更新失败
     *
     * @param error 错误
     */
    @Override
    public void onFailure(UpdateError error) {
        if (mNeedErrorTip) {
            ToastUtils.toast(error.toString());
        }
    }
}
