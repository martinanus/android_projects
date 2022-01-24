package com.example.android.argentinadailyonline;


import com.google.firebase.storage.StorageReference;

import java.io.Serializable;

class Article implements Serializable {

    public String title;
    public String subtitle;
    public String imageRef;
    public byte[] imageByte;
    public String imageDescription;
    public String text;
    public String author;
    public String date;
    public String category;




    Article(){
        //empty constructor to Firebase toObject
    }

    /*
    Article(String title, String subtitle, String imageRef, String imageDescription,
            String text, String author, String date, String category){
        this.title = title;
        this.subtitle = subtitle;
        this.imageRef = imageRef;
        this.imageDescription = imageDescription;
        this.text = text;
        this.author = author;
        this.date = date;
        this.category = category;
    }
     */

    //Define getters
    String getTitle(){return title;}
    String getSubtitle(){return subtitle;}
    String getImageRef(){return imageRef;}
    byte[] getImageByte(){return imageByte;}
    String getImageDescription(){return imageDescription;}
    String getText(){return text;}
    String getAuthor(){return author;}
    String getDate(){return date;}
    String getCategory(){return category;}


}
