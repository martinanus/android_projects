package com.example.android.simpleasynctask;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {
    private WeakReference<TextView> mTextView;
    private WeakReference<TextView> mProgress;
    private  WeakReference<ProgressBar> mProgressBar;

    SimpleAsyncTask(TextView tv, TextView pr, ProgressBar prB){
        mTextView = new WeakReference<>(tv);
        mProgress = new WeakReference<>(pr);
        mProgressBar = new WeakReference<>(prB);
    }


    @Override
    protected String doInBackground(Void... voids) {

        //Define and generate a random number between 1 and 10
        Random r = new Random();
        int s = (r.nextInt(10)+1) * 500;

        //Sleep for the random amount of time

        for(int i = 0 ; i < 10; i++ ){
            try{
                publishProgress(i);
                Thread.sleep(s/10);
            } catch (InterruptedException e){
                e.printStackTrace();
            }

        }
        publishProgress(100);
        //Return String when wake up
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }


    @Override
    protected void onProgressUpdate(Integer... values) {

        if(values[0] == 100)
            mProgress.get().setText("Finish!");
        else{
        String per = values[0]*10 + "%";
        mProgress.get().setText(per);
        }
        mProgressBar.get().setProgress(values[0]*10);
    }

    @Override
    protected void onPostExecute(String result){
        mTextView.get().setText(result);
    }
}
