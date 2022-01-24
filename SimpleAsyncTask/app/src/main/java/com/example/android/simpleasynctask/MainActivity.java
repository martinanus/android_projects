package com.example.android.simpleasynctask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView mTextView;
    TextView mProgress;
    ProgressBar mProgressBar;
    final static String EXTRA_TEXT = "text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView1);
        mProgress = findViewById(R.id.progress);
        mProgressBar = findViewById(R.id.progressBar);

        if(savedInstanceState != null){
            mTextView.setText(savedInstanceState.getString(EXTRA_TEXT));
        }
    }

    public void startTask(View view) {
        mTextView.setText(R.string.napping);

        new SimpleAsyncTask(mTextView, mProgress, mProgressBar).execute();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(EXTRA_TEXT, mTextView.getText().toString());


    }
}
