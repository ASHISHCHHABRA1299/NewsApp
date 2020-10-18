package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements NewsItemClicked {
    RecyclerView mRecyclerView;
    ArrayList<String> items=new ArrayList<String>(Arrays.asList("zee","aajtak","abp","abvp","timesnow","republic","zee",
            "aajtak","abp","abvp","timesnow","republic","zee","aajtak","abp","abvp","timesnow","republic","zee"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView=(RecyclerView)findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        NewsListAdapter myadapter=new NewsListAdapter(items,this);
        mRecyclerView.setAdapter(myadapter);
    }

    @Override
    public void onItemClicked(String item) {
        Toast.makeText(this, item+ " Clicked", Toast.LENGTH_SHORT).show();
    }
}