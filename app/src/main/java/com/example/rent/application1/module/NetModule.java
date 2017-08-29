package com.example.rent.application1.module;

import android.app.Application;

import com.example.rent.application1.service.PicturesApi;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {
    //klasa: CO chcemy wstrzykiwać

    Application application;
    private String url = "https://unsplash.it"; //TODO: zmienić urla na chyba https://unsplash.it?

    //wstrzykuje kontekst aplikacji
    public NetModule(Application application) { //czy w parametrze "(..., String url)" ?
        this.application = application;
//        this.url = url;
    }

    //wstrzykuje kontekst retrofita (zaleznosci do korzystania z retrofita)
    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    Cache provideOkhttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cache(cache);
        return builder.build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    PicturesApi providePicturesApi(Retrofit retrofit) {
        return retrofit.create(PicturesApi.class);
    }
}
