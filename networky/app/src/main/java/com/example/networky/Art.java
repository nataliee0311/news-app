package com.example.networky;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Art implements Serializable {

    private String title;
    private String description;
    private String publishedAt;
    private String content;
    private String author;

    @NonNull
    @PrimaryKey
    private String url;
    private String urlToImage;

    @SerializedName("body")
    private String text;

    //May want to check functions and variables names to make sure they fit with the JSOn response from news API

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {return author; }
    public String getTitle() {
        return title;
    }
    public String getDescription() {return description; }
    public String getUrl() { return url; }
    public String getUrlToImage() { return urlToImage; }
    public String getPublishedAt() {return publishedAt; }
    public String getContent() {return content; }
    public String getText() { return text; }

}
