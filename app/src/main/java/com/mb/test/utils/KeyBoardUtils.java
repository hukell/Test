package com.mb.test.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class KeyBoardUtils {
	/**
	 * 打卡软键盘
	 * 
	 * @param mEditText
	 *            输入框
	 * @param mContext
	 *            上下文
	 */
	public static void openKeybord(EditText mEditText, Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
	}

	/**
	 * 关闭软键盘
	 * 
	 * @param mEditText
	 *            输入框
	 * @param mContext
	 *            上下文
	 */
	public static void closeKeybord(EditText mEditText, Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
	}

	public static void closeKeybord(Activity activity) {
		InputMethodManager imm = (InputMethodManager) activity.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}


	private final static int KEYBOARD_VISIBLE_THRESHOLD_DP = 100;
	public static void setEventListener(final Activity activity, final KeyboardVisibilityEventListener listener) {
		if (activity == null) {
			throw new NullPointerException("Parameter:activity must not be null");
		}
		if (listener == null) {
			throw new NullPointerException("Parameter:listener must not be null");
		}
		final View activityRoot = getActivityRoot(activity);
		activityRoot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			private final Rect r = new Rect();
			private final int visibleThreshold = Math.round(convertDpToPx(activity, KEYBOARD_VISIBLE_THRESHOLD_DP));
			private boolean wasOpened = false;

			@Override
			public void onGlobalLayout() {
				activityRoot.getWindowVisibleDisplayFrame(r);
				int heightDiff = activityRoot.getRootView().getHeight() - r.height();
				boolean isOpen = heightDiff > visibleThreshold;
				if (isOpen == wasOpened) {
					// keyboard state has not changed
					return;
				}
				wasOpened = isOpen;
				listener.onVisibilityChanged(isOpen);
			}
		});
	}

	private static View getActivityRoot(Activity activity) {
		return ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
	}

	public static interface KeyboardVisibilityEventListener {

		void onVisibilityChanged(boolean isOpen);
	}

	public static float convertDpToPx(Context context, float dp) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getApplicationContext().getResources().getDisplayMetrics());
//        Resources res = context.getResources();
//        return dp * (res.getDisplayMetrics().densityDpi / 160f);
	}
}
