package com.example.android.twoactivities;

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
        mMessageEditText = findViewById(R.id.editText_main);
        mReplyTextHeader = findViewById(R.id.text_header_main);
        mReplyTextView = findViewById(R.id.text_reply);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TEXT_REQUEST){
            if(resultCode == RESULT_OK){
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mReplyTextHeader.setText(R.string.text_header_reply);
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
