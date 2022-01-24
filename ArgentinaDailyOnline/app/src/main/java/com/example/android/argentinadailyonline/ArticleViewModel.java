package com.example.android.argentinadailyonline;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ArticleViewModel extends AndroidViewModel {
    private ArticleRepository mRepository;

    public ArticleViewModel(Application application){
        super(application);
        mRepository = new ArticleRepository();
        Log.d("ViewModel", "Constructor");
    }

    LiveData<List<Article>> getAllArticles(){return mRepository.getAllArticles();}

    LiveData<List<Article>> getAllBusinessArticles(){return mRepository.getAllBusinessArticles();}

    LiveData<List<Article>> getAllPoliticsArticles(){return mRepository.getAllPoliticsArticles();}

    LiveData<List<Article>> getAllTechnologyArticles(){return mRepository.getAllTechnologyArticles();}

    LiveData<List<Article>> getAllWorldArticles(){return mRepository.getAllWorldArticles();}

/*
    LiveData<List<Article>> getAllArticles(){return mRepository.getAllArticles();}

    LiveData<List<Article>> getAllBusinessArticles(){return mRepository.getAllBusinessArticles();}

    LiveData<List<Article>> getAllPoliticsArticles(){return mRepository.getAllPoliticsArticles();}

    LiveData<List<Article>> getAllTechnologyArticles(){return mRepository.getAllTechnologyArticles();}

    LiveData<List<Article>> getAllWorldArticles(){return mRepository.getAllWorldArticles();}

    public  void insertArticle(Article article){mRepository.insertArticle(article);}

    public void  deleteAll(){mRepository.deleteAll();}

    public void deleteArticle(Article article){mRepository.deleteArticle(article);}

    public void updateArticle(Article article){mRepository.updateArticle(article);}

 */

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("ViewModel", "onCleared");
    }
}
