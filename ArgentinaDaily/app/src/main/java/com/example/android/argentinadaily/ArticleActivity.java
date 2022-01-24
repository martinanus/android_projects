package com.example.android.argentinadaily;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class ArticleActivity extends AppCompatActivity {

    private List<Article> mArticles; //Cached copy of articles
    private ArticleViewModel mArticleViewModel;

    private static final String SELECTED_ARTICLE_TITLE = "selected_article_title";
    private static final String SELECTED_ARTICLE_SUBTITLE = "selected_article_subtitle";
    private static final String SELECTED_ARTICLE_IMAGE = "selected_article_image";
    private static final String SELECTED_ARTICLE_IMAGEDESC = "selected_article_imagedesc";
    private static final String SELECTED_ARTICLE_AUTHOR = "selected_article_author";
    private static final String SELECTED_ARTICLE_DATE = "selected_article_date";
    private static final String SELECTED_ARTICLE_TEXT = "selected_article_text";
    private static final String SELECTED_ARTICLE_CATEGORY = "selected_article_category";

    private TextView category;
    private TextView title;
    private TextView subTitle;
    private ImageButton image;
    private TextView imageDescr;
    private TextView author;
    private TextView date;
    private TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        category = findViewById(R.id.category_article);
        title = findViewById(R.id.title_article);
        subTitle = findViewById(R.id.subTitle_article);
        image = findViewById(R.id.image_article);
        imageDescr = findViewById(R.id.imageDescription_article);
        author = findViewById(R.id.author_article);
        date = findViewById(R.id.date_article);
        text = findViewById(R.id.text_article);

        Intent intent = getIntent();

        category.setText(intent.getStringExtra(SELECTED_ARTICLE_CATEGORY));
        title.setText(intent.getStringExtra(SELECTED_ARTICLE_TITLE));
        subTitle.setText(intent.getStringExtra(SELECTED_ARTICLE_SUBTITLE));
        Glide.with(this).load(getIntent().getIntExtra(SELECTED_ARTICLE_IMAGE, 0)).into(image);
        image.setBackgroundColor(Color.TRANSPARENT);
        imageDescr.setText(intent.getStringExtra(SELECTED_ARTICLE_IMAGEDESC));
        author.setText(intent.getStringExtra(SELECTED_ARTICLE_AUTHOR));
        date.setText(intent.getStringExtra(SELECTED_ARTICLE_DATE));
        text.setText(intent.getStringExtra(SELECTED_ARTICLE_TEXT));


        mArticleViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_article, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_share:
                Intent intent = getIntent();
                String title = intent.getStringExtra(SELECTED_ARTICLE_TITLE);
                String subTitle = intent.getStringExtra(SELECTED_ARTICLE_SUBTITLE);
                String text = intent.getStringExtra(SELECTED_ARTICLE_TEXT);
                String article = title + "\n \n" + subTitle + "\n \n" + text;

                String mimeType = "text/plain";

                ShareCompat.IntentBuilder
                        .from(this)
                        .setType(mimeType)
                        .setChooserTitle("Share this article with: ")
                        .setText(article)
                        .startChooser();

                return true;

            default:
                //do nothing
        }
        return super.onOptionsItemSelected(item);
    }
}

