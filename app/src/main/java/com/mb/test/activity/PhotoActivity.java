package com.mb.test.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.mb.test.R;
import com.mb.test.utils.ImageLoader;
import com.mb.test.utils.rxjava.RxBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.senab.photoview.PhotoView;

public class PhotoActivity extends AppCompatActivity {

    @Bind(R.id.photoView)
    PhotoView mPhotoView;
    @Bind(R.id.btn)
    Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String photoPath = intent.getStringExtra("photoPath");
        ImageLoader.loadImage(this, photoPath, mPhotoView);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPhotoView != null && mPhotoView.getDrawable() != null) {
            Bitmap oldBitmap = ((BitmapDrawable) mPhotoView.getDrawable()).getBitmap();

            mPhotoView.setImageDrawable(null);

            if (oldBitmap != null) {

                oldBitmap.recycle();

            }
        }

    }

    @OnClick(R.id.btn)
    public void onClick() {
        RxBus.getInstance().post(1);

    }
}
