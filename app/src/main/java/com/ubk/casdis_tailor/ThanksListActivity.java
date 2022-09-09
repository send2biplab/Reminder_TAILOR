package com.ubk.casdis_tailor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.ubk.casdis_tailor.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ThanksListActivity extends AppCompatActivity {




    public static CustomListAdapter adapter;

//    public static final String MyPREFERENCES = "MyPrefs" ;
//    public static final String Name = "nameKey";
//    SharedPreferences sharedpreferences;

    //String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks_list);


        //getdata();





    }

    private void getdata() {


//        sharedpreferences = getSharedPreferences(MyPREFERENCES,
//                Context.MODE_PRIVATE);
//
//        if (sharedpreferences.contains(Name)) {
//           // name.setText();
//
//            Log.e("rerfwedwsaefvs222222",""+sharedpreferences.getString(Name, ""));
//        }
//        else {
//            Log.e("rerfwedwsaefvs111111",""+sharedpreferences.getString(Name, ""));
//        }

        String[] messageArray2;
        String[] messageid;
        ListView contactlist;

        contactlist=findViewById(R.id.contactlist);
        contactlist.removeAllViewsInLayout();

        DatabaseHandler db = new DatabaseHandler(ThanksListActivity.this);

        ArrayList arrayList_db_count = new ArrayList<>();
        arrayList_db_count = db.MobileNoShowAll_db();
        Log.d("byjum.22222222..", ""+arrayList_db_count);


        Log.d("asdaSDASD111",""+arrayList_db_count.size());


        if (!arrayList_db_count.isEmpty()) {

            messageArray2 = new String[arrayList_db_count.size()];
            messageid = new String[arrayList_db_count.size()];
            for (int i = 0; i < arrayList_db_count.size(); i++) {

                HashMap<String, String> map = (HashMap) arrayList_db_count.get(i);

               // Log.d("asdaSDASD111","...."+arrayList_db_count.get(i));
               // Log.d("asdaSDASD111","...."+map);
               // Log.d("asdaSDASD111","...."+map.get("PromoCodeList"));

                //  Log.d("dfasdasda111", "" + message2.get(i).getPhoneNumber());
                //  Log.d("dfasdasda2222", "" + message2.get(i).getName());

                messageArray2[i] = map.get("MobileNumber");
                messageid[i] = map.get("idnew");

            }

           // ContactAdapter contactdapter = new ContactAdapter(ThanksListActivity.this, messageArray2, messageid);
           // contactlist.notify();
          ///  contactlist.setAdapter(contactdapter);

        }
        else
        {


            Toast.makeText(ThanksListActivity.this," No Record Found. ",
                    Toast.LENGTH_SHORT).show();
        }




    }

    @Override
    protected void onStart() {
        getdata();
        Log.e("dszfdghj","lfdsrD");
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
