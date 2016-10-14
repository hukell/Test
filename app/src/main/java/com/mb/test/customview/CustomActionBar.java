package com.mb.test.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mb.test.R;

/**
 * Created by Sky on 2015/9/4 22:33
 * 自定义ActionBar
 */
public class CustomActionBar extends RelativeLayout implements View.OnClickListener {
    private LinearLayout mRootView;
    private FrameLayout mLeftContainer;
    private FrameLayout mRightContainer;
    private TextView mTitle;
    private ImageView mLeftImage;
    private ImageView mRightImage;
    private TextView mLeftText;
    private TextView mRightText;
    private OnActionBarListener mAtionBarListener;

    public CustomActionBar(Context context) {
        super(context);
        initializeView();
    }

    public CustomActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView();
    }

    public CustomActionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeView();
    }

    private void initializeView() {
        inflate(getContext(), R.layout.custom_actionbar_layout, this);
    //    setId(R.id.actionBar);
        mRootView = (LinearLayout) findViewById(R.id.actionbar_rootview);
        mLeftContainer = (FrameLayout) findViewById(R.id.actionbar_left_container);
        mRightContainer = (FrameLayout) findViewById(R.id.actionbar_right_container);

        mTitle = (TextView) findViewById(R.id.actionbar_title);
        mLeftImage = (ImageView) findViewById(R.id.actionbar_left_image);
        mLeftText = (TextView) findViewById(R.id.actionbar_left_txt);

        mRightImage = (ImageView) findViewById(R.id.actionbar_right_image);
        mRightText = (TextView) findViewById(R.id.actionbar_right_txt);


        mLeftImage.setOnClickListener(this);
        mLeftText.setOnClickListener(this);
        mRightImage.setOnClickListener(this);
        mRightText.setOnClickListener(this);

    }

    /**
     * 根布局
     *
     * @return
     */
    public LinearLayout getRootView() {
        return mRootView;
    }

    /**
     * 左边容器
     *
     * @return
     */
    public FrameLayout getLeftContainer() {
        return mLeftContainer;
    }

    /**
     * 右边容器
     *
     * @return
     */
    public FrameLayout getRightContainer() {
        return mRightContainer;
    }

    /**
     * 标题TextView
     *
     * @return
     */
    public TextView getTitle() {
        return mTitle;
    }

    /**
     * 左边ImageView
     *
     * @return
     */
    public ImageView getLeftImage() {
        return mLeftImage;
    }

    /**
     * 右边ImageView
     *
     * @return
     */
    public ImageView getRightImage() {
        return mRightImage;
    }

    /**
     * 左边txtView
     *
     * @return
     */
    public TextView getLeftText() {
        return mLeftText;
    }

    /**
     * 右边txt
     *
     * @return
     */
    public TextView getRightText() {
        return mRightText;
    }

    /**
     * 设置标题
     *
     * @param resId
     */
    public void setTitleText(int resId) {
        mTitle.setText(resId);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitleText(CharSequence title) {
        mTitle.setText(title);
    }

    /**
     * 设置标题颜色
     *
     * @param color
     */
    public void setTitleTextColor(int color) {
        mTitle.setTextColor(getContext().getResources().getColor(color));
    }


    /**
     * 设置左边TextView文字
     *
     * @param resId
     */
    public void setLeftText(int resId) {
        mLeftText.setText(resId);
    }

    /**
     * 设置左边TextView文字
     *
     * @param title
     */
    public void setLeftText(String title) {
        mLeftText.setText(title);
        mLeftText.setVisibility(VISIBLE);
    }

    /**
     * 设置左边文字颜色
     *
     * @param color
     */
    public void setLeftTextColor(int color) {
        mLeftText.setTextColor(getContext().getResources().getColor(color));
    }

    /**
     * 设置右边TextView文字
     *
     * @param resId
     */
    public void setRightText(int resId) {
        mRightText.setText(resId);
    }

    /**
     * 设置右边TextView文字
     *
     * @param title
     */
    public void setRightText(String title) {
        mRightText.setText(title);
    }

    /**
     * 设置右边文字颜色
     *
     * @param color
     */
    public void setRightTextColor(int color) {
        mRightText.setTextColor(getContext().getResources().getColor(color));
    }

    public void setRightTextBgRes(int res) {
        mRightText.setBackgroundResource(res);
    }


    /**
     * 设置左边ImageView
     *
     * @param resId
     */
    public void setLeftImageView(int resId) {
        mLeftImage.setImageResource(resId);
    }

    /**
     * 设置右边ImageView
     *
     * @param resId
     */
    public void setRightImageView(int resId) {
        mRightImage.setImageResource(resId);
    }

    public void setLeftVisible(boolean visible) {
        mLeftImage.setVisibility(visible ? VISIBLE : GONE);
        mLeftText.setVisibility(GONE);
    }

    public void setLeftTextVisible(boolean visible) {
        mLeftText.setVisibility(visible ? VISIBLE : GONE);
        mRightImage.setVisibility(GONE);
    }

    public void setRightVisible(boolean visible) {
        mRightImage.setVisibility(visible ? VISIBLE : GONE);
        mRightText.setVisibility(GONE);
    }

    public void setRightTextVisible(boolean visible) {
        mRightText.setVisibility(visible ? VISIBLE : GONE);
        mRightImage.setVisibility(GONE);
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            mAtionBarListener.onActionBarClick(v);
        }
    }

    public static interface OnActionBarListener {
        void onActionBarClick(View view);
    }

    public void setActionBarListener(OnActionBarListener actionBarListener) {
        this.mAtionBarListener = actionBarListener;
    }
}
