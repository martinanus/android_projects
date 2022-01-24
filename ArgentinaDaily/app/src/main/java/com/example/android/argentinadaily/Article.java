package com.example.android.argentinadaily;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "article_table")
class Article {

    //Autogenerate primary key
    @PrimaryKey(autoGenerate = true)
    protected int id;

    //Define the parts of an Article and define each as column

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @NonNull
    @ColumnInfo(name = "sub_title")
    private String subtitle;

    @NonNull
    @ColumnInfo(name = "image_resource")
    private final int imageResource;

    @NonNull
    @ColumnInfo(name = "image_description")
    private String imageDescription;

    @NonNull
    @ColumnInfo(name = "text")
    private String text;

    @NonNull
    @ColumnInfo(name = "author")
    private String author;

    @NonNull
    @ColumnInfo(name = "date")
    private String date;

    @NonNull
    @ColumnInfo(name = "category")
    private String category;




    //Constructor - assign values to a new instance
    Article(String title, String subtitle, int imageResource, String imageDescription, String text, String author, String date, String category) {
        this.title = title;
        this.subtitle = subtitle;
        this.imageResource = imageResource;
        this.imageDescription = imageDescription;
        this.text = text;
        this.author = author;
        this.date = date;
        this.category = category;
    }


    //Alternative Constructor with selected ID
    @Ignore
    Article(int id, String title, String subtitle, int imageResource, String imageDescription, String text, String author, String date, String category) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.imageResource = imageResource;
        this.imageDescription = imageDescription;
        this.text = text;
        this.author = author;
        this.date = date;
    }


    //Define getters
    int getId(){return this.id;}
    String getTitle(){return this.title;}
    String getSubtitle(){return this.subtitle;}
    int getImageResource(){return this.imageResource;}
    String getImageDescription(){return this.imageDescription;}
    String getText(){return this.text;}
    String getAuthor(){return this.author;}
    String getDate(){return this.date;}
    String getCategory(){return this.category;}

}
