package com.example.networky;

import androidx.annotation.Nullable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticlePlaceHolder {

    @GET("top-headlines")
    Call<BigResponse> getTopHeadlines(@Query("country") String country, @Query("category") String category);

    @GET("everything")
    Call<BigResponse> getEverything(@Query("q") String query, @Query("pageSize") int pageSize);
}
