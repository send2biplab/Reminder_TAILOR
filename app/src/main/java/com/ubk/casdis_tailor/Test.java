//package com.ubk.casdiscall_back_service_off;
//
//import android.Manifest;
//import android.app.AlertDialog;
//import android.app.ProgressDialog;
//import android.content.ContentResolver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.database.CursorIndexOutOfBoundsException;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.provider.Telephony;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.content.ContextCompat;
//
//
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class SplashActivity extends AppCompatActivity {
//
//    Handler handler;
//    ArrayList arrayList_db_count;
//    ArrayList arrayList_db_count2;
//
//    DbPassHandler dbpassword = new DbPassHandler(SplashActivity.this);
//    String id;
//    TextView activationtext;
//
//    SharedPreferences sharedpreferences;
//
//    public static final String MyPREFERENCES = "MyPrefs" ;
//    public static final String Name = "nameKey";
//    public static final boolean loginstatus = false;
//    public static final String passwd = "password";
//    String token;
//
//    private static SplashActivity inst;
//
//    public static SplashActivity instance() {
//
//        Log.d("sdaadsa1","aaaaaa"+inst);
//        return inst;
//
//
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        Log.d("sdaadsa2","aaaaaa"+inst);
//
//        inst = this;
//    }
//
//
//
//    private int checkedPermission = PackageManager.PERMISSION_DENIED;
//    public static final int REQUEST_CODE_PHONE_STATE_READ = 100;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//        ///  MultiDex.install(this);
//
//
//        activationtext=findViewById(R.id.activationtext);
//
//        checkedPermission = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_PHONE_STATE);
//        checkedPermission = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
//        checkedPermission = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        checkedPermission = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_SMS);
//        checkedPermission = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.RECEIVE_SMS);
//        checkedPermission = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.SEND_SMS);
//        checkedPermission = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.WRITE_CONTACTS);
//        checkedPermission = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_CONTACTS);
//
//        // checkedPermission = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_PHONE_STATE);
//        checkedPermission = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.CALL_PHONE);
//        checkedPermission = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.SYSTEM_ALERT_WINDOW);
//        checkedPermission = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_CALL_LOG);
//
//
//        if (Build.VERSION.SDK_INT >= 23 && checkedPermission != PackageManager.PERMISSION_GRANTED) {
//
//            Log.e("rbvcdsx","1111");
//            requestPermission();
//
//
//        } else
//        {
//            Log.e("rbvcdsx","22222");
//            checkedPermission = PackageManager.PERMISSION_GRANTED;
//        }
//
//
//        sharedpreferences = getSharedPreferences(MyPREFERENCES,
//                Context.MODE_PRIVATE);
//
//
//
//        arrayList_db_count2 = new ArrayList<>();
//        arrayList_db_count2 = dbpassword.showAll_db();
//        //uuuuser=(TextView)findViewById(R.id.uuuuser);
//
//        Log.d("tttttttttttttttttttt15", "" + arrayList_db_count2);
//
//
//        handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//
//                if (!arrayList_db_count2.isEmpty()) {
//
//                    HashMap<String, String> map = (HashMap) arrayList_db_count2.get(0);
//
//
//                    Log.d("asdfasdasdasdas", "" + map);
//
//                    if (map.get("status").equals("HELLO DCT EXCEL")) {
//
//                        // startActivity(new Intent(SplashActivity.this,SplashActivity.class));
//                        //finish();
////
////                        startActivity(new Intent(SplashActivity.this,MainNewActivity.class));
//
//
//
////                        finish();
//                        activationtext.setVisibility(View.VISIBLE);
//
//
////                        Log.e("dsdsdsd","sds");
////                        activationtext.setVisibility(View.VISIBLE);
////                        if (sharedpreferences.contains(Name)) {
////                            // name.setText();
////
////                            Log.e("rerfwedwsaefvs222222",""+sharedpreferences.getString(Name, ""));
////
////
////                        }
////                        else {
////                            Log.e("rerfwedwsaefvs111111",""+sharedpreferences.getString(Name, ""));
////                            loginPopup();
////                        }
//
//
//
//
//                    } else  if(map.get("status").equals("HELLO ACT EXCEL"))
//                    {
//                        activationtext.setVisibility(View.GONE);
//                        startActivity(new Intent(SplashActivity.this, MainNewActivity.class));
//                        finish();
//
//
//                        // getCallDetails();
//                    }
//
//                } else {
//
//                    activationtext.setVisibility(View.VISIBLE);
//                    //  messageDatabase.insert("Thanks for Shopping at our Shop. Your Promo Code is ");
//                    dbpassword.insert("12345", "HELLO DCT EXCEL",String.valueOf(System.currentTimeMillis()),String.valueOf(System.currentTimeMillis()));
//
////                    if (sharedpreferences.contains(Name)) {
////                        // name.setText();
////
////                        Log.e("rerfwedwsaefvs222222",""+sharedpreferences.getString(Name, ""));
////
////
////                    }
////                    else {
////                        Log.e("rerfwedwsaefvs111111",""+sharedpreferences.getString(Name, ""));
////                        loginPopup();
////                    }
//
//                }
//
//
//            }
//        }, 1000);
//
//
//    }
//
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private void requestPermission() {
//        // Toast.makeText(SplashActivity.this, "Requesting permission", Toast.LENGTH_SHORT).show();
//        this.requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE,
//                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.RECEIVE_SMS,
//                        Manifest.permission.SEND_SMS,
//                        Manifest.permission.READ_SMS,
//                        Manifest.permission.WRITE_CONTACTS,
//                        Manifest.permission.READ_CONTACTS,
//
//                        Manifest.permission.CALL_PHONE,
//                        Manifest.permission.READ_CALL_LOG,
//                        Manifest.permission.SYSTEM_ALERT_WINDOW},
//                REQUEST_CODE_PHONE_STATE_READ);
//        //showDeviceInfo();
//    }
//
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        switch (requestCode) {
//            case REQUEST_CODE_PHONE_STATE_READ:
//                if (grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED ) {
//                    checkedPermission = PackageManager.PERMISSION_GRANTED;
//                }
//                break;
//
//        }
//    }
//
//
//    public void updateList(final String address,final String smsBody) {
//
//
//        Log.d("sdaadsa3","aaaaaa"+address);
//        Log.d("sdaadsa3","aaaaaa"+smsBody);
//
//
//
//
//        //if(address.equals("+916200938310"))
//        if (address.equals("+918390252627") || address.equals("+918617758895"))
//        {
//
//            // HashMap<String, String> map1 = (HashMap) arrayList_db_count2.get(0);
//
//            //  Log.e("asasdasdasda","asdada"+map1);
//
//
//            Log.d("afjowaf","asfdad");
//            if(smsBody.equals("HELLO ACT EXCEL"))
//            {
//
//                long time= System.currentTimeMillis();
//                android.util.Log.i("Time Class ", " Time value in millisecinds "+time);
//
//                Log.d("afjowaf11111","asfdad");
//                dbpassword.insert("12345",smsBody,String.valueOf(time),String.valueOf(time));
//                if (!arrayList_db_count2.isEmpty()) {
//                    HashMap<String, String> map = (HashMap) arrayList_db_count2.get(0);
//
//                    //  Log.d("afjowaf",""+arrayList_db_count.size());
//
//                    startActivity(new Intent(SplashActivity.this,MainNewActivity.class));
//                    finish();
//
//
//
//                }
//                else
//                {
//                    startActivity(new Intent(SplashActivity.this,MainNewActivity.class));
//                    finish();
//                }
//            }
//            else if (smsBody.equals("HELLO DCT EXCEL"))
//            {
//                activationtext.setVisibility(View.VISIBLE);
//                dbpassword.insert("12345",smsBody,String.valueOf(System.currentTimeMillis()),String.valueOf(System.currentTimeMillis()));
//                // new WhatsManager(SplashActivity.this).saveNotificationStatus(false);
//                Log.d("asdjhnsadbasd",";dkjhwduw");
//                startActivity(new Intent(SplashActivity.this,SplashActivity.class));
//                finish();
//            }
//        }
//        else
//        {
//
////            startActivity(new Intent(SplashActivity.this,SplashActivity.class));
////            finish();
//        }
//
//
//    }
//
//
//
//
//
//
//    public String getCallDetails() {
//
//        Log.e("fdgsdfsdfsd","dfgedf");
//        String time = null;
//
//        DbPassHandler dbpassword = new DbPassHandler(SplashActivity.this);
//        arrayList_db_count2 = new ArrayList<>();
//        arrayList_db_count2 = dbpassword.showAll_db();
//        //uuuuser=(TextView)findViewById(R.id.uuuuser);
//
//        Log.d("c", "" + arrayList_db_count2);
//        if (!arrayList_db_count2.isEmpty()) {
//
//            HashMap<String, String> map = (HashMap) arrayList_db_count2.get(0);
//
//
//            Log.d("asdfasdasdasdas", "" + map);
//
//            if (map.get("status").equals("HELLO ACT EXCEL")) {
//                time=map.get("timeSms");
//
//
//            }
//        }
//
//
//
//
//
//
//        // long time= System.currentTimeMillis();
//        android.util.Log.i("Time Class ", " Time value in millisecinds "+time);
//        List<Sms> lstSms = new ArrayList<Sms>();
//        // Sms objSms = new Sms();
//        Uri message = Uri.parse("content://sms/inbox");
//        ContentResolver cr = this.getContentResolver();
//
//
//        Log.e("gdfdegrf","...."+time);
//        Log.e("gdfdegrf","gfdsa"+message);
//
//        Cursor cursor = getContentResolver().query(message, null, Telephony.Sms.DATE + ">?",
//                new String[] { String.valueOf(time) }, null);
//
//
//
//
//        String phNumber = null;
//        String type = null;
//        String content = null;
//        String date = null;
//
//        Date SMSDate=null;
//        String direction = "";
//        //make sure there is a message being operating
//        if (cursor != null && cursor.moveToFirst()) {
//            try {
//                //the meanings of those variables are quite straight forward
//                type = cursor.getString(cursor.getColumnIndex(Telephony.Sms.TYPE));
//                content = cursor.getString(cursor.getColumnIndex(Telephony.Sms.BODY));
//                date = cursor.getString(cursor.getColumnIndex(Telephony.Sms.DATE));
//                SMSDate = new Date(Long.valueOf(date));
//                phNumber = cursor.getString(cursor.getColumnIndex(Telephony.Sms.ADDRESS));   //this is phone number rather than address
//
//                //  String contact = getContactDisplayNameByNumber(phNumber);    //call the metod that convert phone number to contact name in your contacts
//
//                int typeCode = Integer.parseInt(type);
//
//                //get the right direction
//                switch (typeCode) {
//                    case Telephony.Sms.MESSAGE_TYPE_INBOX:
//                        direction = "INCOMING";
//                        break;
//
//                    case Telephony.Sms.MESSAGE_TYPE_OUTBOX:
//                        direction = "OUTGOING";
//                        break;
//
//                    case Telephony.Sms.MESSAGE_TYPE_SENT:
//                        direction = "SENT";
//                        break;
//
//                    default:
//                        direction = "UNKNOWN";
//                        Log.e("ssss", typeCode + " is unknown");
//                        break;
//                }
//
//                Log.i("dddd", "\nName: " + content + "\nPhone Number:--- " + phNumber + " \nContent:--- "
//                        + content + " \nCall Date:--- " + SMSDate
//                        + " Direction: " + direction + "\n----------------------------------");
//
//                //   DatabaseHandler db = new DatabaseHandler(ViewListActivity.this);
//
//                if (direction.equals("INCOMING"))
//                {
//
//                    // Log.e("")
//
//
//
//                    if (content.equals("HELLO DCT EXCEL")) {
//                        if (phNumber.equals("+918390252627") || phNumber.equals("+918617758895")) {
//
//
//                            Log.e("efwas111", "sdsdw");
//                            updateList(phNumber, content);
//                        }
//                        else {
//                            startActivity(new Intent(SplashActivity.this, MainNewActivity.class));
//                            finish();
//                        }
//                    }
//                    else //if (content.equals("HELLO ACT APP"))
//                    {
//
//                        Log.e("efwas2222", "sdsdw");
//                        startActivity(new Intent(SplashActivity.this, MainNewActivity.class));
//                        finish();
//
//                    }
//
//                    // }
//
//
//
//
//                }
//                else {
//
//                    startActivity(new Intent(SplashActivity.this, MainNewActivity.class));
//                    finish();
//                }
//
//
//
//
//
//            } catch (CursorIndexOutOfBoundsException e) {
//                Log.e("ssss", "SMSHelper: outgoingSMS", e);
//            } finally {
//                cursor.close();
//            }
//
//
//
//        }
//
//
//
//
//
//
//
//        return lstSms.toString();
//
//    }
//
//
//
//}
