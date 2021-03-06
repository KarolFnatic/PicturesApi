package com.example.rent.application1.models;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;

@Getter
public class Picture implements Parcelable {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
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

    public static String getThumbnailUrl(Picture picture) {
        return String.format("https://unsplash.it/%d/%d/?image=%d", WIDTH, HEIGHT, picture.getId());
    }

    public static String getPhotoUrl(Picture picture, float scale) {
        return String.format("https://unsplash.it/%f/%f/?image=%d", picture.getWidth() * scale, picture.getHeight() * scale, picture.getId());
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
