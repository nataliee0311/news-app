package com.example.ui.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.networky.Art;
import com.example.networky.databinding.FragmentSearchBinding;
import com.example.repository.NewsRepository;
import com.example.ui.ItemCallback;
import com.example.ui.ViewModelFactory;

public class SearchFragment extends Fragment {

    private SearchViewModel viewModel;
    private FragmentSearchBinding binding;

    public SearchFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SearchNewsAdapter newsAdapter = new SearchNewsAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        binding.newsResultsRecyclerView.setLayoutManager(gridLayoutManager);
        binding.newsResultsRecyclerView.setAdapter(newsAdapter);

        binding.newsSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.isEmpty()) {
                    viewModel.setQuery(query);
                }
                binding.newsSearchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

        NewsRepository repository = new NewsRepository(getContext());
        viewModel = new ViewModelProvider(this, new ViewModelFactory(repository))
                .get(SearchViewModel.class);
        viewModel.search().observe(getViewLifecycleOwner(), newsResponse -> {
            if (newsResponse != null) {
                newsAdapter.setArticles(newsResponse.getArtlist());
            }
        });

        newsAdapter.setItemCallback(new ItemCallback() {
            @Override
            public void onOpenDetails(Art article) {
                NavHostFragment.findNavController(SearchFragment.this).navigate(SearchFragmentDirections.actionNavigationSearchToNavigationDetails(article));
            }

            @Override
            public void onFavorite(Art article) {
                repository.favoriteArticle(article);
            }
        });
    }
}
