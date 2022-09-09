package com.ubk.casdis_tailor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;


import com.ubk.casdis_tailor.ui.home.HomeFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CallListActivity extends AppCompatActivity {

    ArrayList arrayList_db_count2;
    CallContactAdapter contactdapter1;
    CallContactAdapterManual contactdapterbatch;

    String[] messageArray2;
    String[] messageid;
    String[] callstatus;
    String[] number;

    public static final String MyCOUNTRY = "Mycountry" ;
    public static final String Code = "codeKey";
    public static final String prefix = "prefix";
    SharedPreferences sharedpreferences;

    String typee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_list);

        Log.e("fdssdfsdfsdf..1","Misscalll acti");

        sharedpreferences = getSharedPreferences(MyCOUNTRY, Context.MODE_PRIVATE);

        Bundle extras = getIntent().getExtras();


        if (extras != null) {
            typee = extras.getString("type");
            // and get whatever type user account id is
        }

        DatabaseHandler db = new DatabaseHandler(CallListActivity.this);
        HomeFragment.sendddd="22";

    }




    private void listingStatus()
    {
        SharedPreferences sh = getSharedPreferences("BatchStatus", MODE_PRIVATE);

        int s1 = sh.getInt("batch", 0);
        Log.e("=======333===","1"+s1);

        getdata(typee);
//
//        if (sh.getBoolean("status",false)) {
//        Log.e("----22-----","1");
//            getdatabatch(s1);
//        } else {
//            getdata();
//            Log.e("----22-----","2");
//        }
    }



    private void getdatabatch(int batch)
    {




        ListView contactlist;

        contactlist=findViewById(R.id.contactlist);
        contactlist.removeAllViewsInLayout();




        int Count =0;
        int incriment=1;
        int divider=batch;
        int cccc;
        int loop_count=0;
        int x,ubk=1;
        int[] split_number;
        boolean statussss=false;


        Log.e("s--33323----",sharedpreferences.getString(Code, ""));

        DatabaseHandler db = new DatabaseHandler(CallListActivity.this);

        ArrayList arrayList_db_count = new ArrayList<>();
        arrayList_db_count = db.All_Record_Show_db();
        Log.d("byjum...", ""+arrayList_db_count);

        messageArray2 = new String[arrayList_db_count.size()];
        messageid = new String[arrayList_db_count.size()];
        callstatus = new String[arrayList_db_count.size()];

        number = new String[arrayList_db_count.size()];


        Count=arrayList_db_count.size();
        if (Count>divider) {
            x = Count / divider;
            Log.e("asassasasasasa1","11......"+x);
            split_number = new int[x+2];



            Map<String, Map<String, String>> map2 = new HashMap<>(x+2);

            Log.e("asassasasasasa2","11......"+split_number);
            for (int i = 1; i <= x+1; i++) {
                /// printf("%d ",i*10);

                incriment = i * divider;

                if (incriment < Count) {


                    // numberner = new String[incriment+2];


                    Map<String, String> map1 = new HashMap<>();


                    // Log.e("sdfsdfsdfsdfsd",""+incriment);

                    for (ubk=ubk; ubk <= incriment; ubk++) {

                        HashMap<String, String> map = (HashMap) arrayList_db_count.get(ubk-1);

                        map1.put(map.get("idrecord")+"_True", sharedpreferences.getString(Code, "")+""+map.get("RecordMobileNumber"));


                    }
                    loop_count++;

                    map2.put(""+loop_count, map1);



                } else {

                    int pppp = incriment - divider;
                    split_number[i] = Count;

                    Map<String, String> map1 = new HashMap<>();


                    for (int k = 60  ; k <= 61; k++) {
                        Log.e("kkkkkkkkkk", "2..." + k);
                    }


                    for (int j = pppp +1  ; j <= Count; j++) {

                        Log.e("bpppppppp", "2..." + j);

                        HashMap<String, String> map = (HashMap) arrayList_db_count.get(j-1);

                        //  numberner[pppp] = map.get("MobileNumber");
                        //numberner[pppp] = String.valueOf(i);
                        Log.e("bpppppppp", "2..." + j);

                        map1.put(map.get("idrecord")+"_True", sharedpreferences.getString(Code, "")+""+map.get("RecordMobileNumber"));

                    }
                    loop_count++;

                    map2.put(""+loop_count, map1);
                }

            }

            Log.e("sasasasasasasas",""+map2);



            contactdapterbatch = new CallContactAdapterManual(CallListActivity.this, map2);
            // contactlist.notify();
            contactlist.setAdapter(contactdapterbatch);


        }
        else
        {
//
        }


    }

    private void getdata(String typee) {


            String[] messageArray2;
            String[] messageid;
            String[] guestname;
            ListView contactlist;

            contactlist=findViewById(R.id.contactlist);
            contactlist.removeAllViewsInLayout();

            DatabaseHandler db = new DatabaseHandler(CallListActivity.this);

        String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
      // String typee="ORDER_DATE";
        Log.e("======21222=====",date);
            ArrayList arrayList_db_count = new ArrayList<>();
            arrayList_db_count = db.All_Record_Show_db(date,typee);
            Log.d("byjum...", ""+arrayList_db_count);


            Log.d("asdaSDASD111",""+arrayList_db_count.size());


            if (!arrayList_db_count.isEmpty()) {

                messageArray2 = new String[arrayList_db_count.size()];
                messageid = new String[arrayList_db_count.size()];
                guestname = new String[arrayList_db_count.size()];
                for (int i = 0; i < arrayList_db_count.size(); i++) {

                    HashMap<String, String> map = (HashMap) arrayList_db_count.get(i);


                        messageArray2[i] = map.get("RecordMobileNumber");
                        messageid[i] = map.get("idrecord");
                        guestname[i] = map.get("RecordnName");


                }

                 contactdapter1 = new CallContactAdapter(CallListActivity.this, messageArray2, messageid,guestname,typee);
                // contactlist.notify();
                contactlist.setAdapter(contactdapter1);



            }
            else
            {


                Toast.makeText(CallListActivity.this," No Record Found. ",
                        Toast.LENGTH_SHORT).show();
            }

    }
//
//
    @Override
    protected void onStart() {
        ///getdata();
       // getCallDetails();
        Log.e("dszfdghj","lfdsrD");

        //getdatabatch();
        listingStatus();
        super.onStart();
    }


    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");

        Intent setIntent = new Intent(this,MainNewActivity.class);
        startActivity(setIntent);
        finish();
    }

}
