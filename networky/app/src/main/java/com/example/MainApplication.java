package com.example;

import android.app.Application;

import androidx.room.Room;

import com.example.database.Database;

public class MainApplication extends Application {
    private Database database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(this, Database.class, "news_db").build();
    }

    public Database getDatabase() {
        return database;
    }
}
