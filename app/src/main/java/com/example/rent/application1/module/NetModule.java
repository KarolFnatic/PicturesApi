package com.example.rent.application1.module;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
    private String url = "www.abc.de"; //TODO: zmienić urla na chyba https://unsplash.it?

    //wstrzykuje kontekst aplikacji
    public NetModule(Application application) { //czy w parametrze "(..., String url)" ?
        this.application = application;
//        this.url = url;
    }

    //wstrzykuje kontekst retrofita (zaleznosci do korzystania z retrofita)
    @Provides
    @Singleton
    Application provideApplication(){
        return application;
    }

    @Provides
    @Singleton
    Cache provideOkhttpCache(Application application){
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cache(cache);
        return builder.build();
    }

    @Provides
    @Singleton
    Gson provideGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return retrofit;
    }
}
