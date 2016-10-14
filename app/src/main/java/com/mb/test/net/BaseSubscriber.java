package com.mb.test.net;

import android.content.Context;

import com.mb.test.customview.LoadingDialog;
import com.mb.test.utils.NetStateUtils;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/10/11 0011.
 *  http://blog.csdn.net/dd864140130/article/details/52689010
 */

public class BaseSubscriber<T> extends Subscriber<T> {
    private Context mContext;
    private LoadingDialog mDialog;

    public BaseSubscriber() {
    }


    public BaseSubscriber(Context context) {
        mContext = context;
    }

   @Override
    public void onStart() {
        //请求开始之前，检查是否有网络。无网络直接抛出异常
        if (!NetStateUtils.isConnected(mContext)) {
            this.onError(new ApiException(ApiErrorCode.ERROR_NO_INTERNET,"network interrupt"));
        }
       else {
            if (mDialog==null){
                mDialog = new LoadingDialog(mContext);
            }
            mDialog.setCancelable(false);
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.show();

        }
    }

    @Override
    public void onCompleted() {
        if (mDialog!=null && mDialog.isShowing()){
            mDialog.dismiss();
        }

    }

    @Override
    public void onError(Throwable e) {
        ApiErrorHelper.handleCommonError(mContext, e);
        if (mDialog!=null && mDialog.isShowing()){
            mDialog.dismiss();
        }
    }

    @Override
    public void onNext(T t) {
        if (mDialog!=null && mDialog.isShowing()){
            mDialog.dismiss();
        }
    }
}
