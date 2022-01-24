package com.example.android.argentinadailyonline;

import androidx.lifecycle.LiveData;

import java.util.List;


class ArticleRepository {
    private ArticleFirebase mFirebase;


    ArticleRepository(){
        mFirebase = new ArticleFirebase();
    }

    LiveData<List<Article>> getAllArticles(){ return mFirebase.getAllArticles(); }

    LiveData<List<Article>> getAllBusinessArticles(){ return mFirebase.getAllBusinessArticles();}

    LiveData<List<Article>> getAllPoliticsArticles(){ return mFirebase.getAllPoliticsArticles();}

    LiveData<List<Article>> getAllTechnologyArticles(){ return mFirebase.getAllTechnologyArticles();}

    LiveData<List<Article>> getAllWorldArticles(){ return mFirebase.getAllWorldArticles();}

}






/*
public class ArticleRepository {
    private ArticleDao mArticleDao;

    ArticleRepository(Application application) {
        ArticleRoomDatabase db = ArticleRoomDatabase.getDatabase(application);
        mArticleDao = db.articleDao();
    }


    LiveData<List<Article>> getAllArticles() {
        return mArticleDao.getAllArticles();
    }

    LiveData<List<Article>> getAllBusinessArticles() {
        return mArticleDao.getAllBusinessArticles();
    }


    LiveData<List<Article>> getAllPoliticsArticles() {
        return mArticleDao.getAllPoliticsArticles();
    }

    LiveData<List<Article>> getAllTechnologyArticles() {
        return mArticleDao.getAllTechnologyArticles();
    }

    LiveData<List<Article>> getAllWorldArticles() {
        return mArticleDao.getAllWorldArticles();
    }

    public void insertArticle(Article article) {
        new insertArticleAsyncTask(mArticleDao).execute(article);
    }

    public void deleteAll() {
        new deleteAllAsyncTask(mArticleDao).execute();
    }

    public void deleteArticle(Article article) {
        new deleteArticleAsyncTask(mArticleDao).execute(article);
    }

    public void updateArticle(Article article) {
        new updateArticleAsyncTask(mArticleDao).execute(article);
    }


    private class insertArticleAsyncTask extends AsyncTask<Article, Void, Void> {
        private ArticleDao mArticleDao;

        public insertArticleAsyncTask(ArticleDao dao) {
            mArticleDao = dao;
        }

        @Override
        protected Void doInBackground(Article... articles) {
            mArticleDao.insert(articles[0]);
            return null;
        }
    }

    private class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private ArticleDao mArticleDao;

        public deleteAllAsyncTask(ArticleDao dao) {
            mArticleDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mArticleDao.deleteAll();
            return null;
        }
    }

    private class deleteArticleAsyncTask extends AsyncTask<Article, Void, Void> {
        private ArticleDao mArticleDao;

        public deleteArticleAsyncTask(ArticleDao dao) {
            mArticleDao = dao;
        }

        @Override
        protected Void doInBackground(Article... articles) {
            mArticleDao.delete(articles[0]);
            return null;
        }
    }

    private class updateArticleAsyncTask extends AsyncTask<Article, Void, Void> {
        private ArticleDao mArticleDao;

        public updateArticleAsyncTask(ArticleDao dao) {
            mArticleDao = dao;
        }

        @Override
        protected Void doInBackground(Article... articles) {
            mArticleDao.update(articles[0]);
            return null;
        }
    }


}

 */