package com.example.networky;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonObjHolder {
    //Get response from this end point
    @GET("v2/top-headlines?sources=bbc-news&apiKey=a2eac284c2ad4630a063073966679ad8")
    Call<BigResponse> getArticle();
}
