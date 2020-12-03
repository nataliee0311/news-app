package com.example.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.networky.Art;
import com.example.networky.R;
import com.example.networky.databinding.HomeNewsItemBinding;
import com.example.ui.ItemCallback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeNewsAdapter extends RecyclerView.Adapter<HomeNewsAdapter.HomeNewsViewHolder> {

    private ItemCallback itemCallback;
    public void setItemCallback(ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
    }

    private List<Art> articles = new ArrayList<>();
    public void setArticles(List<Art> newsList) {
        articles.clear();
        articles.addAll(newsList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_news_item, parent, false);
        return new HomeNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeNewsAdapter.HomeNewsViewHolder holder, int position) {
        final Art article = articles.get(position);
        holder.titleTextView.setText(article.getTitle());
        holder.descriptionTextView.setText(article.getDescription());
        holder.favIcon.setImageResource(R.drawable.ic_favorite_24dp);
        Picasso.get().load(article.getUrlToImage()).into(holder.itemImageView);
        holder.itemView.setOnClickListener(v -> itemCallback.onOpenDetails(article));
        holder.favIcon.setOnClickListener(v -> itemCallback.onFavorite(article));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class HomeNewsViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        ImageView favIcon;
        ImageView itemImageView;
        public HomeNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            HomeNewsItemBinding binding = HomeNewsItemBinding.bind(itemView);
            titleTextView = binding.homeItemContent;
            descriptionTextView = binding.homeItemDescriptionContent;
            favIcon = binding.homeItemFavoriteImageView;
            itemImageView = binding.imageView;
        }
    }
}
