package com.example.android.argentinadailyonline;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private Context context;
    private List<Article> mArticles; //Cached copy of articles
    private static final String SELECTED_ARTICLE = "selected_article";


    ArticleAdapter(Context context){
        LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ViewHolder holder, int position) {
        if(mArticles!=null){
            //Populate the textViews with data
            Article current = mArticles.get(position);
            holder.bindTo(current);
        }else{
            //Covers the case of data not being ready yet
            Log.e("onBindViewHolder", "mArticles is null!");

        }
    }

    void setWords(List<Article> articles){
        mArticles = articles;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mArticles!=null)
            return mArticles.size();
        else return 0;
    }

    /*
    public Article getArticleAtPosition(int position){
        return mArticles.get(position);
    }
    */

    class ViewHolder extends RecyclerView.ViewHolder{

        //Member variables for the TextViews
        private TextView mTitle;
        private TextView mSubtitle;
        private ImageButton mImage;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Initialize the views
            mTitle = itemView.findViewById(R.id.title);
            mSubtitle = itemView.findViewById(R.id.subTitle);
            mImage = itemView.findViewById(R.id.image);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ArticleActivity.class);

                    Bundle bundle = new Bundle();
                    Article art =mArticles.get((getAdapterPosition()));
                    bundle.putSerializable(SELECTED_ARTICLE, mArticles.get((getAdapterPosition())));
                    intent.putExtras(bundle);

                    context.startActivity(intent);
                }
            });

        }



        void bindTo(Article current){
            //Populate the textview with data
            mTitle.setText(current.getTitle());
            mSubtitle.setText(current.getSubtitle());
            Glide.with(context).load(current.getImageByte()).into(mImage);
            mImage.setBackgroundColor(Color.TRANSPARENT);
        }
    }
}
