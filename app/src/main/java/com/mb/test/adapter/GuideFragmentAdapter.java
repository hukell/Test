package com.mb.test.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mb.test.R;
import com.mb.test.fragment.GuideFragment;

public class GuideFragmentAdapter extends FragmentPagerAdapter {

    private Context context;

    int path[] = new int[]{R.mipmap.guider_one, R.mipmap.guider_two, R.mipmap.guider_three,R.mipmap.guider_three};

    public GuideFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {


        GuideFragment bannerFragment = new GuideFragment();
        Bundle bundle = new Bundle();


        bundle.putInt("image", path[position]);
        bundle.putBoolean("isEnd", position == path.length - 1);

        bannerFragment.setArguments(bundle);
        return bannerFragment;
    }

    @Override
    public int getCount() {


        if (path.length == 0) {
            return 0;
        }
        return path.length;
    }


}


