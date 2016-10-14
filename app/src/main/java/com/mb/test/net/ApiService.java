package com.mb.test.net;

import com.mb.test.models.javaBean;
import com.mb.test.models.User;

import java.util.HashMap;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/8 0008.
 */

public interface  ApiService {

    /**
     * test  http://apicloud.mob.com/v1/postcode/query?key=15c295614d473&code=102629
     *
     *
     */
   @GET("postcode/query")
   Observable<javaBean<User>> testT(@QueryMap HashMap<String, String> params);

}
