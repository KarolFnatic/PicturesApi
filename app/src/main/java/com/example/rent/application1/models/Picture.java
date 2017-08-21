package com.example.rent.application1.models;

import android.media.Image;

public class Picture {

    // TODO: 2017-08-21 Implement parcelable

    private String filename;
    private String author;
    private int height;
    private int width;
    private int id;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public static String getThumbnailUrl(Picture picture) {
        return "https://unsplash.it/300/200/?image=" + picture.getId();
    }

    public static String getPhotoUrl(Picture picture) {
        return "https://unsplash.it/"+ picture.getWidth()+ "/" + picture.getWidth() + "/?image=" + picture.getId();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getAuthor() {
        return author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
