package com.example.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.repository.NewsRepository;
import com.example.ui.home.HomeViewModel;
import com.example.ui.save.SaveViewModel;
import com.example.ui.search.SearchViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final NewsRepository repository;

    public ViewModelFactory(NewsRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(repository);
        } else if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(repository);
        } else if (modelClass.isAssignableFrom(SaveViewModel.class)) {
            return (T) new SaveViewModel(repository);
        } else {
            throw new IllegalStateException("Unknown ViewModel");
        }
    }
}
