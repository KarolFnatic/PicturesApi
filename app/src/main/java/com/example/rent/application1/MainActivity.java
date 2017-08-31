package com.example.rent.application1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.rent.application1.adapters.PictureListAdapter;
import com.example.rent.application1.adapters.PicturePagerAdapter;
import com.example.rent.application1.models.Picture;
import com.example.rent.application1.service.PicturesApi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://unsplash.it";
    public List<Picture> picturesList;
    private PictureListAdapter pictureListAdapter;


    @Inject
    PicturesApi picturesApi;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //zbieram aplikację, cast na MyApllication
        ((MyApplication) getApplication()).getNetComponent().inject(this);
        ButterKnife.bind(this);
        prepareAdapter();
        getPicturesRxJava();
    }

    private void prepareAdapter() {
        picturesList = new ArrayList<>();
        pictureListAdapter = new PictureListAdapter(picturesList, getSupportFragmentManager());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(pictureListAdapter);
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

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}

