package com.example.ui.save;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.networky.Art;
import com.example.networky.databinding.FragmentSaveBinding;
import com.example.repository.NewsRepository;
import com.example.ui.ItemCallback;

public class SaveFragment extends Fragment {
    private FragmentSaveBinding binding;
    private SaveViewModel viewModel;

    public SaveFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSaveBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SavedNewsAdapter savedNewsAdapter = new SavedNewsAdapter();
        binding.newsSavedRecyclerView.setAdapter(savedNewsAdapter);
        binding.newsSavedRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        NewsRepository repository = new NewsRepository(requireContext());
        viewModel = new SaveViewModel(repository);
        viewModel
                .getAllSavedArticles()
                .observe(
                        getViewLifecycleOwner(),
                        savedArticles -> {
                            if (savedArticles != null) {
                                Log.d("SaveFragment", savedArticles.toString());
                                savedNewsAdapter.setArticles(savedArticles);
                            }
                        });

        savedNewsAdapter.setItemCallback(new ItemCallback() {
            @Override
            public void onOpenDetails(Art article) {
                SaveFragmentDirections.ActionNavigationSaveToNavigationDetails direction = SaveFragmentDirections.actionNavigationSaveToNavigationDetails(article);
                NavHostFragment.findNavController(SaveFragment.this).navigate(direction);
                Log.d("onOpenDetails", article.toString());
            }

            @Override
            public void onRemoveFavorite(Art article) {
                viewModel.deleteSavedArticle(article);
            }
        });
    }
}
