package com.example.android.argentinadailyonline;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
/*
@Dao
public interface ArticleDao {

    //Declare all the operation than the dao will use with the SQLite

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Article article);

    @Query("DELETE FROM article_table")
    void deleteAll();

    @Query("SELECT * from article_table ORDER BY id ASC")
    LiveData<List<Article>> getAllArticles();


    @Query("SELECT * from article_table WHERE category = 'Economía' ORDER BY id ASC")
    LiveData<List<Article>> getAllBusinessArticles();


    @Query("SELECT * from article_table WHERE category = 'Politica' ORDER BY id ASC")
    LiveData<List<Article>> getAllPoliticsArticles();


    @Query("SELECT * from article_table WHERE category = 'Tecnología' ORDER BY id ASC")
    LiveData<List<Article>> getAllTechnologyArticles();


    @Query("SELECT * from article_table WHERE category = 'Internacional' ORDER BY id ASC")
    LiveData<List<Article>> getAllWorldArticles();

    @Query("SELECT * from article_table LIMIT 1")
    Article[] getAnyArticle();


    @Query("SELECT * from article_table WHERE id LIKE :selectedId")
    Article getArticleById(int selectedId);

    @Delete
    void delete(Article article);

    @Update
    void update(Article article);

}
*/