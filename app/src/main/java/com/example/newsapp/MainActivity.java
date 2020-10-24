package com.example.newsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewsItemClicked,AdapterView.OnItemSelectedListener {
    RecyclerView mRecyclerView;
    NewsListAdapter myadapter;
    Spinner mSpinner;
    String url="https://newsapi.org/v2/top-headlines?country=in&category=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView=(RecyclerView)findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mSpinner=(Spinner)findViewById(R.id.categories);
        mSpinner.setOnItemSelectedListener(this);
        myadapter=new NewsListAdapter(this);
        mRecyclerView.setAdapter(myadapter);
    }

    private void fetchdata(String url){
//        String url="https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=f545eab44cea4fee8ba496becd3f7c95";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET,url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray newjsonarray=response.getJSONArray("articles");
                            ArrayList<News> newsArray =new ArrayList<>();
                            for(int i=0;i<newjsonarray.length();i++)
                            {
                                JSONObject newJsonObject=newjsonarray.getJSONObject(i);
                                News news=new News(newJsonObject.getString("title"),
                                        newJsonObject.getString("author"),
                                        newJsonObject.getString("url"),
                                        newJsonObject.getString("urlToImage"));
                                newsArray.add(news);
                            }
                            myadapter.updateNews(newsArray);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
    @Override
    public void onItemClicked(News item) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(item.url));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        fetchdata(url+adapterView.getSelectedItem().toString()+"&apiKey=f545eab44cea4fee8ba496becd3f7c95");
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}