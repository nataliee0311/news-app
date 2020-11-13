package com.example.networky;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Art {

    //private String ;
    private String url;
    private String title;
    private String description;
    private Date publishedAt;
    private String content;
    private String author;
    private String urlToImage;

    @SerializedName("body")
    private String text;

    //May want to check functions and variables names to make sure they fit with the JSOn response from news API

    public String getAuthor() {return author; }
    public String getTitle() {
        return title;
    }
    public String getDescription() {return description; }
    public String getUrl() { return url; }
    public String getUrlToImage() { return urlToImage; }
    public Date getPublishedAt() {return publishedAt; }
    public String getContent() {return content; }
}
