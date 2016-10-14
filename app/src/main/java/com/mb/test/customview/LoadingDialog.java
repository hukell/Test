package com.mb.test.customview;

import android.app.Dialog;
import android.content.Context;

import com.mb.test.R;

/**
 * Created by Administrator on 2016/10/12 0012.
 *
 */

public class LoadingDialog extends Dialog {
    public LoadingDialog(Context context) {
        super(context, R.style.dialogTheme3);
        setContentView(R.layout.loading_dialog_layout);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
