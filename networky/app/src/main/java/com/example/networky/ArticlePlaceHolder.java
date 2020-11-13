package com.example.networky;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ArticlePlaceHolder {

    //Get articles from any type of query to news API, There must be new Queries made but this interface will work with all of them.
    @GET("articles")
    Call<List<Art>> getPosts();
}
