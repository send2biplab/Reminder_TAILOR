package com.ubk.casdis_tailor;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class App extends Application {


    public static final String CHANNEL_ID="channnel";

    @Override
    public void onCreate() {
        super.onCreate();

        notifationChannel();
    }

    private void notifationChannel() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notif=new NotificationChannel(CHANNEL_ID
            ,"name", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notif);
        }



    }
}
