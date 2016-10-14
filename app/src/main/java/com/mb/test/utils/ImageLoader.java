package com.mb.test.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.mb.test.R;
import com.mb.test.activity.PhotoActivity;

/**
 * Created by MagicBean on 2016/01/13 15:15:37
 */
public class ImageLoader {
    public static void loadImage(final Activity activity, String url, ImageView view) {
        if (activity == null) {
            return;
        }
        Glide.with(activity).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(view);

        CustomPhoto(activity, url, view);

    }

    public static void loadImage(Activity activity, String url, int placeholder, ImageView view) {
        if (activity == null) {
            return;
        }
        Glide.with(activity).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(placeholder).error(placeholder).into(view);
    }


    public static void loadImaged(Activity activity, String url, int placeholdering, int placeholdered, ImageView view) {
        if (activity == null) {
            return;
        }
        Glide.with(activity).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(placeholdering).error(placeholdered).into(view);
    }

    public static void loadImage(Activity activity, String url, int placeholder, int width, int height, ImageView view) {
        if (activity == null) {
            return;
        }
        Glide.with(activity).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(placeholder).override(width, height).error(placeholder).into(view);
    }

    public static void loadImage(Fragment fragment, String url, ImageView view) {
        if (fragment == null) {
            return;
        }
        Glide.with(fragment)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
//                .placeholder(R.drawable.loading_spinner)
                .crossFade()
                .into(view);

    }

    public static void loadImage(Context context, String url, ImageView view) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(url)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
//                .placeholder(R.drawable.loading_spinner)
//                .centerCrop()
                .into(view);

    }

    public static void loadImage(Context context, Uri url, ImageView view) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
//                .placeholder(R.drawable.loading_spinner)
                .crossFade()
                .into(view);

    }

    public static void loadCicleImage(Activity activity, String url, ImageView view) {
        if (activity == null) {
            return;
        }
        Glide.with(activity).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).bitmapTransform(new CropCircleTransformation(activity)).into(view);
        CustomPhoto(activity, url, view);
    }

    public static void loadCicleImage(Activity activity, String url, int placeholder, ImageView view) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Glide.with(activity).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(placeholder).error(placeholder).bitmapTransform(new CropCircleTransformation(activity)).into(view);
    }



    public static void loadCicleImage(Activity activity, int resId, ImageView view) {
        if (activity == null) {
            return;
        }
        Glide.with(activity).load(resId).diskCacheStrategy(DiskCacheStrategy.ALL).bitmapTransform(new CropCircleTransformation(activity)).into(view);
    }

    public static void loadCicleImage(Fragment fragment, int resId, ImageView view) {
        if (fragment == null) {
            return;
        }
    //    Glide.with(fragment).load(resId).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.default_person).error(R.mipmap.default_person).bitmapTransform(new CropCircleTransformation(fragment.getActivity())).into(view);
    }

    public static void loadCicleImage(Fragment fragment, String url, ImageView view) {
        if (fragment == null) {
            return;
        }
        Glide.with(fragment).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).bitmapTransform(new CropCircleTransformation(fragment.getActivity())).into(view);
    }

    public static void loadCicleImage(Fragment fragment, String url, int placeholder, ImageView view) {
        if (fragment == null) {
            return;
        }
        Glide.with(fragment)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeholder).error(placeholder)
                .bitmapTransform(new CropCircleTransformation(fragment.getActivity()))
                .into(view);
    }

    public static void loadImage(Activity activity, int resId, ImageView imageView) {
        if (activity == null) {
            return;
        }
        Glide.with(activity).load(resId).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }


    // 图片的缩放
    private static void CustomPhoto(final Activity activity, final String url, ImageView view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity,PhotoActivity.class);
                intent.putExtra("photoPath",url);
                activity.startActivity(intent);
            }
        });
    }



    public static class CropCircleTransformation implements Transformation<Bitmap> {

        private BitmapPool mBitmapPool;

        public CropCircleTransformation(Context context) {
            this(Glide.get(context).getBitmapPool());
        }

        public CropCircleTransformation(BitmapPool pool) {
            this.mBitmapPool = pool;
        }

        @Override
        public Resource<Bitmap> transform(Resource<Bitmap> resource, int outWidth, int outHeight) {
            Bitmap source = resource.get();
            int size = Math.min(source.getWidth(), source.getHeight());

            int width = (source.getWidth() - size) / 2;
            int height = (source.getHeight() - size) / 2;

            Bitmap bitmap = mBitmapPool.get(size, size, Bitmap.Config.ARGB_8888);
            if (bitmap == null) {
                bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            if (width != 0 || height != 0) {
                // source isn't square, move viewport to center
                Matrix matrix = new Matrix();
                matrix.setTranslate(-width, -height);
                shader.setLocalMatrix(matrix);
            }
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return BitmapResource.obtain(bitmap, mBitmapPool);
        }

        @Override
        public String getId() {
            return "CropCircleTransformation()";
        }
    }
}
