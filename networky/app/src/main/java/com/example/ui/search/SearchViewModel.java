package com.example.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.networky.BigResponse;
import com.example.repository.NewsRepository;

public class SearchViewModel extends ViewModel {
    private final NewsRepository repository;
    private final MutableLiveData<String> query = new MutableLiveData<>();

    public SearchViewModel(NewsRepository repository) {
        this.repository = repository;
    }

    public void setQuery(String s) { query.setValue(s); }

    public LiveData<BigResponse> search() {
        return Transformations.switchMap(query, repository::search);
    }
}
