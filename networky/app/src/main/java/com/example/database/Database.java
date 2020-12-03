package com.example.database;

import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.networky.Art;

@androidx.room.Database(entities = {Art.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {

    public abstract ArtDao articleDao();
}
