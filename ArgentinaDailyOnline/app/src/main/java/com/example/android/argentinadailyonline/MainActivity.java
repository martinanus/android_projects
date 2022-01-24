package com.example.android.argentinadailyonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    SharedPreferences mPreferences;
    Fragment fragment1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "OnCreated");



        //Set de  Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        //Set de Tabs
        //Create an instance of the TabLayout from the View
        TabLayout tabLayout = findViewById(R.id.TabLayout);
        //Set the text for each Tab
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label_business));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label_politics));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label_technology));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label_world));
        //Allows to adapt each tab size to the label and scroll if it's bigger than parent
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);


        // Use PagerAdapter to manage page views in fragments.
        // Each page is represented by its own fragment.
        final ViewPager viewPager = findViewById(R.id.ViewPager);
        viewPager.setOffscreenPageLimit(4);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        //Set a listener for clicks
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

         mPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        if(mPreferences.getBoolean(SettingsActivity.KEY_NIGHT_MODE_PREF, false))
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
                /*
            case R.id.action_night_mode:
                if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                else
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                */
            default:
                //do nothing
        }
        return super.onOptionsItemSelected(item);
    }

}
