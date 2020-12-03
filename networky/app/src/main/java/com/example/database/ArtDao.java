package com.example.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.networky.Art;

import java.util.List;

@Dao
public interface ArtDao {
    @Insert
    void saveArticle(Art article);

    @Query("SELECT * FROM art")
    LiveData<List<Art>> getAllArticles();

    @Delete
    void deleteArticle(Art... articles);
}
