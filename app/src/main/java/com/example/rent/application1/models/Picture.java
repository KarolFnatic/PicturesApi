package com.example.rent.application1.models;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

public class Picture implements Parcelable{

    private String filename;
    private String author;
    private int height;
    private int width;
    private int id;

    protected Picture(Parcel in) {
        filename = in.readString();
        author = in.readString();
        height = in.readInt();
        width = in.readInt();
        id = in.readInt();
    }

    public static final Creator<Picture> CREATOR = new Creator<Picture>() {
        @Override
        public Picture createFromParcel(Parcel in) {
            return new Picture(in);
        }

        @Override
        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };

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

    public static String getPhotoUrl(Picture picture, float scale) {
        return "https://unsplash.it/"+ picture.getWidth() * scale + "/" + picture.getWidth() * scale + "/?image=" + picture.getId();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(filename);
        parcel.writeString(author);
        parcel.writeInt(height);
        parcel.writeInt(width);
        parcel.writeInt(id);
    }
}
