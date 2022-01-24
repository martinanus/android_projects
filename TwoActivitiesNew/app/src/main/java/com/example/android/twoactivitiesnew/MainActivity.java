package com.example.android.twoactivitiesnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE="com.example.android.extra.MESSAGE";
    private EditText mMessageEditText;
    public  static final int TEXT_REQUEST = 1;
    private TextView mReplyTextHeader;
    private TextView mReplyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Log the start of the onCreate() method
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");

        //Initialize all the View variable
        mMessageEditText = findViewById(R.id.editText_main);
        mReplyTextHeader = findViewById(R.id.text_header_main);
        mReplyTextView = findViewById(R.id.text_reply);

        //Restore the state
        if(savedInstanceState != null){
            boolean isVisible = savedInstanceState.getBoolean("reply_visible");
            if (isVisible){
                mReplyTextHeader.setVisibility(View.VISIBLE);
                mReplyTextView.setText(savedInstanceState.getString("reply_text"));
                mReplyTextView.setVisibility(View.VISIBLE);

            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //If the heading is visible, message needs to be saved
        //Otherwise we're still using default layout
        if(mReplyTextHeader.getVisibility() == View.VISIBLE){
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text", mReplyTextView.getText().toString());
            Log.d(LOG_TAG, "Data Stored!");

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TEXT_REQUEST){
            if(resultCode == RESULT_OK){
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mReplyTextHeader.setVisibility(View.VISIBLE);
                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);

            }
        }
    }


    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, SecondActivity.class);
        String message = mMessageEditText.getText().toString();
        mMessageEditText.setText("");
        if(message.equals("")){
            mReplyTextHeader.setText(R.string.error_alert);
            mReplyTextHeader.setVisibility(View.VISIBLE);
            mReplyTextView.setText(R.string.empty_message_alert);
            mReplyTextView.setVisibility(View.VISIBLE);

        } else{
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivityForResult(intent, TEXT_REQUEST);
        }
    }
}
