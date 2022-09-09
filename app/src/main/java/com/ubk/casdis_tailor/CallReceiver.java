package com.ubk.casdis_tailor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class CallReceiver extends BroadcastReceiver {

//    @Override
//    public void onReceive(Context context, Intent intent) {
//
//        //Toast.makeText(context, "Call" , Toast.LENGTH_SHORT).show();
//        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        telephony.listen(new PhoneStateListener() {
//            @Override
//            public void onCallStateChanged(int state, String incomingNumber) {
//                super.onCallStateChanged(state, incomingNumber);
//                System.out.println("incomingNumber : " + incomingNumber);
//
//                DatabaseHandler db = new DatabaseHandler(context);
//                db.allKeywordInsert_call(incomingNumber,"ubk");
//            }
//        }, PhoneStateListener.LISTEN_CALL_STATE);
//    }

    public Context c;

    public void onReceive(Context context, Intent intent) {
        c = context;
        try {
            TelephonyManager tmgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            MyPhoneStateListener PhoneListener = new MyPhoneStateListener();
            tmgr.listen(PhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
        } catch (Exception e) {
            Toast.makeText(context, "oops!", Toast.LENGTH_SHORT).show();
        }
    }

    private class MyPhoneStateListener extends PhoneStateListener {
        public void onCallStateChanged(int state, String incomingNumber) {
            Log.e("refgwdasfesdfsd",".."+incomingNumber);

            if (state == 1) {

//                DatabaseHandler db = new DatabaseHandler(c);
//                db.allKeywordInsert_call(incomingNumber,"ubk");

               // String msg = "New Phone Call Event. Incoming Number: " + incomingNumber;
                int duration = Toast.LENGTH_LONG;
                //Toast toast = Toast.makeText(c, msg, duration);
                //toast.show();
            }
        }
    }

}
