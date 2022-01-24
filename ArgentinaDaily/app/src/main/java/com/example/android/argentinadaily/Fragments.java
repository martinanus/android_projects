package com.example.android.argentinadaily;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Fragments extends Fragment {
private ArticleViewModel mBusinessViewModel;
private ArticleViewModel mPoliticsViewModel;
private ArticleViewModel mTechnologyViewModel;
private ArticleViewModel mWorldViewModel;

    private static final String CATEGORY_KEY = "category_key";
    private static final int BUSINESS = 0;
    private static final int POLITICS = 1;
    private static final int TECHNOLOGY = 2;
    private static final int  WORLD= 3;

    public Fragments() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragments, container, false);

        Log.d("ONCREATEFRAGMENT", "a new fragment is created");

        //Initialize the RecyclerView
        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        //Set the Layout Manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //Initialize the adapter and set it at the RecyclerView
        final ArticleAdapter adapter = new ArticleAdapter(container.getContext());
        mRecyclerView.setAdapter(adapter);


        switch (getArguments().getInt(CATEGORY_KEY, -1)){
            case BUSINESS:
                //populate the fragment with the info of the database
                mBusinessViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);

                mBusinessViewModel.getAllBusinessArticles().observe(getViewLifecycleOwner(), new Observer<List<Article>>() {
                    @Override
                    public void onChanged(List<Article> businessArticles) {
                        //Update the cached copy of the words in the adapter
                        adapter.setWords(businessArticles);
                    }
                });
                break;
            case POLITICS:
                //populate the fragment with the info of the database
                mPoliticsViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);

                mPoliticsViewModel.getAllPoliticsArticles().observe(getViewLifecycleOwner(), new Observer<List<Article>>() {
                    @Override
                    public void onChanged(List<Article> politicsArticles) {
                        //Update the cached copy of the words in the adapter
                        adapter.setWords(politicsArticles);
                    }
                });
                break;
            case TECHNOLOGY:
                //populate the fragment with the info of the database
                mTechnologyViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);

                mTechnologyViewModel.getAllTechnologyArticles().observe(getViewLifecycleOwner(), new Observer<List<Article>>() {
                    @Override
                    public void onChanged(List<Article> technologyArticles) {
                        //Update the cached copy of the words in the adapter
                        adapter.setWords(technologyArticles);
                    }
                });
                break;
            case WORLD:
                //populate the fragment with the info of the database
                mWorldViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);

                mWorldViewModel.getAllWorldArticles().observe(getViewLifecycleOwner(), new Observer<List<Article>>() {
                    @Override
                    public void onChanged(List<Article> worldArticles) {
                        //Update the cached copy of the words in the adapter
                        adapter.setWords(worldArticles);
                    }
                });
                break;
            default:
                Toast.makeText(rootView.getContext(),"ERROR", Toast.LENGTH_SHORT).show();
                break;
        }

        // Inflate the layout for this fragment
        return rootView;
    }
}
