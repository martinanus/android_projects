package com.example.android.argentinadailyonline;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class FragmentBusiness extends Fragment {
private ArticleViewModel mBusinessViewModel;

//private  LiveData<List<Article>> arts;
//private ArrayList<Article> articlesCached;
//private final static String ARTICLES_CACHED = "articles_cached";

    public FragmentBusiness() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragments, container, false);


        Log.d("fragmentBusiness", "onCreateView");

        //Initialize the RecyclerView
        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        //Set the Layout Manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //Initialize the adapter and set it at the RecyclerView
        final ArticleAdapter adapter = new ArticleAdapter(container.getContext());
        mRecyclerView.setAdapter(adapter);


        mBusinessViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);

        mBusinessViewModel.getAllBusinessArticles().observe(getViewLifecycleOwner(), new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                adapter.setWords(articles);
            }
        });


/*
        if (savedInstanceState != null) {
            Log.d("fragmentBusiness", "onCreateViewRestoring");

            articlesCached = (ArrayList<Article>) savedInstanceState.getSerializable(ARTICLES_CACHED);
            adapter.setWords(articlesCached);


        } else {
            articlesCached = null;
            mBusinessViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);

            arts =mBusinessViewModel.getAllArticles();
            arts.observe(getViewLifecycleOwner(), new Observer<List<Article>>() {
                @Override
                public void onChanged(List<Article> articles) {
                    adapter.setWords(articles);
                }
            });

        }
*/
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("fragmentBusiness", "onResume");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("fragmentBusiness", "onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("fragmentBusiness", "onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("fragmentBusiness", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("fragmentBusiness", "OnStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("fragmentBusiness", "OnDestroy");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("fragmentBusiness", "onSaveInstanceState");
        outState.clear();

/*
        if(articlesCached == null)
            articlesCached = new ArrayList<>(arts.getValue());

        outState.putSerializable(ARTICLES_CACHED, articlesCached);
*/
    }
}
