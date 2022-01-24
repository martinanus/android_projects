package com.example.android.scorekeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //Define and Initialize scores of each team
    private int mScore1 = 0;
    private int mScore2 = 0;

    //Define key for savedInstance
    private static final String SCORE_TEAM1 = "Score Team 1";
    private static final String SCORE_TEAM2 = "Score Team 2";


    //Define view of each team
    private TextView mScoreText1;
    private TextView mScoreText2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the Score TextViews
        mScoreText1 = (TextView) findViewById(R.id.score_1);
        mScoreText2 = (TextView) findViewById(R.id.score_2);

        if (savedInstanceState != null) {
            mScore1 = savedInstanceState.getInt(SCORE_TEAM1);
            mScore2 = savedInstanceState.getInt(SCORE_TEAM2);

            mScoreText1.setText(String.valueOf(mScore1));
            mScoreText2.setText(String.valueOf(mScore2));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES)
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        else
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);

        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(SCORE_TEAM1, mScore1);
        outState.putInt(SCORE_TEAM2, mScore2);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.night_mode) {
            // Get the night mode state of the app.
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            //Set the theme mode for the restarted activity
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_YES);
            }
// Recreate the activity for the theme change to take effect.
            recreate();
        }
        return super.onOptionsItemSelected(item);
    }



    //Method that handles the onCLick of decreased buttons
    public void decreaseScore(View view) {
        //Get the id of the button clicked
        int viewId = view.getId();
        //Identifie which team call the function
        switch (viewId) {
            //If it is Team 1
            case R.id.decreaseTeam1:
                mScore1--;
                //Rest one point to Team 1
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            //If it is Team 2
            case R.id.decreaseTeam2:
                mScore2--;
                //Rest one point to team 2
                mScoreText2.setText(String.valueOf(mScore2));
                break;
        }
    }

    //Method that handles the onCLick of increase buttons
    public void increaseScore(View view) {
        //Get the id of the button clicked
        int viewId = view.getId();
        //Identifie which team call the function
        switch (viewId) {
            //If it is Team 1
            case R.id.increaseTeam1:
                mScore1++;
                //Rest one point to Team 1
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            //If it is Team 2
            case R.id.increaseTeam2:
                mScore2++;
                //Rest one point to team 2
                mScoreText2.setText(String.valueOf(mScore2));
                break;
        }
    }




}
