package com.example.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.networky.Art;
import com.example.networky.databinding.FragmentHomeBinding;
import com.example.repository.NewsRepository;
import com.example.ui.ItemCallback;
import com.example.ui.ViewModelFactory;

public class HomeFragment extends Fragment implements OnItemSelectedListener {
    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;
    private HomeNewsAdapter newsAdapter;

    public HomeFragment() {
    }

    private void updateViewModel(String category) {
        NewsRepository repository = new NewsRepository(getContext());
        viewModel = new ViewModelProvider(this, new ViewModelFactory(repository))
                .get(HomeViewModel.class);
        viewModel.setSources(category);
        viewModel.getTopHeadlines().observe(getViewLifecycleOwner(), newsResponse -> {
            if (newsResponse != null) {
                newsAdapter.setArticles(newsResponse.getArtlist());
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle homeInstanceState) {
        super.onViewCreated(view, homeInstanceState);

        newsAdapter = new HomeNewsAdapter();
        binding.newsHomeRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.newsHomeRecyclerView.setAdapter(newsAdapter);

        updateViewModel("general");
        viewModel.setSpinnerAdapter(binding, this.getContext());
        binding.categorySpinner.setOnItemSelectedListener(this);

        newsAdapter.setItemCallback(new ItemCallback() {
            @Override
            public void onOpenDetails(Art article) {
                NavHostFragment.findNavController(HomeFragment.this).navigate(HomeFragmentDirections.actionNavigationHomeToNavigationDetails(article));
            }

            @Override
            public void onFavorite(Art article) {
                viewModel.setFavArticleInput(article);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
        String s = (String) parent.getItemAtPosition(pos);
        updateViewModel(s.toLowerCase());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
