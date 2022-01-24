package com.example.android.hellotoast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private int mCount = 0;
    private TextView mShowCount;
    private Button mZero;
    private int colorBlueSky = 0xFF03DAC5;
    private int colorGray = 0xFF808080;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);
        if(savedInstanceState != null){
            mShowCount.setText(savedInstanceState.getString("actual_count"));
        }

    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("actual_count", mShowCount.getText().toString());
        Log.d("ACTIVITY", mShowCount.getText().toString());
    }



    public void showToast(View view){
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);

        toast.show();

    }

    public void countUp(View view){
        if(mCount == 0 ){
            mZero = (Button) findViewById(R.id.button_zero);
            if(mZero != null)
                mZero.setBackgroundColor(colorBlueSky);
        }
        mCount++;
        if (mShowCount != null)
            mShowCount.setText(Integer.toString(mCount));
    }

    public void zero(View view) {
        view.setBackgroundColor(colorGray);
        mCount = 0;
        if (mShowCount != null)
            mShowCount.setText(Integer.toString(mCount));
    }
}
