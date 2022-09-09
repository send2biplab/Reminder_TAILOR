package com.ubk.casdis_tailor;


import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.ubk.casdis_tailor.ui.home.MusicControl;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equalsIgnoreCase("com.ubk.casdis_tailor.rem")) {
            Bundle b = intent.getExtras();


            Integer notification_id = Integer.parseInt(b.getString("NotiID"));

            System.out.println("Notification Id " + notification_id);
            if(b.getString("SetNotify").equalsIgnoreCase("SetNotification")){

                Log.e("sdfsafsfsdf...2","sdfd");
                Intent intent_notification = new Intent(context, MainNewActivity.class);
                intent_notification.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                intent_notification.putExtra("titlefrom",b.getString("Noti_Title"));
                intent_notification.putExtra("descriptionfrom",b.getString("Noti_Desc"));
                intent_notification.putExtra("add_or_update","UPDATE");
                intent_notification.putExtra("recordno",b.getString("NotiID"));
                intent_notification.putExtra("rem_time",b.getString("Rem_Time"));
                intent_notification.putExtra("rem_date",b.getString("Rem_Date"));


                Log.e("sdfsdfsdfsdfs",""+b.getString("Rem_Time"));
                Log.e("sdfsdfsdfsdfs",""+b.getString("Rem_Date"));



                MusicControl.getInstance(context).playMusic();
                String CHANNELID = "ReminderID";
                String GROUP_KEY = "com.android.example.WORK_EMAIL";
                PendingIntent pendingIntent1 = PendingIntent.getActivity(context, notification_id, intent_notification, 0);

                Intent snoozeButton = new Intent(context, SnoozeReceiver.class);
                snoozeButton.setAction("com.ubk.casdis_tailor.SnoozeReceiver");
                snoozeButton.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                //            snoozeButton.putExtra("AlarmSongSnooze", (Parcelable) ring);

                PendingIntent pendingSwitchIntent = PendingIntent.getBroadcast(context, notification_id, snoozeButton, 0);



                NotificationCompat.Action action =
                        new NotificationCompat.Action.Builder(R.drawable.reminder_icon_yellow,
                                "SNOOZE", pendingSwitchIntent)
                                .build();

                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNELID)
                        .setSmallIcon(R.drawable.reminder_icon_yellow)
                        .setColor(ContextCompat.getColor(context, R.color.colorAccent))
                        .setContentTitle(b.getString("Noti_Title"))
//                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setStyle(new NotificationCompat.InboxStyle()
                                .addLine(b.getString("Noti_Desc"))
                                .setBigContentTitle(b.getString("Noti_Title")))
                        // Set the intent that will fire when the user taps the notification
                        .setContentIntent(pendingIntent1)
                        .setLights(Color.RED, 3000, 3000)
                        .setVibrate(new long[] { 0, 1000, 1000, 1000, 1000 })
                        .setAutoCancel(true)

                        .addAction(action)

                        .setGroup(GROUP_KEY)
                        .setGroupSummary(true);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

                // notificationId is a unique int for each notification that you must define
                notificationManager.notify(notification_id, builder.build());


                Toast.makeText(context, b.getString("AlarmMessage"), Toast.LENGTH_LONG).show();
            }
            else{
                System.out.println("Notification also canceled for "+notification_id);
                NotificationManagerCompat notificationManager1 = NotificationManagerCompat.from(context);

                // notificationId is a unique int for each notification that you must define
                notificationManager1.cancel(notification_id);
            }


        }
        else if(intent.getAction().equalsIgnoreCase("android.intent.action.BOOT_COMPLETED")){
            // phone restart

            Log.e("sdfsafsfsdf...1","sdfd");
        }
    }


}


