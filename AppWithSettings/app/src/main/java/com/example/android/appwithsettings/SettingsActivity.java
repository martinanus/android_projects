package com.example.android.appwithsettings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {
    public final static String KEY_PREFS_EXAMPLE_SWITCH = "example_switch";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
