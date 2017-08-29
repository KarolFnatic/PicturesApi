package com.example.rent.application1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rent.application1.adapters.PictureListAdapter;
import com.example.rent.application1.models.Picture;
import com.example.rent.application1.service.PicturesApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://unsplash.it";
    private Retrofit retrofit;
    private PicturesApi picturesApi;
    private List<Picture> picturesList;
    private PictureListAdapter pictureListAdapter;

    @Inject
    Retrofit retrofit;


    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        prepareRetrofit();
        prepareAdapter();

        getPicturesRxJava();

        //zbieram aplikację, cast na MyApllication
        ((MyApplication) getApplication()).getNetComponent().inject(this);

    }

    private void prepareAdapter() {
        picturesList = new ArrayList<>();
        pictureListAdapter = new PictureListAdapter(picturesList, getSupportFragmentManager());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(pictureListAdapter);
    }

    private void prepareRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        picturesApi = retrofit.create(PicturesApi.class);
    }

    private void getPicturesRxJava() {
        picturesApi.getAllPicturesRxJava()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Picture>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull List<Picture> pictures) {
                        Log.d("RxJava", "New pictures ! " + pictures.size());
                        picturesList.addAll(pictures);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("RxJava", "DONE!");
                        showToast("DONE!");
                        pictureListAdapter.notifyDataSetChanged();
                    }
                });
    }


    @NonNull
    private Gson getGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .setLenient()
                .create();
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
