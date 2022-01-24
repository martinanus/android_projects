package com.example.android.argentinadailyonline;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class FragmentTechnology extends Fragment {
    private ArticleViewModel mTechnologyViewModel;

    public FragmentTechnology() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("FragmentTechnology", "OnCreateView");

        View rootView = inflater.inflate(R.layout.fragments, container, false);


        //Initialize the RecyclerView
        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        //Set the Layout Manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //Initialize the adapter and set it at the RecyclerView
        final ArticleAdapter adapter = new ArticleAdapter(container.getContext());
        mRecyclerView.setAdapter(adapter);


        mTechnologyViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);

        mTechnologyViewModel.getAllTechnologyArticles().observe(getViewLifecycleOwner(), new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                adapter.setWords(articles);
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragments", "onResume");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Fragments", "onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Fragments", "onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragments", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Fragments", "OnStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragments", "OnDestroy");
    }

}
