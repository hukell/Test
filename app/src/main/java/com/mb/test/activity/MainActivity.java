package com.mb.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.mb.test.R;
import com.mb.test.customview.CustomActionBar;
import com.mb.test.models.User;
import com.mb.test.models.javaBean;
import com.mb.test.net.Api;
import com.mb.test.net.BaseSubscriber;
import com.mb.test.utils.ImageLoader;
import com.mb.test.utils.rxjava.RxBus;
import java.util.HashMap;
import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * http://pic51.nipic.com/file/20141027/11284670_094822707000_2.jpg
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.img)
    ImageView mImg;
    @Bind(R.id.img1)
    ImageView mImg1;
    @Bind(R.id.img2)
    ImageView mImg2;
    @Bind(R.id.img3)
    ImageView mImg3;
    @Bind(R.id.img4)
    ImageView mImg4;
    @Bind(R.id.img5)
    ImageView mImg5;
    @Bind(R.id.img6)
    ImageView mImg6;
    @Bind(R.id.img7)
    ImageView mImg7;
    @Bind(R.id.img8)
    ImageView mImg8;
    @Bind(R.id.img9)
    ImageView mImg9;
    @Bind(R.id.img10)
    ImageView mImg10;
    @Bind(R.id.btn)
    TextView mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        CustomActionBar customActionBar = (CustomActionBar) findViewById(R.id.custom);
        customActionBar.setLeftText("2222222222222");
        customActionBar.setLeftTextColor(R.color.colorPrimary);


        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //加载圆形图片
        ImageLoader.loadCicleImage(this, "http://pic51.nipic.com/file/20141027/11284670_094822707000_2.jpg", mImg);
        ImageLoader.loadCicleImage(this, "http://b.hiphotos.baidu.com/image/h%3D200/sign=239b2b62d3ca7bcb627bc02f8e086b3f/7dd98d1001e9390170aa9f9f7fec54e737d196e2.jpg", mImg1);
        ImageLoader.loadCicleImage(this, "http://g.hiphotos.baidu.com/image/h%3D200/sign=c485b482b47eca800d053ee7a1229712/8cb1cb1349540923abc071df9658d109b2de49e1.jpg", mImg2);
        ImageLoader.loadCicleImage(this, "http://pic7.nipic.com/20100609/5136651_124423001651_2.jpg", mImg3);
        ImageLoader.loadCicleImage(this, "http://pic11.nipic.com/20101119/3320946_221711832717_2.jpg", mImg4);
        ImageLoader.loadCicleImage(this, "http://a.hiphotos.baidu.com/image/h%3D200/sign=89ffa247237f9e2f6f351a082f31e962/d8f9d72a6059252dfc5e0da5309b033b5ab5b9c1.jpg", mImg5);
        ImageLoader.loadCicleImage(this, "http://i2.cqnews.net/car/attachement/jpg/site82/20120817/5404a6b61e3c1197fb211d.jpg", mImg6);
        ImageLoader.loadCicleImage(this, "http://pic.4j4j.cn/upload/pic/20130815/5e604404fe.jpg", mImg7);
        ImageLoader.loadCicleImage(this, "http://pic.4j4j.cn/upload/pic/20130909/681ebf9d64.jpg", mImg8);
        ImageLoader.loadCicleImage(this, "http://d.hiphotos.baidu.com/image/pic/item/562c11dfa9ec8a13f075f10cf303918fa1ecc0eb.jpg", mImg9);
        ImageLoader.loadCicleImage(this, "http://c.hiphotos.baidu.com/image/pic/item/7af40ad162d9f2d38d7056c8acec8a136327ccb0.jpg", mImg10);

        //     ?key=15c295614d473&code=102629

        RxBus.getInstance().toObserverable(Integer.class)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer i) {
                        if (i == 1) {
                            HashMap<String, String> params = new HashMap<>();
                            params.put("key", "15c295614d473");
                            params.put("code", "102629");
                            Api.getRetrofit().testT(params)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new BaseSubscriber<javaBean<User>>(MainActivity.this) {
                                        @Override
                                        public void onNext(javaBean<User> userjavaBean) {
                                            super.onNext(userjavaBean);
                                            mBtn.setText(userjavaBean.getData().getCity()+"");
                                        }
                                    });
                        }
                    }
                });

    }

}
