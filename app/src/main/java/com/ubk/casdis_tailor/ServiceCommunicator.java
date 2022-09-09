package com.ubk.casdis_tailor;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ServiceCommunicator extends Service
{


    @Override
    public void onCreate()
    {
        super.onCreate();

        Log.e("wdscaxwdcsx","wefdsx");

        Toast.makeText(this, "Service create", Toast.LENGTH_LONG).show();

       // ViewListActivity.getCallDetails();


    }

    public void onStart(Intent intent, int startid) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
          // onCreate();
        Toast.makeText(this, "Service Stop", Toast.LENGTH_LONG).show();
        // Unregister the SMS receiver
        //unregisterReceiver(mSMSreceiver);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;


    }
}