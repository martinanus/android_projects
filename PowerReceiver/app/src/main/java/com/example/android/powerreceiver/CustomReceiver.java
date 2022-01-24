package com.example.android.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {
    private static final String ACTION_CUSTOM_BROADCAST= BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();

        if(intentAction != null) {
            String toastMessage = "unknown intent action";
            switch (intentAction){
                case(Intent.ACTION_POWER_CONNECTED):
                    toastMessage = "Power Connected!";
                    break;
                case(Intent.ACTION_POWER_DISCONNECTED):
                    toastMessage = "Power Disconnected!";
                    break;
                case(ACTION_CUSTOM_BROADCAST):
                    toastMessage = "Custom Broadcast Received";
                    break;
                 case(Intent.ACTION_HEADSET_PLUG):
                     int i;
                    if((i = intent.getIntExtra("state", -1)) == 0)
                        toastMessage = "Headset unplugged!";
                    else if (i == 1)
                        toastMessage = "Headset plugged!";
                    break;
            }
            Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show();
        }
    }
}
