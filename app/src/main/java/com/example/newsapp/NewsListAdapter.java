package com.example.newsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsListAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    NewsItemClicked listen;
    private ArrayList<News> items=new ArrayList<>();
    public NewsListAdapter(NewsItemClicked listener) {
        this.listen=listener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.item_news,parent,false);
        final NewsViewHolder viewHolder=new NewsViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listen.onItemClicked(items.get(viewHolder.getAdapterPosition()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News item=items.get(position);
        holder.title.setText(item.title);
        holder.author.setText(item.author);
        Glide.with(holder.itemView.getContext()).load(item.imageurl).into(holder.image1);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateNews(ArrayList<News> updatedNews)
    {
        items.clear();
        items.addAll(updatedNews);
        notifyDataSetChanged();
    }
}
    class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView title,author;
        ImageView image1;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title=(TextView)itemView.findViewById(R.id.title);
            this.author=(TextView)itemView.findViewById(R.id.author);
            this.image1=(ImageView)itemView.findViewById(R.id.image);
     }
}
interface NewsItemClicked {
    void onItemClicked(News item);
}