package com.example.servicebroadcast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

//broadcastReceiver hører på forskjellige hendelser
public class MinBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "I BroadcastReceiver", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(context, SettPeriodiskService.class);
        context.startService(i);
    }
}
