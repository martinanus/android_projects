package com.example.android.hellosharedprefs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static com.example.android.hellosharedprefs.R.string.Black;

public class Settings extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private SharedPreferences mPreferences;
    private String sharedPreferencesFiles = "com.example.android.hellosharedprefs";

    // Current background color.
    private int mColor;
    private int mCount;

    private final String COLOR_KEY = "color";
    private final String COUNT_KEY = "count";

    private TextView mShowCountTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Spinner spinner = (Spinner) findViewById(R.id.background_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.background_array, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //mShowCountTextView = (TextView) findViewById(R.id.count_textview);
        mPreferences = getSharedPreferences(sharedPreferencesFiles, MODE_PRIVATE);

        Intent intent = getIntent();
        mCount = intent.getIntExtra(COUNT_KEY, 0);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String backgroundSelected = ((TextView)view).getText().toString();

        switch (backgroundSelected){
            case "Black":
                mColor = getColor(R.color.black_background);
                break;
            case "Red":
                mColor = getColor(R.color.red_background);
                break;
            case "Blue":
                mColor = getColor(R.color.blue_background);
                break;
            case "Green":
                mColor = getColor(R.color.green_background);
                break;
            default:
                mColor = getColor(R.color.default_background);
                break;
        }
      //  mShowCountTextView.setBackgroundColor(mColor);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void save(View view) {
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(COLOR_KEY, mColor);
        preferencesEditor.putInt(COUNT_KEY, mCount);
        preferencesEditor.apply();
    }

    public void reset(View view) {


        // Reset color
        mColor = ContextCompat.getColor(this, R.color.default_background);

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();

    }
}
