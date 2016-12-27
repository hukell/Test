package com.mb.test.net;

import com.mb.test.utils.SharePreHelper;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by Administrator on 2016/10/8 0008.
 */

public class Api {
    public static Retrofit mRetrofit;
    private static ApiService mApiService;
    public static String base_url;
    private static OkHttpClient okHttpClient = null;
    public static int TIME_OUT = 20;

    private static boolean checkNull() {
        return mRetrofit == null ? true : false;
    }

    private static void init() {

        okHttpClient = new OkHttpClient.Builder().writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
        base_url = getServerUrl();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(base_url )
                .client(okHttpClient)
                .addConverterFactory(CustomGsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mApiService = mRetrofit.create(ApiService.class);
    }

    public static ApiService getRetrofit() {
        if (checkNull()) {
            init();
        }
        return mApiService;
    }

    public static String getServerUrl() {

           String url = SharePreHelper.getIns().getPath();

        return url;
    }

    public static void resetRetrofit() {
        mRetrofit = null;
    }
}