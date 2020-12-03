package com.example.ui.details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.networky.Art;
import com.example.networky.databinding.FragmentDetailsBinding;
import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment {
    private FragmentDetailsBinding binding;

    public DetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assert getArguments() != null;
        Art article = DetailsFragmentArgs.fromBundle(getArguments()).getArticle();
        binding.detailsTitleTextView.setText(article.getTitle());
        binding.detailsAuthorTextView.setText(article.getAuthor());
        binding.detailsDateTextView.setText(article.getPublishedAt().toString());
        binding.detailsDescriptionTextView.setText(article.getDescription());
        binding.detailsContentTextView.setText(article.getContent());
        Picasso.get().load(article.getUrlToImage()).into(binding.detailsImageView);
    }
}