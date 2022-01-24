package com.example.android.argentinadaily;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.renderscript.ScriptIntrinsicBLAS;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private Context context;
    private final LayoutInflater mInflater;
    private List<Article> mArticles; //Cached copy of articles
    private static final String SELECTED_ARTICLE_TITLE = "selected_article_title";
    private static final String SELECTED_ARTICLE_SUBTITLE = "selected_article_subtitle";
    private static final String SELECTED_ARTICLE_IMAGE = "selected_article_image";
    private static final String SELECTED_ARTICLE_IMAGEDESC = "selected_article_imagedesc";
    private static final String SELECTED_ARTICLE_AUTHOR = "selected_article_author";
    private static final String SELECTED_ARTICLE_DATE = "selected_article_date";
    private static final String SELECTED_ARTICLE_TEXT = "selected_article_text";
    private static final String SELECTED_ARTICLE_CATEGORY = "selected_article_category";




    ArticleAdapter(Context context){
        mInflater = LayoutInflater.from(context);
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
            // ASDASDASDASDASD
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

    public Article getArticleAtPosition(int position){
        return mArticles.get(position);
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        //Member variables for the TextViews
        private TextView mTitle;
        private TextView mSubtitle;
        private ImageButton mImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Initialize the views
            mTitle = itemView.findViewById(R.id.title);
            mSubtitle = itemView.findViewById(R.id.subTitle);
            mImage = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ArticleActivity.class);
                    Article current = mArticles.get((getAdapterPosition()));
                    intent.putExtra(SELECTED_ARTICLE_CATEGORY, current.getCategory());
                    intent.putExtra(SELECTED_ARTICLE_TITLE , current.getTitle());
                    intent.putExtra(SELECTED_ARTICLE_SUBTITLE , current.getSubtitle());
                    intent.putExtra(SELECTED_ARTICLE_IMAGE, current.getImageResource());
                    intent.putExtra(SELECTED_ARTICLE_IMAGEDESC, current.getImageDescription());
                    intent.putExtra(SELECTED_ARTICLE_AUTHOR, current.getAuthor());
                    intent.putExtra(SELECTED_ARTICLE_DATE, current.getDate());
                    intent.putExtra(SELECTED_ARTICLE_TEXT, current.getText());
                    context.startActivity(intent);
                }
            });
        }

        void bindTo(Article current){
            //Populate the textview with data
            mTitle.setText(current.getTitle());
            mSubtitle.setText(current.getSubtitle());
            Glide.with(context).load(current.getImageResource()).into(mImage);
            mImage.setBackgroundColor(Color.TRANSPARENT);
        }
    }
}
