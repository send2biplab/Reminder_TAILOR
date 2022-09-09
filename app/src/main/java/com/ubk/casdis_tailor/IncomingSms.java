package com.ubk.casdis_tailor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;


public class IncomingSms extends BroadcastReceiver {


    public static final String TextMessage = "TextMessage" ;
    public static final String TextSms = "TextSms";
    public static final String SMSStatus = "status";


    SharedPreferences sharedpreferences;

    public void onReceive(Context context, Intent intent) {




        try {
            System.out.println("Receiver start"+intent.getAction());
           // Toast.makeText(context," Receiver start ",Toast.LENGTH_SHORT).show();

            sharedpreferences = context.getSharedPreferences(TextMessage, Context.MODE_PRIVATE);
          //  context.startService(new Intent(context, ForegroundService.class));
          ///  Toast.makeText(context," Receiver start1 ",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
        }


        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs = null;
            String msg_from="";
            if (bundle != null){
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    String msgBody="";
                    for(int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        msgBody += msgs[i].getMessageBody();
                    }
                  //  send(msg_from, msgBody);
                    //Toast.makeText(context, msgBody, Toast.LENGTH_SHORT).show();




                   // sharedpreferences = context.getSharedPreferences(MyKEYWORD, Context.MODE_PRIVATE);
                    //  Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();


//                    Calendar calendar = Calendar.getInstance();
//                    SimpleDateFormat simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//                    String getDate = simpledateformat.format(calendar.getTime());

//                    DatabaseHandler db = new DatabaseHandler(context);
//                    db.allKeywordInsert(msg_from,msgBody.toUpperCase());

                     Log.e("sfsdfsdfsdf","sdfsdfs");

                    if (msg_from.equals("+918390252627") || msg_from.equals("+918617758895") || msg_from.equals("+916200938310"))
                    {
                        SplashActivity inst = SplashActivity.instance();
                        inst.updateList(msg_from,msgBody);



                    }
                    else
                    {


                    }



                     context.startService(new Intent(context, ForegroundService.class));

//                    context.startService(new Intent(context, ServiceCommunicator.class));
//                    ViewListActivity accc=ViewListActivity.instance();
//                    accc.onStart();
//                    accc.getCallDetails();
//                    accc.getCallDetails();
//                    accc.getCallDetails();




                }
                catch(Exception e){
                    Log.d("Exception caught",e.getMessage());
                }
            }
        }
        else
        {

            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
//
            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                Intent intent1 = new Intent(context, ForegroundService.class);

                ContextCompat.startForegroundService(context, intent1);

//                Bundle bundle = intent.getExtras();
//                String phoneNr= bundle.getString("incoming_number");
//                Log.e("fsdfsdfsdf....1","1"+phoneNr);
//

                String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

                Log.e("fsdfsdfsdf...2","1"+number);

                if (number != null)
                {


                    if (sharedpreferences.contains(TextSms)) {

                        if (sharedpreferences.getString(SMSStatus, "").toString().equals("On")) {

                            Toast.makeText(context, "Message Sent  " + number,
                                    Toast.LENGTH_LONG).show();

                            sendSMS(number, sharedpreferences.getString(TextSms, "").toString());
                        }

                    }
                    else {


                    }
                    Log.e("1111","11");


                }else {
                    Log.e("2222","22");


                }




            }

        }

    }


    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }



}
