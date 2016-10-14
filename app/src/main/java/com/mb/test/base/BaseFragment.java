package com.mb.test.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 *
 */
public abstract class BaseFragment extends Fragment {

	protected Activity mActivity;
	protected View mContentView;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		FragmentActivity activity = getActivity();
		if (activity != null) {
			mActivity = activity;
		}
	}


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (mContentView == null) {
			mContentView = inflater.inflate(getLayoutId(), container, false);
		}
		ButterKnife.bind(this, mContentView);
		initView(mContentView);
		return mContentView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initOperation();
	}

	/**
	 * 初始化
	 */
	protected abstract void init();

	/**
	 * 添加布局ID，初始化布局
	 *
	 * @return
	 */
	public abstract int getLayoutId();

	/**
	 * 执行查找控件等基本操作
	 *
	 * @param view
	 */

	protected abstract void initView(View view);

	/**
	 * 初始化一些操作，设置适配器，加载数据等等。
	 */
	protected abstract void initOperation();

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		((ViewGroup) mContentView.getParent()).removeView(mContentView);
		ButterKnife.unbind(this);
	}

}
