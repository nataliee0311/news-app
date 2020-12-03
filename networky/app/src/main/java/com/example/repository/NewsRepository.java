package com.example.repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.MainApplication;
import com.example.database.Database;
import com.example.networky.Art;
import com.example.networky.ArticlePlaceHolder;
import com.example.networky.BigResponse;
import com.example.networky.RetrofitClient;

import java.util.List;
import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    private final ArticlePlaceHolder placeHolder;
    private final Database database;

    public NewsRepository(Context context) {
        placeHolder = RetrofitClient.newInstance(context).create(ArticlePlaceHolder.class);
        database = ((MainApplication)context.getApplicationContext()).getDatabase();
    }

    public LiveData<BigResponse> getTopHeadlines(String category) {
        MutableLiveData<BigResponse> headlines = new MutableLiveData<>();
        placeHolder.getTopHeadlines("us", category)
                .enqueue(new Callback<BigResponse>() {
                    @Override public void onResponse(@NonNull Call<BigResponse> call, @NonNull Response<BigResponse> response) {
                        if (response.isSuccessful()) {
                            headlines.setValue(response.body());
                        } else {
                            Log.d("MyTAG", "response unsuccessful");
                            headlines.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<BigResponse> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        headlines.setValue(null);
                    }
                });
        return headlines;
    }

    public LiveData<BigResponse> search(String query) {
        MutableLiveData<BigResponse> everything = new MutableLiveData<>();
        placeHolder.getEverything(query, 40)
                .enqueue(new Callback<BigResponse>() {
                    @Override public void onResponse(@NonNull Call<BigResponse> call, @NonNull Response<BigResponse> response) {
                        if (response.isSuccessful()) {
                            everything.setValue(response.body());
                        } else {
                            Log.d("MyTAG", "response unsuccessful");
                            everything.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<BigResponse> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        everything.setValue(null);
                    }
                });
        return everything;
    }

    private static class SaveAsyncTask implements Callable<Boolean> {
        private final Database database;
        private final Art article;
        private SaveAsyncTask(Database database, Art article) {
            this.database = database;
            this.article = article;
        }

        @Override
        public Boolean call() {
            try {
                database.articleDao().saveArticle(article);
            } catch (Exception e) {
                return false;
            }
            return true;
        }
    }

    private static class DeleteAsyncTask implements Callable<Boolean> {
        private final Database database;
        private final Art article;
        private DeleteAsyncTask(Database database, Art article) {
            this.database = database;
            this.article = article;
        }

        @Override
        public Boolean call() {
            try {
                database.articleDao().deleteArticle(article);
            } catch (Exception e) {
                return false;
            }
            return true;
        }
    }

    public LiveData<Boolean> favoriteArticle(Art article) {
        MutableLiveData<Boolean> resultLiveData = new MutableLiveData<>();
        TaskRunner runner = new TaskRunner();
        runner.executeAsync(new SaveAsyncTask(database, article), resultLiveData::setValue);
        return resultLiveData;
    }

    public LiveData<List<Art>> getAllSavedArticles() {
        return database.articleDao().getAllArticles();
    }

    public LiveData<Boolean> deleteSavedArticle(Art article) {
        MutableLiveData<Boolean> resultLiveData = new MutableLiveData<>();
        TaskRunner runner = new TaskRunner();
        runner.executeAsync(new DeleteAsyncTask(database, article), resultLiveData::setValue);
        return resultLiveData;
    }
}
