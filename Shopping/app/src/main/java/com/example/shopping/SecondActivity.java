package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    private static final String LOG_TAG = "Second Activity";
    public static final String EXTRA_PRODUCT = "com.example.shopping.extra.PRODUCT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Log message
        Log.d(LOG_TAG, "onCreate() executed");

    }

    public void returnProduct(View view) {
        Button button_selected = (Button) view;

        //Log message
        Log.d(LOG_TAG, "Button " + button_selected.getText().toString()  + " clicked!");

        //Creation and set results in Intent
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_PRODUCT, button_selected.getText().toString() );
        setResult(RESULT_OK, resultIntent);

        //Log message
        Log.d(LOG_TAG, "End of Second Activity");

        finish();


    }

}
