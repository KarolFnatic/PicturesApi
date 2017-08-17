package com.example.rent.application1.service;

import com.example.rent.application1.models.Picture;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PicturesApi {

    @GET("/list.json")
    Call<List<Picture>> getAllPictures();

    @GET("/list.json")
    Observable<List<Picture>> getAllPicturesRxJava();

}
