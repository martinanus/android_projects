package com.example.android.jobscheluder;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private JobScheduler mScheduler;
    private static final int JOB_ID = 0;


    private Switch mDevideIdleSwitch;
    private Switch mDeviceChargingSwich;

    private SeekBar mSeekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDevideIdleSwitch = findViewById(R.id.idle_switch);
        mDeviceChargingSwich = findViewById(R.id.chargin_switch);

        mSeekBar = findViewById(R.id.seek_bar);
        final TextView seekBarProgress = findViewById(R.id.override_deadline);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress > 0){
                    seekBarProgress.setText(progress + " s");
                }else {
                    seekBarProgress.setText("Not Set");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void cancelJobs(View view) {
        if(mScheduler != null){
            mScheduler.cancelAll();
            mScheduler = null;

            NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mNotificationManager.cancelAll();

            Toast.makeText(this,"Jobs cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void scheduleJob(View view) {
        RadioGroup networkOptions = findViewById(R.id.network_group);
        mScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        int selectedNetworkId = networkOptions.getCheckedRadioButtonId();

        int selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;

        switch (selectedNetworkId){
            case R.id.network_type_none:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;
                break;
            case R.id.network_type_any:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_ANY;
                break;
            case R.id.network_type_wifi:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_UNMETERED;
                break;
        }

        ComponentName serviceName = new ComponentName(getPackageName(), NotificationJobService.class.getName());
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceName)
                .setRequiredNetworkType(selectedNetworkOption)
                .setRequiresDeviceIdle(mDevideIdleSwitch.isChecked())
                .setRequiresCharging(mDeviceChargingSwich.isChecked());

        int seekBarInteger = mSeekBar.getProgress();
        boolean seekBarSet = seekBarInteger > 0;

        if(seekBarSet){
            builder.setOverrideDeadline(seekBarInteger * 1000);
        }

        boolean constraintSet = (selectedNetworkOption != JobInfo.NETWORK_TYPE_NONE)
                || mDevideIdleSwitch.isChecked() || mDeviceChargingSwich.isChecked()
                || seekBarSet;

        if(constraintSet){

            JobInfo myJobInfo = builder.build();
            mScheduler.schedule(myJobInfo);

            Toast.makeText(this, "Job Scheduled, job will run when " + "the contraints are met.", Toast.LENGTH_LONG).show();

        } else{
            Toast.makeText(this, "Please, set at least one contraint", Toast.LENGTH_SHORT).show();
        }



    }
}
