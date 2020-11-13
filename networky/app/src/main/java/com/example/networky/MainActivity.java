package com.example.networky;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String getDateString(Date publishDate) {
        DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return formatter.format(publishDate);
    }

    private TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult=findViewById(R.id.Result);
        Retrofit retrotwo=new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonObjHolder articleHolder=retrotwo.create(JsonObjHolder.class);

        Call<BigResponse> shout=articleHolder.getArticle();
        shout.enqueue(new Callback<BigResponse>() {
            @Override
            public void onResponse(Call<BigResponse> call, Response<BigResponse> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                BigResponse articles=response.body();
                List<Art> stuffs=articles.getArtlist();

                for(Art post: stuffs) {
                    String content="";
                    content+="Title: " +post.getTitle() + "\n";
                    content+="Url: " + post.getUrl() + "\n";
                    content+= "Description: " + post.getDescription() + "\n";
                    content+= "Author: " + post.getAuthor() + "\n";
                    content+= "Url To Image: " + post.getUrlToImage() + "\n";

                    content+= "Published At: " + getDateString(post.getPublishedAt()) + "\n";
                    textViewResult.append(content);
                }
            }
            @Override
            public void onFailure(Call<BigResponse> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}