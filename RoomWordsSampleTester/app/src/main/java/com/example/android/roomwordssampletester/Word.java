package com.example.android.roomwordssampletester;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey(autoGenerate = true)
    protected int id;

    @NonNull
    @ColumnInfo(name ="word")
    private String mWord;


    public Word(@NonNull String word){
        this.mWord = word;
    }

    @Ignore
    public Word(int id, @NonNull String word){
        this.id = id;
        this.mWord = word;
    }



    public String getWord(){
        return this.mWord;
    }

    public int getId(){
        return this.id;
    }
}
