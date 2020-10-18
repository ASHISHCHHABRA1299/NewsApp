package com.example.newsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsListAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    NewsItemClicked listen;
    ArrayList<String> items;
    public NewsListAdapter(ArrayList<String> lists,NewsItemClicked listener) {
        this.items=lists;
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
        String item=items.get(position);
        holder.mTextView.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
    class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mTextView=(TextView)itemView.findViewById(R.id.title);
     }
}
interface NewsItemClicked {
    void onItemClicked(String item);
}