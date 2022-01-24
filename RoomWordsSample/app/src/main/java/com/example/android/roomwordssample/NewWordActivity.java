package com.example.android.roomwordssample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewWordActivity extends AppCompatActivity {

    private EditText mEditWord;


    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_WORD_ACTIVITY_REQUEST_CODE = 2;
    public static final String REQUEST_CODE = "request_code";

    public static final String WORD_ID = "word_id";

    public static final String EXTRA_REPLY_WORD = "com.example.android.roomordssample.REPLY_WORD";
    public static final String EXTRA_REPLY_ID = "com.example.android.roomordssample.REPLY_ID";

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        mEditWord = findViewById(R.id.edit_word);

        final Button mSaveButton = findViewById(R.id.button_save);
        final Button mUpdateButton = findViewById(R.id.button_update);

        final Intent intent = getIntent();
         switch (intent.getIntExtra(REQUEST_CODE, 0)){
             case (NEW_WORD_ACTIVITY_REQUEST_CODE):
                 mUpdateButton.setEnabled(false);
                 mUpdateButton.setBackgroundColor(R.color.colorButtonDisable);
                 mSaveButton.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Intent replyIntent = new Intent();
                         if(TextUtils.isEmpty( mEditWord.getText())){
                             setResult(RESULT_CANCELED, replyIntent);
                         }else{
                             String word = mEditWord.getText().toString();
                             replyIntent.putExtra(EXTRA_REPLY_WORD, word);
                             setResult(RESULT_OK, replyIntent);
                         }
                         finish();
                     }
                 });
                 break;
             case(UPDATE_WORD_ACTIVITY_REQUEST_CODE):
                 mSaveButton.setEnabled(false);
                 mSaveButton.setBackgroundColor(R.color.colorButtonDisable);
                 mUpdateButton.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Intent replyIntent = new Intent();
                         if(TextUtils.isEmpty( mEditWord.getText())){
                             setResult(RESULT_CANCELED, replyIntent);
                         }else{
                             int id =  intent.getIntExtra(WORD_ID, 0);
                             String word = mEditWord.getText().toString();
                             replyIntent.putExtra(EXTRA_REPLY_WORD, word);
                             replyIntent.putExtra(EXTRA_REPLY_ID, id);
                             setResult(RESULT_OK, replyIntent);
                         }
                         finish();
                     }
                 });
                 break;
         }
    }
}
