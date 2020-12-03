package com.example.ui;

import android.util.Log;

import com.example.networky.Art;

public interface ItemCallback {
    default void onOpenDetails(Art article) {
        Log.d("MyTag", "Default implementation of onOpenDetails");
    }
    default void onRemoveFavorite(Art article) {
        Log.d("MyTag", "Default implementation of onRemoveFavorite");
    }
    default void onFavorite(Art article) {
        Log.d("MyTag", "Default implementation of onFavorite");
    }
}
