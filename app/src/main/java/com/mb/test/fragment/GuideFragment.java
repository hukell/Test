package com.mb.test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.mb.test.R;
import com.mb.test.activity.MainActivity;

public class GuideFragment extends Fragment implements View.OnClickListener {

    private int images;

    /**
     * 构造函数
     */
    public GuideFragment() {

    }

    /**
     * 创建界面时调用
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        images = getArguments().getInt("image");
        View view = inflater.inflate(R.layout.fragment_guider_layout, container, false);
        ImageView image = (ImageView) view.findViewById(R.id.image);

        ImageView end = (ImageView) view.findViewById(R.id.end);

        image.setImageResource(images);
        boolean isEnd = getArguments().getBoolean("isEnd");


        //判断是不是到了最后一张图片，如果到了，则显示按钮并设置按钮点击事件

        if (isEnd) {

            end.setVisibility(View.VISIBLE);
            end.setOnClickListener(this);
        } else {
            end.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
     //   SharePreHelper.getIns().putIsFirstLoad(true);
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

}
