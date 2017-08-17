package com.example.rent.application1.retrofit;

import com.example.rent.application1.service.PicturesApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by RENT on 2017-08-17.
 */

public class BaseRetrofit {
    private static String ENDPOINT = "http://10.40.21.186:3000/";

    private final Retrofit retrofit;
    private final PicturesApi flowersApi;

    public BaseRetrofit() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())//potrzebne aby można było dać Observable zamiast Call
                .build();

        flowersApi = retrofit.create(PicturesApi.class);
    }
}
