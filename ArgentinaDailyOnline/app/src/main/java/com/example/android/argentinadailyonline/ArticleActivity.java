package com.example.android.argentinadailyonline;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import com.bumptech.glide.Glide;

public class ArticleActivity extends AppCompatActivity {

    private Article article;
    private static final String SELECTED_ARTICLE = "selected_article";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        TextView category = findViewById(R.id.category_article);
        TextView title = findViewById(R.id.title_article);
        TextView subTitle = findViewById(R.id.subTitle_article);
        ImageButton image = findViewById(R.id.image_article);
        TextView imageDescription = findViewById(R.id.imageDescription_article);
        TextView author = findViewById(R.id.author_article);
        TextView date = findViewById(R.id.date_article);
        TextView text = findViewById(R.id.text_article);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        assert bundle != null;
        article = (Article) bundle.getSerializable(SELECTED_ARTICLE);

        assert article != null;
        category.setText(article.getCategory());

        title.setText(article.getTitle());
        subTitle.setText(article.getSubtitle());
        Glide.with(this).load(article.getImageByte()).into(image);
        image.setBackgroundColor(Color.TRANSPARENT);
        imageDescription.setText(article.getImageDescription());
        author.setText(article.getAuthor());
        date.setText(article.getDate());
        String txt = (article.text).replaceAll("\\\\n", "\n");
        text.setText(txt);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_article, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:

                String txt = (article.text).replaceAll("\\\\n", "\n");
                String sharedTxt = article.getTitle() + "\n \n" + article.getSubtitle() + "\n \n" + txt;

                String mimeType = "text/plain";

                ShareCompat.IntentBuilder
                        .from(this)
                        .setType(mimeType)
                        .setChooserTitle("Share this article with: ")
                        .setText(sharedTxt)
                        .startChooser();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

