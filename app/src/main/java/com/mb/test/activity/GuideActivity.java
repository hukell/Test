package com.mb.test.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.mb.test.R;
import com.mb.test.adapter.GuideFragmentAdapter;
import com.mb.test.customview.CirclePageIndicator;

public class GuideActivity extends FragmentActivity {

    private CirclePageIndicator indicator;
    private ViewPager viewPager;
    private GuideFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initViewPager();
  //    SharePreHelper.getIns().putIsFirstLoad(true);
    }

    public void initView() {
        indicator = (CirclePageIndicator) findViewById(R.id.indicatoer);
        viewPager = (ViewPager) findViewById(R.id.vp);
    }

    public void initViewPager() {
        adapter = new GuideFragmentAdapter(getSupportFragmentManager(), getApplicationContext());
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
    }

}
