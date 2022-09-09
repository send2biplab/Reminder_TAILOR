package com.ubk.casdis_tailor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.ubk.casdis_tailor.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ViewListActivity extends AppCompatActivity {



    private static ViewListActivity inst;

    public static ViewListActivity instance() {

        Log.d("sdaadsa1","aaaaaa"+inst);
        return inst;


    }



    public static final String MyKEYWORD = "MyKeyword" ;
    public static final String Keyword = "Keyword";


    ArrayList arrayList_db_count2;
    ViewContactAdapter contactdapter;
   // CallContactAdapter contactdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);


        //long time= System.currentTimeMillis();
       // android.util.Log.i("SmSlistttttttt ", " Time value in millisecinds "+time);

//        DatabaseHandler db = new DatabaseHandler(ViewListActivity.this);
//
//        ArrayList arrayList_db_count = new ArrayList<>();
//        arrayList_db_count = db.showAll_db();
//        Log.d("byjum", ""+arrayList_db_count);
//
//        List<Contact2> message2 = db.getAllContacts();
//
//        Log.d("asdaSDASD111",""+message2.size());
//        Log.d("asdaSDASD111",""+message2.get(0));
//        if (!arrayList_db_count.isEmpty()) {
//
//
//          //  List<Movie> movieList = new ArrayList<Movie>();
//
//
//
//            for (int i = 0; i < message2.size(); i++) {
//
//                  Log.d("dfasdasda111", "" + message2.get(i).getPhoneNumber());
//                  Log.d("dfasdasda2222", "" + message2.get(i).getName());
//                ///messageArray2[i] = "Title :- " + message2.get(i).getName() + "\n" + "Message :- " + message2.get(i).getPhoneNumber();
//            }
//        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.smsmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.action_deletesms) {



            DatabaseHandler db = new DatabaseHandler(ViewListActivity.this);

            ArrayList arrayList_db_count = new ArrayList<>();
            arrayList_db_count = db.MobileKeyword_All_SMS();
            Log.d("byjum...", "" + arrayList_db_count);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            if (!arrayList_db_count.isEmpty()) {


                for (int i = 0; i < arrayList_db_count.size(); i++) {

                    HashMap<String, String> map = (HashMap) arrayList_db_count.get(i);

                    // String dateString = formatter.format(new Date(map.get("PromoCodedate")));

                    Log.e("dffssf",""+map.get("PromoCodedate"));

                    long milisec= Long.parseLong(map.get("PromoCodedate"));

                    Date date = new Date(milisec);
                    //SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.US);
                    formatter.format(date);

                    Log.e("dffssf",""+formatter.format(date));


                    //  if (map.get("MobileStatus").equals("False"))
                    //  {
              //      db.allRecordInsert(map.get("MobileNumber"), "","",formatter.format(date),"False","SMS");

                    //  DatabaseHandler db = new DatabaseHandler(context);
                    // db.deleteDeletePhoneCall(map.get("MobileNumber"));

                    DbPassHandler dbpassword = new DbPassHandler(ViewListActivity.this);
                    dbpassword.updatetimesms(String.valueOf(System.currentTimeMillis()));


                    db.deleteallCAllSMS();
                    // }



                    //  db.allRecordInsert(map.get("MobileNumber"), "","");
                }

                onStart();
                Toast.makeText(this, "Remove Phone Number", Toast.LENGTH_SHORT).show();

                getdata();
            }
        }


        return super.onOptionsItemSelected(item);
    }

    private void getdata() {

        String[] messageArray2;
        String[] messageid;
        String[] status;
        String[] datee;
        ListView contactlist;

        contactlist=findViewById(R.id.contactlist);
        contactlist.removeAllViewsInLayout();

        DatabaseHandler db = new DatabaseHandler(ViewListActivity.this);

        ArrayList arrayList_db_count = new ArrayList<>();






        arrayList_db_count = db.MobileKeyword_All_SMS();
        Log.d("byjum...", ""+arrayList_db_count);


        Log.d("asdaSDASD111",""+arrayList_db_count.size());


        if (!arrayList_db_count.isEmpty()) {

            messageArray2 = new String[arrayList_db_count.size()];
            messageid = new String[arrayList_db_count.size()];
            status = new String[arrayList_db_count.size()];
            datee = new String[arrayList_db_count.size()];
            for (int i = 0; i < arrayList_db_count.size(); i++) {

                HashMap<String, String> map = (HashMap) arrayList_db_count.get(i);

                messageArray2[i] = map.get("MobileNumber");
                status[i] = map.get("status");
                datee[i] = map.get("PromoCodedate");
                messageid[i] = map.get("idnew");

            }



            contactdapter = new ViewContactAdapter(ViewListActivity.this, messageArray2, messageid,status,datee);
            // contactlist.notify();
            contactlist.setAdapter(contactdapter);



        }
        else
        {


            Toast.makeText(ViewListActivity.this," No Record Found. ",
                    Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onStart() {

        inst = this;
        // getdata();
        getCallDetails();
        Log.e("dszfdghj","lfdsrD");



        super.onStart();
    }


    @Override
    protected void onPostResume() {
        Log.e("dszfdghj","333");
        super.onPostResume();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle","onResume invoked");
    }
    @Override
    protected void onPause() {

        super.onPause();
        Log.d("lifecycle","onPause invoked");
        getCallDetails();

    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle","onStop invoked");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle","onRestart invoked");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle","onDestroy invoked");
    }
    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");

        //stopService(new Intent(getBaseContext(), ServiceCommunicator.class));
        Intent setIntent = new Intent(this,MainNewActivity.class);

        startActivity(setIntent);
        finish();
    }


    public String getCallDetails() {

        Log.e("fdgsdfsdfsd","dfgedf");
        String time = null;

        DbPassHandler dbpassword = new DbPassHandler(ViewListActivity.this);
        arrayList_db_count2 = new ArrayList<>();
        arrayList_db_count2 = dbpassword.showAll_db();
        //uuuuser=(TextView)findViewById(R.id.uuuuser);

        Log.d("c", "" + arrayList_db_count2);
        if (!arrayList_db_count2.isEmpty()) {

            HashMap<String, String> map = (HashMap) arrayList_db_count2.get(0);


            Log.d("asdfasdasdasdas", "" + map);

            if (map.get("status").equals("HELLO ACT TAILOR")) {
                time=map.get("timeSms");

            }
        }




        DatabaseHandler db = new DatabaseHandler(ViewListActivity.this);

        ArrayList arrayList_db_count = new ArrayList<>();
        arrayList_db_count = db.showAll_db();
        Log.d("byjum", ""+arrayList_db_count);

        List<Contact2> message2 = db.getAllContacts();

        Log.d("asdaSDASD111",""+message2.size());
        Log.d("asdaSDASD111",""+message2.get(0));
        if (!arrayList_db_count.isEmpty()) {

            for (int i = 0; i < message2.size(); i++) {

                Log.d("dfasdasda111", "" + message2.get(i).getPhoneNumber());
                Log.d("dfasdasda2222", "" + message2.get(i).getName());
                ///messageArray2[i] = "Title :- " + message2.get(i).getName() + "\n" + "Message :- " + message2.get(i).getPhoneNumber();
            }
        }




        android.util.Log.i("Time Class ", " Time value in millisecinds "+time);
        List<Sms> lstSms = new ArrayList<Sms>();
        Sms objSms = new Sms();
        Uri message = Uri.parse("content://sms/inbox");
        ContentResolver cr = this.getContentResolver();


        Log.e("gdfdegrf","...."+time);
        Log.e("gdfdegrf","gfdsa"+message);


        String keyyyyy="ubk";



        Uri smsUri = Uri.parse("content://sms/inbox");
        Cursor cursor2 = getContentResolver().query(smsUri, null, Telephony.Sms.DATE + ">?", new String[] { String.valueOf(time) }, null);
        try {
        while (cursor2.moveToNext()) {
            String body2 = cursor2.getString(cursor2.getColumnIndex("body"));
            String address = cursor2.getString(cursor2.getColumnIndex(Telephony.Sms.ADDRESS));
            String  type2 = cursor2.getString(cursor2.getColumnIndex(Telephony.Sms.TYPE));
            String  date2 = cursor2.getString(cursor2.getColumnIndex(Telephony.Sms.DATE));

            Log.e("aaaaaaaaaa"+address,"aa"+body2+"..."+type2);
           /// DatabaseHandler db = new DatabaseHandler(ViewListActivity.this);


            for (int i = 0; i < message2.size(); i++) {

               // Log.d("dfasdasda111", "" + message2.get(i).getPhoneNumber());
                Log.d("dfasdasda2222", "" + message2.get(i).getName());
                ///messageArray2[i] = "Title :- " + message2.get(i).getName() + "\n" + "Message :- " + message2.get(i).getPhoneNumber();


                     if (message2.get(i).getName().toUpperCase().trim().equals(body2.toUpperCase().trim()))
                    {
                        Log.e("efwas222","sdsdw");
                        if (db.checkPhoneSms(address))
                        {
                            Log.e("efwas333","sdsdw");
                        }
                        else
                        {

                            Log.e("efwas333","sdsdw");
                            db.allKeywordInsert(address,date2,"True",message2.get(i).getName());

                        }
                    }
                    else
                    {
                        Log.e("efwas444","sdsdw");
                    }

            }

            //lstSms.add(body2);
        }




            } catch (CursorIndexOutOfBoundsException e) {
                Log.e("ssss", "SMSHelper: outgoingSMS", e);
            } finally {
            cursor2.close();
            }









        getdata();

        return lstSms.toString();

    }
}
