package com.example.rent.application1.models;

public class Picture {

    private String filename;
    private String author;


    public String getPhotoUrl() {
        return "https://unsplash.it/" + filename;
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

    public void setAuthor(String author) {
        this.author = author;
    }
}
