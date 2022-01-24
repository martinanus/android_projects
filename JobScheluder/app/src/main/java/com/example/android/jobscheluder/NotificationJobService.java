package com.example.android.jobscheluder;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class NotificationJobService extends JobService {

    NotificationManager mNotificationManager;

    //Notification channel id
    private final static String NOTIFICATION_CHANNEL_ID = "primary_notification_channel";


    @Override
    public boolean onStartJob(JobParameters params) {
        //Create a notification Channel
        createNotificationChannel();

        //Create a Pending Intent to launch de MainActivity when click the notification
        Intent contentIntent = new Intent(this, MainActivity.class);
        PendingIntent contentPendingIntent = PendingIntent.getActivity(this, 0, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder
                (this, NOTIFICATION_CHANNEL_ID)
                .setContentTitle("Job Service")
                .setContentText("Your job is running!")
                .setContentIntent(contentPendingIntent)
                .setSmallIcon(R.drawable.ic_job_running)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true);

        mNotificationManager.notify(0, builder.build());

        realJob();

        return false;
    }

    public void realJob(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        //Heres you put the process you want
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }

    public void createNotificationChannel(){

        //Define notifification object
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //Notification channels are only available in Oreo and higher
        // So, add a check on SDK version
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            //Create the notification channel with all the parameters
            NotificationChannel notificationChannel = new NotificationChannel
                    (NOTIFICATION_CHANNEL_ID,
                            "Job Service Notification",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Job Service");

            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
