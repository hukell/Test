package com.mb.test.utils;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created by MagicBean on 2015/06/25 15:15:57
 */
public class CameraUtil {
    public static final int PICK_PHOTO = 105;
    public static final int CROP_IMAGE_CODE = 106;
    public static final int CROP_CAMERA_IMAGE_CODE = 107;
    public static final int PICK_DETAIL = 108;
    public static final int PICK_GROUP = 109;
    public static final int CAMERA_IMAGE = 110;
    public static final int TAKE_PHOTO = 101;
    private static final String TAG = CameraUtil.class.getSimpleName();
    private static String cameraDir = getSDCardPath() + "DCIM/Camera/";
    private static StringBuilder fileName = new StringBuilder();

    /**
     * 获取拍照的Intent
     *
     * @return
     */
    public static Intent getCameraIntent() {
        resetStringBuilder();
        fileName.append("IMG_");
        fileName.append(getCurrentTime());
        fileName.append(".jpg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        creatDir(cameraDir);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(cameraDir, fileName.toString())));
        return intent;
    }

    public static String createImageName() {
        return "IMG_" + getCurrentTime() + ".jpg";
    }

    /**
     * 图片裁剪
     *
     * @param uri
     * @param outputX
     * @param outputY
     * @return
     */
    public static Intent getCropIntent(Uri uri, int outputX, int outputY) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", true);
        // intent.putExtra("outputFormat",
        // Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection

        return intent;
    }

    /**
     * 获取相册intent
     *
     * @return
     */
    public static Intent getAlbumIntent() {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
        } else {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        return intent;
    }

    /**
     * 获取当前时间<br>
     */
    private static CharSequence getCurrentTime() {
        return DateFormat.format("yyyyMMdd_hhmmss", new Date());
    }

    /**
     * 重置fileName
     */
    private static void resetStringBuilder() {
        if (fileName.length() > 0) {
            fileName.delete(0, fileName.length());
        }
    }

    /**
     * 获取图片真实路径
     */
    public static String getRealFilePath() {
        return cameraDir + fileName.toString();
    }

    /**
     * 创建文件夹
     */
    private static String creatDir(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dirPath;
    }

    /**
     * 判断SD卡是否可用
     */
    private static boolean sdCardIsExit() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡路径
     */
    public static String getSDCardPath() {
        if (sdCardIsExit()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        }
        return null;
    }

    public static String resolvePhotoFromIntent(final Context ctx, final Intent data) {
        if (ctx == null || data == null || cameraDir == null) {
            Log.e(TAG, "resolvePhotoFromIntent fail, invalid argument");
            return null;
        }

        String filePath = null;

        final Uri uri = Uri.parse(data.toURI());
        Cursor cu = ctx.getContentResolver().query(uri, null, null, null, null);
        if (cu != null && cu.getCount() > 0) {
            try {
                cu.moveToFirst();
                final int pathIndex = cu.getColumnIndex(MediaStore.MediaColumns.DATA);
                Log.e(TAG, "orition: " + cu.getString(cu.getColumnIndex(MediaStore.Images.ImageColumns.ORIENTATION)));
                filePath = cu.getString(pathIndex);
                Log.d(TAG, "photo from resolver, path:" + filePath);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (data.getData() != null) {
            filePath = data.getData().getPath();
            if (!(new File(filePath)).exists()) {
                filePath = null;
            }
            Log.d(TAG, "photo file from data, path:" + filePath);

        } else if (data.getAction() != null && data.getAction().equals("inline-data")) {

            try {
                final String fileName = createImageName();
                filePath = cameraDir + fileName;

                final Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                final File file = new File(filePath);
                if (!file.exists()) {
                    file.createNewFile();
                }

                BufferedOutputStream out;
                out = new BufferedOutputStream(new FileOutputStream(file));
                final int cQuality = 100;
                bitmap.compress(Bitmap.CompressFormat.PNG, cQuality, out);
                out.close();
                Log.d(TAG, "photo image from data, path:" + filePath);

            } catch (final Exception e) {
                e.printStackTrace();
            }

        } else {
            if (cu != null) {
                cu.close();
                cu = null;
            }
            Log.e(TAG, "resolve photo from intent failed");
            return null;
        }
        if (cu != null) {
            cu.close();
            cu = null;
        }
        return filePath;
    }

}
