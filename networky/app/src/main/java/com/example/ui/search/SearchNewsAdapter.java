package com.example.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.networky.Art;
import com.example.networky.R;
import com.example.networky.databinding.SearchNewsItemBinding;
import com.example.ui.ItemCallback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchNewsAdapter extends RecyclerView.Adapter<SearchNewsAdapter.SearchNewsViewHolder> {
    private List<Art> articles = new ArrayList<>();
    public void setArticles(List<Art> newsList) {
        articles.clear();
        articles.addAll(newsList);
        notifyDataSetChanged();
    }

    private ItemCallback itemCallback;
    public void setItemCallback(ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
    }


    @NonNull
    @Override
    public SearchNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_news_item, parent, false);
        return new SearchNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchNewsViewHolder holder, int position) {
        Art article = articles.get(position);
        holder.favIcon.setImageResource(R.drawable.ic_favorite_24dp);
        holder.itemTitleTextView.setText(article.getTitle());
        Picasso.get().load(article.getUrlToImage()).into(holder.itemImageView);
        holder.itemView.setOnClickListener(v -> itemCallback.onOpenDetails(article));
        holder.favIcon.setOnClickListener(v -> itemCallback.onFavorite(article));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class SearchNewsViewHolder extends RecyclerView.ViewHolder {
        ImageView favIcon;
        ImageView itemImageView;
        TextView itemTitleTextView;
        public SearchNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            SearchNewsItemBinding binding = SearchNewsItemBinding.bind(itemView);
            favIcon = binding.searchItemFavorite;
            itemImageView = binding.searchItemImage;
            itemTitleTextView = binding.searchItemTitle;
        }
    }
}
