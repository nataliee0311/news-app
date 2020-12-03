package com.example.ui.home;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.networky.Art;
import com.example.networky.BigResponse;
import com.example.networky.R;
import com.example.networky.databinding.FragmentHomeBinding;
import com.example.repository.NewsRepository;

public class HomeViewModel extends ViewModel {
    private final NewsRepository repository;
    private final MutableLiveData<String> sources = new MutableLiveData<>();

    public HomeViewModel(NewsRepository repository) {
        this.repository = repository;
    }

    public void setSources(String s) {
        sources.setValue(s);
    }

    public LiveData<BigResponse> getTopHeadlines() {
        return Transformations.switchMap(sources, repository::getTopHeadlines);
    }

    public void setSpinnerAdapter(FragmentHomeBinding binding, Context context) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.category_array, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.categorySpinner.setAdapter(adapter);
    }

    public void setFavArticleInput(Art article) {
        repository.favoriteArticle(article);
    }
}
