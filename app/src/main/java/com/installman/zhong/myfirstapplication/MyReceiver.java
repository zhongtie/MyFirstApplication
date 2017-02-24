package com.installman.zhong.myfirstapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Received broadcast intent: " + intent.getAction());

        if (intent.getAction().equals(intent.ACTION_BOOT_COMPLETED)){
            Intent i= new Intent(context, GetLbsService.class);
            context.startService(i);
        }
        if (intent.getAction().equals(intent.ACTION_USER_PRESENT)){
            Intent i= new Intent(context, GetLbsService.class);
            context.startService(i);
        }
    }
}
